<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<link rel="stylesheet" href="lib/my/myTable.css">
</head>
</html>
<%@include file="../../../admin/common/taglibs.jsp"%>
<table class="table table-bordered table-hover table-striped"
	style="table-layout: fixed;width: 95%">
	<thead>
		<tr class="active">
			<th>#</th>
			<th>账号</th>
			<th>用戶名</th>
			<th>创建时间</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${pageInfo.list}" var="list" varStatus="status">
			<tr>
				<td>${(pageInfo.pageNum-1)*pageInfo.pageSize+status.count}</td>
				<td>${list.uPhone }</td>
				<td>${list.uName }</td>
				<td><fmt:formatDate value="${list.uCreateTime }"
						pattern="yyyy-MM-dd HH:mm:ss" /></td>
				<td><a class="btn btn-primary btn-sm"
					onclick="editEntity('admin/adminUser/showEdit','admin/adminUser/editEntity','修改名称','${list.uId}')">编辑</a>
				</td>
			</tr>
		</c:forEach>

	</tbody>
</table>
