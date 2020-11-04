package com.neuedu.hisunder.service.impl;

import java.util.List;

import com.neuedu.hisunder.dao.DiseaseDao;
import com.neuedu.hisunder.dao.impl.DiseaseDaoImpl;
import com.neuedu.hisunder.entity.DiseCategory;
import com.neuedu.hisunder.entity.Disease;
import com.neuedu.hisunder.service.DiseaseService;

public class DiseaseServiceImpl implements DiseaseService {

	private DiseaseDao dao;
	
	public DiseaseServiceImpl() {
		dao = DiseaseDaoImpl.getDiseaseDaoImpl();
	}
	
	@Override
	public int getTotalCount() throws Exception {
		
		return dao.getTotalCount();
	}

	@Override
	public int addDisease(Disease disease) throws Exception {
		
		return dao.addDisease(disease);
	}

	@Override
	public int updateDisease(Disease disease) throws Exception {
		
		return dao.updateDisease(disease);
	}

	@Override
	public int deleteDisease(int id) throws Exception {
		
		return dao.deleteDisease(id);
	}

	@Override
	public List<Disease> findAllDisease(int beginIndex, int pageSize)
			throws Exception {
		
		return dao.findAllDisease(beginIndex, pageSize);
	}

	@Override
	public List<Disease> findDiseaseByCode(String diseaseCode) throws Exception {
		
		return dao.findDiseaseByCode(diseaseCode);
	}

	@Override
	public List<DiseCategory> findDiseaseInfo() throws Exception {
		
		return dao.findDiseaseInfo();
	}

}
