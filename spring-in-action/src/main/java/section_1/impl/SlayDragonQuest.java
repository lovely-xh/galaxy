package section_1.impl;

import java.io.PrintStream;

import section_1.IQuest;

public class SlayDragonQuest implements IQuest {
	
	private PrintStream stream;
	
	public SlayDragonQuest(PrintStream stream) {
		this.stream = stream;
	}

	public void embark() {
		stream.println("Embarking on quest to slay");
	}

}
