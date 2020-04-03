package netty.netty.protobuf.v1;

import java.util.concurrent.TimeUnit;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelPipeline;
import io.netty.util.CharsetUtil;

public class NettyServerHandler extends ChannelInboundHandlerAdapter {

	//定义一个channel组管理所有channel，在连接后把channel添加到  channelGroup
	//GlobalEventExecutor.INSTANCE是全局事件执行器，是单例
	//private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
	
	/*
	 * ChannelHandlerContext cxt:上下文对象，含有pipeline、channel、地址等 Object msg:客户端发送的数据
	 * 
	 */
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) {
		StudentPOJO.Student student = (StudentPOJO.Student)msg;
		System.out.println("客户端发送的数据:"+student.getId()+","+student.getName());
		
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {

		//ctx.writeAndFlush(Unpooled.copiedBuffer("hello,finished!", CharsetUtil.UTF_8));// 刷新后才将数据发出到SocketChannel
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		cause.printStackTrace();
		ctx.close();
	}

}
