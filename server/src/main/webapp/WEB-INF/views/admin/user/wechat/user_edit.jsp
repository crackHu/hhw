<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<%@include file="../../../admin/common/metaUpload.jsp"%>
<%@include file="../../../admin/common/taglibs.jsp"%>
<form id="edit" class="form-horizontal"
	method="post" enctype="multipart/form-data">
	<input name="usId" value="${entity.data.usId }" hidden>
	
	
	<div class="form-group">
		<label class="col-sm-2 control-label">昵称：</label>

		<div class="col-sm-2">
			<input class="form-control" value="${entity.data.usName }"
				name="usName" placeholder="用户昵称" />
		</div>
	</div>
	
	<div class="form-group">
		<label class="col-sm-2 control-label">创建时间：</label>
		
		<div class="col-sm-5">
			<fmt:formatDate value="${entity.data.usCreateTime }"
							pattern="yyyy-MM-dd HH:mm:ss" />
		</div>
	</div>
	

</form>
