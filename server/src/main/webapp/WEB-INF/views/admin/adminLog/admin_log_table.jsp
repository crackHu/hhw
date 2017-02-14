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
			<th>账号</th>
			<th>ip</th>
			<th>操作系统</th>
			<th>浏览器</th>
			<th>操作内容</th>
			<th>操作时间</th>

		</tr>
	</thead>
	<tbody>
		<c:forEach items="${pageInfo.list}" var="list" varStatus="status">
			<tr>
				<td>${(pageInfo.pageNum-1)*pageInfo.pageSize+status.count}</td>
				<td>${list.aLName }</td>
				<td>${list.aLIp }</td>
				<td>${list.aLSystem }</td>
				<td>${list.aLBho }</td>
				<td>${list.aLContent }</td>

				<td><fmt:formatDate value="${list.aLCreateTime }"
						pattern="yyyy-MM-dd HH:mm:ss" /></td>

			</tr>
		</c:forEach>

	</tbody>
</table>
