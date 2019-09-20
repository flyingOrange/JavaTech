package designPattern.command.v2;

public class AppleCommand extends Command{

	public AppleCommand(Peddler peddler) {
		super();
		this.peddler = peddler;
	}
	
	@Override
	public void sell() {
		this.getPeddler().sellApple();
	}

}
