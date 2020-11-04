package com.neuedu.hisunder.entity;

import java.util.Date;

/*
 * 药品目录表
 */
public class Drugs {

	private int id;
	
	private String drugsCode;  //药品编码
	
	private String drugsName;  //药品名称
	
	private String drugsFormat;  //药品规格
	
	private String drugsUnit;  //包装单位
	
	private String manufacturer;  //生产厂家

	private int drugsDosageID;  //药品剂型

	private int drugsTypeID;  //药品类型
	
	private double drugsPrice;  //药品单价
	
	private String mnemonicCode;  //拼音助记码
	
	private Date creationDate;  //创建时间
	
	private Date lastUpdateDate;  //最后修改时间
	
	private int delMark;  //删除标记,1:正常显示  0：已删除

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDrugsCode() {
		return drugsCode;
	}

	public void setDrugsCode(String drugsCode) {
		this.drugsCode = drugsCode;
	}

	public String getDrugsName() {
		return drugsName;
	}

	public void setDrugsName(String drugsName) {
		this.drugsName = drugsName;
	}

	public String getDrugsFormat() {
		return drugsFormat;
	}

	public void setDrugsFormat(String drugsFormat) {
		this.drugsFormat = drugsFormat;
	}

	public String getDrugsUnit() {
		return drugsUnit;
	}

	public void setDrugsUnit(String drugsUnit) {
		this.drugsUnit = drugsUnit;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public int getDrugsDosageID() {
		return drugsDosageID;
	}

	public void setDrugsDosageID(int drugsDosageID) {
		this.drugsDosageID = drugsDosageID;
	}

	public int getDrugsTypeID() {
		return drugsTypeID;
	}

	public void setDrugsTypeID(int drugsTypeID) {
		this.drugsTypeID = drugsTypeID;
	}

	public double getDrugsPrice() {
		return drugsPrice;
	}

	public void setDrugsPrice(double drugsPrice) {
		this.drugsPrice = drugsPrice;
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

	public int getDelMark() {
		return delMark;
	}

	public void setDelMark(int delMark) {
		this.delMark = delMark;
	}
	
}
