<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>演艺总后台</title>
<meta charset="utf-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->


<link rel="stylesheet" href="lib/my/my.css">
<%@include file="../common/metaAll2.jsp"%>

<script type="text/javascript"
	src="lib/jsp/js/banner.js"></script>


</head>

<body>
	<div id="wrapper">
		<%@include file="../common/meta.jsp"%>
		<%@include file="../common/top.jsp"%>
		<%@include file="../common/taglibs.jsp"%>
		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">财务报表</h1>
				</div>
				<!-- /.col-lg-12 -->
				<c:if test="${type==5 }">
				<div class="col-lg-8">
					<font color="red" style="font-size: 30px"> 总后台钱包余额：${money }<br /></font>
				</div>
				</c:if>
			</div>
			<!-- /.row -->
			<div class="row">
				<div class="col-lg-12">
					<div
						class="easyui-panel panel-body panel-body-noheader panel-body-noborder panel panel-default width100"
						style="width: 95%">

						<div class="op-container clearfix">
							<div class="pull-left">
								<ul class="nav nav-tabs">
									<li id="tab" class="active"><a href="javascript:void(0);"
										onclick="">交易列表</a></li>
									<li id="tab"><a href="javascript:void(0);" onclick=""></a></li>

								</ul>




							</div>
							<br />
							<div class="col-sm-4">
								开始时间：<input type="text" id="J-xl-start" name="j-xl"
									readonly="readonly">
							</div>
							<div class="col-sm-4">
								结束时间：<input type="text" id="J-xl-end" name="j-xl"
									readonly="readonly">
							</div>
							<div class="col-sm-2">
								<button  class="btn btn-primary btn-sm"
									onclick="search2()">查询</button>
							</div>

							<div class="pull-right col-sm-2">

								<input id="search" name="search" type="text"
									class="form-control" placeholder="你想看什么？" onkeyup="change()">
							</div>
						</div>
						<div class="panel" style="display: block;">
							<div class="panel-body easyui-panel width100" id="table"
								data-options="border:false,href:'${pageContext.request.contextPath}/admin/trecord/getTableView?pageNum=1&pageSize=10&type=${type }'">
							</div>

							<ul id="pagination" class="pagination">

							</ul>

						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/lib/dataTime/laydate.dev.js"></script>
<script type="text/javascript">
	var pagination;
	var startTime;
	var endTime;
	var search = null;
	$(function() {
		// String startTime, Integer status
		//$("#f6").addClass("active");
		//$("#f61").addClass("active");
		pagination = $('#pagination').pagination(
				{
					total : 0,
					pageSize : 5,
					pageList : [ 5, 10, 20 ],
					displayMsg : '正在显示 {from} 到 {to}, 共  {total} 条',
					onSelectPage : function(pageIndex, pageSize) {
						Setting();
						$('#table').panel(
								'refresh',
								'${pageContext.request.contextPath}/admin/trecord/getTableView?pageNum='
										+ pageIndex + '&pageSize=' + pageSize
										+ '&type=' + ${type} + '&startTime='
										+ startTime + '&endTime=' + endTime + '&search='+search);
						currentPage = pageIndex;
						updatePagination(pageIndex, pageSize);
					},
					onBeforeRefresh : function(pageIndex, pageSize) {
						pageIndex = $(".pagination-num").val();

						Setting();
						$('#table').panel(
								'refresh',
								'${pageContext.request.contextPath}/admin/trecord/getTableView?pageNum='
										+ pageIndex + '&pageSize=' + pageSize
										+ '&type=' + ${type} + '&startTime='
										+ startTime + '&endTime=' + endTime + '&search='+search);
						currentPage = pageIndex;
						updatePagination(pageIndex, pageSize);
						return false;
					}
				});
		updatePagination(1, 10);

	});

	updatePagination = function(pageNum, pageSize) {
		Setting();
		$
				.ajax({
					url : '${pageContext.request.contextPath}/admin/trecord/getTablePage',
					data : {
						pageNum : pageNum,
						pageSize : pageSize,
						type : ${type},
						startTime : startTime,
						endTime : endTime,
						search : search
					},
					dataType : 'json',
					success : function(r) {
						pagination.pagination('refresh', {
							total : r.total,
							pageNumber : r.pageNum,
							pageSize : r.pageSize
						});
					}
				});
	};

	laydate({

		elem : '#J-xl-start',
	

	});

	/*laydate({

		elem : '#J-xl-end'

	});*/

	Setting = function() {
		search = $("#search").val();
		startTime = $('#J-xl-start').val();
		endTime = $('#J-xl-end').val();

	}
	 $('#J-xl-start').on('blur',function(){
	 	$('#J-xl-end').val('')
	 })
	 $('#J-xl-end').on('focus',function(){
	 if(!$('#J-xl-start').val()){
	 	alert('请先选择开始日期')
	 }else{
	 	laydate({
			elem : '#J-xl-end',
			min:$('#J-xl-start').val()
		})
	 }
	 	
	 })




	search2 = function() {
		Setting();
		$('#table').panel(
				'refresh',
				'${pageContext.request.contextPath}/admin/trecord/getTableView?pageNum='
						+ 1 + '&pageSize=' + 10 + '&type=' + ${type}
						+ '&startTime=' + startTime + '&endTime=' + endTime + '&search='+search);
		updatePagination(1, 10);

	}
	
	change = function (){
	
		Setting();
		$('#table').panel(
				'refresh',
				'${pageContext.request.contextPath}/admin/trecord/getTableView?pageNum='
						+ 1 + '&pageSize=' + 10 + '&type=' + ${type}
						+ '&startTime=' + startTime + '&endTime=' + endTime + '&search='+search);
		updatePagination(1, 10);
	}
</script>
</html>
