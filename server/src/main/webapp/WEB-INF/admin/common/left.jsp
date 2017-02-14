<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="sidebar" id="sidebar">
	<script type="text/javascript">
		try {
			ace.settings.check('sidebar', 'fixed')
		} catch (e) {
		}
	</script>

	<div class="sidebar-shortcuts" id="sidebar-shortcuts">
		<div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
			<button class="btn btn-success">
				<i class="icon-signal"></i>
			</button>

			<button class="btn btn-info">
				<i class="icon-pencil"></i>
			</button>

			<button class="btn btn-warning">
				<i class="icon-group"></i>
			</button>

			<button class="btn btn-danger">
				<i class="icon-cogs"></i>
			</button>
		</div>

		<div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
			<span class="btn btn-success"></span> <span class="btn btn-info"></span>

			<span class="btn btn-warning"></span> <span class="btn btn-danger"></span>
		</div>
	</div>
	<!-- #sidebar-shortcuts -->

	<ul class="nav nav-list">
		<li class="active"><a href="index.html"> <i
				class="icon-dashboard"></i> <span class="menu-text"> 控制台 </span>
		</a></li>

		<li><a href="typography.html"> <i class="icon-text-width"></i>
				<span class="menu-text"> 文字排版 </span>
		</a></li>







		<li id="plug"><a href="#" class="dropdown-toggle"> <i
				class="icon-list-alt"></i> <span class="menu-text"> 插件 </span> <b
				class="arrow icon-angle-down"></b>
		</a>
			<ul class="submenu">
				<li id="sms"><a href="#" class="dropdown-toggle"> <i
						class="icon-double-angle-right"></i> SMS <b
						class="arrow icon-angle-down"></b>
				</a>
					<ul class="submenu">
						<li id="smcpView"><a href="${pageContext.request.contextPath}/v2/admin/smscp/getView"> <i
								class="icon-double-angle-right"></i> 用户管理
						</a></li>
						<li id="smcfgView"><a href="${pageContext.request.contextPath}/v2/admin/smscfg/getView"> <i
								class="icon-double-angle-right"></i> 短信模板
						</a></li>
					</ul></li>
				<li><a href="#"> <i class="icon-double-angle-right"></i>
						添加产品
				</a></li>
			</ul></li>

		<li><a href="calendar.html"> <i class="icon-calendar"></i> <span
				class="menu-text"> 日历 <span
					class="badge badge-transparent tooltip-error"
					title="2&nbsp;Important&nbsp;Events"> <i
						class="icon-warning-sign red bigger-130"></i>
				</span>
			</span>
		</a></li>


		<li><a href="#" class="dropdown-toggle"> <i
				class="icon-file-alt"></i> <span class="menu-text"> 其他页面 <span
					class="badge badge-primary ">5</span>
			</span> <b class="arrow icon-angle-down"></b>
		</a>

			<ul class="submenu">
				<li><a href="faq.html"> <i class="icon-double-angle-right"></i>
						帮助
				</a></li>

				<li><a href="error-404.html"> <i
						class="icon-double-angle-right"></i> 404错误页面
				</a></li>

				<li><a href="error-500.html"> <i
						class="icon-double-angle-right"></i> 500错误页面
				</a></li>

				<li><a href="grid.html"> <i class="icon-double-angle-right"></i>
						网格
				</a></li>

				<li><a href="blank.html"> <i
						class="icon-double-angle-right"></i> 空白页面
				</a></li>
			</ul></li>
	</ul>
	<!-- /.nav-list -->

	<div class="sidebar-collapse" id="sidebar-collapse">
		<i class="icon-double-angle-left" data-icon1="icon-double-angle-left"
			data-icon2="icon-double-angle-right"></i>
	</div>

	<script type="text/javascript">
		try {
			ace.settings.check('sidebar', 'collapsed')
		} catch (e) {
		}
	</script>
</div>