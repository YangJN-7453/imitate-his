package com.neuedu.hisunder.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.neuedu.hisunder.dao.DiseaseDao;
import com.neuedu.hisunder.entity.DiseCategory;
import com.neuedu.hisunder.entity.Disease;
import com.neuedu.hisunder.util.DBConnection;

public class DiseaseDaoImpl implements DiseaseDao {

	private static DiseaseDaoImpl dao = new DiseaseDaoImpl();
	
	private DiseaseDaoImpl() {
		
	}
	
	public static DiseaseDaoImpl getDiseaseDaoImpl() {
		return dao;
	}
	
	@Override
	public int getTotalCount() throws Exception {
		String sql = "select count(*) from disease where delmark = 1";
		Connection connection = DBConnection.getConnection();
		PreparedStatement pstmt = connection.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		rs.next();
		int i = rs.getInt(1);
		DBConnection.closeAll(rs, pstmt, connection);
		return i;
	}

	@Override
	public int addDisease(Disease disease) throws Exception {
		String sql = "insert into disease(diseaseCode,diseaseName,diseaseICD,diseCategoryID) values(?,?,?,?)";
		Connection connection = DBConnection.getConnection();
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setString(1, disease.getDiseaseCode());
		pstmt.setString(2, disease.getDiseaseName());
		pstmt.setString(3, disease.getDiseaseICD());
		pstmt.setInt(4, disease.getDiseCategoryID());
		int i = pstmt.executeUpdate();
		DBConnection.closeAll(null, pstmt, connection);
		return i;
	}

	@Override
	public int updateDisease(Disease disease) throws Exception {
		String sql = "update disease set diseaseCode=?,diseaseName=?,diseaseICD=?,diseCategoryID=? where id=?";
		Connection connection = DBConnection.getConnection();
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setString(1, disease.getDiseaseCode());
		pstmt.setString(2, disease.getDiseaseName());
		pstmt.setString(3, disease.getDiseaseICD());
		pstmt.setInt(4, disease.getDiseCategoryID());
		pstmt.setInt(5, disease.getId());
		int i = pstmt.executeUpdate();
		DBConnection.closeAll(null, pstmt, connection);
		return i;
	}

	@Override
	public int deleteDisease(int id) throws Exception {
		String sql = "update disease set delmark = 0 where id = ?";
		Connection connection = DBConnection.getConnection();
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setInt(1, id);
		int i = pstmt.executeUpdate();
		DBConnection.closeAll(null, pstmt, connection);
		return i;
	}

	@Override
	public List<Disease> findAllDisease(int beginIndex, int pageSize)
			throws Exception {
		List<Disease> list = new ArrayList<Disease>();
		StringBuffer sub = new StringBuffer();
		sub.append("select d.*,");
		sub.append(" (select dicaName from diseCategory dc where dc.id = d.diseCategoryID) as diseCategoryName");
		sub.append(" from disease d where delmark = 1");
		sub.append(" limit ?,?");
		
		String sql = sub.toString();
		Connection connection = DBConnection.getConnection();
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setInt(1, beginIndex);
		pstmt.setInt(2, pageSize);
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			Disease disease = new Disease();
			disease.setId(rs.getInt("id"));
			disease.setDiseaseCode(rs.getString("diseaseCode"));
			disease.setDiseaseName(rs.getString("diseaseName"));
			disease.setDiseaseICD(rs.getString("diseaseICD"));
			disease.setDiseCategoryID(rs.getInt("diseCategoryID"));
			disease.setDelMark(rs.getInt("delMark"));
			disease.setDiseCategoryName(rs.getString("diseCategoryName"));
			
			list.add(disease);
		}
		DBConnection.closeAll(rs, pstmt, connection);
		return list;
	}

	@Override
	public List<Disease> findDiseaseByCode(String diseaseCode) throws Exception {
		List<Disease> list = new ArrayList<Disease>();
		StringBuffer sub = new StringBuffer();
		sub.append("select d.*,");
		sub.append(" (select dicaName from diseCategory dc where dc.id = d.diseCategoryID) as diseCategoryName");
		sub.append(" from disease d where delmark = 1 and diseaseCode like ?");

		String sql = sub.toString();
		Connection connection = DBConnection.getConnection();
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setString(1, '%' + diseaseCode + '%');
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			Disease disease = new Disease();
			disease.setId(rs.getInt("id"));
			disease.setDiseaseCode(rs.getString("diseaseCode"));
			disease.setDiseaseName(rs.getString("diseaseName"));
			disease.setDiseaseICD(rs.getString("diseaseICD"));
			disease.setDiseCategoryID(rs.getInt("diseCategoryID"));
			disease.setDelMark(rs.getInt("delMark"));
			disease.setDiseCategoryName(rs.getString("diseCategoryName"));
			
			list.add(disease);
		}
		DBConnection.closeAll(rs, pstmt, connection);
		return list;
	}

	@Override
	public List<DiseCategory> findDiseaseInfo() throws Exception {
		List<DiseCategory> list = new ArrayList<DiseCategory>();
		String sql = "select id,dicaName from diseCategory where delmark = 1";
		Connection connection = DBConnection.getConnection();
		PreparedStatement pstmt = connection.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			DiseCategory diseCategory = new DiseCategory();
			diseCategory.setId(rs.getInt(1));
			diseCategory.setDicaName(rs.getString(2));
			list.add(diseCategory);
		}
		DBConnection.closeAll(rs, pstmt, connection);
		return list;
	}

}
