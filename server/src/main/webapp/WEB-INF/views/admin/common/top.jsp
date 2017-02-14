<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<style>
.arrow {
	display: inline-block;
	width: 12px;
}

.text-al {
	text-indent: 28px;
}

.sidebar .arrow:before {
	content: "" !important;
	background: url('images/icon/right.png') no-repeat;
	background-size: contain;
	display: inline-block;
	width: 20px;
	height: 20px;
}

.sidebar .active>a>.fa.arrow:before {
	content: "" !important;
	background: url('images/icon/down.png') no-repeat;
	background-size: contain;
	display: inline-block;
	width: 20px;
	height: 20px;
}
</style>
</head>
<!-- Navigation -->
<nav class="navbar navbar-default navbar-static-top" role="navigation"
	style="margin-bottom: 0">
	<div class="navbar-header">
		<button type="button" class="navbar-toggle" data-toggle="collapse"
			data-target=".navbar-collapse">
			<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span>
			<span class="icon-bar"></span> <span class="icon-bar"></span>
		</button>
		<a class="navbar-brand" href="admin/index">候好位总后台</a>
	</div>
	<!-- /.navbar-header -->

	<ul class="nav navbar-top-links navbar-right">
		<li class="dropdown"><a class="dropdown-toggle"
			data-toggle="dropdown" href="#"> <i class="fa icon-envelope"></i>
				<i class="fa icon-caret-down"></i>
		</a>
			<ul class="dropdown-menu dropdown-messages" id="newFeedBack">

			</ul> <!-- /.dropdown-messages --></li>

		<li class="dropdown"><a class="dropdown-toggle"
			data-toggle="dropdown" href="#"> <i class="fa icon-bell"></i> <i
				class="fa icon-caret-down"></i>
		</a>
			<ul class="dropdown-menu dropdown-alerts">
				<li><a href="javascript:void(0)">
						<div>
							<i class="fa icon-comment"></i> New Comment <span
								class="pull-right text-muted small">4 minutes ago</span>
						</div>
				</a></li>
				<li class="divider"></li>
				<li><a href="javascript:void(0)">
						<div>
							<i class="fa icon-twitter"></i> 3 New Followers <span
								class="pull-right text-muted small">12 minutes ago</span>
						</div>
				</a></li>
				<li class="divider"></li>
				<li><a href="javascript:void(0)">
						<div>
							<i class="fa icon-envelope"></i> Message Sent <span
								class="pull-right text-muted small">4 minutes ago</span>
						</div>
				</a></li>
				<li class="divider"></li>
				<li><a href="javascript:void(0)">
						<div>
							<i class="fa icon-tasks"></i> New Task <span
								class="pull-right text-muted small">4 minutes ago</span>
						</div>
				</a></li>
				<li class="divider"></li>
				<li><a href="javascript:void(0)">
						<div>
							<i class="fa icon-upload"></i> Server Rebooted <span
								class="pull-right text-muted small">4 minutes ago</span>
						</div>
				</a></li>
				<li class="divider"></li>
				<li><a class="text-center" href="#"> <strong>See
							All Alerts</strong> <i class="fa icon-angle-right"></i>
				</a></li>
			</ul> <!-- /.dropdown-alerts --></li>
		<!-- /.dropdown -->
		<li class="dropdown"><a class="dropdown-toggle"
			data-toggle="dropdown" href="#"> <i class="fa icon-user"></i> <i
				class="fa icon-caret-down"></i>
		</a>
			<ul class="dropdown-menu dropdown-user">
				<li><a href="javascript:void(0)"
					onclick="editEntity('admin/adminUser/showMyEdit', 'admin/adminUser/editEntity', '修改资料', '')"><i
						class="fa icon-user" id="userName"></i> </a></li>
				<li><a href="javascript:void(0)"
					onclick="editEntity('admin/adminUser/showPasswordEdit', 'admin/adminUser/updataPassowrd', '修改密码', '00')"><i
						class="fa icon-gear"></i> 修改密码</a></li>
				<li class="divider"></li>
				<li><a href="logout"><i class="fa icon-signout"></i> 退出登录</a></li>
			</ul> <!-- /.dropdown-user --></li>
		<!-- /.dropdown -->
	</ul>
	<!-- /.navbar-top-links -->

	<div class="navbar-default sidebar" role="navigation">
		<div class="sidebar-nav navbar-collapse">
			<ul class="nav" id="side-menu">
				<li class="sidebar-search">
					<!--<div class="input-group custom-search-form">
						<input type="text" class="form-control" placeholder="Search...">
						<span class="input-group-btn">
							<button class="btn btn-default" type="button">
								<i class="fa icon-search"></i>
							</button>
						</span>
					</div> <!-- /input-group -->
				</li>
				<li><a href="admin/index"><i class="fa icon-dashboard"></i>
						近期数据</a></li>
				<li><a href="admin/banner/getView"><i
						class="fa icon-picture"></i> 广告管理</a></li>
				<li><a href="javascript:void(0)"><i
						class="fa icon-github-alt"></i> 用户管理<span class="fa arrow"></span></a>
					<ul class="nav nav-second-level">
						<li><a href="v2/admin/user/getView?type=1"> 用户</a></li>
						<li><a href="admin/user/getView?type=2"> 管理员</a></li>
						
					</ul></li>
				<li><a href="${pageContext.request.contextPath}/v2/admin/other/otherparksite/getView"><i
						class="fa icon-info-sign"></i>&nbsp;停车场</a></li>
				<li><a href="javascript:void(0)"><i class="fa icon-tags"></i>
						基础数据<span class="fa arrow"></span></a>
					<ul class="nav nav-second-level">
						<li><a href="admin/artistType/getView"><i></i> 利率设置</a></li>
					</ul></li>

				<li><a href="javascript:void(0)"><i
						class="fa icon-shopping-cart"></i> 订单管理<span class="fa arrow"></span></a>
					<ul class="nav nav-second-level">
						<li><a href="v2/admin/order/getView">全部</a></li>
						<li><a href="admin/order/getView?status=-1">未支付</a></li>
						<li><a href="admin/order/getView?status=0">进行中</a></li>
						<li><a href="admin/order/getView?status=2">已完成</a></li>
					</ul> <!-- /.nav-second-level --></li>

				<li><a href="admin/adminUser/getView"><i
						class="fa icon-user"></i>&nbsp;管理员管理</a></li>
				
				<!--  
				<li><a href="javascript:void(0)"><i class="fa icon-sitemap"></i> Multi-Level
						Dropdown<span class="fa arrow"></span></a>
					<ul class="nav nav-second-level">
						<li><a href="javascript:void(0)">Second Level Item</a></li>
						<li><a href="javascript:void(0)">Second Level Item</a></li>
						<li><a href="javascript:void(0)">Third Level <span class="fa arrow"></span></a>
							<ul class="nav nav-third-level">
								<li><a href="javascript:void(0)">Third Level Item</a></li>
								<li><a href="javascript:void(0)">Third Level Item</a></li>
								<li><a href="javascript:void(0)">Third Level Item</a></li>
								<li><a href="javascript:void(0)">Third Level Item</a></li>
							</ul> </li>
					</ul> </li>
					-->
				<!-- /.nav-third-level -->
				<!-- /.nav-second-level -->
				<li><a href="admin/adminLog/getView"><i
						class="fa icon-cogs"></i> 系统日志</a>
			</ul>
		</div>
		<!-- /.sidebar-collapse -->
	</div>
	<!-- /.navbar-static-side -->
</nav>
<!-- 
<script type="text/javascript">
	$.ajax({
		url : 'admin/adminUser/getUser',
		dataType : 'json',
		success : function(r) {
			$("#userName").html(' ' + r.user.uName);
		}
	});
	
	

	$
			.ajax({
				url : 'admin/feedback/getNewFeedBack',
				dataType : 'json',
				success : function(r) {
					var html = '';
					$
							.each(
									r.pageInfo.list,
									function(index, obj) {
										html += "<li><a href='javascript:void(0)' onclick=\"clickMessage('"
												+ obj.fbId
												+ "')\"><div><strong>"
												+ obj.user.uName
												+ "</strong> <span class='pull-right text-muted'><em>"
												+ obj.fbTitle
												+ "</em></span>"
												+ "</div><div class='text-al'>"
												+ obj.fbContext
												+ "</div></a></li>";
									})
					html += "<li class='divider'></li><li><a class='text-center' href='admin/feedback/getView'> <strong>Read All Messages</strong> <i class='fa icon-angle-right'></i></a></li>"

					$("#newFeedBack").empty();
					$("#newFeedBack").append(html);
					
					
				}
			});

	clickMessage = function(id) {
		editEntity('admin/feedback/showReply', 'admin/feedback/reply', '回复', id);
	}
	
</script>
 -->