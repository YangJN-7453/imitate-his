package com.neuedu.hisunder.service;

import java.util.List;

import com.neuedu.hisunder.entity.DiseCategory;
import com.neuedu.hisunder.entity.Disease;

public interface DiseaseService {

	/*
	 * 统计记录条数
	 * */
	int getTotalCount() throws Exception;
	
	/*
	 * 添加部门
	 * */
	int addDisease(Disease disease) throws Exception;
	
	/*
	 * 修改部门
	 * */
	int updateDisease(Disease disease) throws Exception;

	/*
	 * 删除部门
	 * */
	int deleteDisease(int id) throws Exception;
	
	/*
	 * 查询所有部门,分页查询
	 * */
	List<Disease> findAllDisease(int beginIndex,int pageSize) throws Exception;
	
	/*
	 * 根据部门的拼音助记码，进行模糊查询
	 * */
	List<Disease> findDiseaseByCode(String diseaseCode) throws Exception;

	/*
	 * 查询疾病分类表中所有分类，用于添加或修改 具体疾病
	 * */
	List<DiseCategory> findDiseaseInfo() throws Exception;

	
}
