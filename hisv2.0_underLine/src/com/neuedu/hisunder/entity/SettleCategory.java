package com.neuedu.hisunder.entity;

/*
 * 结算类别表
 */
public class SettleCategory {

	private int id;
	
	private String settleCode;  //类别编码
	
	private String settleName;  //类别名称
	
	private int sequenceNo;  //显示顺序号
	
	private int delMark;  //删除标记,1:正常显示  0：已删除

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSettleCode() {
		return settleCode;
	}

	public void setSettleCode(String settleCode) {
		this.settleCode = settleCode;
	}

	public String getSettleName() {
		return settleName;
	}

	public void setSettleName(String settleName) {
		this.settleName = settleName;
	}

	public int getSequenceNo() {
		return sequenceNo;
	}

	public void setSequenceNo(int sequenceNo) {
		this.sequenceNo = sequenceNo;
	}

	public int getDelMark() {
		return delMark;
	}

	public void setDelMark(int delMark) {
		this.delMark = delMark;
	}
	
}
