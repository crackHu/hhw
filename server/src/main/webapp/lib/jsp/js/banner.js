addBanner = function(event) {
	$.cyOperate.opAdd("admin/banner/showAdd", "admin/banner/add", "添加轮播图");
}

deletedById = function(id) {
	$.fwstl.hrefTip("<a href='admin/banner/deletedById?id=" + id + "'></a>",
			"你确定要删除吗?", "将删除所关联的内容，且删除后将无法恢复!");
}
showEdit = function(id) {
	$.cyOperate.opEdit("admin/banner/showEdit", "admin/banner/edit", "修改轮播图",
			id);
}

addEntity = function(url, sureUrl, message) {
	$.cyOperate.opAdd(url, sureUrl, message);
}



deletedEntity = function(url, id) {
	$.fwstl.hrefTip("<a href=" + url + "?id=" + id + "></a>", "你确定要删除吗?",
			"将删除所关联的内容，且删除后将无法恢复!");
}

updateStatus = function(url, id ,message){
	$.fwstl.hrefTip("<a href=" + url + "?id=" + id + "></a>", message,
	"认证后无法撤销!");
}

editEntity = function(url, sureUrl, message, id) {
	$.cyOperate.opEdit(url, sureUrl, message, id);
}


    $.fn.preview = function(){
        var xOffset = 10;
        var yOffset = 20;
        var w = $(window).width();
        $(this).each(function(){
            $(this).hover(function(e){
                if(/.png$|.gif$|.jpg$|.bmp$/.test($(this).attr("href"))){
                    $("body").append("<div id='preview'><div><img src='"+$(this).attr('href')+"' width='800px' height='400px' /><p>"+$(this).attr('title')+"</p></div></div>");
                }else{
                    $("body").append("<div id='preview'><div><p>"+$(this).attr('title')+"</p></div></div>");
                }
                $("#preview").css({
                    position:"absolute",
                    padding:"4px",
                    border:"1px solid #f3f3f3",
                    backgroundColor:"#eeeeee",
                    top:(e.pageY - yOffset) + "px",
                    zIndex:1000
                });
                $("#preview > div").css({
                    padding:"5px",
                    backgroundColor:"white",
                    border:"1px solid #cccccc"
                });
                $("#preview > div > p").css({
                    textAlign:"center",
                    fontSize:"12px",
                    padding:"8px 0 3px",
                    margin:"0"
                });
                if(e.pageX < w/2){
                    $("#preview").css({
                        left: e.pageX + xOffset + "px",
                        right: "auto"
                    }).fadeIn("fast");
                }else{
                    $("#preview").css("right",(w - e.pageX + yOffset) + "px").css("left", "auto").fadeIn("fast");   
                }
            },function(){
                $("#preview").remove();
            }).mousemove(function(e){
                $("#preview").css("top",(e.pageY - xOffset) + "px")
                if(e.pageX < w/2){
                    $("#preview").css("left",(e.pageX + yOffset) + "px").css("right","auto");
                }else{
                    $("#preview").css("right",(w - e.pageX + yOffset) + "px").css("left","auto");
                }
            });                   
        });
       };