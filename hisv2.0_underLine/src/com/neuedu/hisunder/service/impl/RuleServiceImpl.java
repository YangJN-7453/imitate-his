package com.neuedu.hisunder.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.neuedu.hisunder.dao.DepartmentDao;
import com.neuedu.hisunder.dao.RegistLevelDao;
import com.neuedu.hisunder.dao.RuleDao;
import com.neuedu.hisunder.dao.impl.DepartmentDaoImpl;
import com.neuedu.hisunder.dao.impl.RegistLevelDaoImpl;
import com.neuedu.hisunder.dao.impl.RuleDaoImpl;
import com.neuedu.hisunder.entity.Department;
import com.neuedu.hisunder.entity.RegistLevel;
import com.neuedu.hisunder.entity.Rule;
import com.neuedu.hisunder.service.RuleService;

public class RuleServiceImpl implements RuleService {

	private RuleDao dao;
	
	private DepartmentDao deptDao;
	
	private RegistLevelDao regDao;
	
	public RuleServiceImpl() {
		dao = RuleDaoImpl.getRuleDaoImpl();
		
		deptDao = DepartmentDaoImpl.getDepartmentDaoImpl();
		
		regDao = RegistLevelDaoImpl.getRegistLevelDaoImpl();
	}
	
	@Override
	public int getTotalCount() throws Exception {
		
		return dao.getTotalCount();
	}

	@Override
	public List<Rule> findAllRule(int beginIndex, int pageSize)
			throws Exception {
		
		return dao.findAllRule(beginIndex, pageSize);
	}

	@Override
	public List<Rule> findRuleByUname(String uname) throws Exception {
		
		return dao.findRuleByUname(uname);
	}

	@Override
	public Map<String, List> findDeptNameAndRegName() throws Exception {
		Map<String, List> map = new HashMap<String, List>();
		
		List<RegistLevel> registList = regDao.findAllReg();
		List<Department> deptList = deptDao.finAllDepartments();
		
		map.put("deptName", deptList);
		map.put("registName", registList);
		return map;
	}

	@Override
	public int addRule(Rule rule) throws Exception {
		
		return dao.addRule(rule);
	}

}
