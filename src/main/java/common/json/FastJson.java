package common.json;

import org.junit.Test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class FastJson {
	// json字符串-简单对象型
	private static final String JSON_OBJ_STR = "{\"name\":\"lily\",\"age\":12}";

	// json字符串-数组类型
	private static final String JSON_ARRAY_STR = "[{\"name\":\"lily\",\"age\":12},{\"name\":\"lucy\",\"age\":15}]";

	// 复杂格式json字符串
	private static final String COMPLEX_JSON_STR = "{\"name\":\"crystall\",\"age\":27,\"sons\":[{\"name\":\"lily\",\"birth\":12},{\"name\":\"lucy\",\"birth\":15}]}";

	@Test
	public void bean2Json() {
		// 已知JSONObject,目标要转换为json字符串
		JSONObject jsonObject = JSONObject.parseObject(JSON_OBJ_STR);
		String jsonString = JSONObject.toJSONString(jsonObject);
		System.out.println(jsonString);
		
		//已知JSONArray,目标要转换为json字符串
	    JSONArray jsonArray = JSONArray.parseArray(JSON_ARRAY_STR);
	    String jsonString2 = JSONArray.toJSONString(jsonArray);
	    System.out.println(jsonString2);
		

	}

	@Test
	public void json2Bean() {
		//json字符串-简单对象型到JSONObject的转换
		JSONObject jsonObject = JSONObject.parseObject(JSON_OBJ_STR);
		System.out.println("name:  " + jsonObject.getString("name") + ":" + "  age:  " + jsonObject.getInteger("age"));
		
		JSONArray jsonArray = JSONArray.parseArray(JSON_ARRAY_STR);
	    //遍历方式
	    int size = jsonArray.size();
	    for (int i = 0; i < size; i++) {
	        JSONObject jsonObject2 = jsonArray.getJSONObject(i);
	        System.out.println("name:  " + jsonObject2.getString("name") + ":" + "  age:  "
	                + jsonObject2.getInteger("age"));
	    }
	    

	}
}
