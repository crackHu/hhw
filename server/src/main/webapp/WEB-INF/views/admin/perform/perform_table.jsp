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
			<th>艺人名</th>
			<th>城市</th>
			<th>类型</th>
			<th>价格</th>
			<th>介绍</th>
			<th>时间段</th>
			<th>发布时间</th>

		</tr>
	</thead>
	<tbody>
		<c:forEach items="${pageInfo.list}" var="list" varStatus="status">
			<tr>
				<td>${(pageInfo.pageNum-1)*pageInfo.pageSize+status.count}</td>
				<td>${list.artist.aName }</td>
				<td>${list.pfCity }</td>
				<td>${list.pfPersonTypeName }</td>
				<td>${list.pfMoney }</td>
				<td>${list.pfInfo }</td>
				<td>${list.pfBucket }</td>
				<td><fmt:formatDate value="${list.pfCreateTime }"
						pattern="yyyy-MM-dd HH:mm:ss" /></td>

			</tr>
		</c:forEach>

	</tbody>
</table>
