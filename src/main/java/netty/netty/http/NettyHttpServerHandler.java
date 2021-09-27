package netty.netty.http;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;

import java.net.URI;

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
			// 构造一个http响应
			byte[] bytes = "hello,浏览器".getBytes();
			FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK);
			response.headers().set(HttpHeaderNames.CONTENT_TYPE,"text/plain");
			response.headers().set(HttpHeaderNames.CONTENT_LENGTH,bytes.length);

			response.content().writeBytes(bytes);
		
			ctx.writeAndFlush(response);
		}else if(msg instanceof HttpContent){


		}
	}

}
