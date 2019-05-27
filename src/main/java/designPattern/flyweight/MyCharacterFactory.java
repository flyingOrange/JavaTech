package designPattern.flyweight;

import java.util.HashMap;
import java.util.Map;

//享元工厂
public class MyCharacterFactory {
    private Map<Character,MyCharacter> pool;

    public MyCharacterFactory() {
        super();
        this.pool = new HashMap<Character,MyCharacter>();
    }
    
    public MyCharacter getMyCharacter(Character character) {
        MyCharacter mychar = pool.get(character);
        if(mychar == null) {
            mychar = new MyCharacter(character);
            pool.put(character, mychar);
        }
        return mychar;
    }
}
