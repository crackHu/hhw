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
			<th>停车场名称</th>
			<th>停车场详细</th>
			<th>是否启用</th>
			<th>车位总数</th>
			<th>剩余车位数量</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${pageInfo.data.content}" var="list" varStatus="status">
			<tr>
				<td>${(pageInfo.data.number)*pageInfo.data.size+status.count}</td>
				<td>${list.otName }</td> 
				<td>${list.otDescription }</td> 
				<td>${list.otIsEnabled } 
				</td> 
				<td>${list.otReleaseRes }</td> 
				<td>${list.otFreeRes }</td> 
				<td><a class="btn btn-info btn-sm" onclick="editEntity('v2/admin/other/otherparksite/showEdit','v2/admin/other/otherparksite/update','查看-编辑','${list.otId}')">查看</a>
					</td>
			</tr>
		</c:forEach>

	</tbody>
</table>





