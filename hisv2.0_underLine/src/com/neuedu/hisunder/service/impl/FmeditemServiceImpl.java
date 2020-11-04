package com.neuedu.hisunder.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.neuedu.hisunder.dao.DepartmentDao;
import com.neuedu.hisunder.dao.FmeditemDao;
import com.neuedu.hisunder.dao.impl.DepartmentDaoImpl;
import com.neuedu.hisunder.dao.impl.FmeditemDaoImpl;
import com.neuedu.hisunder.entity.Department;
import com.neuedu.hisunder.entity.ExpenseClass;
import com.neuedu.hisunder.entity.Fmeditem;
import com.neuedu.hisunder.service.FmeditemService;

public class FmeditemServiceImpl implements FmeditemService {

	private FmeditemDao dao;
	
	private DepartmentDao deptDao;
	
	public FmeditemServiceImpl() {
		
		dao = FmeditemDaoImpl.getFmeditemDaoImpl();
		
		deptDao = DepartmentDaoImpl.getDepartmentDaoImpl();
	}
	
	@Override
	public int getTotalCount() throws Exception {
		
		return dao.getTotalCount();
	}

	@Override
	public int addFmeditem(Fmeditem fmeditem) throws Exception {
		
		return dao.addFmeditem(fmeditem);
	}

	@Override
	public int updateFmeditem(Fmeditem fmeditem) throws Exception {
		
		return dao.updateFmeditem(fmeditem);
	}

	@Override
	public int deleteFmeditem(int id) throws Exception {
		
		return dao.deleteFmeditem(id);
	}

	@Override
	public List<Fmeditem> findAllFmeditem(int beginIndex, int pageSize)
			throws Exception {
		
		return dao.findAllFmeditem(beginIndex, pageSize);
	}

	@Override
	public List<Fmeditem> findFmeditemByCode(String mnemonicCode)
			throws Exception {
		
		return dao.findFmeditemByCode(mnemonicCode);
	}

	@Override
	public Map<String, List> findFmeditemInfo() throws Exception {
		Map<String, List> map = new HashMap<String, List>();
		
		List<ExpenseClass> expClassList = dao.findAllExpenseClass();
		List<Department> deptList = deptDao.finAllDepartments();
		
		map.put("expClassNames", expClassList);
		map.put("deptNames", deptList);
		return map;
	}

}
