package designPattern.bridge.good;

public class Jeep extends Car {

    public Jeep(Engine engine) {
        super(engine);
    }

    @Override
    public void installEngine() {
        System.out.print("jeep");
        this.getEngine().installEngine();
    }

}