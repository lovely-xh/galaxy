package section_2.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import section_2.ICompactDisc;
import section_2.IMediaPlayer;

@Component
public class CDPlayer implements IMediaPlayer {

	private ICompactDisc cd;
	
	@Autowired
	public CDPlayer(ICompactDisc cd) {
		System.out.println("Autowired");
		this.cd = cd;
		this.cd.play();
	}
	
	public void play() {
		cd.play();
	}

}
