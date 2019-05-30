package designPattern.mediator.bad;

public class Man extends Human{

    public Man(String name, int condition) {
        super(name, condition);
        
    }

    @Override
    public void getpartner(Human human) {
        if(human instanceof Man) {
            System.out.println("No,我不是同性恋");
        }else {
            if(this.getCondition() == human.getCondition()) {
                System.out.println("OK,"+this.getName()+"和"+human.getName()+"相配");
            }else {
                System.out.println("No,"+this.getName()+"和"+human.getName()+"不相配");
            }
            
        }
        
    }

}
