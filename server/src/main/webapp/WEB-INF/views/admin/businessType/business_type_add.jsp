<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@include file="../../admin/common/metaUpload.jsp"%>

<form id="add" class="form-horizontal" method="post">

	<div class="form-group">
		<label class="col-sm-2 control-label">商户类型名称：</label>

		<div class="col-sm-2">
			<input class="form-control" value="${entity.btName }" name="btName"
				placeholder="类型名称" />
		</div>
	</div>

</form>
<script>
	
</script>