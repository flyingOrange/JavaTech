package designPattern.builder;
//步骤3： 创建具体的建造者（ConcreteBuilder）:装机人员
public class ConcreteBuilder extends Builder{
	//创建产品实例
    private Computer computer;
    
    public ConcreteBuilder(){
    	computer = new Computer();
    }

	@Override
	public void buildCPU() {
		computer.Add("组装CPU");		
	}

	@Override
	public void buildMainboard() {
		computer.Add("组装主板");		
	}

	@Override
	public void buildHD() {
		computer.Add("组装硬盘");		
	}

	@Override
	public Computer getComputer() {
		return computer;
	}

}
