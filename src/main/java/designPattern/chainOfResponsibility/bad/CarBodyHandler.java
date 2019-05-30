package designPattern.chainOfResponsibility.bad;

public class CarBodyHandler extends CarHandler{

    @Override
    public void handleCar() {
        System.out.println("组装车身");
    }

}
