package cache.redis;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import javaBasis.serializable.SerializableListTool;
import javaBasis.serializable.SerializableObjectTool;
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
	
	//缓存对象 
	@Test
	public void cacheObject() throws IOException, ClassNotFoundException {
		Orange orange = new Orange(98,"haha");
		
		jedis.set("obj".getBytes(), SerializableObjectTool.obj2Bytes(orange));
		byte[] bytes = jedis.get("obj".getBytes());
		Object obj = SerializableObjectTool.bytes2Obj(bytes);
		System.out.println((Orange)obj);
		jedis.close();
	}
	
	//缓存List
	@Test
	public void cacheList() {
		List<Orange> list = new LinkedList<Orange>() {
			{
				add(new Orange(22,"hei"));
				add(new Orange(62,"xixi"));
			}
		};
		SerializableListTool<Orange> tool = new SerializableListTool<Orange>();
		byte[] bytes = tool.serializableList(list);
		jedis.set("list".getBytes(),bytes);
		jedis.close();
	}
	
	@Test
	public void getList() {
		byte[] bytes = jedis.get("list".getBytes());
		SerializableListTool<Orange> tool = new SerializableListTool<Orange>();
		List<Orange> list = tool.unSerializableList(bytes);
		
		System.out.println(list);
		jedis.close();
	}
	
	
}
