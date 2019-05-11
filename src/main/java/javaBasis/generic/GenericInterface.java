package javaBasis.generic;

import java.util.Random;

//泛型接口
public interface GenericInterface<T> {
	public T next();
}

class FruitGenerator implements GenericInterface<String> {

	private String[] fruits = new String[]{"Apple", "Banana", "Pear"};
	
	@Override
    public String next() {
        Random rand = new Random();
        return fruits[rand.nextInt(3)];
    }
	
}