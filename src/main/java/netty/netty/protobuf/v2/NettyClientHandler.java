package netty.netty.protobuf.v2;

import java.util.Random;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import netty.netty.protobuf.v1.StudentPOJO;

public class NettyClientHandler extends ChannelInboundHandlerAdapter {

	// 通道就绪就会触发
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		// 随机发送worker和student
		int random = new Random().nextInt(3);
		MyDataInfo.MyMessage myMessage = null;
		if (0 == random) {// 发送student
			myMessage = MyDataInfo.MyMessage.newBuilder().setDataType(MyDataInfo.MyMessage.DataType.StudentType)
					.setStudent(MyDataInfo.Student.newBuilder().setId(88).setName("orange").build()).build();

		}else {//发送worker
			myMessage = MyDataInfo.MyMessage.newBuilder().setDataType(MyDataInfo.MyMessage.DataType.WorkerType)
					.setWorker(MyDataInfo.Worker.newBuilder().setAge(13).setName("fuck").build()).build();
		}
		ctx.writeAndFlush(myMessage);
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) {
		System.out.println("ChannelHandlerContext = " + ctx);
		// 将msg转化成ByteBuf
		ByteBuf byteBuf = (ByteBuf) msg;
		System.out.println("服务器回复的信息是:" + byteBuf.toString(CharsetUtil.UTF_8));
		System.out.println("服务器地址是:" + ctx.channel().remoteAddress());

	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		cause.printStackTrace();
		ctx.close();
	}

}
