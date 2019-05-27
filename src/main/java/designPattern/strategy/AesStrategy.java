package designPattern.strategy;
import designPattern.strategy.inter.Strategy;

public class AesStrategy  implements Strategy{
    
    @Override
    public void encrypt() {
        System.out.println("执行AES加密");
    }
    
}
