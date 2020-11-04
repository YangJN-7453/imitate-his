/**
 * 非药品收费项目管理界面js
 */

$(function() {
	loadData(1);
})

/*
 * 异步加载数据，根据传入的当前页，获取该页数据并渲染到tbody中
 */
function loadData(currpage) {
	$.ajax({
		url : "FindAllFmeditem",
		type : "post",
		data : {currentPage : currpage},
		success : function(data) {
//			console.log(data);
			/*
			 * {"currentPage":1,"pageSize":10,"totalCount":9,"totalPage":1,"beginIndex":0,
			 * "pageData":[
			 * {"id":1,"itemCode":"340100001","itemName":"红外线理疗","format":"次","price":8.5,"expClassID":1,"deptID":8,"mnemonicCode":"HWXLL","creationDate":1602000000000,"lastUpdateDate":1602000000000,"recordType":3,"delmark":0,"expClassName":"理疗","deptName":"治疗科"},
			 * {"id":2,"itemCode":"410000003","itemName":"中药涂擦","format":"次","price":12.5,"expClassID":2,"deptID":8,"mnemonicCode":"ZYTC","creationDate":1602000000000,"lastUpdateDate":1602000000000,"recordType":3,"delmark":0,"expClassName":"换药","deptName":"治疗科"},
			 * {"id":3,"itemCode":"340200026","itemName":"关节功能锻炼","format":"次","price":18.7,"expClassID":3,"deptID":7,"mnemonicCode":"GJGNDL","creationDate":1602000000000,"lastUpdateDate":1602000000000,"recordType":3,"delmark":0,"expClassName":"康复","deptName":"康复科"},
			 * {"id":4,"itemCode":"450000006","itemName":"腰椎间盘推拿","format":"次","price":25.5,"expClassID":1,"deptID":7,"mnemonicCode":"YZJPTN","creationDate":1602000000000,"lastUpdateDate":1602000000000,"recordType":3,"delmark":0,"expClassName":"理疗","deptName":"康复科"},
			 * {"id":5,"itemCode":"430000022","itemName":"穴位注射","format":"穴","price":6.0,"expClassID":1,"deptID":8,"mnemonicCode":"XWZS","creationDate":1602000000000,"lastUpdateDate":1602000000000,"recordType":3,"delmark":0,"expClassName":"理疗","deptName":"治疗科"},
			 * {"id":6,"itemCode":"200000001","itemName":"挂号","format":"号","price":10.0,"expClassID":4,"deptID":9,"mnemonicCode":"GH","creationDate":1602000000000,"lastUpdateDate":1602000000000,"recordType":1,"delmark":0,"expClassName":"挂号","deptName":"挂号处"},
			 * {"id":7,"itemCode":"200000002","itemName":"诊断","format":"位","price":5.0,"expClassID":4,"deptID":1,"mnemonicCode":"ZD","creationDate":1602000000000,"lastUpdateDate":1602000000000,"recordType":1,"delmark":0,"expClassName":"挂号","deptName":"胸外科"},
			 * {"id":8,"itemCode":"100000001","itemName":"血常规","format":"位","price":30.0,"expClassID":5,"deptID":10,"mnemonicCode":"XCG","creationDate":1602000000000,"lastUpdateDate":1602000000000,"recordType":2,"delmark":0,"expClassName":"检验","deptName":"测试科"},
			 * {"id":9,"itemCode":"100000002","itemName":"肝功能三项","format":"位","price":30.0,"expClassID":5,"deptID":10,"mnemonicCode":"GGNSX","creationDate":1602000000000,"lastUpdateDate":1602000000000,"recordType":2,"delmark":0,"expClassName":"检验","deptName":"测试科"}]}
			 * */
//			data.totalCount = 0;
			if(data.totalCount == 0) {
				var tr = $("<tr><td colspan='11'>暂无数据</td></tr>");
				$("#fmeditemData").append(tr);
			} else {
				$("#fmeditemData").children().remove();
				$("#pagediv").children().remove();
				// 填充数据
				for (var i = 0 ; i < data.pageData.length ; i++) {
					var obj = data.pageData[i];
					var tr = $("<tr></tr>");
					var td1 = $("<td><input type='checkbox' name='all'></td>");
					var td2 = $("<td>" + obj.itemCode + "</td>");
					var td3 = $("<td>" + obj.itemName + "</td>");
					var td4 = $("<td>" + obj.price + "元/" + obj.format + "</td>");
					var td5 = $("<td>" + obj.expClassName + "</td>");
					var td6 = $("<td>" + obj.deptName + "</td>");
					var td7 = $("<td>" + obj.mnemonicCode + "</td>");
					var td8 = $("<td>" + obj.creationDate + "</td>");
					var td9 = $("<td>" + obj.lastUpdateDate + "</td>");
					var recordTypeName ;
					switch (obj.recordType) {
					case 1:
						recordTypeName = "检查";
						break;
					case 2:
						recordTypeName = "检验";
						break;
					case 3:
						recordTypeName = "处置";
						break;
					default:
						break;
					}
					var td10 = $("<td>" + recordTypeName + "</td>");
					
					var td11 = $("<td><button class='btn btn-warning btn-xs' onclick='fupdate(" + JSON.stringify(obj) + ")'>修改</button>&nbsp;&nbsp;"
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
					tr.append(td10);
					tr.append(td11);
					$("#fmeditemData").append(tr);
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
 * 点击“删除”按钮，显示删除确认框
 */
function fdelete(id) {
	// 首先调用删除确认框显示
	$('#myModal').modal('show');
	
	// 传参：将要删除的行的id值传入到隐藏表单域中
	$("#fmesitemId").val(id);
}


/*
 * 点击确认按钮，异步请求，执行删除操作
 */
function doDelete() {
	var fmId = $("#fmesitemId").val();
	$.ajax({
		url : "DeleteFmeditemById",
		type : "post",
		data : {fmeditemId : fmId},
		success : function(data) {
//			console.log(data);
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
 * 点击查询按钮，根据输入框内容进行模糊查询
 */
function findByCode() {
	var content = $("#fmeditemCode").val();
	$.ajax({
		url : "FindFmeditemByCode",
		type : "post",
		data : {mnemonicCode : content},
		success : function(data) {
			console.log(data);
			/*
			 * [{"id":1,"itemCode":"340100001","itemName":"红外线理疗","format":"次","price":8.5,"expClassID":1,"deptID":8,"mnemonicCode":"HWXLL","creationDate":"2020-10-07","lastUpdateDate":"2020-10-07","recordType":3,"delmark":0,"expClassName":"理疗","deptName":"治疗科"}]
			 * */
			$("#fmeditemData").children().remove();
			if(data.length == 0) {
				var tr = $("<tr><td colspan='11'>暂无数据</td></tr>");
				$("#fmeditemData").append(tr);
			} else {
				// 填充数据
				for (var i = 0 ; i < data.length ; i++) {
					var obj = data[i];
					var tr = $("<tr></tr>");
					var td1 = $("<td><input type='checkbox' name='all'></td>");
					var td2 = $("<td>" + obj.itemCode + "</td>");
					var td3 = $("<td>" + obj.itemName + "</td>");
					var td4 = $("<td>" + obj.price + "元/" + obj.format + "</td>");
					var td5 = $("<td>" + obj.expClassName + "</td>");
					var td6 = $("<td>" + obj.deptName + "</td>");
					var td7 = $("<td>" + obj.mnemonicCode + "</td>");
					var td8 = $("<td>" + obj.creationDate + "</td>");
					var td9 = $("<td>" + obj.lastUpdateDate + "</td>");
					var td10 = $("<td>" + obj.recordType + "</td>");
					
					var td11 = $("<td><button class='btn btn-warning btn-xs' onclick='fupdate(" + JSON.stringify(obj) + ")'>修改</button>&nbsp;&nbsp;"
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
					tr.append(td10);
					tr.append(td11);
					$("#fmeditemData").append(tr);
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
//	$('#saveAndUpdate').modal('show');
	/*
	 * {"deptNames":[
	 * {"id":1,"deptCode":"XWK","deptName":"胸外科","deptCategoryID":3,"deptType":19,"delMark":1,"deptCategoryName":null,"deptTypeName":null},
	 * {"id":2,"deptCode":"XXGNK","deptName":"心血管内科","deptCategoryID":3,"deptType":19,"delMark":1,"deptCategoryName":null,"deptTypeName":null},
	 * {"id":3,"deptCode":"SJNK","deptName":"神经内科","deptCategoryID":3,"deptType":19,"delMark":1,"deptCategoryName":null,"deptTypeName":null},
	 * {"id":4,"deptCode":"PTNK","deptName":"普通内科","deptCategoryID":3,"deptType":19,"delMark":1,"deptCategoryName":null,"deptTypeName":null},
	 * {"id":5,"deptCode":"XHNK","deptName":"消化内科","deptCategoryID":3,"deptType":19,"delMark":1,"deptCategoryName":null,"deptTypeName":null},
	 * {"id":6,"deptCode":"HXNK","deptName":"呼吸内科","deptCategoryID":3,"deptType":20,"delMark":1,"deptCategoryName":null,"deptTypeName":null},
	 * {"id":7,"deptCode":"KFK","deptName":"康复科","deptCategoryID":3,"deptType":19,"delMark":1,"deptCategoryName":null,"deptTypeName":null},
	 * {"id":8,"deptCode":"ZLK","deptName":"治疗科","deptCategoryID":3,"deptType":19,"delMark":1,"deptCategoryName":null,"deptTypeName":null},
	 * {"id":9,"deptCode":"GHC","deptName":"挂号处","deptCategoryID":1,"deptType":22,"delMark":1,"deptCategoryName":null,"deptTypeName":null},
	 * {"id":10,"deptCode":"CSK","deptName":"测试科","deptCategoryID":1,"deptType":22,"delMark":1,"deptCategoryName":null,"deptTypeName":null}],
	 * "expClassNames":[
	 * {"id":1,"expCode":null,"expName":"理疗","delMark":0},
	 * {"id":2,"expCode":null,"expName":"换药","delMark":0},
	 * {"id":3,"expCode":null,"expName":"康复","delMark":0},
	 * {"id":4,"expCode":null,"expName":"挂号","delMark":0},
	 * {"id":5,"expCode":null,"expName":"检验","delMark":0}]}
	 * */
	$.ajax({
		url : "FindFmeditemInfo",
		type : "post",
		success : function(data) {
//			console.log(data);
			var deptNames = data.deptNames;
			var expClassNames = data.expClassNames;
			
			$("#addDeptName")[0].options.length = 1;
			$("#addExpClassName")[0].options.length = 1;
			
			// 将执行科室填充到下拉列表中
			for(var i = 0 ; i < deptNames.length ; i++){
				var obj = deptNames[i];
				var option = $("<option value='" + obj.id + "'>" + obj.deptName + "</option>");
				$("#addDeptName").append(option);
			}
			
			// 将所属费用科目填充到下拉列表中
			for(var i = 0 ; i < expClassNames.length ; i++){
				var obj = expClassNames[i];
				var option = $("<option value='" + obj.id + "'>" + obj.expName + "</option>");
				$("#addExpClassName").append(option);
			}
			
			$("#addItemCode").val("");
			$("#addItemName").val("");
			$("#addPrice").val("");
			$("#addFormat").val("");
			$("#addMnemonicCode").val("");
			$("#saveAndUpdateLabel").text("新增非药品收费项目");
			$("#addBtn").text("添加");
			$('#saveAndUpdate').modal('show');
		},
		error : function() {
			alert("服务器端出错");
		}
	});
}


/*
 * 点击修改按钮，显示id为saveAndUpdate的模态框
 */
function fupdate(fmedItemObj) {
//	console.log(fmedItemObj);
	
	$("#addItemId").val(fmedItemObj.id);
	$.ajax({
		url : "FindFmeditemInfo",
		type : "post",
		success : function(data) {
//			console.log(data);
			var deptNames = data.deptNames;
			var expClassNames = data.expClassNames;
			
			$("#addDeptName")[0].options.length = 1;
			$("#addExpClassName")[0].options.length = 1;
			
			// 将执行科室填充到下拉列表中
			for(var i = 0 ; i < deptNames.length ; i++){
				var obj = deptNames[i];
				var option = $("<option value='" + obj.id + "'>" + obj.deptName + "</option>");
				$("#addDeptName").append(option);
				
				if(option.val() == fmedItemObj.deptID){
					option[0].selected = true;
				}
			}
			
			// 将所属费用科目填充到下拉列表中
			for(var i = 0 ; i < expClassNames.length ; i++){
				var obj = expClassNames[i];
				var option = $("<option value='" + obj.id + "'>" + obj.expName + "</option>");
				$("#addExpClassName").append(option);
				
				if(option.val() == fmedItemObj.expClassID){
					option[0].selected = true;
				}
			}

			var addRecordType = $("#addRecordType option");
			for(var i = 0 ; i < addRecordType.length ; i++){
				if(addRecordType.eq(i).val() == fmedItemObj.recordType){
					addRecordType.eq(i)[0].selected = true;
				}
			}
			
			$("#addItemCode").val(fmedItemObj.itemCode);
			$("#addItemName").val(fmedItemObj.itemName);
			$("#addPrice").val(fmedItemObj.price);
			$("#addFormat").val(fmedItemObj.format);
			$("#addMnemonicCode").val(fmedItemObj.mnemonicCode);
			$("#saveAndUpdateLabel").text("修改非药品收费项目信息");
			$("#addBtn").text("修改");
			$('#saveAndUpdate').modal('show');
		},
		error : function() {
			alert("服务器端出错");
		}
	});
}


/*
 * 点击模态框中的保存/修改按钮，显示id为saveAndUpdate的模态框
 */
function doSaveOrUpdate() {
	var op = $("#addBtn").text();

	var fmeditemId = $("#addItemId").val();
	var addItemCode = $("#addItemCode").val();
	var addItemName = $("#addItemName").val();
	var addPrice = $("#addPrice").val();
	var addFormat = $("#addFormat").val();
	var addExpClassId = $("#addExpClassName option:selected").val();
	var addDeptId = $("#addDeptName option:selected").val();
	var addMnemonicCode = $("#addMnemonicCode").val();
	var addRecordType = $("#addRecordType option:selected").val();
	
	$.ajax({
		url : "SaveFmeditem",
		type : "post",
		data : {
			fmeditemId : fmeditemId,
			itemCode : addItemCode,
			itemName : addItemName,
			price : addPrice,
			format : addFormat,
			expClassId : addExpClassId,
			deptId : addDeptId,
			mnemonicCode : addMnemonicCode,
			recordType : addRecordType
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
