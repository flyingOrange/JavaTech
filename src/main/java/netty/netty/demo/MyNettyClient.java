package netty.netty.demo;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyNettyClient {
	private final Logger logger = LoggerFactory.getLogger(MyNettyClient.class);

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
//					ch.pipeline().addLast(new MyNettyClientHandler());
					
//					ch.pipeline().addLast(new LineBasedFrameDecoder(1024));
//					ch.pipeline().addLast(new StringDecoder());
					ch.pipeline().addLast(new LoggingHandler(LogLevel.DEBUG));
					ch.pipeline().addLast(new StringEncoder());
//					ch.pipeline().addLast(new MessageDefHandler());

				}
			});
			// 发起异步连接操作
			ChannelFuture channelFuture = b.connect(host, port);
			channelFuture.sync();
            // 等待客户端链路关闭
			Channel channel = channelFuture.channel();
			logger.debug("{}",channel);
			channel.writeAndFlush("hello");

			ChannelFuture cf = channel.closeFuture();
			//cf.sync();
			//logger.debug("关闭后处理操作");
			cf.addListener(new ChannelFutureListener() {
				@Override
				public void operationComplete(ChannelFuture future) throws Exception {
					logger.debug("关闭后处理操作");
				}
			});

/**			channelFuture.addListener(new ChannelFutureListener() {
				@Override
				public void operationComplete(ChannelFuture future) throws Exception {
					Channel channel = channelFuture.channel();
					logger.debug("{}",channel);
					channel.writeAndFlush("hello");
				}
			});
 **/

		}finally{
			group.shutdownGracefully();
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		new MyNettyClient().connect("127.0.0.1", 9999);
	}
}

class MyNettyClientHandler extends ChannelInboundHandlerAdapter {
	public MyNettyClientHandler()
	{
		System.out.println("MyNettyClientHandler");
	}
	@Override
	public void channelRead(ChannelHandlerContext ctx,Object msg) {
		ByteBuf buff = (ByteBuf)msg;
		System.out.println("channelRead    "+buff.readableBytes());
		
	}
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		ctx.writeAndFlush("abc");
		System.out.println("channelActive");
	}
	
	@Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        //System.out.println("server channelReadComplete..");
        ctx.flush();//刷新后才将数据发出到SocketChannel
    }
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		System.out.println("exceptionCaught");
		//cause.printStackTrace();
		ctx.close();
	}

}
