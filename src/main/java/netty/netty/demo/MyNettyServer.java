package netty.netty.demo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.Charset;


public class MyNettyServer {
	private final Logger logger = LoggerFactory.getLogger(MyNettyServer.class);

	public void connect(int port) throws InterruptedException
	{
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup(10);
		
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
					ch.pipeline().addLast(new LoggingHandler(LogLevel.DEBUG));
					ch.pipeline().addLast("handler111111",new MyNettyServerHandler());
					//ch.pipeline().addLast(new StringDecoder());

				}
			});
			logger.debug("server start  22...............");
			//System.out.println("服务器启动");
			//绑定端口,同步等待成功
			ChannelFuture channel = b.bind(port).sync();
			//等待服务器监听端口关闭
			channel.channel().closeFuture().sync();
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
	public void channelRead(ChannelHandlerContext ctx,Object msg) throws Exception {
		System.out.println("323232");
		ByteBuf buf = (ByteBuf)msg;
		String str = buf.toString(Charset.defaultCharset());
		System.out.println(str);
		super.channelRead(ctx,msg);
	}
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("channelActive");
	}
	
	@Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("server channelReadComplete..");
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

