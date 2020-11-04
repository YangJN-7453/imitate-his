package com.neuedu.hisunder.entity;

public class ConstantItem {

	private int id;
	
	private int constantTypeID;  //所属常数类别ID
	
	private String constantCode;  //常数项编码
	
	private String constantName;  //常数项名称
	
	private int delMark;  //删除标记,1:正常显示  0：已删除

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getConstantTypeID() {
		return constantTypeID;
	}

	public void setConstantTypeID(int constantTypeID) {
		this.constantTypeID = constantTypeID;
	}

	public String getConstantCode() {
		return constantCode;
	}

	public void setConstantCode(String constantCode) {
		this.constantCode = constantCode;
	}

	public String getConstantName() {
		return constantName;
	}

	public void setConstantName(String constantName) {
		this.constantName = constantName;
	}

	public int getDelMark() {
		return delMark;
	}

	public void setDelMark(int delMark) {
		this.delMark = delMark;
	}
	
}
