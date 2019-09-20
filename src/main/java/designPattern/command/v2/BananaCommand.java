package designPattern.command.v2;

public class BananaCommand extends Command{
	
	public BananaCommand(Peddler peddler) {
		super();
		this.peddler = peddler;
	}
	
	@Override
	public void sell() {
		this.getPeddler().sellBanana();
	}

}
