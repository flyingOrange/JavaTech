package netty;
/*
 * 没有任何编码，直接接受所有二进制数据
 * */
import java.nio.ByteOrder;
import java.util.List;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;


public class MyNettyServer {
	public void connect(int port) throws InterruptedException
	{
		EventLoopGroup bossGroup = new NioEventLoopGroup(1);
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		
		try{
			ServerBootstrap b = new ServerBootstrap();
			b.group(bossGroup,workerGroup)
			.channel(NioServerSocketChannel.class)
			//.option(ChannelOption.SO_BACKLOG,100)
			//.handler(new LoggingHandler(LogLevel.INFO))
			.childHandler(new ChannelInitializer<SocketChannel>() {
				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					//ch.pipeline().addLast(new ByteToStringDecoder(1024*1024,0,2));
					System.out.println("client "+ch.remoteAddress()+" connect");
					ch.pipeline().addLast(new MyNettyServerHandler());	
					
				}
			});
			System.out.println("服务器启动");
			//绑定端口,同步等待成功
			ChannelFuture f = b.bind(port).sync();
			//等待服务器监听端口关闭
			f.channel().closeFuture().sync();	
		}finally
		{
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();	
		}	
	}

	public static void main(String[] args) throws InterruptedException {
		new MyNettyServer().connect(9999);
	}

}



class MyNettyServerHandler extends ChannelInboundHandlerAdapter
{
	public MyNettyServerHandler()
	{
		System.out.println("MyNettyServerHandler");
	}
	@Override
	public void channelRead(ChannelHandlerContext ctx,Object msg)
	{
		ByteBuf buff = (ByteBuf) msg;
		int n = buff.readableBytes();
		System.out.println("收到字节数:"+n);
		byte []data = new byte[n];
		
		short s = buff.readShort();
		System.out.println(s);
//		buff.readBytes(data);
//		for(int i=0;i<n;i++)
//		{
//			byte b = data[i];
//			System.out.println(Integer.toHexString(0xFF & b));
//		}
		
	}
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		//System.out.println("channelActive");
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

