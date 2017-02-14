<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>请先登录</title>
<%@ include file="common/metaAll2.jsp"%>
<style type="text/css">
.login {
	margin:-150px 0 0 -200px;
	position: absolute;
	top: 50%;
	left: 50%;
	width: 400px;
	height: 300px;
}
</style>
</head>
 
<body style="background-color: #F5F5F5;">
	<div class="container login">
		<form class="form-horizontal" role="form" action="" method="POST">
			<div class="form-group">
				<div class="col-sm-10">
					<input type="text" class="form-control" id="username" name="username" placeholder="Username" required="required" autofocus="autofocus">
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-10">
					<input type="password" class="form-control" id="password" name="password" placeholder="Password" required="required">
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-10">
					<button type="button" class="btn btn-default" onclick="login()">Sign in</button>
				</div>
			</div>
		</form>
	</div>
</body>
<script type="text/javascript">
	function aa() {
		var message = '${requestScope.message }';
		if (message == "no") {
			$("#message").removeClass('hide');
		}
	}
	
	function login() {
		$.ajax({
			url : '${pageContext.request.contextPath}/v2/open/user/login',
			data : {
				loginName : $("#username").val(),
				password : $("#password").val(),
				loginType : 2
			},
			success : function(data) {
				if (data.code == 1001) {
					window.location.href = 'index';
				} else {
					alert(data.message);
				}

			}
		});
	}
</script>
</html>