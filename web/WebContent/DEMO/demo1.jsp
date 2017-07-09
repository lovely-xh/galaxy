<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" import="java.util.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>You Are Welcome</title>
</head>

<body>
	<%--JSP注释标签示例 --%>
	
	<%! int x = 10;
		void a() {
		}
	%>  <!-- 声明标签 -->
	
	<% 
		int x = 10;
		out.println("<H1>HELLO fengliu</H1>"); 
		out.println(x);
		
		pageContext.setAttribute("x", 100);
		out.println(request.getAttribute("username"));
		out.println(session.getId());
		out.println(application.getClass());
		out.println(config.getServletName());
		
		response.sendRedirect("../index.jsp");
	%>  <!-- 代码段标签 -->
	
	<%= "<H1>HELLO fengliu</H1>"%>  <!-- jsp输出标签 -->
	<%= pageContext.getAttribute("x")%>
	
	<%-- <jsp:attribute name=""></jsp:attribute>   JSP动作标签 --%>
	
</body>

</html>