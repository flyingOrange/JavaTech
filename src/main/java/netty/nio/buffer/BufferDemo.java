package netty.nio.buffer;

import java.nio.IntBuffer;

public class BufferDemo {

	public static void main(String[] args) {
		
		IntBuffer intBuffer = IntBuffer.allocate(5);
		for(int i=0;i<intBuffer.capacity();i++) {
			intBuffer.put(i * 2);
			
		}
		
		//flip方法将Buffer从写模式切换到读模式。调用flip()方法会将position设回0，并将limit设置成之前position的值。 
		//换句话说，position现在用于标记读的位置，limit表示之前写进了多少个byte、char等 —— 现在能读取多少个byte、char等
		intBuffer.flip();
		while(intBuffer.hasRemaining()) {
			System.out.println(intBuffer.get());
		}
				
	}
}
