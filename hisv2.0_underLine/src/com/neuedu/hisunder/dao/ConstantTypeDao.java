package com.neuedu.hisunder.dao;

import java.util.List;

import com.neuedu.hisunder.entity.ConstantItem;
import com.neuedu.hisunder.entity.ConstantType;

public interface ConstantTypeDao {

	/*
	 * 添加常数类型
	 * */
	int add(ConstantType constantType) throws Exception;
	
	/*
	 * 修改常数类型
	 * */
	int update(ConstantType constantType) throws Exception;
	
	/*
	 * 删除常数类型
	 * */
	int delete(int id) throws Exception;
	
	/*
	 * 查询常数类型,分页查询
	 * */
	List<ConstantType> findAll() throws Exception;
	
	/*
	 * 统计记录条数
	 * */
	int getTotalCount() throws Exception;
	
	/*
	 * 根据常数类型的code的值，显示该常数类型及其常数项,(使用情况较少)
	 * */
	ConstantType findConstantTypes(String constantTypeCode) throws Exception;
	
	/*
	 * 根据常数类型的code的值，查询所有的常数项集合,(科室管理)
	 * */
	List<ConstantItem> findConstantItem(String constantTypeCode) throws Exception;
	
	/*
	 * 根据常数类型的code的值，查找ConstantType 模糊查询(使用情况较少)
	 * */
	List<ConstantType> findConstantTypeByCode(String constantTypeCode) throws Exception;
	
}
