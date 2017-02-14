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
<script type="text/javascript" src="lib/jsp/js/banner.js"></script>
</head>

<body>
	<div id="wrapper">
		<%@include file="../common/meta.jsp"%>
		<%@include file="../common/top.jsp"%>

		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">app反馈问题</h1>
				</div>
				<!-- /.col-lg-12 -->
			</div>
			<!-- /.row -->
			<div class="row">
				<div class="col-lg-12">
					<div
						class="easyui-panel panel-body panel-body-noheader panel-body-noborder panel panel-default width100"
						style="width: 95%">

						<div class="op-container clearfix">
							<div class="pull-left">
								<ul id="myTab" class="nav nav-tabs">
									<li id="tab" class="active" data-toggle="tab"><a
										href="#all" onclick="one()">用户反馈列表</a></li>
									<li id="tab" data-toggle="tab"><a href="#all"
										onclick="two()">app问题帮助</a></li>
									<li id="tab" data-toggle="tab"><a href="#all"
										onclick="req()">反馈回复列表</a></li>

								</ul>
							</div>
							<button class="btn btn-info pull-right btnApp"
								onclick="addEntity('admin/feedback/showAdd','admin/feedback/add','添加App问题帮助');"
								disabled="disabled">添加</button>
						</div>
						<div id="myTabContent" class="tab-content">
							<div id="all" class="tab-pane panel fade in active"
								style="display: block;">
								<div class="panel-body easyui-panel width100" id="table"
									data-options="border:false,href:'${pageContext.request.contextPath}/admin/feedback/getTableView?pageNum=1&pageSize=10&status=1'">
								</div>

								<ul id="pagination" class="pagination">

								</ul>

							</div>

						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	var pagination;
	var status = 1;
	$(function() {
		// String startTime, Integer status
		//$("#f6").addClass("active");
		//$("#f61").addClass("active");
		pagination = $('#pagination').pagination(
				{
					total : 0,
					pageSize : 5,
					pageList : [ 5, 10 ],
					displayMsg : '正在显示 {from} 到 {to}, 共  {total} 条',
					onSelectPage : function(pageIndex, pageSize) {
						$('#table').panel(
								'refresh',
								'${pageContext.request.contextPath}/admin/feedback/getTableView?pageNum='
										+ pageIndex + '&pageSize=' + pageSize);
						currentPage = pageIndex;
						updatePagination(pageIndex, pageSize);
					},
					onBeforeRefresh : function(pageIndex, pageSize) {
						pageIndex = $(".pagination-num").val();
						$('#table').panel(
								'refresh',
								'${pageContext.request.contextPath}/admin/feedback/getTableView?pageNum='
										+ pageIndex + '&pageSize=' + pageSize);
						currentPage = pageIndex;
						updatePagination(pageIndex, pageSize);
						return false;
					}
				});
		updatePagination(1, 10);

	});

	updatePagination = function(pageNum, pageSize) {
		$
				.ajax({
					url : '${pageContext.request.contextPath}/admin/feedback/getTablePage',
					data : {
						pageNum : pageNum,
						pageSize : pageSize,
						status : status
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

	refalshList = function() {
		$('#table').panel(
				'refresh',
				'${pageContext.request.contextPath}/admin/feedback/getTableView?pageNum='
						+ 1 + '&pageSize=' + 10 + '&status=' + status);

	}

	one = function() {
		$('.btnApp').attr('disabled', 'disabled')
		status = 1;
		refalshList();
		updatePagination(1, 10);
	}

	two = function() {
		$('.btnApp').removeAttr('disabled')
		status = 2;
		refalshList();
		updatePagination(1, 10);
	}

	req = function() {
		$('.btnApp').attr('disabled', 'disabled')
		status = 0;
		refalshList();
		updatePagination(1, 10);
	}
</script>
</html>
