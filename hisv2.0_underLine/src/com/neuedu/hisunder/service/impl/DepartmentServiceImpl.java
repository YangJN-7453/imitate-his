package com.neuedu.hisunder.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.neuedu.hisunder.dao.ConstantTypeDao;
import com.neuedu.hisunder.dao.DepartmentDao;
import com.neuedu.hisunder.dao.impl.ConstantTypeDaoImpl;
import com.neuedu.hisunder.dao.impl.DepartmentDaoImpl;
import com.neuedu.hisunder.entity.ConstantItem;
import com.neuedu.hisunder.entity.Department;
import com.neuedu.hisunder.service.DepartmentService;

public class DepartmentServiceImpl implements DepartmentService {

	private DepartmentDao dao;
	
	private ConstantTypeDao constantTypeDao;
	
	public DepartmentServiceImpl() {
		dao = DepartmentDaoImpl.getDepartmentDaoImpl();
		
		constantTypeDao = ConstantTypeDaoImpl.getConstantTypeDaoImpl();
	}
	
	@Override
	public int getTotalCount() throws Exception {
		
		return dao.getTotalCount();
	}

	@Override
	public int addDept(Department department) throws Exception {
		
		return dao.addDept(department);
	}

	@Override
	public int updateDept(Department department) throws Exception {
		
		return dao.updateDept(department);
	}

	@Override
	public int deleteDept(int id) throws Exception {
		
		return dao.deleteDept(id);
	}

	@Override
	public List<Department> findAllDept(int beginIndex, int pageSize)
			throws Exception {
		
		return dao.findAllDept(beginIndex, pageSize);
	}

	@Override
	public List<Department> findDeptByCode(String deptCode) throws Exception {
		
		return dao.findDeptByCode(deptCode);
	}

	@Override
	public List<Department> finAllDepartments() throws Exception {
		
		return dao.finAllDepartments();
	}

	@Override
	public Map<String, List> findDeptInfo() throws Exception {
		Map<String, List> map = new HashMap<String, List>();
		
		// 获取科室分类
		List<ConstantItem> deptCategoryList = constantTypeDao.findConstantItem("KSFL");
		// 获取科室职能
		List<ConstantItem> deptTypeList = constantTypeDao.findConstantItem("KSZN");
		
		//将list存放到map中
		map.put("deptCategorys", deptCategoryList);
		map.put("deptTypes", deptTypeList);
		return map;
	}

}
