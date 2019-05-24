package designPattern.factory.abstractFactory;

import designPattern.factory.abstractFactory.inter.Meat;

public class MainClass {
    public static void main(String[] args) {
        /*MeatFactory是抽象工厂xxxMeatFactory是具体工厂。
         * xxxMeatFactory工厂相当于一个产品族，pork、beef是具体产品，可以随时增加新产品 且不影响其他产品，符合开放封闭原则。
         * 
         * 缺点:
         * 如果要增加Mutton羊肉，则需要先在MeatFactory中增加getMutton()
         * 然后在 所有具体工厂xxxMeatFactory中增加getMutton
         * 
         * */
        MeatFactory janpanFactory = new JanpanMeatFactory();
        Meat jpork = janpanFactory.getPork();
        jpork.get();
        Meat jbeef = janpanFactory.getBeef();
        jbeef.get();
        
        MeatFactory chineseFactory = new ChineseMeatFactory();
        Meat cpork = chineseFactory.getPork();
        cpork.get();
        Meat cbeef = chineseFactory.getBeef();
        cbeef.get();

    }
}
