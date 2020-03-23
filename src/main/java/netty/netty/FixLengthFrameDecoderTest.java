package netty.netty;

import static org.junit.Assert.*;

import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.ByteToMessageDecoder;

import org.junit.Test;

public class FixLengthFrameDecoderTest {

	@Test
	public void testFrameDecoded()
	{
		System.out.println("hhah");
		ByteBuf buf = Unpooled.buffer();
		for(int i=0;i<9;i++)
		{
			buf.writeByte(i);
		}
		ByteBuf input = buf.duplicate();
		//write message
		EmbeddedChannel channel = new EmbeddedChannel(new FixLengthFrameDecoder(3));
		assertTrue(channel.writeInbound(input.retain()));
		assertTrue(channel.finish());
		//read message
		ByteBuf read = (ByteBuf)channel.readInbound();
		assertEquals(buf.readSlice(3), read);
		read.release();
		
		read = (ByteBuf)channel.readInbound();
		assertEquals(buf.readSlice(3), read);
		read.release();
		
		read = (ByteBuf)channel.readInbound();
		assertEquals(buf.readSlice(3), read);
		read.release();
		
		assertNull(channel.readInbound());
		buf.release();
		System.out.println("over");
		
	}
}


class FixLengthFrameDecoder extends ByteToMessageDecoder
{
	private final int frameLength;
	public FixLengthFrameDecoder(int frameLength)
	{
		this.frameLength = frameLength;
	}

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in,
			List<Object> out) throws Exception {
		while(in.readableBytes() >= frameLength)
		{
			ByteBuf buf  = in.readBytes(frameLength);
			out.add(buf);
			
		}
		
	}
	
}