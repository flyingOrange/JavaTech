package designPattern.observer;

import java.util.Observable;

//被观察者
public class Person extends Observable{
    String name;
    String sex;
    int age;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
        this.setChanged();
        //设置name时通知观察者,可携带参数
        this.notifyObservers();
        //this.notifyObservers(arg);
    }
    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    @Override
    public String toString() {
        return "Person [name=" + name + ", sex=" + sex + ", age=" + age + "]";
    }
    
}
