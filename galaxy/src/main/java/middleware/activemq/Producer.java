package middleware.activemq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class Producer {
	
	//默认连接用户名
    private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;
    //默认连接密码
    private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
    //默认连接地址
    private static final String BROKEURL = ActiveMQConnection.DEFAULT_BROKER_URL;
    //发送的消息数量
    private static final int SENDNUM = 10;
 
	public static void main(String[] args) {
		ConnectionFactory connectionFactory;
		
		Connection connection = null;
		
		Session session;
		
		Destination destination;
		
		MessageProducer messageProducer;
		
		connectionFactory = new ActiveMQConnectionFactory(USERNAME, PASSWORD, BROKEURL);
		
		try {
			connection = connectionFactory.createConnection();
			
			connection.start();
			
			session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
			
			destination = session.createQueue("HelloWorld");
			
			messageProducer = session.createProducer(destination);
			
			sendMessage(session, messageProducer);
			
			session.commit();
		} catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(connection != null){
                try {
                    connection.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        }
	}
	
	/**
     * 发送消息
     * @param session
     * @param messageProducer  消息生产者
     * @throws Exception
     */
    public static void sendMessage(Session session,MessageProducer messageProducer) throws Exception{
        for (int i = 0; i < Producer.SENDNUM; i++) {
            //创建一条文本消息 
            TextMessage message = session.createTextMessage("ActiveMQ 发送消息" +i);
            System.out.println("发送消息：Activemq 发送消息" + i);
            //通过消息生产者发出消息 
            messageProducer.send(message);
        }

    }
}
