var btnColor = "#078ad2";
/**
 * 飞威弹出框
 */
$.fwstl = {
	/**
	 * 错误消息
	 * 
	 * @param msg
	 *            消息内容
	 */
	error : function(msg) {
		swal({
			title : msg,
			text : "",
			type : "error",
			confirmButtonText : "确定"
		});
	},
	success : function(msg) {
		$.fwstl.mySuccess(msg)
	},
	warning : function(msg) {
		swal({
			title : msg,
		});
	},
	/**
	 * 提示头
	 * 
	 * @param msg
	 *            提示消息
	 * @returns {___anonymous278_475}
	 */
	warningTitil : function(msg) {
		var jsonData = {
			title : msg,
			text : "",
			type : "warning",
			showCancelButton : true,
			confirmButtonColor : "#DD6B55",
			confirmButtonText : "确定",
			cancelButtonText : "取消",
			closeOnConfirm : false
		};
		return jsonData;
	},
	deleteTip : function(obj) {
		swal({
			title : "你确定要删除吗?",
			text : "将删除所关联的内容，且删除后将无法恢复!",
			type : "error",
			showCancelButton : true,
			confirmButtonColor : "red",
			confirmButtonText : "确认",
			cancelButtonText : "取消",
			closeOnConfirm : false
		}, function() {
			location.href = $(obj).attr('href');
		})
	},
	hrefTip : function(obj, title, text) {
		swal({
			title : title,
			text : text,
			type : "warning",
			showCancelButton : true,
			confirmButtonColor : btnColor,
			confirmButtonText : "确认",
			cancelButtonText : "取消",
			closeOnConfirm : false
		}, function() {
			$.ajax({
				url : $(obj).attr('href'),
				dataType : 'json',
				success : function(r) {
					if (r.code=='1001')
						swal({
							title : "",
							text : r.message,
							type : "success",
							confirmButtonColor : btnColor,
							confirmButtonText : "确认",
							closeOnConfirm : false
						}, function() {
							location.reload();
						})

				}
			});

		})
	},
	mySuccess : function(msg) {
		swal({
			title : "",
			text : msg,
			type : "success",
			confirmButtonColor : btnColor,
			confirmButtonText : "确认",
			closeOnConfirm : false
		}, function() {
			location.reload();
		})
	}

}, $.cyOperate = {
	opAdd : function(url, sureUrl, title) {
		$.ajax({
			url : url,
			dataType : 'html',
			success : function(r) {
				bootbox.dialog({
					title : title,
					message : r,
					size : 'large',
					buttons : {
						success : {
							label : "确定",
							className : "btn-primary",
							callback : function() {
								$("#add").ajaxSubmit({
									type : 'post', // 提交方式 get/post
									url : sureUrl, // 需要提交的 url
									success : function(data) {
										if (data.code=='1001') {
											$.fwstl.success(data.message);
										} else {
											$.fwstl.error(data.message);
										}
									}
								});
							}
						},
						back : {
							label : "返回",
							className : "btn-primary",
							callback : function() {
								return true;
							}
						}

					}
				});
			}
		});
	},
	opEdit : function(url, sureUrl, title, id) {
		$.ajax({
			url : url,
			data : {
				id : id
			},
			dataType : 'html',
			success : function(r) {
				bootbox.dialog({
					title : title,
					message : r,
					size : 'large',
					buttons : {
						success : {
							label : "确定",
							className : "btn-primary",
							callback : function() {

								if (sureUrl != null && sureUrl!='')
									$("#edit").ajaxSubmit({
										type : 'post', // 提交方式 get/post
										url : sureUrl, // 需要提交的 url
										success : function(data) {
											$.fwstl.success(data.message);
										}
									});
							}
						},
						back : {
							label : "返回",
							className : "btn-primary",
							callback : function() {
								return true;
							}
						}

					}
				});
			}
		});
	}
}
clickAll = function() {
	$("#all").removeClass("hidden");
	$("#pass").addClass("hidden");
	$("#wait").addClass("hidden");
}

clickPass = function() {
	$("#pass").removeClass("hidden");
	$("#all").addClass("hidden");
	$("#wait").addClass("hidden");
}

clickWait = function() {
	$("#wait").removeClass("hidden");
	$("#pass").addClass("hidden");
	$("#all").addClass("hidden");
}