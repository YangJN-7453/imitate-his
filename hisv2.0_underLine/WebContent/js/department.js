/**
 * 科室管理界面js
 */

$(function() {
	loadData(1);
})

/*
 * 异步加载数据，根据传入的当前页，获取该页数据并渲染到tbody中
 */
function loadData(currpage) {
	$.ajax({
		url : "FindAllDept",
		type : "post",
		data : {currentPage : currpage},
		success : function(data) {
//			console.log(data);
			/*{"currentPage":1,"pageSize":10,"totalCount":6,"totalPage":1,"beginIndex":0,
			 * "pageData":[
			 * {"id":1,"deptCode":"XWK","deptName":"胸外科","deptCategoryID":4,"deptType":19,"delMark":1,"deptCategoryName":"儿科","deptTypeName":"临床科室"},
			 * {"id":2,"deptCode":"XXGNK","deptName":"心血管内科","deptCategoryID":3,"deptType":19,"delMark":1,"deptCategoryName":"外科","deptTypeName":"临床科室"},
			 * {"id":3,"deptCode":"SJNK","deptName":"神经内科","deptCategoryID":3,"deptType":19,"delMark":1,"deptCategoryName":"外科","deptTypeName":"临床科室"},
			 * {"id":4,"deptCode":"PTNK","deptName":"普通内科","deptCategoryID":3,"deptType":19,"delMark":1,"deptCategoryName":"外科","deptTypeName":"临床科室"},
			 * {"id":5,"deptCode":"XHNK","deptName":"消化内科","deptCategoryID":3,"deptType":19,"delMark":1,"deptCategoryName":"外科","deptTypeName":"临床科室"},
			 * {"id":6,"deptCode":"HXNK","deptName":"呼吸内科","deptCategoryID":3,"deptType":19,"delMark":1,"deptCategoryName":"外科","deptTypeName":"临床科室"}]}
			 */
			if(data.totalCount == 0) {
				var tr = $("<tr><td colspan='6'>暂无数据</td></tr>");
				$("#deptData").append(tr);
			} else {
				$("#deptData").children().remove();
				$("#pagediv").children().remove();
				// 填充数据
				for (var i = 0 ; i < data.pageData.length ; i++) {
					var obj = data.pageData[i];
					var tr = $("<tr></tr>");
					var td1 = $("<td><input type='checkbox' name='all'></td>");
					var td2 = $("<td>" + obj.deptCode + "</td>");
					var td3 = $("<td>" + obj.deptName + "</td>");
					var td4 = $("<td>" + obj.deptCategoryName + "</td>");
					var td5 = $("<td>" + obj.deptTypeName + "</td>");
					
					var td6 = $("<td><button class='btn btn-success btn-xs' onclick=fsee(" + obj.id + ")>查看人员</button>&nbsp;&nbsp;"
							+ "<button class='btn btn-warning btn-xs' onclick='fupdate(" + JSON.stringify(obj) + ")'>修改</button>&nbsp;&nbsp;"
							+ "<button class='btn btn-danger btn-xs' onclick='fdelete(" + obj.id + ")'>删除</button></td>");
					
					tr.append(td1);
					tr.append(td2);
					tr.append(td3);
					tr.append(td4);
					tr.append(td5);
					tr.append(td6);
					$("#deptData").append(tr);
				}
				// 显示分页
				showPage(data);
			}
		},
		error : function() {
			alert("服务器端出错！");
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
	$("#deptid").val(id);
}


/*
 * 点击确认按钮，异步请求，执行删除操作
 */
function doDelete() {
	var did = $("#deptid").val();
	$.ajax({
		url : "DeleteDeptById",
		type : "post",
		data : {deptid :did},
		success : function(data) {
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
		error : function() {
			alert("服务器端出错");
		}
	});
}


/*
 * 点击查看人员按钮，显示查看框
 */
function fsee(deptid) {
//	console.log(deptid);
	$.ajax({
		url : "FindUserByDeptID",
		type : "post",
		data : {deptid : deptid},
		success : function(data) {
//			console.log(data);
			/*[{"id":2,"userName":null,"password":null,"realName":"张仲景","userType":0,
				"docTitleID":0,"isScheduling":null,"deptID":0,"registLeID":0,
				"delMark":0,"deptname":"心血管内科","docTitleName":null,"registLeName":null,"userTypeName":null}]*/
			$("#departmentsData").children().remove();
			if(data.length == 0) {
				var tr = $("<tr><td colspan='3'>暂无数据</td></tr>");
				$("#departmentsData").append(tr);
			} else {
				for(var i = 0 ; i < data.length ; i++) {
					var obj = data[i];
					var tr = $("<tr></tr>");
					var td1 = $("<td>" + obj.id + "</td>");
					var td2 = $("<td>" + obj.realName + "</td>");
					var td3 = $("<td>" + obj.deptname + "</td>");
					
					tr.append(td1);
					tr.append(td2);
					tr.append(td3);
					
					$("#departmentsData").append(tr);
				}
			}
			$('#see').modal('show');
		},
		error : function() {
			alert("服务器端出错");
		}
	});
}


/*
 * 点击查询按钮，根据输入框内容进行模糊查询
 */
function findByCode() {
	var content = $("#deptcode").val();
//	console.log(content);
	$.ajax({
		url : "FindDeptByCode",
		type : "post",
		data : {code : content},
		success : function(data) {
//			console.log(data);
			$("#deptData").children().remove();
			$("#pagediv").children().remove();
			if(data.length == 0){
				var tr = $("<tr><td colspan='6'>暂无数据</td></tr>");
				$("#deptData").append(tr);
			} else {
				for (var i = 0 ; i < data.length ; i++) {
					var obj = data[i];
					var tr = $("<tr></tr>");
					var td1 = $("<td><input type='checkbox' name='all'></td>");
					var td2 = $("<td>" + obj.deptCode + "</td>");
					var td3 = $("<td>" + obj.deptName + "</td>");
					var td4 = $("<td>" + obj.deptCategoryName + "</td>");
					var td5 = $("<td>" + obj.deptTypeName + "</td>");
					
					var td6 = $("<td><button class='btn btn-success btn-xs' onclick=fsee(" + obj.id + ")>查看人员</button>&nbsp;&nbsp;"
							+ "<button class='btn btn-warning btn-xs' onclick='fupdate(" + JSON.stringify(obj) + ")'>修改</button>&nbsp;&nbsp;"
							+ "<button class='btn btn-danger btn-xs' onclick='fdelete(" + obj.id + ")'>删除</button></td>");
					
					tr.append(td1);
					tr.append(td2);
					tr.append(td3);
					tr.append(td4);
					tr.append(td5);
					tr.append(td6);
					$("#deptData").append(tr);
				}
			}
		},
		error :function(){
			alert("服务器端出错！");
		}
	});
}


/*
 * 点击添加按钮，显示id为saveAndUpdate的模态框
 */
function fadd() {
	/*
	 * {"deptCategorys":[
	 * {"id":1,"constantTypeID":1,"constantCode":"GK","constantName":"骨科","delMark":1},
	 * {"id":2,"constantTypeID":1,"constantCode":"NK","constantName":"内科","delMark":1},
	 * {"id":3,"constantTypeID":1,"constantCode":"WK","constantName":"外科","delMark":1},
	 * {"id":4,"constantTypeID":1,"constantCode":"EK","constantName":"儿科","delMark":1}],
	 * "deptTypes":[
	 * {"id":19,"constantTypeID":6,"constantCode":"LCKS","constantName":"临床科室","delMark":1},
	 * {"id":20,"constantTypeID":6,"constantCode":"YJKS","constantName":"医技科室","delMark":1},
	 * {"id":21,"constantTypeID":6,"constantCode":"CWKS","constantName":"财务科室","delMark":1},
	 * {"id":22,"constantTypeID":6,"constantCode":"XZKS","constantName":"行政科室","delMark":1}]}
	 */
	$.ajax({
		url : "FindDeptInfo",
		type : "post",
		success : function(data) {
//			console.log(data);
			// 科室分类
			var deptCategorys = data.deptCategorys;
			// 科室职能
			var deptTypes = data.deptTypes;
			
			$("#addDeptCategoryName")[0].options.length = 1;
			$("#addDeptTypeName")[0].options.length = 1;
			
			// 将科室分类填充到下拉列表中
			for(var i = 0 ; i < deptCategorys.length ; i++){
				var obj = deptCategorys[i];
				var option = $("<option value='" + obj.id + "'>" + obj.constantName + "</option>");
				$("#addDeptCategoryName").append(option);
			}
			
			// 将科室职能填充到下拉列表中
			for(var i = 0 ; i < deptTypes.length ; i++){
				var obj = deptTypes[i];
				var option = $("<option value='" + obj.id + "'>" + obj.constantName + "</option>");
				$("#addDeptTypeName").append(option);
			}
			$("#addDeptCode").val("");
			$("#addDeptName").val("");
			$("#saveAndUpdateLabel").text("新增部门");
			$("#addBtn").text("添加");
			$('#saveAndUpdate').modal('show');
		},
		error : function(){
			alert("服务器端出错！");
		}
	});
}


/*
 * 点击修改按钮，显示id为saveAndUpdate的模态框
 */
function fupdate(deptObj) {
//	console.log(deptObj);
	
	$("#addDeptId").val(deptObj.id);
	$.ajax({
		url : "FindDeptInfo",
		type : "post",
		success : function(data) {
//			console.log(data);
			// 科室分类
			var deptCategorys = data.deptCategorys;
			// 科室职能
			var deptTypes = data.deptTypes;
			
			$("#addDeptCategoryName")[0].options.length = 1;
			$("#addDeptTypeName")[0].options.length = 1;
			
			// 将科室分类填充到下拉列表中
			for(var i = 0 ; i < deptCategorys.length ; i++){
				var obj = deptCategorys[i];
				var option = $("<option value='" + obj.id + "'>" + obj.constantName + "</option>");
				$("#addDeptCategoryName").append(option);

//				console.log("value:--" + option[0].value)
				if(option[0].value == deptObj.deptCategoryID){
					option[0].selected = true;
				}
			}
			
			// 将科室职能填充到下拉列表中
			for(var i = 0 ; i < deptTypes.length ; i++){
				var obj = deptTypes[i];
				var option = $("<option value='" + obj.id + "'>" + obj.constantName + "</option>");
				$("#addDeptTypeName").append(option);
				
				if(option[0].value == deptObj.deptType){
					option[0].selected = true;
				}
			}
			// 传初始值
			$("#addDeptCode").val(deptObj.deptCode);
			$("#addDeptName").val(deptObj.deptName);
			$("#saveAndUpdateLabel").text("修改部门信息");
			$("#addBtn").text("修改");
			
			$('#saveAndUpdate').modal('show');
		},
		error : function(){
			alert("服务器端出错！");
		}
	});
}


/*
 * 点击模态框中的保存/修改按钮，隐藏id为saveAndUpdate的模态框
 */
function doSaveOrUpdate() {
	var op = $("#addBtn").text();
	
	var deptId = $("#addDeptId").val();
	var deptCode = $("#addDeptCode").val();
	var deptName = $("#addDeptName").val();
	var deptCategoryId = $("#addDeptCategoryName option:selected").val();
	var deptType = $("#addDeptTypeName option:selected").val();
//	console.log("deptid=" + deptId)
		
	$.ajax({
		url : "SaveDepartment",
		type : "post",
		data : {
			deptId : deptId,
			deptCode : deptCode,
			deptName : deptName,
			deptCategoryId : deptCategoryId,
			deptType : deptType
		},
		success : function(data) {
//			console.log(data);
			if(data.msg == 1){
				if(op == "修改"){
					alert("修改成功");
				} else {
					alert("添加成功");
				}
				
				$('#saveAndUpdate').modal('hide');
				loadData(1);
			}
		},
		error : function() {
			alert("服务器端出错");
		}
	});
		
}
