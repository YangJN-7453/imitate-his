/**
 * 分页
 */

function showPage(data){
		//填充分页区域div
		// 让分页区域显示
		$("#pagediv").css("display","block")
		var span1 = $("<span>当前第" + data.currentPage + "页，总共" + data.totalPage + "页</span>");
		var span2 = $("<span style='margin-left:50px'><a href='javascript:loadData(1)'>首页</a></span>");
		var nextpage = data.currentPage < data.totalPage?data.currentPage + 1:data.currentPage;
		var prepage = data.currentPage > 1?data.currentPage - 1:1;
		var span3 = $("<span style='margin-left:20px'><a href='javascript:loadData(" + prepage + ")'>上一页</a></span>");
		var span4 = $("<span style='margin-left:20px'><a href='javascript:loadData(" + nextpage  + ")'>下一页</a></span>");
		var span5 = $("<span style='margin-left:20px'><a href='javascript:loadData(" + data.totalPage + ")'>末页</a></span>");
		$("#pagediv").append(span1);
		$("#pagediv").append(span2);
		$("#pagediv").append(span3);
		$("#pagediv").append(span4);
		$("#pagediv").append(span5);
	}