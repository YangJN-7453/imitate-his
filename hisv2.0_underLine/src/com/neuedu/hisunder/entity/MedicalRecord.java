package com.neuedu.hisunder.entity;

/*
 * 常数类别
 */
public class MedicalRecord {

	private int id;
	
	private String caseNumber;  //病历号

	private int registID;  //挂号ID
	
	private String readMe;  //主诉
	
	private String present;  //现病史
	
	private String presentTreat;  //现病治疗情况
	
	private String history;  //既往史
	
	private String allergy;  //过敏史
	
	private String physique;  //体格检查
	
	private String proposal;  //检查建议
	
	private String careful;  //注意事项
	
	private String checkResult;  //检查结果
	
	private String diagnosis;  //诊断结果
	
	private String handling;  //处理意见

	private int caseState;  //病历状态:1-暂存;2-已提交;3-诊毕

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCaseNumber() {
		return caseNumber;
	}

	public void setCaseNumber(String caseNumber) {
		this.caseNumber = caseNumber;
	}

	public int getRegistID() {
		return registID;
	}

	public void setRegistID(int registID) {
		this.registID = registID;
	}

	public String getReadMe() {
		return readMe;
	}

	public void setReadMe(String readMe) {
		this.readMe = readMe;
	}

	public String getPresent() {
		return present;
	}

	public void setPresent(String present) {
		this.present = present;
	}

	public String getPresentTreat() {
		return presentTreat;
	}

	public void setPresentTreat(String presentTreat) {
		this.presentTreat = presentTreat;
	}

	public String getHistory() {
		return history;
	}

	public void setHistory(String history) {
		this.history = history;
	}

	public String getAllergy() {
		return allergy;
	}

	public void setAllergy(String allergy) {
		this.allergy = allergy;
	}

	public String getPhysique() {
		return physique;
	}

	public void setPhysique(String physique) {
		this.physique = physique;
	}

	public String getProposal() {
		return proposal;
	}

	public void setProposal(String proposal) {
		this.proposal = proposal;
	}

	public String getCareful() {
		return careful;
	}

	public void setCareful(String careful) {
		this.careful = careful;
	}

	public String getCheckResult() {
		return checkResult;
	}

	public void setCheckResult(String checkResult) {
		this.checkResult = checkResult;
	}

	public String getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}

	public String getHandling() {
		return handling;
	}

	public void setHandling(String handling) {
		this.handling = handling;
	}

	public int getCaseState() {
		return caseState;
	}

	public void setCaseState(int caseState) {
		this.caseState = caseState;
	}

}
