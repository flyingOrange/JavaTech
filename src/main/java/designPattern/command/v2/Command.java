package designPattern.command.v2;

public abstract class Command {
	protected Peddler peddler;
	
	public Peddler getPeddler() {
		return peddler;
	}

	public void setPeddler(Peddler peddler) {
		this.peddler = peddler;
	}

	public abstract void sell();
	
}
