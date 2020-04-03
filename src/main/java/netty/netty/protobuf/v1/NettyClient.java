package netty.netty.protobuf.v1;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufEncoder;

public class NettyClient {

	public static void main(String[] args) throws InterruptedException {
		EventLoopGroup group = new NioEventLoopGroup();
		try {

			Bootstrap bootstrap = new Bootstrap();
			bootstrap.group(group)
			.channel(NioSocketChannel.class)
			.handler(new ChannelInitializer<SocketChannel>() {

				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					ChannelPipeline pipeline = ch.pipeline();
					//加入protobuf编码器
					pipeline.addLast(new ProtobufEncoder());
					pipeline.addLast(new NettyClientHandler());
				}
				
			});
			// 发起异步连接操作
            ChannelFuture f = bootstrap.connect("127.0.0.1", 10000).sync();
            // 等待客户端链路关闭
            f.channel().closeFuture().sync(); 

		} finally {
			group.shutdownGracefully();
		}

	}

}
