<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<link rel="stylesheet" href="lib/my/myTable.css">
</head>
</html>
<%@include file="../../admin/common/taglibs.jsp"%>
<table class="table table-bordered table-hover table-striped"
	style="table-layout: fixed;width: 95%">
	<thead>
		<tr class="active">
			<th>#</th>
			<th>小图</th>
			<th>排序</th>
			<th>上传时间</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${pageInfo.list}" var="list" varStatus="status">
			<tr>
				<td>${(pageInfo.pageNum-1)*pageInfo.pageSize+status.count}</td>
				<td><a onClick="javascript:return false" class="preview" href="${list.bannerPath }" title="轮播图">
						<img src="${list.bannerPath }" width="100px" height="50px">
				</a></td>
				<td>${list.bannerSort }</td>
				<td><fmt:formatDate value="${list.bannerCreateTime }"
						pattern="yyyy-MM-dd HH:mm:ss" /></td>
				<td><a class="btn btn-primary btn-sm"
					onclick="showEdit('${list.bannerId}')">编辑</a>
					<button class="btn btn-danger btn-sm"
						onclick="deletedById('${list.bannerId}')">删除</button></td>
			</tr>
		</c:forEach>

	</tbody>
</table>
<script type="text/javascript">
	$(function() {
		$("a.preview").preview();
	});
</script>
