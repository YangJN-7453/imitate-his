package com.neuedu.hisunder.entity;

/*
 * 疾病分类表
 */
public class DiseCategory {

	private int id;
	
	private String dicaCode;  //分类编码
	
	private String dicaName;  //分类名称
	
	private int sequenceNo;  //显示顺序号
	
	private int dicaType;  //疾病类型
	
	private int delMark;  //删除标记,1:正常显示  0：已删除

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDicaCode() {
		return dicaCode;
	}

	public void setDicaCode(String dicaCode) {
		this.dicaCode = dicaCode;
	}

	public String getDicaName() {
		return dicaName;
	}

	public void setDicaName(String dicaName) {
		this.dicaName = dicaName;
	}

	public int getSequenceNo() {
		return sequenceNo;
	}

	public void setSequenceNo(int sequenceNo) {
		this.sequenceNo = sequenceNo;
	}

	public int getDicaType() {
		return dicaType;
	}

	public void setDicaType(int dicaType) {
		this.dicaType = dicaType;
	}

	public int getDelMark() {
		return delMark;
	}

	public void setDelMark(int delMark) {
		this.delMark = delMark;
	}
	
}
