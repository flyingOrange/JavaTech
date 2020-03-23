package netty.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * 服务器接受订单，采用java原生序列化订单类
 */
public class NettyOrderServerDemo {
	public void connect(int port) throws InterruptedException
	{
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		
		try{
			ServerBootstrap b = new ServerBootstrap();
			b.group(bossGroup,workerGroup)
			.channel(NioServerSocketChannel.class)
			.option(ChannelOption.SO_BACKLOG,100)
			.handler(new LoggingHandler(LogLevel.INFO))
			.childHandler(new ChannelInitializer<SocketChannel>() {
				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					
					ch.pipeline()
					.addLast(new ObjectDecoder(
						1024*1024,ClassResolvers.weakCachingConcurrentResolver(this.getClass().getClassLoader())
					));
					ch.pipeline().addLast(new ObjectEncoder());
					ch.pipeline().addLast(new SubReqServerHandler());	
					
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
		new NettyOrderServerDemo().connect(9999);
	}

}

class SubReqServerHandler extends ChannelInboundHandlerAdapter //ChannelHandlerAdapter
{
	@Override
	public void channelRead(ChannelHandlerContext ctx,Object msg)
	{
		SubscribeReq req = (SubscribeReq)msg;
		System.out.println("收到请求:    "+req.toString());
		if("orange".equalsIgnoreCase(req.getUserName()))
		{
			ctx.writeAndFlush(resp(req.getSubReqID()));	
			//ctx.writeAndFlush(new MessageDefHandler());	
		}
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
	{
		//cause.printStackTrace();
		ctx.close();
	}
	
	private SubscribeResponse resp(int subReqID)
	{
		SubscribeResponse resp = new SubscribeResponse();
		resp.setSubReqID(subReqID);
		resp.setResponseCode(0);
		resp.setDesc("你们好啊");
		return resp;
	}
	
}

//订单类 
class SubscribeReq implements java.io.Serializable
{
	private int subReqID;//订单ID
	private String userName;//用户名
	private String productName;//产品名
	private String phoneNumber;//电话号
	private String address;//地址
	
	@Override
	public String toString()
	{
		return "SubscribeReq[subReqID="+subReqID+",userName="+userName+
				",productName="+productName+",phoneNeme="+phoneNumber+",address="+address+"]";
	}
	
	public int getSubReqID() {
		return subReqID;
	}
	public void setSubReqID(int subReqID) {
		this.subReqID = subReqID;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

}
