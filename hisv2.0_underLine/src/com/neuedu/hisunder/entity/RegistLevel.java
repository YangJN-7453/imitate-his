package com.neuedu.hisunder.entity;

public class RegistLevel {

	private int id;
	
	private String registCode;  //号别编码
	
	private String registName;  //号别名称
	
	private int sequenceNo;  //显示顺序号
	
	private double registFee;  //挂号费
	
	private int registQuota;  //挂号限额
	
	private int delMark;  //删除标记,1:正常显示  0：已删除

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRegistCode() {
		return registCode;
	}

	public void setRegistCode(String registCode) {
		this.registCode = registCode;
	}

	public String getRegistName() {
		return registName;
	}

	public void setRegistName(String registName) {
		this.registName = registName;
	}

	public int getSequenceNo() {
		return sequenceNo;
	}

	public void setSequenceNo(int sequenceNo) {
		this.sequenceNo = sequenceNo;
	}

	public double getRegistFee() {
		return registFee;
	}

	public void setRegistFee(double registFee) {
		this.registFee = registFee;
	}

	public int getRegistQuota() {
		return registQuota;
	}

	public void setRegistQuota(int registQuota) {
		this.registQuota = registQuota;
	}

	public int getDelMark() {
		return delMark;
	}

	public void setDelMark(int delMark) {
		this.delMark = delMark;
	}
	
}
