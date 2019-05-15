package cache.redis;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import javaBasis.serializable.SerializableTool;
import redis.clients.jedis.Jedis;

public class JedisTool implements Serializable{

	
	@Test
	public void cacheStr() {
		Jedis jedis = new JedisConnPool().getConnection();
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
		Jedis jedis = new JedisConnPool().getConnection();
		Orange orange = new Orange(98,"haha");
		
		jedis.set("obj".getBytes(), SerializableTool.obj2Bytes(orange));
		byte[] bytes = jedis.get("obj".getBytes());
		Object obj = SerializableTool.bytes2Obj(bytes);
		System.out.println((Orange)obj);
		jedis.close();
	}
	
	//缓存List
	@Test
	public void cacheList() {
		Jedis jedis = new JedisConnPool().getConnection();
		ArrayList<Orange> list = new ArrayList<Orange>() {
			{
				add(new Orange(22,"hei"));
				add(new Orange(62,"xixi"));
			}
		};
		byte[] bytes = SerializableTool.serializableList(list);
		jedis.set("list".getBytes(),bytes);
		jedis.close();
	}
	
	@Test
	public void getList() {
		Jedis jedis = new JedisConnPool().getConnection();
		byte[] bytes = jedis.get("list".getBytes());
		List<Orange> list = SerializableTool.unSerializableList(bytes);
		
		System.out.println(list);
		jedis.close();
	}
	
	
}
