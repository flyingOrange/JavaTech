package netty.netty.tcp.client;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import netty.netty.tcp.codec.MyMessageDecoder;
import netty.netty.tcp.codec.MyMessageEncoder;

public class MyClientInitializer extends ChannelInitializer<SocketChannel>{

	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		ChannelPipeline channelPipeline = ch.pipeline();
		//加入编码器
		channelPipeline.addLast(new MyMessageEncoder());
		channelPipeline.addLast(new MyMessageDecoder());
		channelPipeline.addLast(new MyClientHandler());
		
		
	}

}
