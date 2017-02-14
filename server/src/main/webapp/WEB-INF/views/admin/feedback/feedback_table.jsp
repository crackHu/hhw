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
			<th><c:if test="${type==2 }">
			标题
			</c:if> <c:if test="${type==0 }">
			AppVersion
			</c:if> <c:if test="${type==1 }">
			系统
			</c:if></th>
			<th>内容</th>
			<c:if test="${type==0 || type==1 }">
				<th>用户</th>
			</c:if>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${pageInfo.list}" var="list" varStatus="status">
			<tr>
				<td>${(pageInfo.pageNum-1)*pageInfo.pageSize+status.count}</td>
				<td>${list.fbTitle }</td>
				<td>${list.fbContext }</td>
				<c:if test="${type==0 || type==1 }">
					<td>${list.user.uName }</td>
				</c:if>
				<td><c:if test="${type==2 }">
						<a class="btn btn-primary btn-sm"
							onclick="editEntity('admin/feedback/showEdit','admin/feedback/edit','修改app帮助内容','${list.fbId}')">编辑</a>
					</c:if> <c:if test="${type==1 }">
						<a class="btn btn-primary btn-sm"
							onclick="editEntity('admin/feedback/showReply','admin/feedback/reply','回复','${list.fbId}')">回复</a>
					</c:if>
					<button class="btn btn-danger btn-sm"
						onclick="deletedEntity('admin/feedback/deletedById','${list.fbId}')">删除</button></td>
			</tr>
		</c:forEach>

	</tbody>
</table>
