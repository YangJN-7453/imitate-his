package com.neuedu.hisunder.entity;

import java.util.Date;

/*
 * 排班表
 */
public class Scheduling {

	private int id;
	
	private Date schedDate;  //排班日期

	private int deptID;  //科室ID

	private int userID;  //医生ID
	
	private String noon;  //午别
	
	private int delMark;  //删除标记,1:正常显示  0：已删除

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getSchedDate() {
		return schedDate;
	}

	public void setSchedDate(Date schedDate) {
		this.schedDate = schedDate;
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

	public String getNoon() {
		return noon;
	}

	public void setNoon(String noon) {
		this.noon = noon;
	}

	public int getDelMark() {
		return delMark;
	}

	public void setDelMark(int delMark) {
		this.delMark = delMark;
	}
	
}
