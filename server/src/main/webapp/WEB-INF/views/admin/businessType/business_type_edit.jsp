<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String pbth = request.getContextPath();
	String basePbth = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ pbth + "/";
%>

<%@include file="../../admin/common/metaUpload.jsp"%>

<form id="edit" action="admin/banner/edit" class="form-horizontal"
	method="post" >
	<input name="btId" value="${entity.btId }" hidden>
	
	
	<div class="form-group">
		<label class="col-sm-2 control-label">类型名称：</label>

		<div class="col-sm-2">
			<input class="form-control" value="${entity.btName }"
				name="btName" placeholder="类型名称" />
		</div>
	</div>
	

</form>
