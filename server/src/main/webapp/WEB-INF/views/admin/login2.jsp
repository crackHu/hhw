<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>登录页面</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/admin/css/base.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/admin/css/style.css">
	<script type="text/javascript" src="${pageContext.request.contextPath }/admin/js/jquery-1.12.3.min.js"></script>
	<script type="text/javascript" src='${pageContext.request.contextPath }/admin/js/common.js'></script>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
 </head>
 <script type="text/javascript">
	function aa() {
		var message = '${requestScope.message }';
		if (message == "no") {
			$("#message").removeClass('hide');
		}
	}
</script>

 <body class="login_bg" onload="aa()">
    <div class="loginContainer">
    	<div class="login_wrap">
    		<p class="login_p1"><img src="admin/images/logo.png">&nbsp;用户登录</p>
    		<p><img src="admin/images/bottom_bg.png"></p>
    		<p id="message"class="hide" style="color: red;text-indent: 20;">用户或密码错误</p>
    		<form class="login_form" action="${pageContext.request.contextPath }/admin/checkLogin" method="post">
    			
    			<p class="text_p"><input type="text" name="username" placeholder='账号' class="username" /></p>
    			<p class="text_p"><input type="password" name="password" placeholder='密码' class="password" /></p>
    			<p class="checkbox_p"><input type="checkbox" id="check_box" checked="checked" class="hide" /></p>
    			<p class="hide_p  red"></p>
    			<p class="login_btn_p"><input type="submit" value="登录" class="login_btn"/></p>
    		</form>
    	</div>
    </div>
</body>
</html>
