
<script>
function setImages(a, b, c) {
		var docObj = document.getElementById(a);

		var imgObjPreview = document.getElementById(b);
		if (docObj.files && docObj.files[0]) {
			// 火狐下，直接设img属性
			imgObjPreview.style.display = 'block';
			imgObjPreview.style.width = '500px';
			imgObjPreview.style.height = '250px';
			imgObjPreview.style.border = "1px solid #DDD";
			// 火狐7以上版本不能用上面的getAsDataURL()方式获取，需要改一下方式
			imgObjPreview.src = window.URL.createObjectURL(docObj.files[0]);
		} else {
			// IE下，使用滤镜
			docObj.select();
			var imgSrc = document.selection.createRange().text;
			var localImagId = document.getElementById(c);
			// 必须设置初始大小
			localImagId.style.width = "500px";
			localImagId.style.height = "250px";
			localImagId.style.border = "1px solid #DDD";
			// 图片异常的捕捉，防止用户修改后缀来伪造图片
			try {
				localImagId.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";
				localImagId.filters
						.item("DXImageTransform.Microsoft.AlphaImageLoader").src = imgSrc;
			} catch (e) {
				alert("您上传的图片格式不正确，请重新选择!");
				return false;
			}
			imgObjPreview.style.display = 'none';
			document.selection.empty();
		}
		return true;
	}
</script>
<!-- jquery.fileupload -->

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/lib/jquery-file-upload/css/jquery.fileupload.css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/lib/sbAdmin/jquery/dist/jquery.form.js" ></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/lib/jquery-file-upload/js/vendor/jquery.ui.widget.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/lib/jquery-file-upload/js/jquery.iframe-transport.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/lib/jquery-file-upload/js/jquery.fileupload.js"></script>