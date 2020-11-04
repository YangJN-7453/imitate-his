/**
 * 常数类别js
 */

$(function() {
		// 1.页面加载完成后，异步请求服务器servlet，获取第一页的数据
		loadData();
})

/*
 * 异步加载数据，根据传入的当前页，获取该页数据并渲染到tbody中
 */
function loadData() {
	$.ajax({
		url : "FindConstantType",
		type : "post",
		data : {},
		success :function(data) {
//			alert(data.length);
			
			if(data.length == 0){
				var tr = $("<tr><td colspan='4'>暂无数据</td></tr>");
				$("#constantTypeData").append(tr);
			} else {
				// 清除tbody节点中的所有子元素
				$("#constantTypeData").children().remove();
				// 清除#pagediv分页节点中的所有子元素
				$("#pagediv").children().remove();
				// 填充数据到表格
				for(var i = 0; i < data.length; i++) {
					var obj = data[i];
					var tr = $("<tr></tr>");
					var td1 = $("<td><input type='checkbox' name='all'></td>");
					var td2 = $("<td>" + obj.constantTypeCode + "</td>");
					var td3 = $("<td>" + obj.constantTypeName + "</td>");
					
					var td4 = $("<td><button class='btn btn-success btn-xs' onclick=fsee(" + JSON.stringify(obj) + ")>查看子项</button>&nbsp;&nbsp;"
							+ "<button class='btn btn-warning btn-xs' onclick='fupdate(" + JSON.stringify(obj) + ")'>修改</button>&nbsp;&nbsp;"
							+ "<button class='btn btn-danger btn-xs' onclick='fdelete(" + obj.id + ")'>删除</button></td>");

					tr.append(td1);
					tr.append(td2);
					tr.append(td3);
					tr.append(td4);

					$("#constantTypeData").append(tr);
				}
			}
		},
		error : function() {
			alert("服务器端出错！")
		}
	})
}



/*
 * 点击“删除”按钮，显示删除确认框
 */
function fdelete(id) {
	// 首先调用删除确认框显示
	$('#myModal').modal('show');
	
	// 传参：将要删除的行的id值传入到隐藏表单域中
	$("#constanttypeid").val(id);
}


/*
 * 点击确认按钮，异步请求，执行删除操作
 */
function doDelete() {
	// 获取要删除的行的id值
	var typeid = $("#constanttypeid").val();
	//异步调用执行删除操作
	$.ajax({
		url:"DeleteConstantTypeById",
		type:"post",
		data:{id:typeid},
		success:function(data){
			//alert(data)
			// 隐藏删除确认框
			$('#myModal').modal('hide');
			if(data.msg == 1){
			//msg为1时 ，删除成功

				//重新加载页面
				window.location.reload();
				//loadData(1);该方法在仅有最后一条数据的时候，有bug
			} else {
				alert("未找到要删除的数据")
			}
		},
		error:function(){
			alert("服务器端出错")
		}
	})
}

/*
 * 查看子项
 */
function fsee(objstr) {
	// 异步请求服务器，获取所有子项的id code name
//	console.log(objstr);
	$.ajax({
		url:"FindConstantItemByTypeCode",
		type:"post",
		data:{code:objstr.constantTypeCode},
		success:function(data){
			// 清除tbody节点中的所有子元素
			$("#constantitemsData").children().remove();
			
			if(data.length == 0){
				var tr = $("<tr><td colspan='3'>暂无数据</td></tr>");
				// 将创建的元素追加到id为tbody的父元素 中
				$("#constantitemsData").append(tr);
			} else {
				// 填充数据到表格
				for(var i = 0;i < data.length;i++){
					var obj = data[i];
					
					var tr = $("<tr></tr>");
					var td1 = $("<td>" + obj.id + "</td>");
					var td2 = $("<td>" + obj.constantCode + "</td>");
					var td3 = $("<td>" + obj.constantName + "</td>");
					/*td1.text(obj.id);
					td2.text(obj.constantCode);
					td3.text(obj.constantName);*/
					tr.append(td1);
					tr.append(td2);
					tr.append(td3);
					
					$("#constantitemsData").append(tr);
				}
			}
			
			$('#see').modal('show');
		},
		error:function(){
			alert("服务器端出错fsee");
		}
	})
	
}

/*
 * 点击查询按钮，获取输入框中内容，并查询
 */
function findByCode() {
	var content = $("#constanttypecode").val();
//	console.log("*" + content + "*");
	$.ajax({
		url : "FindConstantTypeByCode",
		type : "post",
		data : {constantTypeCode : content},
		success : function(data){
			
			// 清除tbody节点中的所有子元素
			$("#constantTypeData").children().remove();

			if(data.length == 0){
				var tr = $("<tr><td colspan='4'>暂无数据</td></tr>");
				$("#constantTypeData").append(tr);
			} else {
				
				// 填充数据到表格
				for(var i = 0; i < data.length; i++) {
					var obj = data[i];
					var tr = $("<tr></tr>");
					var td1 = $("<td><input type='checkbox' name='all'></td>");
					var td2 = $("<td>" + obj.constantTypeCode + "</td>");
					var td3 = $("<td>" + obj.constantTypeName + "</td>");
					
					var td4 = $("<td><button class='btn btn-success btn-xs' onclick=fsee(" + JSON.stringify(obj) + ")>查看子项</button>&nbsp;&nbsp;"
							+ "<button class='btn btn-warning btn-xs' onclick='fupdate(" + JSON.stringify(obj) + ")'>修改</button>&nbsp;&nbsp;"
							+ "<button class='btn btn-danger btn-xs' onclick='fdelete(" + obj.id + ")'>删除</button></td>");

					tr.append(td1);
					tr.append(td2);
					tr.append(td3);
					tr.append(td4);

					$("#constantTypeData").append(tr);
				}
			}

		},
		error : function(){
			alert("服务器端出错！");
		}
	});
}


/*
 * 点击添加按钮，显示id为saveAndUpdate的模态框
 */
function fadd() {
	$("#addConstantTypeCode").val("");
	$("#addConstantTypeName").val("");
	$("#addConstantTypeId").val("");
	$("#saveAndUpdateLabel").text("添加常数类别");
	$("#addBtn").text("保存");
	$('#saveAndUpdate').modal('show');
}


/*
 * 点击修改按钮，显示id为saveAndUpdate的模态框
 */
function fupdate(obj) {
	$("#saveAndUpdateLabel").text("修改常数类别");
	$("#addConstantTypeCode").val(obj.constantTypeCode);
	$("#addConstantTypeName").val(obj.constantTypeName);
	// 给隐藏表单域设置id的值
	$("#addConstantTypeId").val(obj.id);
	$("#addBtn").text("修改");
	$('#saveAndUpdate').modal('show');
}


/*
 * 点击模态框中的保存/修改按钮，显示id为saveAndUpdate的模态框
 */
function doSaveOrUpdate() {
	var op = $("#addBtn").text();
	
	// 获取模态框中 类别id code name
	var vid = $("#addConstantTypeId").val();
	var vcode = $("#addConstantTypeCode").val();
	var vname = $("#addConstantTypeName").val();
	if(op == "修改") {
		// 调用后台执行修改操作
		$.ajax({
			url:"SaveConstantType",
			type:"post",
			data:{id:vid,code:vcode,name:vname},
			success:function(data){
				if(data.msg == 1){
					alert("修改成功");
					$('#saveAndUpdate').modal('hide');
					loadData();
				}
			},
			error:function(){
				alert("服务器端出错");
			}
		})
	} else {
		// 调用后台执行添加操作
		$.ajax({
			url:"SaveConstantType",
			type:"post",
			data:{code:vcode,name:vname},
			success:function(data){
				alert("添加成功");
				$('#saveAndUpdate').modal('hide');
				loadData();
			},
			error:function(){
				alert("服务器端出错");
			}
		})
	}
}