package netty.netty.simple;

import java.util.concurrent.TimeUnit;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelPipeline;
import io.netty.util.CharsetUtil;

public class NettyServerHandler extends ChannelInboundHandlerAdapter {

	/*
	 * ChannelHandlerContext cxt:上下文对象，含有pipeline、channel、地址等 Object msg:客户端发送的数据
	 * 
	 */
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) {
		
		System.out.println("服务器读取线程:"+Thread.currentThread().getName());
		System.out.println("ChannelHandlerContext = " + ctx);
		//pipeline和channel互相持有对方的引用,ctx持有pipeline和channel
		Channel channel = ctx.channel();
		ChannelPipeline pipeline = ctx.pipeline();//pipeline是一个双向链表，
		
		// 将msg转化成ByteBuf
		ByteBuf byteBuf = (ByteBuf) msg;
		System.out.println("客户端发送的信息是:" + byteBuf.toString(CharsetUtil.UTF_8));
		System.out.println("客户端地址是:" + ctx.channel().remoteAddress());
		
		/*
		 * 如果一项业务非常耗时--->异步执行--->提交该channel对应的NioEventLoop的taskQueue
		 * 
		 * */
		//1、用户自定义的普通任务
		ctx.channel().eventLoop().execute(new Runnable() {

			@Override
			public void run() {
				try {
					Thread.sleep(10*1000);
					System.out.println("task执行线程:"+Thread.currentThread().getName());
					ctx.writeAndFlush(Unpooled.copiedBuffer("hello,got message", CharsetUtil.UTF_8));
				} catch (Exception e) {
					System.out.println("任务发生异常");
				}
				
			}
		});
		System.out.println("读取完毕,不会被阻塞在上面,但是有多个任务要依次执行，时间也要叠加");
		
		//2、用户自定义定时任务，该任务提交到scheduleTaskQueue
		ctx.channel().eventLoop().schedule(new Runnable() {

			@Override
			public void run() {
				try {
					Thread.sleep(5*1000);
					System.out.println("scheduleTask执行线程:"+Thread.currentThread().getName());
					ctx.writeAndFlush(Unpooled.copiedBuffer("hello,got message", CharsetUtil.UTF_8));
				} catch (Exception e) {
					System.out.println("任务发生异常");
				}
				
			}
		}, 5, TimeUnit.SECONDS);
		
		
//		try {
//			Thread.sleep(5*1000);
//			System.out.println("读取完毕");
//			ctx.writeAndFlush(Unpooled.copiedBuffer("hello,got message", CharsetUtil.UTF_8));
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		
		
		

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
