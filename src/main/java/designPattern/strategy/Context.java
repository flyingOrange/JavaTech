package designPattern.strategy;

import designPattern.strategy.inter.Strategy;

public class Context {
    private Strategy strategy;
    
    public Context(Strategy strategy) {
        this.strategy = strategy;
    }
    
    //根据不同策略执行不同的动作，策略由外部环境决定
    public void encrypt() {
        this.strategy.encrypt();
    }
    

}
