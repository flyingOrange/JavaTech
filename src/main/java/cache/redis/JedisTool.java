package cache.redis;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import redis.clients.jedis.Jedis;

public class JedisTool {
	private Jedis jedis;
	
	@Before
	public void init() {
		jedis = new JedisConnPool().getConnection();
	}
	
	@Test
	public void cacheStr() {
		//添加,会覆盖
		jedis.set("hello", "world");
		String hello = jedis.get("hello");
		System.out.println(hello);
		//刪除
		jedis.del("hello");
		hello = jedis.get("hello");
		System.out.println(hello);
        jedis.close();
	}
	
	@Test
	public void cacheObject() throws IOException, ClassNotFoundException {
		Orange orange = new Orange(98,"haha");
		
		jedis.set("obj".getBytes(), Obj2Bytes(orange));
		byte[] bytes = jedis.get("obj".getBytes());
		Object obj = bytes2Obj(bytes);
		System.out.println((Orange)obj);
		jedis.close();
	}
	
	@Test
	public void cacheList() {
		List<Orange> list = new LinkedList<Orange>() {
			{
				add(new Orange(22,"hei"));
				add(new Orange(62,"xixi"));
			}
		};
		
		
		
	}
	
	//对象转化为二进制流(序列化)
	private byte[] Obj2Bytes(Object obj) throws IOException {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(bos);
		oos.writeObject(obj);
		
		byte[] sss = bos.toByteArray();
		return sss;
	}
	//二进制流转化为对象(反序列化)
	private Object bytes2Obj(byte[] bytes) throws IOException, ClassNotFoundException {
		ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
		ObjectInputStream ois = new ObjectInputStream(bis);
		Object obj = ois.readObject();
		
		return obj;
	}
	
}
