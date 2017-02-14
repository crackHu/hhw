<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@include file="../../../admin/common/metaUpload.jsp"%>

<form id="add" action="admin/banner/add" class="form-horizontal"
	method="post" enctype="multipart/form-data">

	<div class="form-group">
		<label class="col-sm-2 control-label">用户名：</label>

		<div class="col-sm-5">
			<input class="form-control" value="" name="uName" placeholder="用户名" />
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">电话：</label>

		<div class="col-sm-5">
			<input class="form-control" value="${entity.aTCode }" name="uPhone"
				placeholder="手机号码不能为空" />
		</div>
	</div>

	<div class="form-group">
		<label class="col-sm-2 control-label">密码：</label>

		<div class="col-sm-5">
			<input class="form-control" value="" name="uPassword"
				placeholder="密码不能为空.." />
		</div>
	</div>

	<div class="form-group">
		<label class="col-sm-2 control-label">QQ：</label>

		<div class="col-sm-5">
			<input class="form-control" value="" name="uQq" placeholder="选填" />
		</div>
	</div>

	<div class="form-group">
		<label class="col-sm-2 control-label">邮箱：</label>

		<div class="col-sm-5">
			<input class="form-control" value="" name="uEmail" placeholder="选填" />
		</div>
	</div>


</form>
<script>
	
</script>