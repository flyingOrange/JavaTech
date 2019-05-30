package designPattern.chainOfResponsibility.bad;

public class CarHeadHandler extends CarHandler{

    @Override
    public void handleCar() {
        System.out.println("组装车头");
    }

}
