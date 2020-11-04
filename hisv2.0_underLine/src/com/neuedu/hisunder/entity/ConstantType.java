package com.neuedu.hisunder.entity;

import java.util.List;

/*
 * 常数类别
 */
public class ConstantType {

	private int id;
	
	private String constantTypeCode;  //常数类别编码
	
	private String constantTypeName;  //常数类别名称
	
	private int delMark;  //删除标记,1:正常显示  0：已删除

	private List<ConstantItem> constantItems;  //所有的子项
	
	public List<ConstantItem> getConstantItems() {
		return constantItems;
	}

	public void setConstantItems(List<ConstantItem> constantItems) {
		this.constantItems = constantItems;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getConstantTypeCode() {
		return constantTypeCode;
	}

	public void setConstantTypeCode(String constantTypeCode) {
		this.constantTypeCode = constantTypeCode;
	}

	public String getConstantTypeName() {
		return constantTypeName;
	}

	public void setConstantTypeName(String constantTypeName) {
		this.constantTypeName = constantTypeName;
	}

	public int getDelMark() {
		return delMark;
	}

	public void setDelMark(int delMark) {
		this.delMark = delMark;
	}
	
}
