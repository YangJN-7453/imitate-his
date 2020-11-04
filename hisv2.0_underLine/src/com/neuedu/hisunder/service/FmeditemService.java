package com.neuedu.hisunder.service;

import java.util.List;
import java.util.Map;

import com.neuedu.hisunder.entity.Fmeditem;

public interface FmeditemService {

	/*
	 * 统计记录条数
	 * */
	int getTotalCount() throws Exception;
	
	/*
	 * 添加非药品收费项目
	 * */
	int addFmeditem(Fmeditem fmeditem) throws Exception;
	
	/*
	 * 修改非药品收费项目
	 * */
	int updateFmeditem(Fmeditem fmeditem) throws Exception;

	/*
	 * 删除非药品收费项目
	 * */
	int deleteFmeditem(int id) throws Exception;
	
	/*
	 * 查询所有非药品收费项目,分页查询
	 * */
	List<Fmeditem> findAllFmeditem(int beginIndex,int pageSize) throws Exception;
	
	/*
	 * 根据非药品收费项目的拼音助记码，进行模糊查询
	 * */
	List<Fmeditem> findFmeditemByCode(String mnemonicCode) throws Exception;
	
	/*
	 * 将ExpClassName和DeptName封装到map中，用于添加或修改fmeditem时
	 * */
	Map<String, List> findFmeditemInfo() throws Exception;
	
}
