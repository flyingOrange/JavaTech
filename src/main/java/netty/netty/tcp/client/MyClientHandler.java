package netty.netty.tcp.client;

import java.nio.charset.Charset;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import netty.netty.tcp.protocol.MessageProtocol;

/*
 * */
public class MyClientHandler extends SimpleChannelInboundHandler<MessageProtocol> {
	private int count;
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {

		for(int i = 0;i<10;i++) {
			String mes = "GOGOGO测试协议";
			byte[] content = mes.getBytes(Charset.forName("UTF-8"));
			int len = mes.getBytes(Charset.forName("UTF-8")).length;
			
			MessageProtocol message = new MessageProtocol();
			message.setLen(len);
			message.setContent(content);
			ctx.writeAndFlush(message);
			
		}
		
	}
	
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, MessageProtocol msg) throws Exception {
		int len = msg.getLen();
		byte[] content = msg.getContent();
		System.out.println("客户端收到如下信息:");
		System.out.println("长度="+len);
		System.out.println("内容="+new String(content,Charset.forName("UTF-8")));
		System.out.println("接收到数据包数量:"+(++this.count));
		
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		System.out.println("异常信息:"+cause.getMessage());
		ctx.close();
	}
	

}
