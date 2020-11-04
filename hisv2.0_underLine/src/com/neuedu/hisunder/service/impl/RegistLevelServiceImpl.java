package com.neuedu.hisunder.service.impl;

import java.util.List;

import com.neuedu.hisunder.dao.RegistLevelDao;
import com.neuedu.hisunder.dao.impl.RegistLevelDaoImpl;
import com.neuedu.hisunder.entity.RegistLevel;
import com.neuedu.hisunder.entity.User;
import com.neuedu.hisunder.service.RegistLevelService;

public class RegistLevelServiceImpl implements RegistLevelService {

	private RegistLevelDao dao;
	
	public RegistLevelServiceImpl() {
		
		dao = RegistLevelDaoImpl.getRegistLevelDaoImpl();
	}
	
	@Override
	public int getTotalCount() throws Exception {
		
		return dao.getTotalCount();
	}

	@Override
	public int addRegistLevel(RegistLevel registLevel) throws Exception {
		
		return dao.addRegistLevel(registLevel);
	}

	@Override
	public int updateRegistLevel(RegistLevel registLevel) throws Exception {
		
		return dao.updateRegistLevel(registLevel);
	}

	@Override
	public int deleteRegistLevel(int id) throws Exception {
		
		return dao.deleteRegistLevel(id);
	}

	@Override
	public List<RegistLevel> findAllRegistLevel(int beginIndex, int pageSize)
			throws Exception {
		
		return dao.findAllRegistLevel(beginIndex, pageSize);
	}

	@Override
	public List<RegistLevel> findRegistLevelByCode(String registLevelCode)
			throws Exception {
		
		return dao.findRegistLevelByCode(registLevelCode);
	}

	@Override
	public List<User> findUserByRegistLevelId(int registId) throws Exception {
		
		return dao.findUserByRegistLevelId(registId);
	}

}
