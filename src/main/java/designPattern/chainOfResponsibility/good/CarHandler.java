package designPattern.chainOfResponsibility.good;

public abstract class CarHandler {
    //设置成protected，以便子类调用
    protected CarHandler carHandler;
    
    public CarHandler setNextHandler(CarHandler carHandler) {
        this.carHandler = carHandler;
        return this.carHandler;
    }
    
    public abstract void handleCar(); 

}
