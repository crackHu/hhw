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

		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">艺人类型</h1>
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
								<ul class="nav nav-tabs">
									<li id="tab" class="active"><a href="javascript:void(0);"
										onclick="">类型列表</a></li>
									<li id="tab"><a href="javascript:void(0);" onclick=""></a></li>

								</ul>
							</div>
							<button class="btn btn-info pull-right"
								onclick="addEntity('admin/artistType/showAdd','admin/artistType/add','添加艺人类型');">添加</button>
						</div>
						<div class="panel" style="display: block;">
							<div class="panel-body easyui-panel width100" id="table"
								data-options="border:false,href:'${pageContext.request.contextPath}/admin/artistType/getTableView?pageNum=1&pageSize=10'">
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
<script type="text/javascript">
	var pagination;

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
								'${pageContext.request.contextPath}/admin/artistType/getTableView?pageNum='
										+ pageIndex + '&pageSize=' + pageSize);
						currentPage = pageIndex;
						updatePagination(pageIndex, pageSize);
					},
					onBeforeRefresh : function(pageIndex, pageSize) {
						pageIndex = $(".pagination-num").val();
						$('#table').panel(
								'refresh',
								'${pageContext.request.contextPath}/admin/artistType/getTableView?pageNum='
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
					url : '${pageContext.request.contextPath}/admin/artistType/getTablePage',
					data : {
						pageNum : pageNum,
						pageSize : pageSize,
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
</script>
</html>
