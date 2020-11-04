package com.neuedu.hisunder.entity;

public class User {

	private int id;
	
	private String userName;  //登录名
	
	private String password;  //密码
	
	private String realName;  //真实姓名
	
	private int userType;  //用户类别
	
	private int docTitleID;  //医生职称ID
	
	private String isScheduling;  //是否参与排班
	
	private int deptID;  //所在科室ID
	
	private int registLeID;  //挂号级别ID
	
	private int delMark;  //删除标记,1:正常显示  0：已删除
	
	// 扩充属性
	private String deptname;
	
	private String docTitleName;
	
	private String registLeName;
	
	private String registFee;

	private String userTypeName;
	
	public String getRegistFee() {
		return registFee;
	}

	public void setRegistFee(String registFee) {
		this.registFee = registFee;
	}

	public String getDeptname() {
		return deptname;
	}

	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}

	public String getDocTitleName() {
		return docTitleName;
	}

	public void setDocTitleName(String docTitleName) {
		this.docTitleName = docTitleName;
	}

	public String getRegistLeName() {
		return registLeName;
	}

	public void setRegistLeName(String registLeName) {
		this.registLeName = registLeName;
	}

	public String getUserTypeName() {
		return userTypeName;
	}

	public void setUserTypeName(String userTypeName) {
		this.userTypeName = userTypeName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}

	public int getDocTitleID() {
		return docTitleID;
	}

	public void setDocTitleID(int docTitleID) {
		this.docTitleID = docTitleID;
	}

	public String getIsScheduling() {
		return isScheduling;
	}

	public void setIsScheduling(String isScheduling) {
		this.isScheduling = isScheduling;
	}

	public int getDeptID() {
		return deptID;
	}

	public void setDeptID(int deptID) {
		this.deptID = deptID;
	}

	public int getRegistLeID() {
		return registLeID;
	}

	public void setRegistLeID(int registLeID) {
		this.registLeID = registLeID;
	}

	public int getDelMark() {
		return delMark;
	}

	public void setDelMark(int delMark) {
		this.delMark = delMark;
	}
	
}
