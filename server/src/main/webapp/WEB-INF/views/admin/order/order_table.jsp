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
			<th>订单单号</th>
			<th>订单状态</th>
			<th>车牌号</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${pageInfo.data.content}" var="list" varStatus="status">
			<tr>
				<td>${(pageInfo.data.number)*pageInfo.data.size+status.count}</td>
				<td>${list.odBarcode }</td> 
				<td>${list.odStatus }</td> 
				<td>${list.odCarNum }</td> 
				<td><a class="btn btn-info btn-sm" onclick="editEntity('v2/admin/order/showEdit','','查看','${list.odId}')">查看</a>
					</td>
			</tr>
		</c:forEach>

	</tbody>
</table>





