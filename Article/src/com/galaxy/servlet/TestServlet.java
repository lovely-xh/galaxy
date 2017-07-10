package com.galaxy.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class TestServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8660331444039496229L;

	private static Logger logger = Logger.getLogger(TestServlet.class);

	public void init() {
		String prefix = getServletContext().getRealPath("/");
		String location = getInitParameter("configfile");
		PropertyConfigurator.configure(prefix + location);
	}

	public void destroy() {

	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter pWriter = response.getWriter();
		pWriter.print("hello fengliu");
		pWriter.close();

		logger.debug("test");
	}
}

//log4jconfig.properties文件和当前运行的class放在同一目录下  
//String connectdir=Test.class.getResource("log4jconfig.properties").getPath();  
//PropertyConfigurator.configure(connectdir);  
//System.out.println(connectdir);  
