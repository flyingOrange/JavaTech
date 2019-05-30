package designPattern.chainOfResponsibility.bad;

/*
 * 责任链模式
 * 
 * */
public class MainClass {
    public static void main(String[] args) {
        CarHandler carHandlerH = new CarHeadHandler();
        CarHandler carHandlerB = new CarBodyHandler();
        CarHandler carHandlerT = new CarTailHandler();
        
        carHandlerH.handleCar();
        carHandlerB.handleCar();
        carHandlerT.handleCar();

    }

}
