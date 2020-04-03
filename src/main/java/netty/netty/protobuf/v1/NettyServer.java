package netty.netty.protobuf.v1;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;

public class NettyServer {

	public static void main(String[] args) throws Exception {
		// 创建两个线程组,boss只处理连接请求,worker处理业务
		// 两个都是无限循环
		//含有子线程数为 CPU核数*2
		EventLoopGroup bossGroup = new NioEventLoopGroup(1);
		EventLoopGroup workerGroup = new NioEventLoopGroup();

		try {
			// 创建服务端启动对象，配置参数
			ServerBootstrap bootstrap = new ServerBootstrap();
			bootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)// 使用NioServerSocketChannel作为通道
					.option(ChannelOption.SO_BACKLOG, 128)//
					.childOption(ChannelOption.SO_KEEPALIVE, true)// 设置保持活动连接状态
					//.handler(handler) handler对应bossGroup  childHandler对应workerGroup
					.childHandler(new ChannelInitializer<SocketChannel>() {// 创建一个通道测试对象

						// 给pipeline设置处理器
						@Override
						protected void initChannel(SocketChannel ch) throws Exception {
							ChannelPipeline pipeline = ch.pipeline();
							//加入protobuf解码器
							
							pipeline.addLast(new ProtobufDecoder(StudentPOJO.Student.getDefaultInstance()));
							pipeline.addLast(new NettyServerHandler());
							
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
