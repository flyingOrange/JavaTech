package netty.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class MyNettyClient {
	public void connect(String host,int port) throws InterruptedException
	{
		EventLoopGroup group = new NioEventLoopGroup();
		try{
			Bootstrap b = new Bootstrap();
			b.group(group).channel(NioSocketChannel.class)
			//.option(ChannelOption.TCP_NODELAY,true)
			.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5 * 1000)
			.handler(new ChannelInitializer<SocketChannel>(){
				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					ch.pipeline().addLast(new MyNettyClientHandler());	
					
//					ch.pipeline().addLast(new LineBasedFrameDecoder(1024));
//					ch.pipeline().addLast(new StringDecoder());
//					ch.pipeline().addLast(new StringEncoder());
//					ch.pipeline().addLast(new MessageDefHandler());	

				}
			});
			// 发起异步连接操作
            ChannelFuture f = b.connect(host, port).sync();
            // 等待客户端链路关闭
            f.channel().closeFuture().sync(); 
		}finally{
			group.shutdownGracefully();
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		new MyNettyClient().connect("127.0.0.1", 9999);
	}
}

class MyNettyClientHandler extends ChannelInboundHandlerAdapter
{
	public MyNettyClientHandler()
	{
		System.out.println("MyNettyClientHandler");
	}
	@Override
	public void channelRead(ChannelHandlerContext ctx,Object msg)
	{
		ByteBuf buff = (ByteBuf)msg;
		System.out.println("channelRead    "+buff.readableBytes());
		
	}
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("channelActive");
	}
	
	@Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        //System.out.println("server channelReadComplete..");
        ctx.flush();//刷新后才将数据发出到SocketChannel
    }
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
	{
		System.out.println("exceptionCaught");
		//cause.printStackTrace();
		ctx.close();
	}

}
