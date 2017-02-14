<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<%@include file="../../admin/common/metaUpload.jsp"%>

<form id="edit" action="admin/banner/edit" class="form-horizontal"
	method="post" enctype="multipart/form-data">
	<input name="pId" value="${entity.pId }" hidden>
	
	
	<div class="form-group">
		<label class="col-sm-2 control-label">利率名称：</label>

		<div class="col-sm-3">
			<input class="form-control" value="${entity.pName }"
				name="pName" placeholder="利率名称" disabled="disabled"/>
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">利率代码：</label>

		<div class="col-sm-3">
			<input class="form-control" value="${entity.pNumber }"
				name="pNumber" placeholder="利率代码" disabled="disabled"/>
		</div>
	</div>
	
	<div class="form-group">
		<label class="col-sm-2 control-label">利率(%)：</label>

		<div class="col-sm-5">
			<input class="form-control" value="${entity.pPercentage }"
				name="pPercentage" placeholder="利率代码" />
		</div>
	</div>
	

</form>
