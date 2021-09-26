package netty.netty;

import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.FixedLengthFrameDecoder;

public class EmbeddedChannelDemo {
    public static void main(String[] args) {
       // MyNettyServerHandler
        EmbeddedChannel channel = new EmbeddedChannel(new FixedLengthFrameDecoder(3));
        //模拟入栈操作
        channel.writeInbound(ByteBufAllocator.DEFAULT.buffer().writeBytes("hello".getBytes()));

        //模拟出栈操作
        channel.writeOneInbound(ByteBufAllocator.DEFAULT.buffer().writeBytes("hello".getBytes()));

    }
}
