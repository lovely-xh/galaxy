<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
	String path = request.getContextPath();
	int port = request.getServerPort();
	String basePath = null;
	if(port==80){
		basePath = request.getScheme()+"://"+request.getServerName()+path;
	}else{
		basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
	}
	request.setAttribute("basePath", basePath);
%>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>首页</title>
	</head>

	<style>
		*{
		    padding: 0 ; 
		    margin: 0 ;
		    font-family: "微软雅黑" ;
		}
		.header {
		    height: 72px ;
		    background: #458fce ;
		}
		.header .logo {
		    color: #fff ;
		    line-height: 72px ;
		    font-size: 30px ;
		    margin-left: 20px ;
		    font-weight:500 ;
		    display: inline-block;
		    float: left;
		}
		ul li {
	    	list-style: none ;	
		}
		
		.header ul {
			display: inline-block ;
		}
	
		.header ul li.first {
	    	margin-left: 30px;
	    	background: #74b0e2;
		}
	
		.header ul li {
		    color: #fff ;
		    width: 112px ;
		    height: 72px ; 
		    text-align: center ;
		    line-height:72px ;
		    cursor: pointer ;
		    display: inline-block;
		    float: left;
		}
		
	    a {
	    	color: #fff ;
	    	text-decoration: none ; /* 设置元素文本装饰 */
		}
		
		.header ul li:hover { /* 选择鼠标指针浮动在其上的元素，并设置其样式 */
	    	background:#74b0e2 ;
		}
		
		.header .login {
			float: right;
			color: #fff;
			line-height: 72px;
			margin-right: 20px;
		}
		
		/* 轮播图样式 */
		.banner {
			height: 380px;
			overflow: hidden;
			background: #ccc;
			position: relative;
			margin-top: 2px;
		}
		
		.banner .content {
			width: 1060px;
			height: 450px;
			margin: 20px auto;
			position: relative;
			float: left;
		}
		
		.banner .content ul {
 		    width: 10000px;
		}
		
		.banner .content ul li {
			display: inline-block ;
		}
		
 		/* .banner ul li img {
		    width: 1024px ;
		    height: 380px ;
		} */
		
		.floatLeft {float: left;}
		.floatRight {float: right;}
		
		.btn_left .btn_right {
		    display: inline-block;
		    width: 21px;
		    height: 150px;
		    /* background: url(./banner_tb.png) no-repeat; */
		    position: absolute;
		    left: -38px;
		    top: 90px;
		    opacity: 0;
			transition: all ease 0.6s;
			cursor: pointer;
		}
		
		.banner .content:hover .btn_left {
			transition: all ease 0.6s;
    		opacity: 1;
		}

		.banner .content:hover .btn_right {
			transition: all ease 0.6s;
    		opacity: 1;
		}
	</style>
	
	<body>
		<div class="header">
			<div class='logo'>原创文字</div>
			<ul>
				<li class='first'><a href="javascript:void(0)">首页</a></li>
				<li><a href="javascript:void(0)">原创故事</a></li>
				<li><a href="javascript:void(0)">热门专题</a></li>
				<li><a href="javascript:void(0)">欣赏美文</a></li>
				<li><a href="javascript:void(0)">赞助</a></li>
			</ul>
			<div class='login'>
				<span><a href="javascript:void(0)">登陆</a></span>
				<span> | </span>
				<span><a href="javascript:void(0)">注册</a></span>
			</div>
		</div>
		
		<div class="banner">
			<div class='content'>
				<ul>
					<li><a href="javascript:void(0)"><img src="1.png"/></a></li>
					<li><a href="javascript:void(0)"><img src="2.png"/></a></li>
					<li><a href="javascript:void(0)"><img src="3.png"/></a></li>
					<li><a href="javascript:void(0)"><img src="4.png"/></a></li>
					<li><a href="javascript:void(0)"><img src="5.png"/></a></li>
				</ul>
				<span><button class='btn_left'>上一张</button></span>
				<span><button class='btn_right'>下一张</button></span>
			</div>
		</div>
	</body>
	
	<script>
	</script>
</html>