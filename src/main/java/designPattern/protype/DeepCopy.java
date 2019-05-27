package designPattern.protype;

import java.io.Serializable;

public class DeepCopy implements Serializable{
    
    int age;
    Prototype prototype;
    
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public Prototype getPrototype() {
        return prototype;
    }
    public void setPrototype(Prototype prototype) {
        this.prototype = prototype;
    }
    @Override
    public String toString() {
        return "DeepCopy [age=" + age + ", prototype=" + prototype + "]";
    }
    
}
