package netty.netty.http;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

public class ServerInitializer extends ChannelInitializer<SocketChannel>{

	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		//使用netty提供的http解码器
		ChannelPipeline channelPipeline = ch.pipeline();
		channelPipeline.addLast("httpServerCodec",new HttpServerCodec());
		channelPipeline.addLast("myself",new NettyHttpServerHandler());
		
		System.out.println("加载handler完毕");	
	}

}
