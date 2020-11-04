package com.neuedu.hisunder.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/*
 * 非药品收费项目表
 */
public class Fmeditem {

	private int id;
	
	private String itemCode;  //项目编码
	
	private String itemName;  //项目名称
	
	private String format;  //规格
	
	private double price;  //单价
	
	private int expClassID;  //所属费用科目ID
	
	private int deptID;  //执行科室ID
	
	private String mnemonicCode;  //拼音助记码
	

	@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
	private Date creationDate;  //创建时间
	

	@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8") // HH:mm:ss
	private Date lastUpdateDate;  //最后修改时间
	
	private int recordType;  //项目类型,1-检查;2-检验;3-处置

	private int delmark;  //删除标记,1:正常显示  0：已删除
	
	// 扩充属性
	private String expClassName;
	
	private String deptName;

	public String getExpClassName() {
		return expClassName;
	}

	public void setExpClassName(String expClassName) {
		this.expClassName = expClassName;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getExpClassID() {
		return expClassID;
	}

	public void setExpClassID(int expClassID) {
		this.expClassID = expClassID;
	}

	public int getDeptID() {
		return deptID;
	}

	public void setDeptID(int deptID) {
		this.deptID = deptID;
	}

	public String getMnemonicCode() {
		return mnemonicCode;
	}

	public void setMnemonicCode(String mnemonicCode) {
		this.mnemonicCode = mnemonicCode;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public int getRecordType() {
		return recordType;
	}

	public void setRecordType(int recordType) {
		this.recordType = recordType;
	}

	public int getDelmark() {
		return delmark;
	}

	public void setDelmark(int delmark) {
		this.delmark = delmark;
	}
	
}
