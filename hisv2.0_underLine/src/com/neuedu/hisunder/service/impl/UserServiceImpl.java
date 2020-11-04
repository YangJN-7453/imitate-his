package com.neuedu.hisunder.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.neuedu.hisunder.dao.ConstantTypeDao;
import com.neuedu.hisunder.dao.DepartmentDao;
import com.neuedu.hisunder.dao.RegistLevelDao;
import com.neuedu.hisunder.dao.UserDao;
import com.neuedu.hisunder.dao.impl.ConstantTypeDaoImpl;
import com.neuedu.hisunder.dao.impl.DepartmentDaoImpl;
import com.neuedu.hisunder.dao.impl.RegistLevelDaoImpl;
import com.neuedu.hisunder.dao.impl.UserDaoImpl;
import com.neuedu.hisunder.entity.ConstantItem;
import com.neuedu.hisunder.entity.Department;
import com.neuedu.hisunder.entity.RegistLevel;
import com.neuedu.hisunder.entity.User;
import com.neuedu.hisunder.service.UserService;

public class UserServiceImpl implements UserService {
	
	private UserDao dao;
	
	private ConstantTypeDao constantTypeDao;
	
	private DepartmentDao departmentDao;
	
	private RegistLevelDao registLevelDao;
	
	public UserServiceImpl(){
		
		dao = UserDaoImpl.getUserDaoImpl();
		
		constantTypeDao = ConstantTypeDaoImpl.getConstantTypeDaoImpl();
		
		departmentDao = DepartmentDaoImpl.getDepartmentDaoImpl();
		
		registLevelDao = RegistLevelDaoImpl.getRegistLevelDaoImpl();
	}

	@Override
	public int addUser(User user) throws Exception {
		
		return dao.addUser(user);
	}

	@Override
	public int update(User user) throws Exception {
		
		return dao.update(user);
	}

	@Override
	public int delete(int userid) throws Exception {
		
		return dao.delete(userid);
	}

	@Override
	public List<User> findAll(int beginIndex, int pageSize) throws Exception {
		
		return dao.findAll(beginIndex, pageSize);
	}

	@Override
	public int getTotalCount() throws Exception {
		
		return dao.getTotalCount();
	}

	@Override
	public List<User> findUserByUname(String username) throws Exception {
		
		return dao.findUserByUname(username);
	}

	@Override
	public Map<String, List> findInfoAboutUser() throws Exception {
		Map<String, List> map = new HashMap<String, List>();
		
		// 获取用户类型
		List<ConstantItem> userTypeList = constantTypeDao.findConstantItem("YHLX");
		// 获取职称列表
		List<ConstantItem> docTitleList = constantTypeDao.findConstantItem("YSZC");
		// 获取科室列表
		List<Department> deptList = departmentDao.finAllDepartments();
		// 获取挂号级别
		List<RegistLevel> registLevelList = registLevelDao.findAllReg();
		
		//将各个list存放到map中
		map.put("usertypes", userTypeList);
		map.put("docTitles", docTitleList);
		map.put("depts", deptList);
		map.put("reglevels", registLevelList);
		
		return map;
	}

	@Override
	public List<User> findUserByDeptID(int deptid) throws Exception {
		
		return dao.findUserByDeptID(deptid);
	}

	@Override
	public List<User> findClinicalUser() throws Exception {
		
		return dao.findClinicalUser();
	}

	@Override
	public List<User> findUnameByDeptAndReg(int deptid, int regid)
			throws Exception {
		
		return dao.findUnameByDeptAndReg(deptid, regid);
	}

}
