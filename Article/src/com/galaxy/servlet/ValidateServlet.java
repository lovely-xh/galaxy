package com.galaxy.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.dom4j.DocumentException;

import com.galaxy.po.TextMessage;
import com.galaxy.utils.CheckUtil;
import com.galaxy.utils.MessageUtil;

public class ValidateServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger.getLogger(ValidateServlet.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		log4jInit();

		String signature = req.getParameter("signature");
		String timestamp = req.getParameter("timestamp");
		String nonce = req.getParameter("nonce");
		String echostr = req.getParameter("echostr");
		
		logger.debug("signature = " + signature);
		logger.debug("timestamp = " + timestamp);
		logger.debug("nonce = " + nonce);
		logger.debug("echostr = " + echostr);
		
		PrintWriter pWriter = resp.getWriter();
		if (CheckUtil.checkSignature(signature, timestamp, nonce)) {
			pWriter.print(echostr);
		}
	}
	
	private void log4jInit() {
		String connectdir = "E:\\Eclipse-workspace\\GitHub\\galaxy\\Article\\log4j.properties";
//		String connectdir = "C:\\Users\\Administrator\\Desktop\\Tomcat\\apache-tomcat-8.0.45\\webapps\\log4j.properties";
		PropertyConfigurator.configure(connectdir);

		logger.debug(connectdir);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		PrintWriter pWriter = resp.getWriter();
		try {
			Map<String, String> map = MessageUtil.xmlToMap(req);
			String fromUserName = map.get("FromUserName");
			String toUserName = map.get("ToUserName");
			String createTime = map.get("CreateTime");
			String msgType = map.get("MsgType");
			String content = map.get("Content");
			String msgId = map.get("MsgId");
			
			String msg = null;
			if("text".equals(msgType)) {
				TextMessage textMessage = new TextMessage();
				textMessage.setContent("您发送的消息是：" + content);
				textMessage.setFromUserName(toUserName);
				textMessage.setToUserName(fromUserName);
				textMessage.setMsgType("text");
				textMessage.setCreateTime(Long.toString(new Date().getTime()));
				msg = MessageUtil.textMsgToXml(textMessage);
				System.out.println(msg);
			}
			
			pWriter.print(msg);
		} catch (DocumentException e) {
			e.printStackTrace();
		} finally {
			pWriter.close();
		}
	}
}
