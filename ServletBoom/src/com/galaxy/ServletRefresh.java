package com.galaxy;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletRefresh extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

		if (request.getParameter("refresh") != null) {
			System.out.println("come in");
			response.setContentType("text/html;charset=utf-8");
			response.setIntHeader("refresh", 1);
	
			Calendar calendar = Calendar.getInstance();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String nowTime = simpleDateFormat.format(calendar.getTime());
			System.out.println(nowTime);
	
			PrintWriter pWriter = response.getWriter();
	
			String title = "自动刷新 Header设置";
			String docType = "<!DOCTYPE html>\n";
			pWriter.println(docType + "<html>\n" + "<head><title>" + title + "</title></head>\n" 
						+ "<body bgcolor=\"#f0f0f0\">\n"
						+ "<h1 align=\"center\">" + title + "</h1>\n"
						+ "<p>当前时间是：" + nowTime + "</p>\n");
		} else if (request.getParameter("error") != null) {
			response.sendError(407, "Need authentication!!!");
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		doGet(request, response);
	}

}
