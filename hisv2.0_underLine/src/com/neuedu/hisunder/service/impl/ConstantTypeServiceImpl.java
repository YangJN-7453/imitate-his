package com.neuedu.hisunder.service.impl;

import java.util.List;

import com.neuedu.hisunder.dao.ConstantTypeDao;
import com.neuedu.hisunder.dao.impl.ConstantTypeDaoImpl;
import com.neuedu.hisunder.entity.ConstantItem;
import com.neuedu.hisunder.entity.ConstantType;
import com.neuedu.hisunder.service.ConstantTypeService;

public class ConstantTypeServiceImpl implements ConstantTypeService {

	private ConstantTypeDao dao;
	
	public ConstantTypeServiceImpl() {
		dao = ConstantTypeDaoImpl.getConstantTypeDaoImpl();
	}
	
	@Override
	public int add(ConstantType constantType) throws Exception {
		
		return dao.add(constantType);
	}

	@Override
	public int update(ConstantType constantType) throws Exception {
		
		return dao.update(constantType);
	}

	@Override
	public int delete(int id) throws Exception {
		
		return dao.delete(id);
	}

	@Override
	public List<ConstantType> findAll() throws Exception {
		
		return dao.findAll();
	}

	@Override
	public int getTotalCount() throws Exception {
		
		return dao.getTotalCount();
	}

	@Override
	public ConstantType findConstantTypes(String constantTypeCode)
			throws Exception {
		
		return dao.findConstantTypes(constantTypeCode);
	}

	@Override
	public List<ConstantItem> findConstantItem(String constantTypeCode)
			throws Exception {
		
		return dao.findConstantItem(constantTypeCode);
	}

	@Override
	public List<ConstantType> findConstantTypeByCode(String constantTypeCode)
			throws Exception {
		
		return dao.findConstantTypeByCode(constantTypeCode);
	}

}
