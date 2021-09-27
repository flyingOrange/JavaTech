package netty.netty.decoder;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public class LengthFieldBasedFrDecoder {

    public static void main(String[] args) {
        EmbeddedChannel emc = new EmbeddedChannel(
                new LengthFieldBasedFrameDecoder(1024,0,4,0,4),
                new LoggingHandler(LogLevel.DEBUG));

        ByteBuf byteBuf = ByteBufAllocator.DEFAULT.buffer();
        send(byteBuf,"hello");
        send(byteBuf,"world");
        send(byteBuf,"!!hhhhhh");
        emc.writeInbound(byteBuf);

    }

    public static void send(ByteBuf buf,String content){
        byte[] bytes = content.getBytes();//内容长度
        int length = bytes.length;

        buf.writeInt(length);
        buf.writeBytes(bytes);
    }
}
