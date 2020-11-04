package com.neuedu.hisunder.service;

import java.util.List;

import com.neuedu.hisunder.entity.RegistLevel;
import com.neuedu.hisunder.entity.User;

public interface RegistLevelService {

	/*
	 * 统计记录条数
	 * */
	int getTotalCount() throws Exception;
	
	/*
	 * 添加挂号等级
	 * */
	int addRegistLevel(RegistLevel registLevel) throws Exception;
	
	/*
	 * 修改挂号等级
	 * */
	int updateRegistLevel(RegistLevel registLevel) throws Exception;

	/*
	 * 删除挂号等级
	 * */
	int deleteRegistLevel(int id) throws Exception;
	
	/*
	 * 查询所有挂号等级,分页查询
	 * */
	List<RegistLevel> findAllRegistLevel(int beginIndex,int pageSize) throws Exception;
	
	/*
	 * 根据挂号登记的code拼音助记码，进行模糊查询
	 * */
	List<RegistLevel> findRegistLevelByCode(String registLevelCode) throws Exception;
	
	/*
	 * 查询本挂号登记的用户/医生
	 * */
	List<User> findUserByRegistLevelId(int registId) throws Exception;

}
