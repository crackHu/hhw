<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<%@include file="../../admin/common/metaUpload.jsp"%>
<%@include file="../../admin/common/taglibs.jsp"%>
<form id="edit" class="form-horizontal"
	method="post" enctype="multipart/form-data">
	<input name="odId" value="${entity.data.odId }" hidden>
	
	
	<div class="form-group">
		<label class="col-sm-2 control-label">订单号：</label>

		<div class="col-sm-3">
			<input class="form-control" value="${entity.data.odBarcode }"
				name="odBarcode" placeholder="地址名称" />
		</div>
	</div>
	
	
	
	
	

</form>
