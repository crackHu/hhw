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
			<th>交易单号</th>
			<th>订单单号</th>
			<th>交易内容</th>
			<th>交易人信息</th>
			<th>交易类型</th>
			<th>支付方式</th>
			<th>交易金额</th>
			<th>交易时间</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${pageInfo.list}" var="list" varStatus="status">
			<tr>
				<td>${(pageInfo.pageNum-1)*pageInfo.pageSize+status.count}</td>
				<td>${list.trNum }</td>
				<td>${list.trMerchantNum }</td>

				<td>${list.trName}</td>
				<td>
				
				<c:if test="${list.trMerchantName==null }">
					<a href="javascript:void(0)" onclick="checkInfo('${list.trWId}')">
					保密			
					</a>	
				</c:if>
					<a href="javascript:void(0)" onclick="checkInfo('${list.trWId}')">
					${list.trMerchantName}
					</a>	
				</td>
				
				<td>
				<c:if test="${list.trType==1 }">
					收入
				</c:if>
				<c:if test="${list.trType==2 }">
					支出
				</c:if>
				</td>
				<td>${list.trPayType }</td>
				<td>
				<c:if test="${list.trType==1 }">
					<font style="color: green;">	+${list.trPrice }</font>
				</c:if>
				<c:if test="${list.trType==2 }">
					<font style="color: red;">	-${list.trPrice }</font>
				</c:if>
				</td>
				<td><fmt:formatDate value="${list.trTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			</tr>
		</c:forEach>

	</tbody>
</table>

<script>
checkInfo = function(wId){
$.ajax({
	url:'admin/trecord/getTrPersonInfo',
	data:{wId:wId},
	dataType:'json',
	success:function(data){
		
		swal({title:"用户信息",text:data.message,html:true});
	}
});
}
</script>
