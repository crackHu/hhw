<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP '403.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <h1>This is my 403 page.权限不足,
     </h1><br>
     
     <h2>原因：</h2><br/>
     <h3>1.登陸两种类型的后台,返回第一次登陆的后台页面,即可能先登录了总后台,再登陆了代理的后台,然后返回了总后台的页面。</h3><br/>
     <h3>2. ...</h3><br/>
     <h3>3. ...</h3><br/>
       
     
  </body>
</html>
