package designPattern.protype;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class MainClass {
    public static void main(String[] args) {
        System.out.println("-----------------克隆的一般使用---------------------");
        // 克隆的一般使用
        Prototype pro1 = new Prototype();
        pro1.setName("hahaha");
        Prototype pro2 = pro1.clone();
        pro2.setName("NONONON");
        System.out.println(pro1);
        System.out.println(pro2);
        System.out.println("-----------------潜拷贝---------------------");
        // 潜拷贝
        List<String> friends = new ArrayList<String>() {
            {
                this.add("lily");
                this.add("jenny");
                this.add("bill");
            }
        };
        People p1 = new People();
        p1.setName("kkk");
        p1.setFriends(friends);
        People p2 = p1.clone();
        p2.setName("jjjjj");
        System.out.println(p1);
        System.out.println(p2);
        // 克隆时只复制了friends的引用地址，所以p1、p2共用一个friends引用，这是--潜克隆
        friends.add("lilei");
        System.out.println(p1);
        System.out.println(p2);
        System.out.println("-----------------深拷贝方法一---------------------");
        // 深拷贝方法一
        // 通过重写clone方法实现浅拷贝的基本思路一样，只需要为对象图的每一层的每一个对象都实现Cloneable接口并重写clone方法，
        //最后在最顶层的类的重写的clone方法中调用所有的clone方法即可实现深拷贝。简单的说就是：每一层的每个对象都进行浅拷贝=深拷贝
        List<String> newFriends = new ArrayList<String>() {
            {
                this.add("11111");
                this.add("22222");
                this.add("3333");
            }
        };
        DeepPeople deepp1 = new DeepPeople();
        deepp1.setName("deep People");
        deepp1.setFriends(newFriends);
        DeepPeople deepp2 = deepp1.clone();
        System.out.println(deepp1);
        System.out.println(deepp2);
        newFriends.add("lilei");
        System.out.println(deepp1);
        System.out.println(deepp2);
        System.out.println("-----------------深拷贝方法二---------------------");
        // 深拷贝方法二
        //将对象序列化为字节序列后，默认会将该对象的整个对象图进行序列化，再通过反序列即可完美地实现深拷贝。
        DeepCopy dp = new DeepCopy();
        Prototype pt = new Prototype();
        pt.setName("pt name");
        dp.setAge(23);
        dp.setPrototype(pt);
        //序列化对象
        ByteArrayOutputStream bos=new ByteArrayOutputStream();
        ObjectOutputStream oos;
        try {
            oos = new ObjectOutputStream(bos);
            oos.writeObject(dp);
            oos.flush();
            //反序列化对象
            ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bos.toByteArray()));
            DeepCopy newDp = (DeepCopy)ois.readObject();
            
            System.out.println(dp);
            System.out.println(newDp);
            dp.setAge(92);
            pt.setName("new pt name");
            System.out.println(dp);
            System.out.println(newDp);
            
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        
        
        
    }
}
