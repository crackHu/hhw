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
			<th>订单号</th>
			<th>商家</th>
			<th>艺人</th>
			<th>档期</th>
			<th>职业</th>
			<th>演出费</th>
			<th>状态</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${pageInfo.list}" var="list" varStatus="status">
			<tr>
				<td>${(pageInfo.pageNum-1)*pageInfo.pageSize+status.count}</td>
				<td>${list.oNumber }</td>
				<td>${list.oBName }</td>
				<td>${list.oAName }</td>
				<td><fmt:formatDate value="${list.oStartTime }"
						pattern="yyyy-MM-dd" /> 至 <fmt:formatDate
						value="${list.oEndTime }" pattern="yyyy-MM-dd" /></td>
				<td>${list.oDType }</td>
				<td>${list.oMoney }</td>
				<td><c:if test="${list.oStatus==-1}">预约中</c:if> 
					<c:if test="${list.oStatus==0}">待商家付定金</c:if>
				<c:if
						test="${list.oStatus==1}">待商家付全款</c:if> <c:if
						test="${list.oStatus==2}">已付全款</c:if> <c:if
						test="${list.oStatus==3}">取消</c:if> <c:if
						test="${list.oStatus==4}">拒绝</c:if> </td>
			</tr>
		</c:forEach>

	</tbody>
</table>
