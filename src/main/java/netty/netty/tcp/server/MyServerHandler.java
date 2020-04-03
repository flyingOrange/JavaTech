package netty.netty.tcp.server;

import java.nio.charset.Charset;
import java.util.UUID;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import netty.netty.tcp.protocol.MessageProtocol;

/*
 * */
public class MyServerHandler extends SimpleChannelInboundHandler<MessageProtocol> {
	
	private int count;

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, MessageProtocol msg) throws Exception {
		int len = msg.getLen();
		byte[] content = msg.getContent();
		System.out.println("服务器收到如下信息:");
		System.out.println("长度="+len);
		System.out.println("内容="+new String(content,Charset.forName("UTF-8")));
		System.out.println("接收到数据包数量:"+(++this.count));
		//回复消息
		String responseContent = UUID.randomUUID().toString();
		byte[] responseContents = responseContent.getBytes("UTF-8");
		int responseLength = responseContent.getBytes("UTF-8").length;
		MessageProtocol message = new MessageProtocol();
		message.setContent(responseContents);
		message.setLen(responseLength);
		ctx.writeAndFlush(message);
		
	}

}
