package com.galaxy;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletReadCookie extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Cookie cookie = null;
		Cookie[] cookies = null;

		cookies = request.getCookies();

		response.setContentType("text/html;charset=utf-8");

		PrintWriter pWriter = response.getWriter();
		String title = "Delete Cookie Example";
		String docType = "<!DOCTYPE html>\n";
		pWriter.println(docType + "<html>\n" + "<head><title>"
				+ title + "</title></head>\n" + "<body bgcolor=\"#f0f0f0\">\n");
		if (cookies != null) {
			pWriter.println("<h2>Cookie 名称和值</h2>");
			for (int i = 0; i < cookies.length; i++) {
				cookie = cookies[i];
				if ((cookie.getName()).compareTo("name") == 0) {
					cookie.setMaxAge(0);
					response.addCookie(cookie);
					pWriter.print("已删除的 cookie：" + cookie.getName() + "<br/>");
				}
				pWriter.print("名称：" + cookie.getName() + "，");
				pWriter.print("值：" + URLDecoder.decode(cookie.getValue(), "utf-8") + " <br/>");
			}
		} else {
			pWriter.println("<h2 class=\"tutheader\">No Cookie founds</h2>");
		}
		pWriter.println("</body>");
		pWriter.println("</html>");
	}
}
