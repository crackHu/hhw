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
			<th>名称</th>
			<th>代码</th>
			<th>排序</th>
			<th>创建时间</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${pageInfo.list}" var="list" varStatus="status">
			<tr>
				<td>${(pageInfo.pageNum-1)*pageInfo.pageSize+status.count}</td>
				<td>${list.aTName }</td>
				<td>${list.aTCode }</td>
				<td>${list.aTOrderBy }</td>
				<td><fmt:formatDate value="${list.aTCreateTime }"
						pattern="yyyy-MM-dd HH:mm:ss" /></td>
				<td><a class="btn btn-primary btn-sm"
					onclick="editEntity('admin/artistType/showEdit','admin/artistType/edit','修改艺人类型','${list.aTId}')">编辑</a>
					<button class="btn btn-danger btn-sm"
						onclick="deletedEntity('admin/artistType/deletedById','${list.aTId}')">删除</button></td>
			</tr>
		</c:forEach>

	</tbody>
</table>
