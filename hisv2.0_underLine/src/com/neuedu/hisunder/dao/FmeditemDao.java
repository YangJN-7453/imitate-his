package com.neuedu.hisunder.dao;

import java.util.List;

import com.neuedu.hisunder.entity.ExpenseClass;
import com.neuedu.hisunder.entity.Fmeditem;

public interface FmeditemDao {

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
	 * 查询所有费用科目,不分页查询,用于添加/修改非药品收费项目
	 * */
	List<ExpenseClass> findAllExpenseClass() throws Exception;
	
}
