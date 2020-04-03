package netty.netty.simple;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;

public class HeartBeatHandler extends ChannelInboundHandlerAdapter{

	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
		if(evt instanceof IdleStateEvent) {
			IdleStateEvent event = (IdleStateEvent)evt;
			String status = null;
			switch(event.state()) {
			 	case READER_IDLE:
			 		status = "读空闲";
			 		break;
			 	case WRITER_IDLE:
			 		status = "写空闲";
			 		break;
			 	case ALL_IDLE:
			 		status = "读写空闲";
			 		break;
			}
			System.out.println(ctx.channel().remoteAddress()+"--超时--"+status);
		}
	}
}
