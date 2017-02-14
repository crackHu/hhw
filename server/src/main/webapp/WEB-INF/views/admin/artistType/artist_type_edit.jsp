<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<%@include file="../../admin/common/metaUpload.jsp"%>

<form id="edit" action="admin/banner/edit" class="form-horizontal"
	method="post" enctype="multipart/form-data">
	<input name="aTId" value="${entity.aTId }" hidden>
	
	
	<div class="form-group">
		<label class="col-sm-2 control-label">类型名称：</label>

		<div class="col-sm-2">
			<input class="form-control" value="${entity.aTName }"
				name="aTName" placeholder="类型名称" />
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">类型代码：</label>

		<div class="col-sm-5">
			<input class="form-control" value="${entity.aTCode }"
				name="aTCode" placeholder="类型代码" />
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">排序：</label>

		<div class="col-sm-5">
			<input class="form-control" value="${entity.aTOrderBy }"
				name="aTOrderBy" placeholder="排序" />
		</div>
	</div>
	

</form>
