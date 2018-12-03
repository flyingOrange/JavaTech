package common.json;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import common.json.bean.Person;
import common.json.bean.Son;

public class Jackson {
	
	@Test
	public void bean2Json() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        // 美化输出
    	mapper.enable(SerializationFeature.INDENT_OUTPUT);
        
        Person person = new Person();
        person.setAge(11);
        person.setName("hello");
        List<Son> sons = new ArrayList<Son>();
        sons.add(new Son("aa",new Date()));
    	sons.add(new Son("bb",new Date()));
    	person.setSons(sons);
        
    	
        String json = mapper.writeValueAsString(person);
        System.out.println(json);
	}
	
	@Test
	public void json2Bean()
            throws JsonParseException, JsonMappingException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        // 在遇到未知属性的时候不抛出异常
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        String jsonStr = "{\"name\" : \"hello\",\"age\" : 11,\"test\":\"haha\"}";
        
        Person person = mapper.readValue(jsonStr, Person.class);
        System.out.println(person);
    }

}
