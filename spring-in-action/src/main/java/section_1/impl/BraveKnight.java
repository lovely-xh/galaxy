package section_1.impl;

import section_1.IKnight;
import section_1.IQuest;

public class BraveKnight implements IKnight {

	private IQuest quest;
	
	public BraveKnight(IQuest quest) {
		this.quest = quest;
	}
	
	public void embarkOnQuest() {
		quest.embark();
	}

}
