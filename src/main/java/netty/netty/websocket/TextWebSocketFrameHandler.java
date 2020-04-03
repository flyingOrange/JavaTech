package netty.netty.websocket;

import java.util.Date;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

public class TextWebSocketFrameHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {

		System.out.println("服务器收到:" + msg.text());
		// 回复消息
		ctx.channel().writeAndFlush(new TextWebSocketFrame("服务器时间:" + new Date() + "  " + msg.text()));
	}
	
	//web客户端连接后触发
	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		//id表示唯一的值，asLongText是唯一的，asShortText不是唯一的
		System.out.println("handlerAdded被调用"+ctx.channel().id().asLongText());
		System.out.println("handlerAdded被调用"+ctx.channel().id().asShortText());
		
	}

	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
		System.out.println("handlerRemoved被调用"+ctx.channel().id().asShortText());
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		System.out.println("发生异常:"+cause.getMessage());
		ctx.channel().close();
	}
}
