package section_2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;

import section_2.config.CDPlayerConfig;

@ContextConfiguration(classes=CDPlayerConfig.class)
public class CDPlayerTester {
	
	@Autowired
	private static ICompactDisc cd;
	
	@Autowired
	private static IMediaPlayer mediaPlayer;

	public static void main(String[] args) {
		boolean a = true;
		ClassPathXmlApplicationContext context = 
        		new ClassPathXmlApplicationContext("section_2/config/CDPlayerConfig.xml");
		
		cd = context.getBean(ICompactDisc.class);
		mediaPlayer = context.getBean(IMediaPlayer.class);
		
		System.out.println(cd);
		System.out.println(mediaPlayer);
		
		cd.play();
		mediaPlayer.play();
	}
	
}
