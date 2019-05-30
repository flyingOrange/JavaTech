package designPattern.chainOfResponsibility.good;

public class CarTailHandler extends CarHandler{

    @Override
    public void handleCar() {
        System.out.println("组装车尾");
        if(this.carHandler != null) {
            this.carHandler.handleCar();
        }
    }
}
