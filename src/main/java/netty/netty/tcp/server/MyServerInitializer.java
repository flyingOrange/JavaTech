package netty.netty.tcp.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import netty.netty.tcp.codec.MyMessageDecoder;
import netty.netty.tcp.codec.MyMessageEncoder;

public class MyServerInitializer extends ChannelInitializer<SocketChannel>{

	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		//使用netty提供的http解码器
		ChannelPipeline channelPipeline = ch.pipeline();
		channelPipeline.addLast(new MyMessageEncoder());
		channelPipeline.addLast(new MyMessageDecoder());
		channelPipeline.addLast(new MyServerHandler());
	}

}
