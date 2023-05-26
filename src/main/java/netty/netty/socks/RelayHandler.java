package netty.netty.socks;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.ReferenceCountUtil;

public class RelayHandler extends ChannelInboundHandlerAdapter {
    String dstAddr;
    int dstPort;

    public RelayHandler(String addr, int port) {
        this.dstAddr = addr;
        this.dstPort = port;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg){
        doRelay(dstAddr,dstPort,ctx,msg);
    }


    //连接dst.addr:dst.port,把数据转发回socks client
    private void doRelay(String addr,int port,final ChannelHandlerContext socks5ClientCtx,Object socksClientMsg) {
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap(); // (1)
            b.group(workerGroup); // (2)
            b.channel(NioSocketChannel.class); // (3)
            b.option(ChannelOption.SO_KEEPALIVE, true); // (4)
            b.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch){
                    ch.pipeline().addLast(new ChannelInboundHandlerAdapter(){
                        @Override
                        public void channelRead(ChannelHandlerContext ctx, Object msg) {
                            System.out.println("把目标服务器的数据转发回socks5客户端............");
                            ChannelFuture f = socks5ClientCtx.writeAndFlush(msg);
                            f.addListener(new ChannelFutureListener(){
                                @Override
                                public void operationComplete(ChannelFuture channelFuture){
                                    System.out.println("转发成功: " + msg.getClass().getName());
                                }
                            });
                        }
                        @Override
                        public void channelActive(ChannelHandlerContext ctx)  {
                            System.out.println("转发来自sock5客户端的数据............");
                            final ChannelFuture f = ctx.writeAndFlush(socksClientMsg); // (3)
                            f.addListener(new ChannelFutureListener() {
                                @Override
                                public void operationComplete(ChannelFuture future) {
                                    System.out.println("转发客户端数据............成功");
                                }
                            });
                        }
                        @Override
                        public void channelReadComplete(ChannelHandlerContext ctx) {
                            /*从目标服务器读取数据完毕后，应当立即断开连接。
                            如果不断开连接，*/
                            System.out.println("从目标服务器读取数据完毕后断开连接,也断开和socks5客户端的连接");
                            ReferenceCountUtil.release(socksClientMsg);
                            ctx.close();
                            socks5ClientCtx.close();
                        }
                        @Override
                        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
                            System.out.println("访问目标服务器失败");
                            ctx.close();
                            socks5ClientCtx.close();
                        }

                    });
                }
            });

            ChannelFuture f = b.connect(addr, port).sync();
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            System.out.println("relay  .........  exceptionCaught");
            e.printStackTrace();
        } finally {
            workerGroup.shutdownGracefully();
        }
    }


}
