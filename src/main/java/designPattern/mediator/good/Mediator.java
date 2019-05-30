package designPattern.mediator.good;

public class Mediator {
    private Man man;
    private Woman woman;

    public void setMan(Man man) {
        this.man = man;
    }
    public void setWoman(Woman woman) {
        this.woman = woman;
    }
    
    public void getpartner(Human human) {
        if(human instanceof Man) {
            this.setMan((Man)human);
        }else {
            this.setWoman((Woman)human);
        }
        //判断条件
        if(man.getCondition() == woman.getCondition()) {
            System.out.println("OK,"+man.getName()+"和"+woman.getName()+"相配");
        }else {
            System.out.println("No,"+man.getName()+"和"+woman.getName()+"不相配");
        }
        
    }
    

}
