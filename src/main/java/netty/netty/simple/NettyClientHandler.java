package netty.netty.simple;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

public class NettyClientHandler extends ChannelInboundHandlerAdapter{
	
	//通道就绪就会触发
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		ctx.writeAndFlush(Unpooled.copiedBuffer("hello,server端", CharsetUtil.UTF_8));
	}
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) {
		System.out.println("ChannelHandlerContext = " + ctx);
		// 将msg转化成ByteBuf
		ByteBuf byteBuf = (ByteBuf) msg;
		System.out.println("服务器回复的信息是:" + byteBuf.toString(CharsetUtil.UTF_8));
		System.out.println("服务器地址是:" + ctx.channel().remoteAddress());

	}


	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		cause.printStackTrace();
		ctx.close();
	}


}
