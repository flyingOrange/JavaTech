package designPattern.bridge.good;

public class MainClass {
    public static void main(String[] args) {
        /*
         * 桥接模式：将抽象部分与实现部分分离，使它们都可以独立的变化
         * 抽象--Car，行为实现--Engine
         * 
         * */
        Engine engine1 = new Engine2000();
        Engine engine2 = new Engine2200();
        
        Car car1 = new Bus(engine1);
        car1.installEngine();
        Car car2 = new Jeep(engine2);
        car2.installEngine();
        
    }

}
