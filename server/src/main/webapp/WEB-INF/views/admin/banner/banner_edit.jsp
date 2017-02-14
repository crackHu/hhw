<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<%@include file="../../admin/common/metaUpload.jsp"%>

<form id="edit" action="admin/banner/edit" class="form-horizontal"
	method="post" enctype="multipart/form-data">
	<input name="bannerId" value="${entity.bannerId }" hidden>
	<div class="form-group">
		<label class="col-sm-2 control-label">图片展示：</label>

		<div class="col-sm-6">
			<img id="preview" width="500px" height="250px"
				src="${entity.bannerPath }">
		</div>
	</div>
	<div class="form-group">
		<label for="firstname" class="col-sm-2 control-label">图片上传：</label>
		<div class="col-sm-6">
			<span class="btn btn-success fileinput-button"> <span>图片上传</span>
				<input type="file" name="files[]" id="banner"
				onchange="setImages('banner','preview','')">

			</span>
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">排序(从小到大)：</label>

		<div class="col-sm-2">
			<input class="form-control" value="${entity.bannerSort }"
				name="bannerSort" placeholder="只能是数字" />
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">链接：</label>

		<div class="col-sm-5">
			<input class="form-control" value="${entity.bannerUrl }"
				name="bannerUrl" placeholder="链接" />
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">信息内容：</label>
		<div class="col-sm-7">
			<textarea rows="5" cols="15" name="bannerInfo" class="form-control">${entity.bannerInfo }</textarea>
		</div>
	</div>

</form>
