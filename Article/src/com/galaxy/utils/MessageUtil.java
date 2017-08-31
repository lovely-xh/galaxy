package com.galaxy.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.galaxy.po.TextMessage;
import com.thoughtworks.xstream.XStream;

public class MessageUtil {

	public static Map<String, String> xmlToMap(HttpServletRequest request) throws DocumentException, IOException {
		Map<String, String> map = new HashMap<String, String>();
		SAXReader reader = new SAXReader();
		
		InputStream inputStream = request.getInputStream();
		Document document = reader.read(inputStream);
		
		Element root = document.getRootElement();
		
		List<Element> list = root.elements();
		
		for(Element e : list) {
			map.put(e.getName(), e.getText());
		}
		
		inputStream.close();
		
		return map;
	}
	
	public static String textMsgToXml(TextMessage textMessage) {
		XStream xstream = new XStream();
		return xstream.toXML(textMessage);
	}
}
