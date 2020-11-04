/**
 * 挂号级别管理界面js
 */

$(function() {
	loadData(1);
})

/*
 * 异步加载数据，根据传入的当前页，获取该页数据并渲染到tbody中
 */
function loadData(currpage) {
	$.ajax({
		url : "FindAllRegistLevel",
		type : "post",
		data : {currentPage : currpage},
		success : function(data) {
//			console.log(data);
			/*
			 * {"currentPage":1,"pageSize":10,"totalCount":6,"totalPage":1,"beginIndex":0,
			 * "pageData":[
			 * {"id":1,"registCode":"WKZJH","registName":"外科专家号","sequenceNo":1,"registFee":60.0,"registQuota":20,"delMark":0},
			 * {"id":2,"registCode":"WKPTH","registName":"外科普通号","sequenceNo":3,"registFee":20.0,"registQuota":60,"delMark":0},
			 * {"id":3,"registCode":"WKJZH","registName":"外科急诊号","sequenceNo":2,"registFee":40.0,"registQuota":40,"delMark":0},
			 * {"id":4,"registCode":"NKZJH","registName":"内科专家号","sequenceNo":1,"registFee":60.0,"registQuota":20,"delMark":0},
			 * {"id":5,"registCode":"NKPTH","registName":"内科普通号","sequenceNo":3,"registFee":20.0,"registQuota":60,"delMark":0},
			 * {"id":6,"registCode":"NKJZH","registName":"内科急诊号","sequenceNo":2,"registFee":40.0,"registQuota":40,"delMark":0}]}
			 * */
//			data.totalCount = 0;
			if(data.totalCount == 0) {
				var tr = $("<tr><td colspan='7'>暂无数据</td></tr>");
				$("#registLevelData").append(tr);
			} else {
				$("#registLevelData").children().remove();
				$("#pagediv").children().remove();
				
				// 填充数据
				for(var i = 0 ; i < data.pageData.length ; i++) {
					var obj = data.pageData[i];
					var tr = $("<tr></tr>");
					var td1 = $("<td><input type='checkbox' name='all'></td>");
					var td2 = $("<td>" + obj.registCode + "</td>");
					var td3 = $("<td>" + obj.registName + "</td>");
					var td4 = $("<td>" + obj.sequenceNo + "</td>");
					var td5 = $("<td>" + obj.registFee + "元/号</td>");
					var td6 = $("<td>" + obj.registQuota + "位/天</td>");
					
					var td7 = $("<td><button class='btn btn-success btn-xs' onclick=fsee(" + obj.id + ")>查看人员</button>&nbsp;&nbsp;"
							+ "<button class='btn btn-warning btn-xs' onclick='fupdate(" + JSON.stringify(obj) + ")'>修改</button>&nbsp;&nbsp;"
							+ "<button class='btn btn-danger btn-xs' onclick='fdelete(" + obj.id + ")'>删除</button></td>");
					
					tr.append(td1);
					tr.append(td2);
					tr.append(td3);
					tr.append(td4);
					tr.append(td5);
					tr.append(td6);
					tr.append(td7);
					$("#registLevelData").append(tr);
				}
				// 分页
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
	$("#registLevelid").val(id);
}


/*
 * 点击确认按钮，异步请求，执行删除操作
 */
function doDelete() {
	var regid = $("#registLevelid").val();
	$.ajax({
		url : "DeleteRegistLevelById",
		type : "post",
		data : {registLevelId :regid},
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
 * 点击查看本级别的人员按钮，显示查看框
 */
function fsee(regid) {
//	console.log(regid);
	$.ajax({
		url : "FindUserByRegistLevelId",
		type : "post",
		data : {registLeId : regid},
		success : function(data) {
//			console.log(data);
			/*
			 *[{"id":1,"userName":null,"password":null,"realName":"扁鹊","userType":0,
			 *"docTitleID":0,"isScheduling":null,"deptID":0,"registLeID":0,"delMark":0,
			 *"deptname":null,"docTitleName":null,"registLeName":"外科专家号","registFee":"60.00","userTypeName":null}] 
			 * */
			$("#RegistLevelToUserData").children().remove();
			if(data.length == 0){
				var tr = $("<tr><td colspan='4'>暂无数据</td></tr>");
				$("#RegistLevelToUserData").append(tr);
			} else {
				for(var i = 0 ; i < data.length ; i++){
					var obj = data[i];
					var tr = $("<tr></tr>");
					var td1 = $("<td>" + obj.id + "</td>");
					var td2 = $("<td>" + obj.realName + "</td>");
					var td3 = $("<td>" + obj.registLeName + "</td>");
					var td4 = $("<td>" + obj.registFee + "</td>");
					
					tr.append(td1);
					tr.append(td2);
					tr.append(td3);
					tr.append(td4);

					$("#RegistLevelToUserData").append(tr);
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
	var content = $("#registCode").val();
//	console.log(content);
	$.ajax({
		url : "FindRegistLevelByCode",
		type : "post",
		data : {regCode : content},
		success : function(data) {
//			console.log(data);
			/*
			 * [{"id":1,"registCode":"WKZJH","registName":"外科专家号","sequenceNo":1,"registFee":60.0,"registQuota":20,"delMark":0},
			 * {"id":4,"registCode":"NKZJH","registName":"内科专家号","sequenceNo":1,"registFee":60.0,"registQuota":20,"delMark":0}]
			 * */
			
			$("#registLevelData").children().remove();
			if(data.length == 0){
				var tr = $("<tr><td colspan='7'>暂无数据</td></tr>");
				$("#registLevelData").append(tr);
			} else {
				
				// 填充数据
				for(var i = 0 ; i < data.length ; i++) {
					var obj = data[i];
					var tr = $("<tr></tr>");
					var td1 = $("<td><input type='checkbox' name='all'></td>");
					var td2 = $("<td>" + obj.registCode + "</td>");
					var td3 = $("<td>" + obj.registName + "</td>");
					var td4 = $("<td>" + obj.sequenceNo + "</td>");
					var td5 = $("<td>" + obj.registFee + "元/号</td>");
					var td6 = $("<td>" + obj.registQuota + "位/天</td>");
					
					var td7 = $("<td><button class='btn btn-success btn-xs' onclick=fsee(" + obj.id + ")>查看人员</button>&nbsp;&nbsp;"
							+ "<button class='btn btn-warning btn-xs' onclick='fupdate(" + JSON.stringify(obj) + ")'>修改</button>&nbsp;&nbsp;"
							+ "<button class='btn btn-danger btn-xs' onclick='fdelete(" + obj.id + ")'>删除</button></td>");
					
					tr.append(td1);
					tr.append(td2);
					tr.append(td3);
					tr.append(td4);
					tr.append(td5);
					tr.append(td6);
					tr.append(td7);
					$("#registLevelData").append(tr);
				}
			}
		},
		error : function() {
			alert("服务器端出错");
		}
	});
}


/*
 * 点击添加按钮，显示id为saveAndUpdate的模态框
 */
function fadd() {
	$("#addRegistCode").val("");
	$("#addRegistName").val("");
	$("#addSequenceNo").val("");
	$("#addRegistFee").val("");
	$("#addRegistQuota").val("");
	$("#addRegistLevelId").val("");
	$("#saveAndUpdateLabel").text("添加挂号等级");
	$("#addBtn").text("保存");
	$('#saveAndUpdate').modal('show');
}


/*
 * 点击修改按钮，显示id为saveAndUpdate的模态框
 */
function fupdate(obj) {
	$("#addRegistCode").val(obj.registCode);
	$("#addRegistName").val(obj.registName);
	$("#addSequenceNo").val(obj.sequenceNo);
	$("#addRegistFee").val(obj.registFee);
	$("#addRegistQuota").val(obj.registQuota);
	// 给隐藏表单域设置id的值
	$("#addRegistLevelId").val(obj.id);
	
	$("#saveAndUpdateLabel").text("修改挂号等级");
	$("#addBtn").text("修改");
	$('#saveAndUpdate').modal('show');
}


/*
 * 点击模态框中的保存/修改按钮，显示id为saveAndUpdate的模态框
 */
function doSaveOrUpdate() {
	var op = $("#addBtn").text();
	
	var regId = $("#addRegistLevelId").val();
	var regCode = $("#addRegistCode").val();
	var regName = $("#addRegistName").val();
	var sequenceNo = $("#addSequenceNo").val();
	var regFee = $("#addRegistFee").val();
	var regQuota = $("#addRegistQuota").val();
	
	$.ajax({
		url : "SaveRegistLevel",
		type : "post",
		data : {
			regId : regId,
			regCode : regCode,
			regName : regName,
			sequenceNo : sequenceNo,
			regFee : regFee,
			regQuota : regQuota
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
