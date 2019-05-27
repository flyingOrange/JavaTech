package designPattern.strategy;

import designPattern.strategy.inter.Strategy;

/*
 * 策略模式
 * */
public class MainClass {
    public static void main(String[] args) {
        //策略的Context和Decorator很像,但Context不会实现Strategy接口,但Decorator要实现Component接口
        //策略模式可以针对环境提供不同的算法，如消费打折时，StrategyA、StrategB……
        //Strategy也可以是抽象类，把不同策略的共同代码在Strategy中实现
        Strategy strategy = new MD5Strategy();
        Context context = new Context(strategy);
        context.encrypt();
        
    }

}
