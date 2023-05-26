package netty.netty.socks;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.socksx.v5.*;
import io.netty.util.ReferenceCountUtil;

/*

不支持代理https请求
* */
public class SocksProxyHandler extends ChannelInboundHandlerAdapter {


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg){

        ChannelPipeline pipeline = ctx.pipeline();
        //处理 SOCKS5 命令请求，这里可以实现代理转发逻辑
        if(msg instanceof DefaultSocks5InitialRequest){
            DefaultSocks5InitialRequest request = (DefaultSocks5InitialRequest)msg;
            //选择Socks5AuthMethod鉴权方法，本例采用不鉴权的NO_AUTH
            DefaultSocks5InitialResponse response = new DefaultSocks5InitialResponse(Socks5AuthMethod.NO_AUTH);
            ctx.writeAndFlush(response);
            /* DefaultChannelPipeline是双向链表，注意addBefore和addAfter到底谁前谁后的问题！*/
            pipeline.addBefore(ctx.name(),null,new Socks5CommandRequestDecoder());
            System.out.println("Socks5 初始化 握手");
            ReferenceCountUtil.release(msg);
        }
        if(msg instanceof Socks5CommandRequest){
            Socks5CommandRequest request = (Socks5CommandRequest)msg;
            //Socks5AddressType dstAddrType = request.dstAddrType();
            //Socks5CommandType type = request.type();
            String dstAddr = request.dstAddr();
            int dstPort = request.dstPort();
            /*注意response几个参数：bndAddrType | BND.ADDR | BND.PORT，而不是dstType|DST.ADDR|DST.PORT
            当我们的relay server和socks5 server是同一台服务器时，BND.ADDR的值全部为0即可,BND.PORT是监听端口。
            */
            Socks5CommandResponse response = new DefaultSocks5CommandResponse
                    (Socks5CommandStatus.SUCCESS,Socks5AddressType.IPv4,null,1080);
            ctx.writeAndFlush(response);
            System.out.println("Socks5请求阶段:  "+dstAddr+":"+dstPort);
            pipeline.remove(this);
            pipeline.addLast(ctx.name(),new RelayHandler(dstAddr,dstPort));
            ReferenceCountUtil.release(msg);
        }

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        System.out.println("socks5客户端连接中断");
        cause.printStackTrace();
        ctx.close();
    }

}
