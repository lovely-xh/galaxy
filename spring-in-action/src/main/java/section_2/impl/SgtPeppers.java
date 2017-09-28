package section_2.impl;

import org.springframework.stereotype.Component;

import section_2.ICompactDisc;

@Component
public class SgtPeppers implements ICompactDisc {
	
	private String title = "Sgt. Pepper's Lonely Hearts Club Band";
	
	private String artist = "The Beatles";

	public void play() {
		System.out.println(String.format("Playing %s \r\nby %s", title, artist));
	}
	
}
