<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>访问错误--页面跳转中...</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta name="viewport" content="width=device-width,height=device-height,inital-scale=1.0,maximum-scale=1.0,user-scalable=no;" />
	<meta http-equiv="refresh" content="500;url=#">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
body {
    font-size: 14px;
    font-family: microsoft yahei, Arial, Helvetica, sans-serif;
    background: #fff;
    overflow-x: hidden;
}
.error_404 {
    background: #fff;
    height: 540px;
    padding-bottom: 30px;
}
.error_404 .container {
    width: 960px;
    height: 540px;
    overflow: hidden;
}
.container {
    width: 960px;
    max-width: 960px;
    margin: auto;
}
.clearfix {
    display: block;
}
.error_404 .error_pic {
    float: left;
    width: 560px;
    height: 540px;
    background: url(./error_404.jpg) 0 0 no-repeat;
}
.error_404 .error_info {
    float: right;
    width: 390px;
    padding-top: 180px;
}
.error_404 .error_info h2 {
    height: 80px;
    line-height: 80px;
    color: #333;
    font-size: 24px;
    font-family: "Microsoft YaHei", Arial, Helvetica, sans-serif;
}
.error_404 .error_info .operate {
    height: 30px;
    line-height: 30px;
}
.btn_89bf43 {
    background: #89bf43;
    color: #fff;
}
.global_btn {
    float: left;
    height: 30px;
    line-height: 30px;
    padding: 0 15px;
    margin: 0;
    font-size: 14px;
    font-family: '\5B8B\4F53';
    border: none;
}
.ml1 {
    margin-left: 1px;
}
.btn_39dec8 {
    background: #39dec8;
    color: #fff;
}
.clearfix:after {
    position: relative;
    z-index: 99;
    clear: both;
    content: ".";
    display: block;
    font-size: 0;
    line-height: 0;
    visibility: hidden;
    width: 0;
    height: 0;
}
</style>
  </head>
  
  <body>
   <div class="menu"></div>
<!--顶部导航-->
<div class="error_404">
  <div class="container clearfix">
    <div class="error_pic"></div>
    <div class="error_info">
      <h2>
        <p>对不起，您访问的页面不存在！</p>
      </h2>
      <div class="operate">
        <input class="global_btn btn_89bf43" onClick="location.href='/'" type="button" value="返回主页">
        <input class="global_btn btn_39dec8 ml1" onClick="history.go(-1)" type="button" value="返回上一页">
      </div>
    </div>
  </div>
</div>
</body>
</html>
