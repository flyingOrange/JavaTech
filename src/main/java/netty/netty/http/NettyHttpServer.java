package netty.netty.http;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

public class NettyHttpServer {

	public static void main(String[] args) throws InterruptedException {
		EventLoopGroup bossGroup = new NioEventLoopGroup(1);
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			// 创建服务端启动对象，配置参数
			ServerBootstrap bootstrap = new ServerBootstrap();
			bootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)// 使用NioServerSocketChannel作为通道
					.option(ChannelOption.SO_BACKLOG, 128)//
					.childOption(ChannelOption.SO_KEEPALIVE, true)// 设置保持活动连接状态
					.childHandler(new ChannelInitializer<SocketChannel>(){

						@Override
						protected void initChannel(SocketChannel ch) throws Exception {
							//使用netty提供的http解码器
							ChannelPipeline channelPipeline = ch.pipeline();
							channelPipeline.addLast("httpServerCodec",new HttpServerCodec());
							channelPipeline.addLast("myself",new NettyHttpServerHandler());

							System.out.println("加载handler完毕");
						}
					});
			System.out.println("服务器启动");
			// 绑定端口,同步等待成功
			ChannelFuture f = bootstrap.bind(10000).sync();
			// 对关闭通道进行监听
			f.channel().closeFuture().sync();
		} finally {
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}

}
