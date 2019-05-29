package designPattern.bridge.bad;

public class MainClass {
    public static void main(String[] args) {
        /*下面的例子看出，如果增加一个Truck类或者在Car中增加方法，所有的实现类中都要增加，几何级的增加
         * 
         * */
        Car car1 = new Bus2000();
        car1.installEngine();
        Car car2 = new Bus2200();
        car2.installEngine();
        Car car3 = new Jeep2200();
        car3.installEngine();
        Car car4 = new Jeep2000();
        car4.installEngine();
        
        
    }
}
