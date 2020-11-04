/**
 * 用户管理界面js
 */

$(function() {
	loadData(1);
})


/*
 * 异步加载数据，根据传入的当前页，获取该页数据并渲染到tbody中
 */
function loadData(currpage) {
	$.ajax({
		url : "FindAllUser",
		type : "post",
		data : {currentPage : currpage},
		success : function(data) {
//			data.totalCount = 0;
			
			if(data.totalCount == 0) {
				var tr = $("<tr><td colspan='9'>暂无数据</td></tr>");
				$("#userData").append(tr);
			} else {
				// 清除tbody节点中的所有子元素
				$("#userData").children().remove();
				$("#pagediv").children().remove();
				// 填充数据到表格
				for(var i = 0 ; i < data.pageData.length ; i++){
					var obj = data.pageData[i];
					var tr = $("<tr></tr>");
					var td1 = $("<td><input type='checkbox' name='all'></td>");
					var td2 = $("<td>" + obj.userName + "</td>");
					var td3 = $("<td>" + obj.realName + "</td>");
					var td4 = $("<td>" + obj.userTypeName + "</td>");
					var td5 = $("<td>" + obj.deptname + "</td>");
					var td6 = $("<td>" + obj.docTitleName + "</td>");
					var td7 = $("<td>" + obj.registLeName + "</td>");
					var td8 = $("<td>" + (obj.isScheduling == 1?'是':'否') + "</td>");
					
					var td9 = $("<td>"
							+ "<button class='btn btn-warning btn-xs' onclick='fupdate(" + JSON.stringify(obj) + ")'>修改</button>&nbsp;&nbsp;"
							+ "<button class='btn btn-danger btn-xs' onclick='fdelete(" + obj.id + ")'>删除</button></td>");

					tr.append(td1);
					tr.append(td2);
					tr.append(td3);
					tr.append(td4);
					tr.append(td5);
					tr.append(td6);
					tr.append(td7);
					tr.append(td8);
					tr.append(td9);
					
					$("#userData").append(tr);
				}
				// 分页栏
				showPage(data);
			}
		},
		error : function() {
			alert("服务器端出错！");
		}
	});
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
		url : "DeleteUserById",
		type : "post",
		data : {id : typeid},
		success : function(data) {
			// 先隐藏删除框
			$('#myModal').modal('hide');
//			console.log(data);
			if(data.msg == 1){
				//msg为1时 ，删除成功

				//重新加载页面
				window.location.reload();
			} else {
				alert("未找到要删除的数据");
			}
		},
		error:function(){
			alert("服务器端出错")
		}
	})
}


/*
 * 点击添加按钮，显示id为saveAndUpdate的模态框
 */
function fadd() {
	/*
	 * {"reglevels":[
	 * {"id":1,"registCode":"WKZJH","registName":"外科专家号","sequenceNo":1,"registFee":60.0,"registQuota":20,"delMark":0},
	 * {"id":2,"registCode":"WKPTH","registName":"外科普通号","sequenceNo":3,"registFee":20.5,"registQuota":60,"delMark":0},
	 * {"id":3,"registCode":"WKJZH","registName":"外科急诊号","sequenceNo":2,"registFee":40.0,"registQuota":40,"delMark":0},
	 * {"id":4,"registCode":"NKZJH","registName":"内科专家号","sequenceNo":1,"registFee":60.0,"registQuota":20,"delMark":0},
	 * {"id":5,"registCode":"NKPTH","registName":"内科普通号","sequenceNo":3,"registFee":20.0,"registQuota":60,"delMark":0},
	 * {"id":6,"registCode":"NKJZH","registName":"内科急诊号","sequenceNo":2,"registFee":40.0,"registQuota":40,"delMark":0},
	 * {"id":7,"registCode":"CSZJH","registName":"测试专家号","sequenceNo":-1,"registFee":2.5,"registQuota":888,"delMark":0}],
	 * "docTitles":[
	 * {"id":5,"constantTypeID":3,"constantCode":"ZRYS","constantName":"主任医师","delMark":1},
	 * {"id":6,"constantTypeID":3,"constantCode":"FZRYS","constantName":"副主任医师","delMark":1},
	 * {"id":7,"constantTypeID":3,"constantCode":"ZZYS","constantName":"主治医师","delMark":1},
	 * {"id":8,"constantTypeID":3,"constantCode":"ZYYS","constantName":"住院医师","delMark":1}],
	 * "usertypes":[
	 * {"id":13,"constantTypeID":5,"constantCode":"GHSFY","constantName":"挂号收费员","delMark":1},
	 * {"id":14,"constantTypeID":5,"constantCode":"MZYS","constantName":"门诊医生","delMark":1},
	 * {"id":15,"constantTypeID":5,"constantCode":"YJYS","constantName":"医技医生","delMark":1},
	 * {"id":16,"constantTypeID":5,"constantCode":"YFCZY","constantName":"药房操作员","delMark":1},
	 * {"id":17,"constantTypeID":5,"constantCode":"CWGLY","constantName":"财务管理员","delMark":1},
	 * {"id":18,"constantTypeID":5,"constantCode":"YYGLY","constantName":"医院管理员","delMark":1}],
	 * "depts":[
	 * {"id":1,"deptCode":"XWK","deptName":"胸外科","deptCategoryID":3,"deptType":19,"delMark":1,"deptCategoryName":null,"deptTypeName":null},
	 * {"id":2,"deptCode":"XXGNK","deptName":"心血管内科","deptCategoryID":3,"deptType":19,"delMark":1,"deptCategoryName":null,"deptTypeName":null},
	 * {"id":3,"deptCode":"SJNK","deptName":"神经内科","deptCategoryID":3,"deptType":19,"delMark":1,"deptCategoryName":null,"deptTypeName":null},
	 * {"id":4,"deptCode":"PTNK","deptName":"普通内科","deptCategoryID":3,"deptType":19,"delMark":1,"deptCategoryName":null,"deptTypeName":null},
	 * {"id":5,"deptCode":"XHNK","deptName":"消化内科","deptCategoryID":3,"deptType":19,"delMark":1,"deptCategoryName":null,"deptTypeName":null},
	 * {"id":6,"deptCode":"HXNK","deptName":"呼吸内科","deptCategoryID":3,"deptType":20,"delMark":1,"deptCategoryName":null,"deptTypeName":null},
	 * {"id":7,"deptCode":"CSK","deptName":"测试科","deptCategoryID":1,"deptType":22,"delMark":1,"deptCategoryName":null,"deptTypeName":null}]}
	 * */
	
	$.ajax({
		url : "FindInfoAboutUser",
		type :"post",
		success : function(data) {
//			console.log(data);
			// 用户类型
			var usertypes = data.usertypes
			// 医生职称
			var docTitles = data.docTitles;
			// 科室
			var depts = data.depts;;
			// 挂号级别
			var registLevels = data.reglevels;
			
			// 将用户类型填充到下拉列表中
			// 等价于 doucument.getElementById("userType").options.length = 1
			$("#userType")[0].options.length = 1;
			$("#docTitleID")[0].options.length = 1;
			$("#departmentid")[0].options.length = 1;
			$("#registLeID")[0].options.length = 1;
			
			// 将用户类型填充到下拉列表
			for(var i = 0;i < usertypes.length;i++){
				var obj = usertypes[i];
				var option = $("<option value='" + obj.id + "'>" + obj.constantName + "</option>");
				$("#userType").append(option);
			}
			// 将科室填充到下拉列表
			for(var i = 0;i <depts.length;i++){
				var obj = depts[i];
				var option = $("<option value='" + obj.id + "'>" + obj.deptName + "</option>");
				$("#departmentid").append(option);
			}
			// 将挂号级别填充到下拉列表
			for(var i = 0;i <registLevels.length;i++){
				var obj = registLevels[i];
				var option = $("<option value='" + obj.id + "'>" + obj.registName + "</option>");
				$("#registLeID").append(option);
			}
			// 将医生职称填充到下拉列表
			for(var i = 0;i <docTitles.length;i++){
				var obj = docTitles[i];
				var option = $("<option value='" + obj.id + "'>" + obj.constantName + "</option>");
				$("#docTitleID").append(option);
			}
			// 传初始值
			$("#sche").removeAttr("checked")
			$("#nosche").removeAttr("checked")
			
			console.log($(":radio[name='isScheduling'][value='1']"))
			console.log($(":radio[name='isScheduling'][value='0']"))
			$("#addUserId").val("");
			$("#username").val("");
			$("#password").val("");
			$("#realname").val("");
			$("#saveLabel").text("添加用户");
			$("#addBtn").text("添加");
			
			$('#save').modal('show');
		},
		error : function(){
			alert("服务器端出错");
		}
	})

}


/*
 * 点击修改按钮，显示id为saveAndUpdate的模态框
 */
function fupdate(userObj) {
//	console.log(userObj)
	$("#addUserId").val(userObj.id);
	$.ajax({
		url : "FindInfoAboutUser",
		type :"post",
		success : function(data) {
//			console.log(data);
			// 用户类型
			var usertypes = data.usertypes
			// 医生职称
			var docTitles = data.docTitles;
			// 科室
			var depts = data.depts;;
			// 挂号级别
			var registLevels = data.reglevels;
			
			// 将用户类型填充到下拉列表中
			// 等价于 doucument.getElementById("userType").options.length = 1
			$("#userType")[0].options.length = 1;
			$("#docTitleID")[0].options.length = 1;
			$("#departmentid")[0].options.length = 1;
			$("#registLeID")[0].options.length = 1;
			
			// 将用户类型填充到下拉列表
			for(var i = 0;i < usertypes.length;i++){
				var obj = usertypes[i];
				var option = $("<option value='" + obj.id + "'>" + obj.constantName + "</option>");
				$("#userType").append(option);
				
				if(option.val() == userObj.userType){
					option[0].selected = true;
				}
			}
			// 将医生职称填充到下拉列表
			for(var i = 0;i <docTitles.length;i++){
				var obj = docTitles[i];
				var option = $("<option value='" + obj.id + "'>" + obj.constantName + "</option>");
				$("#docTitleID").append(option);
				
				if(option.val() == userObj.docTitleID){
					option[0].selected = true;
				}
			}
			// 将科室填充到下拉列表
			for(var i = 0;i <depts.length;i++){
				var obj = depts[i];
				var option = $("<option value='" + obj.id + "'>" + obj.deptName + "</option>");
				$("#departmentid").append(option);
				
				if(option.val() == userObj.deptID){
					option[0].selected = true;
				}
			}
			// 将挂号级别填充到下拉列表
			for(var i = 0;i <registLevels.length;i++){
				var obj = registLevels[i];
				var option = $("<option value='" + obj.id + "'>" + obj.registName + "</option>");
				$("#registLeID").append(option);
				
				if(option.val() == userObj.registLeID){
					option[0].selected = true;
				}
			}
			// 传初始值
			$(":radio[name='isScheduling'][value='" + userObj.isScheduling + "']").attr("checked","checked");

			$("#username").val(userObj.userName);
			$("#password").val(userObj.password);
			$("#realname").val(userObj.realName);
			$("#saveLabel").text("修改用户信息");
			$("#addBtn").text("修改");
			
			$('#save').modal('show');
		},
		error : function(){
			alert("服务器端出错");
		}
	});
}
function fre(){
//	$(":radio[name='isScheduling'][value='1']").attr("checked","unchecked");
//	$(":radio[name='isScheduling'][value='0']").attr("checked","unchecked");
	
}

/*
 * 点击模态框中的保存/修改按钮，显示id为saveAndUpdate的模态框
 */
function doSaveOrUpdate() {
	var op = $("#addBtn").text();
	
	var uid = $("#addUserId").val();
	var username = $("#username").val();
	var password = $("#password").val();
	var realname = $("#realname").val();
	var userType = $("#userType option:selected").val();
	var isScheduling = $("input[name='isScheduling']:checked").val();
	var docTitleID = $("#docTitleID option:selected").val();
	var deptId = $("#departmentid option:selected").val();
	var registLeID = $("#registLeID option:selected").val();

//	console.log(isScheduling);
	
	$.ajax({
		url : "SaveOrUpdateUser",
		type : "post",
		data : {
			userId : uid,
			username : username,
			password : password,
			realname : realname,
			userType : userType,
			isScheduling : isScheduling,
			docTitleID : docTitleID,
			deptId : deptId,
			registLeID : registLeID
		},
		success : function(data) {
//			console.log(data);
			if(data.msg == 1){
				if(op == "修改"){
					alert("修改成功");
				} else {
					alert("添加成功");
				}
				
				$('#save').modal('hide');
				loadData(1);
			}
		},
		error : function() {
			alert("服务器端出错");
		}
	});
	
}


/*
 * 点击查询按钮，获取输入框中内容，并进行模糊查询
 */
function findByUname() {
	var content = $("#userName").val();
//	console.log(content);
	$.ajax({
		url : "FindUserByUname",
		type : "post",
		data : {Uname : content},
		success : function(data) {
			// 清除tbody节点中的所有子元素
			$("#userData").children().remove();
			
			if(data.length == 0){
				var tr = $("<tr><td colspan='9'>暂无数据</td></tr>");
				$("#userData").append(tr);
			} else {
				// 填充数据到表格
				for(var i = 0; i < data.length; i++) {
					var obj = data[i];
					var tr = $("<tr></tr>");
					var td1 = $("<td><input type='checkbox' name='all'></td>");
					var td2 = $("<td>" + obj.userName + "</td>");
					var td3 = $("<td>" + obj.realName + "</td>");
					var td4 = $("<td>" + obj.userTypeName + "</td>");
					var td5 = $("<td>" + obj.deptname + "</td>");
					var td6 = $("<td>" + obj.docTitleName + "</td>");
					var td7 = $("<td>" + obj.registLeName + "</td>");
					var td8 = $("<td>" + (obj.isScheduling == 1?'是':'否') + "</td>");
					
					var td9 = $("<td>"
							+ "<button class='btn btn-warning btn-xs' onclick='fupdate(" + JSON.stringify(obj) + ")'>修改</button>&nbsp;&nbsp;"
							+ "<button class='btn btn-danger btn-xs' onclick='fdelete(" + obj.id + ")'>删除</button></td>");

					tr.append(td1);
					tr.append(td2);
					tr.append(td3);
					tr.append(td4);
					tr.append(td5);
					tr.append(td6);
					tr.append(td7);
					tr.append(td8);
					tr.append(td9);
					
					$("#userData").append(tr);
				}
			}
		},
		error : function() {
			alert("服务器端出错！");
		}
	});
}


