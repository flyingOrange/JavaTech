package designPattern.command.v2;

/*
 * 命令模式:此例不好，以后再改
 * 
 * */
public class MainClass {
	
	public static void main(String[] args) {
		Peddler peddler = new Peddler();
		peddler.sellApple();
		peddler.sellBanana();
		
		//新做法
		Command appleCpmmand = new AppleCommand(peddler);
		appleCpmmand.sell();
		Command bananaCommand = new BananaCommand(peddler);
		bananaCommand.sell();
		
		
	}

}
