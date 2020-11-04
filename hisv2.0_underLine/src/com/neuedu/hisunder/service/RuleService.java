package com.neuedu.hisunder.service;

import java.util.List;
import java.util.Map;

import com.neuedu.hisunder.entity.Rule;

public interface RuleService {

	/*
	 * 统计记录条数
	 * */
	int getTotalCount() throws Exception;
	
	/*
	 * 查询所有部门,分页查询
	 * */
	List<Rule> findAllRule(int beginIndex,int pageSize) throws Exception;
	
	/*
	 * 根据部门的拼音助记码，进行模糊查询
	 * */
	List<Rule> findRuleByUname(String uname) throws Exception;
	
	/*
	 * 将部门名 与 挂号级别名 封装到map中，用于添加rule
	 * */
	Map<String, List> findDeptNameAndRegName() throws Exception;
	
	/*
	 * 添加排班规则
	 * */
	int addRule(Rule rule) throws Exception;
}
