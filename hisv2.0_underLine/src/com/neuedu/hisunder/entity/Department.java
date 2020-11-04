package com.neuedu.hisunder.entity;

public class Department {

	private int id;
	
	private String deptCode;  //科室编码
	
	private String deptName;  //科室名称
	
	private int deptCategoryID;  //科室分类;(内科，外科...)
	
	private int deptType;  //科室类型,根据科室职能分类,外键constantTypeID;1-临床,2-医技,3-财务,4-行政,5-其它
	
	private int delMark;  //删除标记,1:正常显示  0：已删除
	
	private String deptCategoryName;
	
	public String getDeptCategoryName() {
		return deptCategoryName;
	}

	public void setDeptCategoryName(String deptCategoryName) {
		this.deptCategoryName = deptCategoryName;
	}

	public String getDeptTypeName() {
		return deptTypeName;
	}

	public void setDeptTypeName(String deptTypeName) {
		this.deptTypeName = deptTypeName;
	}

	private String deptTypeName;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public int getDeptCategoryID() {
		return deptCategoryID;
	}

	public void setDeptCategoryID(int deptCategoryID) {
		this.deptCategoryID = deptCategoryID;
	}

	public int getDeptType() {
		return deptType;
	}

	public void setDeptType(int deptType) {
		this.deptType = deptType;
	}

	public int getDelMark() {
		return delMark;
	}

	public void setDelMark(int delMark) {
		this.delMark = delMark;
	}
	
}
