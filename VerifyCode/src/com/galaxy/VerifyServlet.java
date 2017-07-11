package com.galaxy;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//http://www.cnblogs.com/xdp-gacl/tag/JavaWeb%E5%AD%A6%E4%B9%A0%E6%80%BB%E7%BB%93/
//http://www.imooc.com/video/5769
//http://blog.csdn.net/rioli/article/details/49470781
public class VerifyServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String piccode = (String) request.getSession().getAttribute("piccode");
		String checkcode = request.getParameter("checkcode").toUpperCase();
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pWriter = response.getWriter();
		if (checkcode.equals(piccode)) {
			pWriter.println("验证通过");
		} else {
			pWriter.println("验证失败");
		}
		
		pWriter.close();
	}

}
