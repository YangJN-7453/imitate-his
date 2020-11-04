package com.neuedu.hisunder.entity;

import java.util.Date;

/*
 * 常数类别
 */
public class Register {

	private int id;
	
	private String caseNumber;  //病历号
	
	private String realName;  //姓名

	private int gender;  //性别
	
	private String IDnumber;  //身份证号
	
	private Date birthDate;  //出生日期

	private int age;  //年龄
	
	private String ageType;  //年龄类型:岁/月/天
	
	private String homeAddress;  //家庭住址
	
	private Date visitDate;  //本次看诊日期
	
	private String noon;  //午别:上午/下午
	
	private int deptID;  //本次挂号科室ID
	
	private int userID;  //本次挂号医生ID
	
	private int registLeID;  //本次挂号级别ID
	
	private int settleID;  //结算类别ID
	
	private String isBook;  //病历本要否
	
	private Date registTime;  //挂号时间
	
	private int registerID;  //挂号员ID
	
	private int isitState;  //本次看诊状态:1-已挂号;2-医生接诊;3-看诊结束;4-已退号

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCaseNumber() {
		return caseNumber;
	}

	public void setCaseNumber(String caseNumber) {
		this.caseNumber = caseNumber;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public String getIDnumber() {
		return IDnumber;
	}

	public void setIDnumber(String iDnumber) {
		IDnumber = iDnumber;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getAgeType() {
		return ageType;
	}

	public void setAgeType(String ageType) {
		this.ageType = ageType;
	}

	public String getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}

	public Date getVisitDate() {
		return visitDate;
	}

	public void setVisitDate(Date visitDate) {
		this.visitDate = visitDate;
	}

	public String getNoon() {
		return noon;
	}

	public void setNoon(String noon) {
		this.noon = noon;
	}

	public int getDeptID() {
		return deptID;
	}

	public void setDeptID(int deptID) {
		this.deptID = deptID;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public int getRegistLeID() {
		return registLeID;
	}

	public void setRegistLeID(int registLeID) {
		this.registLeID = registLeID;
	}

	public int getSettleID() {
		return settleID;
	}

	public void setSettleID(int settleID) {
		this.settleID = settleID;
	}

	public String getIsBook() {
		return isBook;
	}

	public void setIsBook(String isBook) {
		this.isBook = isBook;
	}

	public Date getRegistTime() {
		return registTime;
	}

	public void setRegistTime(Date registTime) {
		this.registTime = registTime;
	}

	public int getRegisterID() {
		return registerID;
	}

	public void setRegisterID(int registerID) {
		this.registerID = registerID;
	}

	public int getIsitState() {
		return isitState;
	}

	public void setIsitState(int isitState) {
		this.isitState = isitState;
	}

}
