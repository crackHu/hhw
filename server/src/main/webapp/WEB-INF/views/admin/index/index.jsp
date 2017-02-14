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
<%@include file="../common/metaAll2.jsp"%>
<script type="text/javascript"
	src="lib/jsp/js/banner.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/lib/jsp/js/Chart.bundle.js"></script>
</head>

<body>
	<div id="wrapper">
		<jsp:include page="../common/taglibs.jsp"></jsp:include>
		<jsp:include page="../common/top.jsp"></jsp:include>

		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">近七天数据</h1>
				</div>
				<!-- /.col-lg-12 -->
			</div>
			<!-- /.row -->
			<div class="row">
				<div class="col-lg-3 col-md-6">
					<div class="panel panel-primary">
						<div class="panel-heading">
							<div class="row">
								<div class="col-xs-3">
									<i class="fa icon-comments icon-5x"></i>
								</div>
								<div class="col-xs-9 text-right">
									<div id="newArtistSize" class="huge"></div>
									<div>新的用户</div>
								</div>
							</div>
						</div>
						<a href="javascript:void(0)">
							<div class="panel-footer">
								<span id="spanNewArtistSize" class="pull-left"></span> <span
									class="pull-right"><i class="fa icon-arrow-circle-right"></i></span>
								<div class="clearfix"></div>
							</div>
						</a>
					</div>
				</div>
				<div class="col-lg-3 col-md-6">
					<div class="panel panel-green">
						<div class="panel-heading">
							<div class="row">
								<div class="col-xs-3">
									<i class="fa icon-tasks icon-5x"></i>
								</div>
								<div class="col-xs-9 text-right">
									<div id="newBusinessesSize" class="huge"></div>
									<div>新的管理员</div>
								</div>
							</div>
						</div>
						<a href="javascript:void(0)">
							<div class="panel-footer">
								<span id="spanNewBusinessesSize" class="pull-left"></span> <span
									class="pull-right"><i class="fa icon-arrow-circle-right"></i></span>
								<div class="clearfix"></div>
							</div>
						</a>
					</div>
				</div>
				<div class="col-lg-3 col-md-6">
					<div class="panel panel-yellow">
						<div class="panel-heading">
							<div class="row">
								<div class="col-xs-3">
									<i class="fa icon-shopping-cart icon-5x"></i>
								</div>
								<div class="col-xs-9 text-right">
									<div id="newOrderSize" class="huge"></div>
									<div>最新的交易</div>
								</div>
							</div>
						</div>
						<a href="javascript:void(0)">
							<div class="panel-footer">
								<span id="spanNewOrderSize" class="pull-left"></span> <span
									class="pull-right"><i class="fa icon-arrow-circle-right"></i></span>
								<div class="clearfix"></div>
							</div>
						</a>
					</div>
				</div>
				<div class="col-lg-3 col-md-6">
					<div class="panel panel-red">
						<div class="panel-heading">
							<div class="row">
								<div class="col-xs-3">
									<i class="fa icon-support icon-5x"></i>
								</div>
								<div class="col-xs-9 text-right">
									<div id="newMoney" class="huge"></div>
									<div>最新的成交金额</div>
								</div>
							</div>
						</div>
						<a href="javascript:void(0)">
							<div class="panel-footer">
								<span id="spanNewMoney" class="pull-left"></span> <span
									class="pull-right"><i class="fa icon-arrow-circle-right"></i></span>
								<div class="clearfix"></div>
							</div>
						</a>
					</div>
				</div>
			</div>
			<div class="col-lg-12 col-lg-10">
				<canvas id="canvas"></canvas>
			</div>
			<!--  
			<script>
				var MONTHS = [ "January", "February", "March", "April", "May",
						"June", "July", "August", "September", "October",
						"November", "December" ];
				var labelsJson = [ '${timeString.get(0)}',
						'${timeString.get(1)}', '${timeString.get(2)}',
						'${timeString.get(3)}', '${timeString.get(4)}',
						'${timeString.get(5)}', '${timeString.get(6)}' ]

				var totalMoneyJson = [ '${totalMoney.get(0)}',
						'${totalMoney.get(1)}', '${totalMoney.get(2)}',
						'${totalMoney.get(3)}', '${totalMoney.get(4)}',
						'${totalMoney.get(5)}', '${totalMoney.get(6)}', ]

				var incomeMoneyJson = [ '${incomeMoney.get(0)}',
						'${incomeMoney.get(1)}', '${incomeMoney.get(2)}',
						'${incomeMoney.get(3)}', '${incomeMoney.get(4)}',
						'${incomeMoney.get(5)}', '${incomeMoney.get(6)}', ]

				var expendMoneyJson = [ '${expendMoney.get(0)}',
						'${expendMoney.get(1)}', '${expendMoney.get(2)}',
						'${expendMoney.get(3)}', '${expendMoney.get(4)}',
						'${expendMoney.get(5)}', '${expendMoney.get(6)}', ]

				var randomScalingFactor = function() {
					return Math.round(Math.random() * 100);
					//return 0;
				};
				var randomColorFactor = function() {
					return Math.round(Math.random() * 255);
				};
				var randomColor = function(opacity) {
					return 'rgba(' + randomColorFactor() + ','
							+ randomColorFactor() + ',' + randomColorFactor()
							+ ',' + (opacity || '.3') + ')';
				};

				var config = {
					type : 'line',
					data : {
						labels : labelsJson,
						datasets : [ {
							label : "当天成交收益",
							data : totalMoneyJson,
							fill : false,
							borderDash : [ 5, 5 ],
						}, {
							//hidden : true,
							label : "当天平台总收入",
							data : incomeMoneyJson,
						}, {
							label : "当天平台总支出",
							data : expendMoneyJson,
						} ]
					},
					options : {
						responsive : true,
						title : {
							display : true,
							text : '交易详情'
						},
						tooltips : {
							mode : 'label',
							callbacks : {

							}
						},
						hover : {
							mode : 'dataset'
						},
						scales : {
							xAxes : [ {
								display : true,
								scaleLabel : {
									display : true,
									labelString : '时间'
								}
							} ],
							yAxes : [ {
								display : true,
								scaleLabel : {
									display : true,
									labelString : '金额(元)'
								},
								ticks : {
									suggestedMin : -50,
									suggestedMax : 250,
								}
							} ]
						}
					}
				};

				$.each(config.data.datasets, function(i, dataset) {
					dataset.borderColor = randomColor(0.4);
					dataset.backgroundColor = randomColor(0.5);
					dataset.pointBorderColor = randomColor(0.7);
					dataset.pointBackgroundColor = randomColor(0.5);
					dataset.pointBorderWidth = 1;
				});

				window.onload = function() {
					var ctx = document.getElementById("canvas")
							.getContext("2d");
					window.myLine = new Chart(ctx, config);
				};

				$('#randomizeData').click(function() {
					$.each(config.data.datasets, function(i, dataset) {
						dataset.data = dataset.data.map(function() {
							return randomScalingFactor();
						});

					});

					window.myLine.update();
				});

				$('#changeDataObject')
						.click(
								function() {
									config.data = {
										labels : [ "July", "August",
												"September", "October",
												"November", "December" ],
										datasets : [
												{
													label : "My First dataset",
													data : [
															randomScalingFactor(),
															randomScalingFactor(),
															randomScalingFactor(),
															randomScalingFactor(),
															randomScalingFactor(),
															randomScalingFactor() ],
													fill : false,
												},
												{
													label : "My Second dataset",
													fill : false,
													data : [
															randomScalingFactor(),
															randomScalingFactor(),
															randomScalingFactor(),
															randomScalingFactor(),
															randomScalingFactor(),
															randomScalingFactor() ],
												} ]
									};

									$
											.each(
													config.data.datasets,
													function(i, dataset) {
														dataset.borderColor = randomColor(0.4);
														dataset.backgroundColor = randomColor(0.5);
														dataset.pointBorderColor = randomColor(0.7);
														dataset.pointBackgroundColor = randomColor(0.5);
														dataset.pointBorderWidth = 1;
													});

									// Update the chart
									window.myLine.update();
								});
			</script>
			<div></div>
		</div>
		-->
		<!-- /#page-wrapper -->
	</div>
</body>
<!--
<script type="text/javascript">

	$
			.ajax({
				url : 'getRecentData',
				dataType : 'json',
				success : function(r) {
					$("#newArtistSize").html(' ' + r.newArtistSize);
					$("#spanNewArtistSize").html(' 总人数：' + r.spanNewArtistSize);

					$("#newBusinessesSize").html(' ' + r.newBusinessesSize);
					$("#spanNewBusinessesSize").html(
							' 总人数：' + r.spanNewBusinessesSize);

					$("#newOrderSize").html(' ' + r.newOrderSize);
					$("#spanNewOrderSize").html(' 订单总数：' + r.spanNewOrderSize);

					$("#newMoney").html(' ' + r.newMoney);

					$("#spanNewMoney").html(' 交易收益总金额：' + r.spanNewMoney);

				}
			});
</script>
-->
</html>
