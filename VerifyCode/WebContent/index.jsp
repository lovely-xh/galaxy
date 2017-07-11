<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" import="java.util.*" info=""%>
<%
String path = request.getContextPath();
//String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>VerifyCode</title>

<%! public void jspInit() {
		System.out.println("heihiehei");
	}
%>
	
<script type="text/javascript">
	function reloadCode() {
		var time = new Date().getTime();
		document.getElementById("verifyCode").src="<%=request.getContextPath()%>/servlet/ImageServlet?d="+time;
	}	
</script>
</head>
<body>
	<form action="<%=request.getContextPath() %>/servlet/VerifyServlet" method="get">
		验证码：<input type="text" name="checkcode"/>
		<img alt="验证码" id="verifyCode" src="<%=request.getContextPath()%>/servlet/ImageServlet"/>
		<a href="javascript:reloadCode();">换一张</a><br>		
		<input type="submit" value="提交">
	</form>
</body>
</html>