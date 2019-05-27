package designPattern.flyweight;

/*
 * 享元模式:主要用于减少创建对象的数量，以减少内存占用和提高性能
 * 这种类型的设计模式属于结构型模式，它提供了减少对象数量从而改善应用所需的对象结构的方式
 * 
 * */
public class MainClass {
    public static void main(String[] args) {
        //mychar1和mychar3都是‘a’但使用了两个对象，使用享元模式共享
        MyCharacter mychar1 = new MyCharacter('a');
        MyCharacter mychar2 = new MyCharacter('b');
        MyCharacter mychar3 = new MyCharacter('a');
        MyCharacter mychar4 = new MyCharacter('d');
        mychar1.diszplay();
        mychar2.diszplay();
        mychar3.diszplay();
        mychar4.diszplay();
        if(mychar1 == mychar3) {
            System.out.println(true);
        }else {
            System.out.println(false);
        }
        //享元模式
        MyCharacterFactory factory = new MyCharacterFactory();
        MyCharacter mychar5 = factory.getMyCharacter('a');
        MyCharacter mychar6 = factory.getMyCharacter('b');
        MyCharacter mychar7 = factory.getMyCharacter('a');
        MyCharacter mychar8 = factory.getMyCharacter('d');
        mychar5.diszplay();
        mychar6.diszplay();
        mychar7.diszplay();
        mychar8.diszplay();
        //mychar5和mychar7是同一个对象，节约了内存
        if(mychar5 == mychar7) {
            System.out.println(true);
        }else {
            System.out.println(false);
        }
        
        
        
    }

}
