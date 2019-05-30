package designPattern.chainOfResponsibility.bad;

public class CarTailHandler extends CarHandler{

    @Override
    public void handleCar() {
        System.out.println("组装车尾");
    }
}
