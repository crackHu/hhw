<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<%@include file="../../admin/common/metaUpload.jsp"%>

<form id="edit" action="admin/banner/edit" class="form-horizontal"
	method="post" enctype="multipart/form-data">
	<input name="fbId" value="${entity.fbId }" hidden>


	<div class="form-group">
		<label class="col-sm-2 control-label">标题：</label>

		<div class="col-sm-2">
			<input class="form-control" value="${entity.fbTitle }" name="fbTitle" placeholder="标题" />
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">内容：</label>

		<div class="col-sm-5">
			<input class="form-control" value="${entity.fbContext }" name="fbContext"
				placeholder="帮助内容" />
		</div>
	</div>

	

</form>
