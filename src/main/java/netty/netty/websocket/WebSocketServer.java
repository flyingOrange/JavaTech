package netty.netty.websocket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

public class WebSocketServer {

	public static void main(String[] args) throws InterruptedException {
		EventLoopGroup bossGroup = new NioEventLoopGroup(1);
		EventLoopGroup workerGroup = new NioEventLoopGroup();

		try {
			// 创建服务端启动对象，配置参数
			ServerBootstrap bootstrap = new ServerBootstrap();
			bootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
					.option(ChannelOption.SO_BACKLOG, 128)//
					.childOption(ChannelOption.SO_KEEPALIVE, true)
					.childHandler(new ChannelInitializer<SocketChannel>() {
						@Override
						protected void initChannel(SocketChannel ch) throws Exception {
							ChannelPipeline pipeline = ch.pipeline();
							//基于http协议，所以需要http解码器
							pipeline.addLast(new HttpServerCodec());
							//以块方式写，添加ChunkedWriteHandler处理器
							pipeline.addLast(new ChunkedWriteHandler());

							//htto协议在传输过程中是分段的，HttpObjectAggregator可以把多段聚合
							//http发送数据量过大，就会分多次发送
							pipeline.addLast(new HttpObjectAggregator(8192));
							
							//websocket它的数据以  帧frame 形式传播
							//WebSocketFrame下面有6个子类
							//ws://localhost:7000/xxx    是websocket的uri
							//WebSocketServerProtocolHandler核心功能是把http协议升级为ws协议,保持长链接
							pipeline.addLast(new WebSocketServerProtocolHandler("/hello"));
							pipeline.addLast(new TextWebSocketFrameHandler());
							
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
