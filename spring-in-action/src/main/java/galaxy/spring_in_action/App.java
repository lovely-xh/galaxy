package galaxy.spring_in_action;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import section_1.IKnight;

public class App 
{
    public static void main( String[] args )
    {
        ClassPathXmlApplicationContext context = 
        		new ClassPathXmlApplicationContext("section_1/knights.xml");
        IKnight knight = context.getBean(IKnight.class);
        knight.embarkOnQuest();
        context.close();
    }
}
