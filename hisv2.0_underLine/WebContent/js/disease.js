/**
 * 疾病目录管理界面js
 */


$(function() {
	loadData(1);
})

/*
 * 异步加载数据，根据传入的当前页，获取该页数据并渲染到tbody中
 */
function loadData(currpage) {
	/*
	 *{"currentPage":1,"pageSize":10,"totalCount":64,"totalPage":7,"beginIndex":0,
	 * "pageData":[
	 * {"id":1,"diseaseCode":"GDXHL","diseaseName":"古典性霍乱","diseaseICD":"A00.051","diseCategoryID":1,"delMark":1,"diseCategoryName":"传染病"},
	 * {"id":2,"diseaseCode":"SH","diseaseName":"伤寒","diseaseICD":"A01.001","diseCategoryID":1,"delMark":1,"diseCategoryName":"传染病"},
	 * {"id":3,"diseaseCode":"SY","diseaseName":"鼠疫","diseaseICD":"Y01.001","diseCategoryID":1,"delMark":1,"diseCategoryName":"传染病"},
	 * {"id":4,"diseaseCode":"WA","diseaseName":"胃癌","diseaseICD":"Y02.001","diseCategoryID":2,"delMark":1,"diseCategoryName":"恶性肿瘤"},
	 * {"id":5,"diseaseCode":"GA","diseaseName":"肝癌","diseaseICD":"Y02.002","diseCategoryID":2,"delMark":1,"diseCategoryName":"恶性肿瘤"},
	 * {"id":6,"diseaseCode":"FA","diseaseName":"肺癌","diseaseICD":"Y02.003","diseCategoryID":2,"delMark":1,"diseCategoryName":"恶性肿瘤"},
	 * {"id":7,"diseaseCode":"XGL","diseaseName":"血管瘤","diseaseICD":"Y03.001","diseCategoryID":3,"delMark":1,"diseCategoryName":"心血管病"},
	 * {"id":8,"diseaseCode":"GXY","diseaseName":"高血压","diseaseICD":"Y03.002","diseCategoryID":3,"delMark":1,"diseCategoryName":"心血管病"},
	 * {"id":9,"diseaseCode":"JXXJSJ","diseaseName":"急性心肌衰竭","diseaseICD":"Y03.003","diseCategoryID":3,"delMark":1,"diseCategoryName":"心血管病"},
	 * {"id":10,"diseaseCode":"PZXNZ","diseaseName":"皮脂腺囊肿","diseaseICD":"Y04.001","diseCategoryID":4,"delMark":1,"diseCategoryName":"感官疾病"}]}
	 * */
	$.ajax({
		url : "FindAllDisease",
		type : "post",
		data : {currentPage : currpage},
		success : function(data) {
//			console.log(data);
//			data.totalCount = 0;
			if(data.totalCount == 0){
				var tr = $("<tr><td colspan='6'>暂无数据</td></tr>");
				$("#diseaseData").append(tr);
			} else {
				$("#diseaseData").children().remove();
				$("#pagediv").children().remove();
				// 填充数据
				for (var i = 0 ; i < data.pageData.length ; i++) {
					var obj = data.pageData[i];
					var tr = $("<tr></tr>");
					var td1 = $("<td><input type='checkbox' name='all'></td>");
					var td2 = $("<td>" + obj.diseaseCode + "</td>");
					var td3 = $("<td>" + obj.diseaseName + "</td>");
					var td4 = $("<td>" + obj.diseaseICD + "</td>");
					var td5 = $("<td>" + obj.diseCategoryName + "</td>");
					
					var td6 = $("<td><button class='btn btn-warning btn-xs' onclick='fupdate(" + JSON.stringify(obj) + ")'>修改</button>&nbsp;&nbsp;"
							+ "<button class='btn btn-danger btn-xs' onclick='fdelete(" + obj.id + ")'>删除</button></td>");
					
					tr.append(td1);
					tr.append(td2);
					tr.append(td3);
					tr.append(td4);
					tr.append(td5);
					tr.append(td6);
					$("#diseaseData").append(tr);
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
	$("#diseaseId").val(id);
}


/*
 * 点击确认按钮，异步请求，执行删除操作
 */
function doDelete() {
	var disId = $("#diseaseId").val();
	$.ajax({
		url : "DeleteDiseaseById",
		type : "post",
		data : {diseaseId : disId},
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
			alert("服务器端出错！");
		}
	});
}


/*
 * 点击查询按钮，根据输入框内容进行模糊查询
 */
function findByCode() {
	var content = $("#diseaseCode").val();
	console.log(content)
	$.ajax({
		url : "FindDiseaseByCode",
		type : "post",
		data : {code : content},
		success : function(data) {
//			console.log(data);
			/*
			 * [{"id":2,"diseaseCode":"SH","diseaseName":"伤寒","diseaseICD":"A01.001","diseCategoryID":1,"delMark":1,"diseCategoryName":"传染病"},{"id":33,"diseaseCode":"SHXDGR","diseaseName":"上呼吸道感染","diseaseICD":"Y10.001","diseCategoryID":10,"delMark":1,"diseCategoryName":"呼吸系统疾病"}]
			 * */
			$("#diseaseData").children().remove();
			$("#pagediv").children().remove();
			if(data.length == 0){
				var tr = $("<tr><td colspan='6'>暂无数据</td></tr>");
				$("#diseaseData").append(tr);
			} else {
				// 填充数据
				for (var i = 0 ; i < data.length ; i++) {
					var obj = data[i];
					var tr = $("<tr></tr>");
					var td1 = $("<td><input type='checkbox' name='all'></td>");
					var td2 = $("<td>" + obj.diseaseCode + "</td>");
					var td3 = $("<td>" + obj.diseaseName + "</td>");
					var td4 = $("<td>" + obj.diseaseICD + "</td>");
					var td5 = $("<td>" + obj.diseCategoryName + "</td>");
					
					var td6 = $("<td><button class='btn btn-warning btn-xs' onclick='fupdate(" + JSON.stringify(obj) + ")'>修改</button>&nbsp;&nbsp;"
							+ "<button class='btn btn-danger btn-xs' onclick='fdelete(" + obj.id + ")'>删除</button></td>");
					
					tr.append(td1);
					tr.append(td2);
					tr.append(td3);
					tr.append(td4);
					tr.append(td5);
					tr.append(td6);
					$("#diseaseData").append(tr);
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
	/*
	 * [{"id":1,"dicaCode":null,"dicaName":"传染病","sequenceNo":0,"dicaType":0,"delMark":0},
	 * {"id":2,"dicaCode":null,"dicaName":"恶性肿瘤","sequenceNo":0,"dicaType":0,"delMark":0},
	 * {"id":3,"dicaCode":null,"dicaName":"心血管病","sequenceNo":0,"dicaType":0,"delMark":0},
	 * {"id":4,"dicaCode":null,"dicaName":"感官疾病","sequenceNo":0,"dicaType":0,"delMark":0},
	 * {"id":5,"dicaCode":null,"dicaName":"神经系统疾病","sequenceNo":0,"dicaType":0,"delMark":0},
	 * {"id":6,"dicaCode":null,"dicaName":"内分泌营养代谢病","sequenceNo":0,"dicaType":0,"delMark":0},
	 * {"id":7,"dicaCode":null,"dicaName":"血液造血系统病","sequenceNo":0,"dicaType":0,"delMark":0},
	 * {"id":8,"dicaCode":null,"dicaName":"精神病","sequenceNo":0,"dicaType":0,"delMark":0},
	 * {"id":9,"dicaCode":null,"dicaName":"脑血管病","sequenceNo":0,"dicaType":0,"delMark":0},
	 * {"id":10,"dicaCode":null,"dicaName":"呼吸系统疾病","sequenceNo":0,"dicaType":0,"delMark":0},
	 * {"id":11,"dicaCode":null,"dicaName":"消化系统疾病","sequenceNo":0,"dicaType":0,"delMark":0},
	 * {"id":12,"dicaCode":null,"dicaName":"泌尿生殖系统疾病","sequenceNo":0,"dicaType":0,"delMark":0},
	 * {"id":13,"dicaCode":null,"dicaName":"损伤与中毒","sequenceNo":0,"dicaType":0,"delMark":0},
	 * {"id":14,"dicaCode":null,"dicaName":"意外","sequenceNo":0,"dicaType":0,"delMark":0},
	 * {"id":15,"dicaCode":null,"dicaName":"运动系统疾病","sequenceNo":0,"dicaType":0,"delMark":0},
	 * {"id":16,"dicaCode":null,"dicaName":"其他","sequenceNo":0,"dicaType":0,"delMark":0},
	 * {"id":17,"dicaCode":null,"dicaName":"结缔组织疾病","sequenceNo":0,"dicaType":0,"delMark":0}]
	 * */
	$.ajax({
		url : "FindDiseaseInfo",
		type : "post",
		success : function(data) {
//			console.log(data);
			
			$("#addDiseCategoryName")[0].options.length = 1;
			
			// 将疾病所属分类填充到下拉列表中
			for(var i = 0 ; i < data.length ; i++){
				var obj = data[i];
				var option = $("<option value='" + obj.id + "'>" + obj.dicaName + "</option>");
				$("#addDiseCategoryName").append(option);
			}
			$("#addDiseaseCode").val("");
			$("#addDiseaseName").val("");
			$("#addDiseaseICD").val("");
			$("#saveAndUpdateLabel").text("新增疾病");
			$("#addBtn").text("添加");
			$('#saveAndUpdate').modal('show');
		},
		error : function() {
			alert("服务器端出错！");
		}
	});
}


/*
 * 点击修改按钮，显示id为saveAndUpdate的模态框
 */
function fupdate(disObj) {
//	console.log(disObj);
	
	$("#addDiseaseId").val(disObj.id);
	$.ajax({
		url : "FindDiseaseInfo",
		type : "post",
		success : function(data) {
//			console.log(data);
			
			$("#addDiseCategoryName")[0].options.length = 1;
			
			// 将疾病所属分类填充到下拉列表中
			for(var i = 0 ; i < data.length ; i++){
				var obj = data[i];
				var option = $("<option value='" + obj.id + "'>" + obj.dicaName + "</option>");
				$("#addDiseCategoryName").append(option);
				
				if(option.val() == disObj.diseCategoryID){
					option[0].selected = true;
				}
			}
			$("#addDiseaseCode").val(disObj.diseaseCode);
			$("#addDiseaseName").val(disObj.diseaseName);
			$("#addDiseaseICD").val(disObj.diseaseICD);
			$("#saveAndUpdateLabel").text("修改疾病信息");
			$("#addBtn").text("修改");
			
			$('#saveAndUpdate').modal('show');
		},
		error : function() {
			alert("服务器端出错！");
		}
	});
}


/*
 * 点击模态框中的保存/修改按钮，显示id为saveAndUpdate的模态框
 */
function doSaveOrUpdate() {
	var op = $("#addBtn").text();
	
	var disId = $("#addDiseaseId").val();
	var disCode = $("#addDiseaseCode").val();
	var disName = $("#addDiseaseName").val();
	var disICD = $("#addDiseaseICD").val();
	var diseCategoryId = $("#addDiseCategoryName option:selected").val();
	
	$.ajax({
		url : "SaveDisease",
		type : "post",
		data : {
			disId : disId,
			disCode : disCode,
			disName : disName,
			disICD : disICD,
			diseCategoryId : diseCategoryId
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
