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
			<th>用户昵称</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${pageInfo.content}" var="list" varStatus="status">
			<tr>
				<td>${(pageInfo.number)*pageInfo.size+status.count}</td>
				<td>${list.usName }</td> 
				<td><a class="btn btn-info btn-sm" onclick="editEntity('v2/admin/user/showEdit','','查看','${list.usId}')">查看</a>
					</td>
			</tr>
		</c:forEach>

	</tbody>
</table>





