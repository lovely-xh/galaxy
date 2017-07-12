package com.galaxy;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletHeader extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		response.setContentType("text/html;charset=utf-8");
		
		PrintWriter pWriter = response.getWriter();
		
		String title = "HTTP header请求实例";
		
		String docType = "<!DOCTYPE html> \n";
		pWriter.println(docType +
				"<html>\n" +
				"<head><meta charset=\"utf-8\"><title>" + title + "</title></head>\n"+
				"<body bgcolor=\"#f0f0f0\">\n" +
				"<h1 align=\"center\">" + title + "</h1>\n" +
				"<table width=\"100%\" border=\"1\" align=\"center\">\n" +
				"<tr bgcolor=\"#949494\">\n" +
				"<th>Header 名称</th><th>Header 值</th>\n"+
				"</tr>\n");
		Enumeration headerNames = request.getHeaderNames();
		
		while (headerNames.hasMoreElements()) {
			String paramName = (String)headerNames.nextElement();
			pWriter.print("<tr><td>" + paramName + "</td>\n");
			String paramValue = request.getHeader(paramName);
			pWriter.println("<td> " + paramValue + "</td></tr>\n");
		}
		
		pWriter.println("</table>\n</body></html>");
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		doGet(request, response);
	}

}
