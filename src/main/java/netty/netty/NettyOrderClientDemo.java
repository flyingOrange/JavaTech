package netty.netty;

/**
 * 客户端提交订单，采用java原生序列化
 */
import io.netty.bootstrap.Bootstrap;
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
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public class NettyOrderClientDemo {
	
	public void connect(String host,int port) throws InterruptedException
	{
		EventLoopGroup group = new NioEventLoopGroup();
		try{
			Bootstrap b = new Bootstrap();
			b.group(group).channel(NioSocketChannel.class)
			.option(ChannelOption.TCP_NODELAY,true)
			.handler(new ChannelInitializer<SocketChannel>(){
				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
			
					ch.pipeline()
					.addLast(new ObjectDecoder(
							1024*1024,ClassResolvers.weakCachingConcurrentResolver(this.getClass().getClassLoader())
							));
					ch.pipeline().addLast(new ObjectEncoder());
					ch.pipeline().addLast(new SubReqClientHandler());	
					
					ch.pipeline().addLast("MessageDefHandler",new MessageDefHandler());	
					
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
		new NettyOrderClientDemo().connect("127.0.0.1", 9999);
	}

}
class MessageDefHandler extends ChannelInboundHandlerAdapter 
{
	public MessageDefHandler()
	{
		System.out.println("MessageDefHandler");
	}
	@Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
	{
		System.out.println("MessageDefHandler:"+ msg);
//		String ss = "haha";
//		ctx.writeAndFlush(ss);
	}
}

class SubReqClientHandler extends ChannelInboundHandlerAdapter 
{	
	@Override
	public void channelActive(ChannelHandlerContext ctx)
	{
		System.out.println("连接成功");
		for(int i=0;i<100;i++)
		{
			ctx.write(subReq(i));
			//ctx.write("hhhhh");
		}
		ctx.flush();
	}

	@Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
	{
		System.out.println("收到服务器回复:"+ msg);
	}

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("server channelReadComplete..");
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        System.out.println("server exceptionCaught..");
        cause.printStackTrace();
        ctx.close();
    }
    
    private SubscribeReq subReq(int subReqID)
    {
    	SubscribeReq req = new SubscribeReq();
    	req.setSubReqID(subReqID);
    	req.setUserName("orange");
    	req.setAddress("SJZ");
    	req.setProductName("矿泉水");
    	req.setPhoneNumber("1111");
    	return req;
    }
}

//订单响应类
class SubscribeResponse implements java.io.Serializable
{
	private int subReqID;//订单ID
	private int responseCode;//返回码
	private String desc;//备注
	
	public int getSubReqID() {
		return subReqID;
	}
	public void setSubReqID(int subReqID) {
		this.subReqID = subReqID;
	}
	public int getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	@Override
	public String toString()
	{	
		return "SubscribeReq[subReqID="+subReqID+",responseCode="+responseCode+
				",desc="+desc+"]";
	}

	
}




