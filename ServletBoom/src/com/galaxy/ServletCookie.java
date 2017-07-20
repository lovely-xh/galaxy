package com.galaxy;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletCookie extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Cookie name = new Cookie("name", URLEncoder.encode(request.getParameter("name"), "utf-8"));
		Cookie url = new Cookie("url", request.getParameter("url"));
		
		name.setMaxAge(60*60*24);
		url.setMaxAge(60*60*24);
		
		response.addCookie(name);
		response.addCookie(url);
		
		response.setContentType("text/html;charset=utf-8");
		
		PrintWriter pWriter = response.getWriter();
		String title = "设置Cookie实例";
		
		String docType = "<!DOCTYPE html>\n";
		pWriter.println(docType +
		        "<html>\n" +
		        "<head><title>" + title + "</title></head>\n" +
		        "<body bgcolor=\"#f0f0f0\">\n" +
		        "<h1 align=\"center\">" + title + "</h1>\n" +
		        "<ul>\n" +
		        "  <li><b>站点名：</b>："
		        + request.getParameter("name") + "\n</li>" +
		        "  <li><b>站点 URL：</b>："
		        + request.getParameter("url") + "\n</li>" +
		        "</ul>\n" +
		        "</body></html>");
	}
}
