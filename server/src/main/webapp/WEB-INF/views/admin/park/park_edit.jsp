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
	<input name="otId" value="${entity.data.otId }" hidden>
	
	
	<div class="form-group">
		<label class="col-sm-2 control-label">地址名称：</label>

		<div class="col-sm-2">
			<input class="form-control" value="${entity.data.otName }"
				name="otName" placeholder="地址名称" />
		</div>
	</div>
	
	<div class="form-group">
		<label class="col-sm-2 control-label">停车场信息：</label>

		<div class="col-sm-5">
			<input class="form-control" value="${entity.data.otDescription }"
				name="otDescription" placeholder="停车场信息" />
		</div>
	</div>
	
	
	<div class="form-group">
		<label class="col-sm-2 control-label">详细地址：</label>

		<div class="col-sm-5">
			<input class="form-control" value="${entity.data.otAddress }"
				name="otAddress" placeholder="详细地址" />
		</div>
	</div>
	
	<div class="form-group">
		<label class="col-sm-2 control-label">是否启用：</label>

		<div class="col-sm-2">
			<input class="form-control" value="${entity.data.otIsEnabled }"
				name="otIsEnabled" placeholder="是否启用" />
		</div>
	</div>
	
	<div class="form-group">
		<label class="col-sm-2 control-label">x坐标：</label>

		<div class="col-sm-2">
			<input class="form-control" value="${entity.data.otPointX }"
				name="otOointX" placeholder="x坐标" />
		</div>
	</div>
	
	<div class="form-group">
		<label class="col-sm-2 control-label">y坐标：</label>

		<div class="col-sm-2">
			<input class="form-control" value="${entity.data.otPointY }"
				name="otOointY" placeholder="y坐标" />
		</div>
	</div>
	
	
	<div class="form-group">
		<label class="col-sm-2 control-label">车位总数：</label>

		<div class="col-sm-2">
			<input class="form-control" value="${entity.data.otReleaseRes }"
				name="otReleaseRes" placeholder="车位总数" />
		</div>
	</div>
	
	
	<div class="form-group">
		<label class="col-sm-2 control-label">剩余车位数：</label>

		<div class="col-sm-2">
			<input class="form-control" value="${entity.data.otFreeRes }"
				name="otFreeRes" placeholder="剩余车位数" />
		</div>
	</div>
	
	
	<div class="form-group">
		<label class="col-sm-2 control-label">创建时间：</label>
		
		<div class="col-sm-5">
			<fmt:formatDate value="${entity.data.otCreateDate }"
							pattern="yyyy-MM-dd HH:mm:ss" />
		</div>
	</div>
	

</form>
