package designPattern.chainOfResponsibility.good;

public class CarBodyHandler extends CarHandler{

    @Override
    public void handleCar() {
        System.out.println("组装车身");
        if(this.carHandler != null) {
            this.carHandler.handleCar();
        }
    }

}
