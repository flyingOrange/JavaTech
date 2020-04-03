package netty.netty.tcp.codec;

import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;
import netty.netty.tcp.protocol.MessageProtocol;

public class MyMessageDecoder extends ReplayingDecoder<Void>{

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
		System.out.println("MyMessageDecoder被调用");
		//需要把二进制字节码转化为MessageProtocol对象
		int length = in.readInt();
		byte[] content = new byte[length];
		in.readBytes(content);
		//封装成MessageProtocol对象，放入out传递给下一个handler处理
		MessageProtocol message = new MessageProtocol();
		message.setLen(length);
		message.setContent(content);
		out.add(message);
		
	}

}
