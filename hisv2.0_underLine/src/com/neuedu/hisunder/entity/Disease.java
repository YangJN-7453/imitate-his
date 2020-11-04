package com.neuedu.hisunder.entity;

/*
 * 疾病表
 */
public class Disease {

	private int id;
	
	private String diseaseCode;  //疾病助记编码
	
	private String diseaseName;  //疾病名称
	
	private String diseaseICD;  //国际ICD编码

	private int diseCategoryID;  //疾病所属分类

	private int delMark;  //删除标记,1:正常显示  0：已删除
	
	private String diseCategoryName;  // 分类名

	public String getDiseCategoryName() {
		return diseCategoryName;
	}

	public void setDiseCategoryName(String diseCategoryName) {
		this.diseCategoryName = diseCategoryName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDiseaseCode() {
		return diseaseCode;
	}

	public void setDiseaseCode(String diseaseCode) {
		this.diseaseCode = diseaseCode;
	}

	public String getDiseaseName() {
		return diseaseName;
	}

	public void setDiseaseName(String diseaseName) {
		this.diseaseName = diseaseName;
	}

	public String getDiseaseICD() {
		return diseaseICD;
	}

	public void setDiseaseICD(String diseaseICD) {
		this.diseaseICD = diseaseICD;
	}

	public int getDiseCategoryID() {
		return diseCategoryID;
	}

	public void setDiseCategoryID(int diseCategoryID) {
		this.diseCategoryID = diseCategoryID;
	}

	public int getDelMark() {
		return delMark;
	}

	public void setDelMark(int delMark) {
		this.delMark = delMark;
	}
	
	
}
