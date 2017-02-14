<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<%@include file="../../../admin/common/metaUpload.jsp"%>

<form id="edit" action="admin/banner/edit" class="form-horizontal"
	method="post" enctype="multipart/form-data">
	<input name="uId" value="${entity.uId }" hidden>
	
	
	<div class="form-group">
		<label class="col-sm-2 control-label">用户名：</label>

		<div class="col-sm-2">
			<input class="form-control" value="${entity.uName }"
				name="uName" placeholder="用户名" />
		</div>
	</div>

</form>
