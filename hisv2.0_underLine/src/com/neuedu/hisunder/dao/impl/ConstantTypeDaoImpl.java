package com.neuedu.hisunder.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.neuedu.hisunder.dao.ConstantTypeDao;
import com.neuedu.hisunder.entity.ConstantItem;
import com.neuedu.hisunder.entity.ConstantType;
import com.neuedu.hisunder.util.DBConnection;

public class ConstantTypeDaoImpl implements ConstantTypeDao {

	private static ConstantTypeDaoImpl dao = new ConstantTypeDaoImpl();
	
	private ConstantTypeDaoImpl(){
		
	}
	
	public static ConstantTypeDaoImpl getConstantTypeDaoImpl() {
		return dao;
	}
	
	@Override
	public int add(ConstantType constantType) throws Exception {
		String sql = "insert into constanttype(constantTypeCode,constantTypeName) values(?,?)";
		Connection connection = DBConnection.getConnection();
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setString(1, constantType.getConstantTypeCode());
		pstmt.setString(2, constantType.getConstantTypeName());
		int i = pstmt.executeUpdate();
		DBConnection.closeAll(null, pstmt, connection);
		return i;
	}

	@Override
	public int update(ConstantType constantType) throws Exception {
		String sql = "update constanttype set constantTypeCode=?,constantTypeName=? where id=?";
		Connection connection = DBConnection.getConnection();
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setString(1, constantType.getConstantTypeCode());
		pstmt.setString(2, constantType.getConstantTypeName());
		pstmt.setInt(3, constantType.getId());
		int i = pstmt.executeUpdate();
		DBConnection.closeAll(null, pstmt, connection);
		return i;
	}

	@Override
	public int delete(int id) throws Exception {
		String sql = "update constanttype set delMark=0 where id=?";
		Connection connection = DBConnection.getConnection();
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setInt(1, id);
		int i = pstmt.executeUpdate();
		DBConnection.closeAll(null, pstmt, connection);
		return i;
	}

	@Override
	public List<ConstantType> findAll() throws Exception {
		List<ConstantType> list = new ArrayList<ConstantType>();
		String sql = "select * from constanttype where delmark = 1";
		Connection connection = DBConnection.getConnection();
		PreparedStatement pstmt = connection.prepareStatement(sql);
		
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			ConstantType c = new ConstantType();
			c.setId(rs.getInt("id"));
			c.setConstantTypeCode(rs.getString("constanttypecode"));
			c.setConstantTypeName(rs.getString("constantTypeName"));
			c.setDelMark(rs.getInt("delMark"));

			list.add(c);
		}
		DBConnection.closeAll(rs, pstmt, connection);
		return list;
	}

	@Override
	public int getTotalCount() throws Exception {
		String sql = "select count(*) from constanttype where delmark = 1";
		Connection connection = DBConnection.getConnection();
		PreparedStatement pstmt = connection.prepareStatement(sql);
		
		ResultSet rs = pstmt.executeQuery();
		rs.next();
		int i = rs.getInt(1);
		DBConnection.closeAll(rs, pstmt, connection);
		return i;
	}

	@Override
	public ConstantType findConstantTypes(String constantTypeCode)
			throws Exception {
		List<ConstantType> list = new ArrayList<ConstantType>();
		
		String sql = "select t.*,i.id,constantcode,constantname"
				+ " from constanttype t ,constantitem i"
				+ " where t.id = i.constanttypeid"
				+ " and t.constanttypecode = ?";
		Connection connection = DBConnection.getConnection();
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setString(1, constantTypeCode);
		ResultSet rs = pstmt.executeQuery();
		ConstantType t = new ConstantType();
		List<ConstantItem> list2 = new ArrayList<ConstantItem>();
		
		while (rs.next()) {
			t.setId(rs.getInt("id"));
			t.setConstantTypeCode(rs.getString("constanttypecode"));
			t.setConstantTypeName(rs.getString("constanttypename"));
			t.setDelMark(rs.getInt("delMark"));
			
			ConstantItem i = new ConstantItem();
			i.setId(rs.getInt(5));  //item表的id
			i.setConstantCode(rs.getString("constantcode"));
			i.setConstantName(rs.getString("constantname"));
			list2.add(i);
			
		}
		t.setConstantItems(list2);
//		list.add(t);
		
		DBConnection.closeAll(rs, pstmt, connection);
//		return list;
		return t;
	}

	@Override
	public List<ConstantItem> findConstantItem(String constantTypeCode)
			throws Exception {
		String sql = "select i.*"
				+ " from constanttype t ,constantitem i"
				+ " where t.id = i.constanttypeid"
				+ " and t.constanttypecode = ?";
		List<ConstantItem> list = new ArrayList<ConstantItem>();
		Connection connection = DBConnection.getConnection();
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setString(1, constantTypeCode);
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			ConstantItem constantItem = new ConstantItem();
			constantItem.setId(rs.getInt("id"));
			constantItem.setConstantCode(rs.getString("constantcode"));
			constantItem.setConstantName(rs.getString("constantname"));
			constantItem.setDelMark(rs.getInt("delmark"));
			constantItem.setConstantTypeID(rs.getInt("constanttypeid"));
			list.add(constantItem);
		}
		DBConnection.closeAll(rs, pstmt, connection);
		return list;
	}

	@Override
	public List<ConstantType> findConstantTypeByCode(String constantTypeCode)
			throws Exception {
		String sql = "select * from constanttype where delmark = 1 and constanttypecode like ?";
		List<ConstantType> list = new ArrayList<ConstantType>();
		Connection connection = DBConnection.getConnection();
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setString(1,'%' + constantTypeCode + '%');
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			ConstantType constantType = new ConstantType();
			constantType.setId(rs.getInt("id"));
			constantType.setConstantTypeCode(rs.getString("constanttypecode"));
			constantType.setConstantTypeName(rs.getString("constanttypename"));
			constantType.setDelMark(rs.getInt("delMark"));
			
			list.add(constantType);
		}
		DBConnection.closeAll(rs, pstmt, connection);
		return list;
	}

}
