package javaBasis.serializable;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.List;

import cache.redis.Orange;

public class SerializableListTool<T>{

	// 反序列化List
	public List<T> unSerializableList(byte[] bytes) {
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
	public byte[] serializableList(List<T> list) {
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
