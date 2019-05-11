package javaBasis.serializable;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import cache.redis.Orange;

public class SerializableTool {
	
	//对象转化为二进制流(序列化)
	public static byte[] obj2Bytes(Object obj) {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			ObjectOutputStream oos = new ObjectOutputStream(bos);
			oos.writeObject(obj);
			bos.close();
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		byte[] bytes = bos.toByteArray();
		return bytes;
	}
	//二进制流转化为对象(反序列化)
	public static Object bytes2Obj(byte[] bytes){
		ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
		ObjectInputStream ois;
		Object obj = null;
		try {
			ois = new ObjectInputStream(bis);
			obj = ois.readObject();
			ois.close();
			bis.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return obj;
	}
	
	public static <T> List<T> unSerializableList(byte[] bytes) {
		ObjectInputStream ois = null;
		List<T> list = null;
		ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
		try {
			ois = new ObjectInputStream(bis);
			Orange obj = (Orange) ois.readObject();
			//list = obj;
			ois.close();
			bis.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return list;
	}

	// 序列化List
	public static <T> byte[] serializableList(List<T> list) {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			ObjectOutputStream oos = new ObjectOutputStream(bos);
			for(T elem : list) {
				oos.writeObject(elem);
			}
			bos.close();
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		byte[] bytes = bos.toByteArray();
		return bytes;
	}
	
}
