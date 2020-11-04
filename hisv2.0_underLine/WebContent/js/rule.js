/**
 * 医生排班管理界面js
 */

$(function() {
	loadData(1);
})

/*
 * 异步加载数据，根据传入的当前页，获取该页数据并渲染到tbody中
 */
function loadData(currpage) {
	$.ajax({
		url : "FindAllRule",
		type : "post",
		data : {currentPage : currpage},
		success : function(data) {
//			console.log(data);
			/*
			 * {"currentPage":1,"pageSize":10,"totalCount":4,"totalPage":1,"beginIndex":0,
			 * "pageData":[
			 * {"id":1,"ruleName":"r1","deptID":1,"week":"10101010101010","delMark":1,"deptName":"胸外科","userName":"扁鹊","userID":1},
			 * {"id":2,"ruleName":"r1","deptID":2,"week":"01010101010101","delMark":1,"deptName":"心血管内科","userName":"张仲景","userID":2},
			 * {"id":3,"ruleName":"r1","deptID":6,"week":"11111111000000","delMark":1,"deptName":"呼吸内科","userName":"李时珍","userID":3},
			 * {"id":4,"ruleName":"r1","deptID":7,"week":"00000011111111","delMark":1,"deptName":"康复科","userName":"测试杨","userID":4}]}
			 * */
			if(data.totalCount == 0) {
				var tr = $("<tr><td colspan='16'>暂无数据</td></tr>");
				$("#ruleData").append(tr);
			} else {
				$("#ruleData").children().remove();
				$("#pagediv").children().remove();
				// 填充数据
				for (var i = 0 ; i < data.pageData.length ; i++) {
					var obj = data.pageData[i];
					var week = obj.week;
					
					var tr = $("<tr></tr>");
					var td1 = $("<td><input type='checkbox' name='all'></td>");
					var td2 = $("<td>" + obj.userName + "</td>");
					var td3 = $("<td>" + obj.ruleName + "</td>");
					tr.append(td1);
					tr.append(td2);
					tr.append(td3);
					
					for(var j = 0 ; j < week.length ; j++) {
						if(week.charAt(j) == 1){
							var tdn = $("<td>值班</td>")
						} else {
							var tdn = $("<td>休息</td>")
						}
						tr.append(tdn);
					}
					
					var td4 = $("<td><button class='btn btn-warning btn-xs' onclick='fupdate(" + JSON.stringify(obj) + ")'>修改</button>	</td>");
					
					tr.append(td4);
					$("#ruleData").append(tr);
				}
				// 显示分页
				showPage(data);
			}
		},
		error : function() {
			alert("服务器端出错！");
		}
	});
}


/*
 * 点击查询按钮，根据输入框内容进行模糊查询
 */
function findByCode() {
	var content = $("#userName").val();
	$.ajax({
		url : "FindRuleByUname",
		type : "post",
		data : {uname : content},
		success : function(data) {
//			console.log(data);
//			[{"id":1,"ruleName":"r1","deptID":1,"week":"10101010101010","delMark":1,"deptName":"胸外科","userName":"扁鹊","userID":1}]
			$("#ruleData").children().remove();
			$("#pagediv").children().remove();
			if(data.length == 0){
				var tr = $("<tr><td colspan='18'>暂无数据</td></tr>");
				$("#ruleData").append(tr);
			} else {
				
				// 填充数据
				for (var i = 0 ; i < data.length ; i++) {
					var obj = data[i];
					var week = obj.week;
					
					var tr = $("<tr></tr>");
					var td1 = $("<td><input type='checkbox' name='all'></td>");
					var td2 = $("<td>" + obj.userName + "</td>");
					var td3 = $("<td>" + obj.ruleName + "</td>");
					tr.append(td1);
					tr.append(td2);
					tr.append(td3);
					
					for(var j = 0 ; j < week.length ; j++) {
						if(week.charAt(j) == 1){
							var tdn = $("<td>值班</td>")
						} else {
							var tdn = $("<td>休息</td>")
						}
						tr.append(tdn);
					}
					
					var td4 = $("<td><button class='btn btn-warning btn-xs' onclick='fupdate(" + JSON.stringify(obj) + ")'>修改</button>	</td>");
					
					tr.append(td4);
					$("#ruleData").append(tr);
				}
			}
		},
		error : function() {
			alert("服务器端出错！");
		}
	});
}


/*
 * 点击添加按钮，显示id为saveAndUpdate的模态框
 */
function fadd() {
	$.ajax({
		url : "FindRuleAboutUser",
		type : "post",
		success : function(data) {
//			console.log(data);
			/*
			 * {"deptName":[
			 * {"id":1,"deptCode":"XWK","deptName":"胸外科","deptCategoryID":3,"deptType":19,"delMark":1,"deptCategoryName":null,"deptTypeName":null},
			 * {"id":2,"deptCode":"XXGNK","deptName":"心血管内科","deptCategoryID":3,"deptType":19,"delMark":1,"deptCategoryName":null,"deptTypeName":null},
			 * {"id":3,"deptCode":"SJNK","deptName":"神经内科","deptCategoryID":3,"deptType":19,"delMark":1,"deptCategoryName":null,"deptTypeName":null},
			 * {"id":4,"deptCode":"PTNK","deptName":"普通内科","deptCategoryID":3,"deptType":19,"delMark":1,"deptCategoryName":null,"deptTypeName":null},
			 * {"id":5,"deptCode":"XHNK","deptName":"消化内科","deptCategoryID":3,"deptType":19,"delMark":1,"deptCategoryName":null,"deptTypeName":null},
			 * {"id":6,"deptCode":"HXNK","deptName":"呼吸内科","deptCategoryID":3,"deptType":19,"delMark":1,"deptCategoryName":null,"deptTypeName":null},
			 * {"id":7,"deptCode":"KFK","deptName":"康复科","deptCategoryID":3,"deptType":20,"delMark":1,"deptCategoryName":null,"deptTypeName":null},
			 * {"id":8,"deptCode":"ZLK","deptName":"治疗科","deptCategoryID":3,"deptType":20,"delMark":1,"deptCategoryName":null,"deptTypeName":null},
			 * {"id":9,"deptCode":"GHC","deptName":"挂号处","deptCategoryID":1,"deptType":22,"delMark":1,"deptCategoryName":null,"deptTypeName":null},
			 * {"id":10,"deptCode":"CSK","deptName":"测试科","deptCategoryID":1,"deptType":22,"delMark":1,"deptCategoryName":null,"deptTypeName":null}],
			 * "registName":[
			 * {"id":1,"registCode":"WKZJH","registName":"外科专家号","sequenceNo":1,"registFee":60.0,"registQuota":20,"delMark":0},
			 * {"id":2,"registCode":"WKPTH","registName":"外科普通号","sequenceNo":3,"registFee":20.5,"registQuota":60,"delMark":0},
			 * {"id":3,"registCode":"WKJZH","registName":"外科急诊号","sequenceNo":2,"registFee":40.0,"registQuota":40,"delMark":0},
			 * {"id":4,"registCode":"NKZJH","registName":"内科专家号","sequenceNo":1,"registFee":60.0,"registQuota":20,"delMark":0},
			 * {"id":5,"registCode":"NKPTH","registName":"内科普通号","sequenceNo":3,"registFee":20.0,"registQuota":60,"delMark":0},
			 * {"id":6,"registCode":"NKJZH","registName":"内科急诊号","sequenceNo":2,"registFee":40.0,"registQuota":40,"delMark":0},
			 * {"id":7,"registCode":"CSZJH","registName":"测试专家号","sequenceNo":-1,"registFee":2.5,"registQuota":888,"delMark":0}],
			 * "uname":[
			 * {"id":1,"userName":null,"password":null,"realName":"扁鹊","userType":0,"docTitleID":0,"isScheduling":null,"deptID":1,"registLeID":0,"delMark":0,"deptname":null,"docTitleName":null,"registLeName":null,"registFee":null,"userTypeName":null},
			 * {"id":2,"userName":null,"password":null,"realName":"张仲景","userType":0,"docTitleID":0,"isScheduling":null,"deptID":2,"registLeID":0,"delMark":0,"deptname":null,"docTitleName":null,"registLeName":null,"registFee":null,"userTypeName":null},
			 * {"id":3,"userName":null,"password":null,"realName":"李时珍","userType":0,"docTitleID":0,"isScheduling":null,"deptID":6,"registLeID":0,"delMark":0,"deptname":null,"docTitleName":null,"registLeName":null,"registFee":null,"userTypeName":null}]}
			 * */
			var deptName = data.deptName;
			var registName = data.registName;
			
			$("#infoDeptName")[0].options.length = 1;
			$("#infoRegistLevel")[0].options.length = 1;
			$("#userInRuleData").children().remove();
			
			for(var i = 0 ; i < deptName.length ; i++){
				var obj = deptName[i];
				if(obj.deptType == 19){
					var option = $("<option value='" + obj.id + "'>" + obj.deptName + "</option>");
					$("#infoDeptName").append(option);
				}
			}
			
			for(var i = 0 ; i < registName.length ; i++){
				var obj = registName[i];
				var option = $("<option value='" + obj.id + "'>" + obj.registName + "</option>");
				$("#infoRegistLevel").append(option);
			}
			
			for(var i = 0 ; i < data.uname.length ; i++) {
				var tr = $("<tr></tr>");
				var td1 = $("<td><input type='checkbox' name='all'></td>");
				var td2 = $("<td>" + data.uname[i].realName + "</td>");
				var td3 = $("<td><input type='checkbox' name='mon1'></td>");
				var td4 = $("<td><input type='checkbox' name='mon2'></td>");
				var td5 = $("<td><input type='checkbox' name='tue1'></td>");
				var td6 = $("<td><input type='checkbox' name='tue2'></td>");
				var td7 = $("<td><input type='checkbox' name='wed1'></td>");
				var td8 = $("<td><input type='checkbox' name='wed2'></td>");
				var td9 = $("<td><input type='checkbox' name='thu1'></td>");
				var td10 = $("<td><input type='checkbox' name='thu2'></td>");
				var td11 = $("<td><input type='checkbox' name='fri1'></td>");
				var td12 = $("<td><input type='checkbox' name='fri2'></td>");
				var td13 = $("<td><input type='checkbox' name='sat1'></td>");
				var td14 = $("<td><input type='checkbox' name='sat2'></td>");
				var td15 = $("<td><input type='checkbox' name='sun1'></td>");
				var td16 = $("<td><input type='checkbox' name='sun2'></td>");
				var td201 = $("<input type='hidden' id='uid" + (i+1) + "' value='" + data.uname[i].id + "'>");
				var td202 = $("<input type='hidden' id='deptId" + (i+1) + "' value='" + data.uname[i].deptID + "'>");
				td2.append(td201);
				td2.append(td202);
				
				tr.append(td1);
				tr.append(td2);
				tr.append(td3);
				tr.append(td4);
				tr.append(td5);
				tr.append(td6);
				tr.append(td7);
				tr.append(td8);
				tr.append(td9);
				tr.append(td10);
				tr.append(td11);
				tr.append(td12);
				tr.append(td13);
				tr.append(td14);
				tr.append(td15);
				tr.append(td16);
				$("#userInRuleData").append(tr);
			}

			$('#saveAndUpdate').modal('show');
		},
		error : function() {
			alert("服务器端出错！");
		}
	});
}


function doselect(deptid,regid) {
	$.ajax({
		url : "FindUserByDeptAndReg",
		type : "post",
		data : {
			deptid : deptid,
			regid : regid
		},
		success : function(data) {
//			console.log(data);
			/*
			 * [{"id":1,"userName":null,"password":null,"realName":"扁鹊","userType":0,"docTitleID":0,"isScheduling":null,"deptID":1,"registLeID":0,"delMark":0,"deptname":null,"docTitleName":null,"registLeName":null,"registFee":null,"userTypeName":null}]
			 * */
			$("#userInRuleData").children().remove();
			
			if(data.length == 0) {
				var tr = $("<tr><td colspan='16'>暂无数据</td></tr>");
				$("#userInRuleData").append(tr);
			} else {
				for(var i = 0 ; i < data.length ; i++) {
					var tr = $("<tr></tr>");
					var td1 = $("<td><input type='checkbox' name='all'></td>");
					var td2 = $("<td>" + data[i].realName + "</td>");
					var td3 = $("<td><input type='checkbox' name='mon1'></td>");
					var td4 = $("<td><input type='checkbox' name='mon2'></td>");
					var td5 = $("<td><input type='checkbox' name='tue1'></td>");
					var td6 = $("<td><input type='checkbox' name='tue2'></td>");
					var td7 = $("<td><input type='checkbox' name='wed1'></td>");
					var td8 = $("<td><input type='checkbox' name='wed2'></td>");
					var td9 = $("<td><input type='checkbox' name='thu1'></td>");
					var td10 = $("<td><input type='checkbox' name='thu2'></td>");
					var td11 = $("<td><input type='checkbox' name='fri1'></td>");
					var td12 = $("<td><input type='checkbox' name='fri2'></td>");
					var td13 = $("<td><input type='checkbox' name='sat1'></td>");
					var td14 = $("<td><input type='checkbox' name='sat2'></td>");
					var td15 = $("<td><input type='checkbox' name='sun1'></td>");
					var td16 = $("<td><input type='checkbox' name='sun2'></td>");
					var td201 = $("<input type='hidden' id='uid" + (i+1) + "' value='" + data[i].id + "'>");
					var td202 = $("<input type='hidden' id='deptId" + (i+1) + "' value='" + data[i].deptID + "'>");
					td2.append(td201);
					td2.append(td202);
					
					tr.append(td1);
					tr.append(td2);
					tr.append(td3);
					tr.append(td4);
					tr.append(td5);
					tr.append(td6);
					tr.append(td7);
					tr.append(td8);
					tr.append(td9);
					tr.append(td10);
					tr.append(td11);
					tr.append(td12);
					tr.append(td13);
					tr.append(td14);
					tr.append(td15);
					tr.append(td16);
					$("#userInRuleData").append(tr);
				}
			}
		},
		error : function() {
			alert("服务器端出错！");
		}
	});
}


/*
 * 点击模态框中查询按钮，寻找指定部门及挂号级别的人员
 */
function findForSetRule(){
	var deptid = $("#infoDeptName option:selected").val();
	var regid = $("#infoRegistLevel option:selected").val();
	doselect(deptid, regid);
}


/*
 * 点击模态框中清空按钮
 */
function resetInfo() {
	doselect(null, null);
	$("#selectDeptAndRegist")[0].reset();
}


/*
 * 点击模态框中的保存/修改按钮，隐藏id为saveAndUpdate的模态框
 */
function doSaveOrUpdate() {
	var ruleName = $("#ruleName").val();
	var addnum = 0;
//	console.log($.trim(ruleName))

	if($.trim(ruleName).length == 0){
		alert("请输入规则名称");
	} else {
		var trs = $("#userInRuleData tr");
//		console.log(trs);
		// var ruleInfo = new Array();
		for(var i = 0 ; i < trs.length ; i++) {
//			console.log( trs.children());
			var tds = trs.eq(i).children(); //每行的td
			var uname = tds.eq(1).text();
			var uid = tds.eq(1).children().eq(0).val();
			var deptid = tds.eq(1).children().eq(1).val();
			
//			console.log(uname)
			var week = "";
			for(var j = 2 ; j < tds.length ; j++) {
				var isSche = tds.eq(j).children().is(':checked');
//				console.log(tds.eq(j).children()); //input标签
//				console.log(isSche);
				if(isSche){
					week += "1";
				} else {
					week += "0";
				}
			}
			
			// ruleInfo.push([uid,uname,deptid,week]);
			
			
			$.ajax({
				url : "SaveRule",
				type : "post",
				data : {
					ruleName : ruleName,
					uid : uid,
					deptid : deptid,
					week : week
				},
				success : function(data) {
//					console.log(data);
					addnum += data.msg;
				},
				error : function() {
					alert("服务器端出错");
				}
			});
		}
		
		if (i == addnum){
			
		}
	}
	
//	console.log(ruleInfo)
	
}
