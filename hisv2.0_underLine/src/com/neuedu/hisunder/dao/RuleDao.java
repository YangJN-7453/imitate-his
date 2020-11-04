package com.neuedu.hisunder.dao;

import java.util.List;

import com.neuedu.hisunder.entity.Rule;

public interface RuleDao {

	/*
	 * 统计记录条数
	 * */
	int getTotalCount() throws Exception;
	
	/*
	 * 查询所有排班规则,分页查询
	 * */
	List<Rule> findAllRule(int beginIndex,int pageSize) throws Exception;
	
	/*
	 * 根据用户真实姓名对 排班规则，进行模糊查询
	 * */
	List<Rule> findRuleByUname(String uname) throws Exception;
	
	/*
	 * 添加排班规则
	 * */
	int addRule(Rule rule) throws Exception;

}
