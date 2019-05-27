package designPattern.flyweight;
//享元角色
public class MyCharacter {
    private char mychar;

    public MyCharacter(char mychar) {
        super();
        this.mychar = mychar;
    }
    
    public void diszplay() {
        System.out.println(mychar);
    }

}
