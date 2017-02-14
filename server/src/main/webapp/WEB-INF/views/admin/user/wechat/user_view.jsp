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

<title>候好位总后台</title>
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
<%@include file="../../common/metaAll2.jsp"%>
<script type="text/javascript" src="lib/jsp/js/banner.js"></script>
</head>

<body>
	<div id="wrapper">
		<%@include file="../../common/meta.jsp"%>
		<%@include file="../../common/top.jsp"%>

		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">用户管理</h1>
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
									<li class="active"><a href="#all" data-toggle="tab"
										>微信用户</a></li>

								</ul>
							</div>

							<div class="pull-right col-sm-2">

								<input id="search" name="search" type="text"
									class="form-control" placeholder="你想看什么？" onkeyup="change()">
							</div>

						</div>
						<div id="myTabContent" class="tab-content">
							<div id="all" class="tab-pane panel fade in active">
								<div class="panel-body easyui-panel width100" id="table"
									data-options="border:false,href:'${pageContext.request.contextPath}/v2/admin/user/getTableView?page=0&size=10'">
								</div>

							</div>
							<ul id="pagination" class="pagination"></ul>
						</div>


					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	var pagination;
	var search = null;
	
	
	
	$(function() {

		pagination = $('#pagination')
				.pagination(
						{
							total : 0,
							pageSize : 10,
							pageList : [ 5, 10 ],
							displayMsg : '正在显示 {from} 到 {to}, 共  {total} 条',
							onSelectPage : function(pageIndex, pageSize) {
								
								$('#table')
										.panel(
												'refresh',
												'${pageContext.request.contextPath}/v2/admin/user/getTableView?page='
														+ (pageIndex-1)
														+ '&size='
														+ pageSize
														);
								alert(pageIndex+" " + pageSize);
								
								updatePagination(pageIndex, pageSize);
								
								return;
							},
							onBeforeRefresh : function(pageIndex, pageSize) {
								pageIndex = $(".pagination-num").val();
								$('#table')
										.panel(
												'refresh',
												'${pageContext.request.contextPath}/v2/admin/user/getTableView?page='
														+ (pageIndex-1)
														+ '&size='
														+ pageSize
														);
								currentPage = pageIndex;
								updatePagination(pageIndex, pageSize);
								return;
							}
						});
		
		updatePagination(1, 10);

	});

	updatePagination = function(pageNum, pageSize) {
		$
				.ajax({
					url : '${pageContext.request.contextPath}/v2/admin/user/getTablePage',
					data : {
						page : pageNum,
						size : pageSize,
					},
					dataType : 'json',
					success : function(r) {
						pagination.pagination('refresh', {
							total : r.totalElements,
							pageNumber : r.number,
							pageSize : r.size
						});
					}
				});
	};

</script>
</html>
