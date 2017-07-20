package com.galaxy;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/track")
public class ServletTrackSession extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession(true);
		
		Date createTime = new Date(session.getCreationTime());
		
		Date lastAccessTime = new Date(session.getLastAccessedTime());
		
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		
		String title = "Servlet Session 实例 - 菜鸟教程";
		Integer visitCount = new Integer(0);
		String visitCountKey = new String("visitCount");
		String userIDKey = new String("userID");
		String userID = new String("Runoob");
	
		// 检查网页上是否有新的访问者
		if (session.isNew()){
			title = "Servlet Session 实例 - 菜鸟教程";
		 	session.setAttribute(userIDKey, userID);
		} else {
		 	visitCount = (Integer)session.getAttribute(visitCountKey);
		 	visitCount = visitCount + 1;
		 	userID = (String)session.getAttribute(userIDKey);
		}
		session.setAttribute(visitCountKey,  visitCount);
	
		// 设置响应内容类型
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
	
		String docType = "<!DOCTYPE html>\n";
		out.println(docType +
		        "<html>\n" +
		        "<head><title>" + title + "</title></head>\n" +
		        "<body bgcolor=\"#f0f0f0\">\n" +
		        "<h1 align=\"center\">" + title + "</h1>\n" +
		         "<h2 align=\"center\">Session 信息</h2>\n" +
		        "<table border=\"1\" align=\"center\">\n" +
		        "<tr bgcolor=\"#949494\">\n" +
		        "  <th>Session 信息</th><th>值</th></tr>\n" +
		        "<tr>\n" +
		        "  <td>id</td>\n" +
		        "  <td>" + session.getId() + "</td></tr>\n" +
		        "<tr>\n" +
		        "  <td>创建时间</td>\n" +
		        "  <td>" +  df.format(createTime) + 
		        "  </td></tr>\n" +
		        "<tr>\n" +
		        "  <td>最后访问时间</td>\n" +
		        "  <td>" + df.format(lastAccessTime) + 
		        "  </td></tr>\n" +
		        "<tr>\n" +
		        "  <td>用户 ID</td>\n" +
		        "  <td>" + userID + 
		        "  </td></tr>\n" +
		        "<tr>\n" +
		        "  <td>访问统计：</td>\n" +
		        "  <td>" + visitCount + "</td></tr>\n" +
		        "</table>\n" +
		        "</body></html>"); 
	}
}
