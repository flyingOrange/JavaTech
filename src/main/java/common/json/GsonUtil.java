package common.json;

import com.google.gson.Gson;
import common.json.bean.Person;
import common.json.bean.Son;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GsonUtil {


    @Test
    public void object2Json(){
        Person person = new Person();
        person.setAge(11);
        person.setName("hello");
        List<Son> sons = new ArrayList<Son>();
        sons.add(new Son("aa",new Date()));
        sons.add(new Son("bb",new Date()));
        person.setSons(sons);

        String str = new Gson().toJson(person);
        /**********实现序列化***********/
        byte[] bytes = str.getBytes();
        /**********反序列化***********/
        String json = new String(bytes);
        Person per = new Gson().fromJson(json,Person.class);

        System.out.println("obj转json串"+"\n"+str+"\n"+bytes.length);

    }

    @Test
    public void json2Object(){
        String jsonStr = "{\"name\" : \"hello\",\"age\" : 11,\"test\":\"haha\",\"birth\":\"Sep 29, 2021 3:38:18 PM\"}";
        Person person = new Gson().fromJson(jsonStr,Person.class);
        Son son = new Gson().fromJson(jsonStr,Son.class);

        System.out.print("json串转obj\t");
        System.out.println(person);
        System.out.println(son);

    }

}
