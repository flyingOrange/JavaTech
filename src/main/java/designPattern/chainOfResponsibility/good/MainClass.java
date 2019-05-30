package designPattern.chainOfResponsibility.good;

/*
 * 责任链模式
 * 
 * */
public class MainClass {
    public static void main(String[] args) {
        CarHandler carHandlerH = new CarHeadHandler();
        CarHandler carHandlerB = new CarBodyHandler();
        CarHandler carHandlerT = new CarTailHandler();
        
        //预先设定好组装顺序  车头--->车身--->车位
        //carHandlerH.setNextHandler(carHandlerB);
        //carHandlerB.setNextHandler(carHandlerT);
        //调用责任链链头完成操作
        //carHandlerH.handleCar();
        
        //简便写法
        carHandlerH.setNextHandler(carHandlerB).setNextHandler(carHandlerT);
        carHandlerH.handleCar();
    }

}
