package netty.netty.http;

import java.net.URI;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpObject;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.GlobalEventExecutor;

/*
 * SimpleChannelInboundHandler是ChannelInboundHandlerAdapter子类
 * HttpObject 服务器端和客户端通信时数据被封装成HttpObject
 * 
 * */
public class NettyHttpServerHandler extends SimpleChannelInboundHandler<HttpObject> {
	
	//定义一个channel组管理所有channel，在连接后把channel添加到  channelGroup
	//GlobalEventExecutor.INSTANCE是全局事件执行器，是单例
	//private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {

		// 判断msg 是不是HttpRequest请求
		if (msg instanceof HttpRequest) {
			System.out.println("msg类型是: " + msg.getClass());
			System.out.println("客户端地址是:" + ctx.channel().remoteAddress());
			
			HttpRequest request = (HttpRequest)msg;
			URI uri = new URI(request.uri());
			
			if("/favicon.ico".equals(uri.getPath())) {
				System.out.println("请求了favicon.ico,不做响应");
				return;
			}
			
			// 回复信息给浏览器
			ByteBuf content = Unpooled.copiedBuffer("hello,浏览器", CharsetUtil.UTF_8);
			// 构造一个http响应
			FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK,
					content);
			response.headers().set(HttpHeaderNames.CONTENT_TYPE,"text/plain");
			response.headers().set(HttpHeaderNames.CONTENT_LENGTH,content.readableBytes());
		
			ctx.writeAndFlush(response);
		}
	}

}
