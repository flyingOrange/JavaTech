package netty.netty.protobuf.v2;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class NettyServerHandler extends SimpleChannelInboundHandler<MyDataInfo.MyMessage> {

	//定义一个channel组管理所有channel，在连接后把channel添加到  channelGroup
	//GlobalEventExecutor.INSTANCE是全局事件执行器，是单例
	//private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
	
	/*
	 * ChannelHandlerContext cxt:上下文对象，含有pipeline、channel、地址等 Object msg:客户端发送的数据
	 * 
	 */
	@Override
	public void channelRead0(ChannelHandlerContext ctx, MyDataInfo.MyMessage msg) {
		
		MyDataInfo.MyMessage.DataType dataType = msg.getDataType();
		if(dataType == MyDataInfo.MyMessage.DataType.StudentType) {
			MyDataInfo.Student student = msg.getStudent();
			System.out.println("id="+student.getId()+",name="+student.getName());
		}else if(dataType == MyDataInfo.MyMessage.DataType.WorkerType){
			MyDataInfo.Worker worker = msg.getWorker();
			System.out.println("age="+worker.getAge()+",name="+worker.getName());
			
		}else {
			System.out.println("传输类型不正确");
		}
		
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
