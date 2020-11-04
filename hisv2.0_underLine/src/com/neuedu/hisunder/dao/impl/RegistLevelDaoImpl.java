package com.neuedu.hisunder.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.neuedu.hisunder.dao.RegistLevelDao;
import com.neuedu.hisunder.entity.RegistLevel;
import com.neuedu.hisunder.entity.User;
import com.neuedu.hisunder.util.DBConnection;

public class RegistLevelDaoImpl implements RegistLevelDao {

	/* 使用单例模式 */
	private static RegistLevelDaoImpl dao = new RegistLevelDaoImpl();

	private RegistLevelDaoImpl() {

	}

	public static RegistLevelDaoImpl getRegistLevelDaoImpl() {
		return dao;
	}
	
	@Override
	public int getTotalCount() throws Exception {
		String sql = "select count(*) from registlevel where delmark = 1";
		Connection connection = DBConnection.getConnection();
		PreparedStatement pstmt = connection.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		rs.next();
		int i = rs.getInt(1);
		DBConnection.closeAll(rs, pstmt, connection);
		return i;
	}

	@Override
	public int addRegistLevel(RegistLevel registLevel) throws Exception {
		String sql = "insert into registlevel (registCode,registName,sequenceNo,registFee,registQuota) values (?,?,?,?,?)";
		Connection connection = DBConnection.getConnection();
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setString(1, registLevel.getRegistCode());
		pstmt.setString(2, registLevel.getRegistName());
		pstmt.setInt(3, registLevel.getSequenceNo());
		pstmt.setDouble(4, registLevel.getRegistFee());
		pstmt.setInt(5, registLevel.getRegistQuota());
		int i = pstmt.executeUpdate();
		DBConnection.closeAll(null, pstmt, connection);
		return i;
	}

	@Override
	public int updateRegistLevel(RegistLevel registLevel) throws Exception {
		String sql = "update registlevel set registCode=?,registName=?,sequenceNo=?,registFee=?,registQuota=? where id=?";
		Connection connection =DBConnection.getConnection();
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setString(1, registLevel.getRegistCode());
		pstmt.setString(2, registLevel.getRegistName());
		pstmt.setInt(3, registLevel.getSequenceNo());
		pstmt.setDouble(4, registLevel.getRegistFee());
		pstmt.setInt(5, registLevel.getRegistQuota());
		pstmt.setInt(6, registLevel.getId());
		int i = pstmt.executeUpdate();
		DBConnection.closeAll(null, pstmt, connection);
		return i;
	}

	@Override
	public int deleteRegistLevel(int id) throws Exception {
		String sql = "update registlevel set delmark = 0 where id = ?";
		Connection connection = DBConnection.getConnection();
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setInt(1, id);
		int i = pstmt.executeUpdate();
		DBConnection.closeAll(null, pstmt, connection);
		return i;
	}

	@Override
	public List<RegistLevel> findAllRegistLevel(int beginIndex, int pageSize)
			throws Exception {
		List<RegistLevel> list = new ArrayList<RegistLevel>();
		String sql = "select * from registlevel where delmark = 1 limit ?,?";
		Connection connection = DBConnection.getConnection();
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setInt(1, beginIndex);
		pstmt.setInt(2, pageSize);
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			RegistLevel r = new RegistLevel();
			r.setId(rs.getInt("id"));
			r.setRegistCode(rs.getString("registCode"));
			r.setRegistName(rs.getString("registName"));
			r.setSequenceNo(rs.getInt("sequenceNo"));
			r.setRegistFee(rs.getDouble("registFee"));
			r.setRegistQuota(rs.getInt("registQuota"));
			
			list.add(r);
		}
		DBConnection.closeAll(rs, pstmt, connection);
		return list;
	}

	@Override
	public List<RegistLevel> findRegistLevelByCode(String registLevelCode)
			throws Exception {
		// 模糊查询
		List<RegistLevel> list = new ArrayList<RegistLevel>();
		String sql = "select * from registlevel where delmark = 1 and registCode like ?";
		Connection connection = DBConnection.getConnection();
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setString(1, '%' + registLevelCode + '%');
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			RegistLevel r = new RegistLevel();
			r.setId(rs.getInt("id"));
			r.setRegistCode(rs.getString("registCode"));
			r.setRegistName(rs.getString("registName"));
			r.setSequenceNo(rs.getInt("sequenceNo"));
			r.setRegistFee(rs.getDouble("registFee"));
			r.setRegistQuota(rs.getInt("registQuota"));
			
			list.add(r);
		}
		DBConnection.closeAll(rs, pstmt, connection);
		return list;
	}

	@Override
	public List<User> findUserByRegistLevelId(int registId) throws Exception {
		StringBuffer sub = new StringBuffer();
		sub.append("select u.id,u.realName,r.registName,r.RegistFee from user u,RegistLevel r");
		sub.append(" where u.delmark = 1 and r.delmark = 1");
		sub.append(" and u.registLeID = r.id and r.id = ?");
		String sql = sub.toString();
		List<User> list = new ArrayList<User>();
		Connection connection = DBConnection.getConnection();
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setInt(1, registId);
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			User u = new User();
			u.setId(rs.getInt("id"));
			u.setRealName(rs.getString("realName"));
			u.setRegistLeName(rs.getString("registName"));
			u.setRegistFee(rs.getString("RegistFee"));
			
			list.add(u);
		}
		DBConnection.closeAll(rs, pstmt, connection);
		return list;
	}

	@Override
	public List<RegistLevel> findAllReg() throws Exception {
		List<RegistLevel> list = new ArrayList<RegistLevel>();
		String sql = "select * from registlevel where delmark = 1";
		Connection connection = DBConnection.getConnection();
		PreparedStatement pstmt = connection.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			RegistLevel r = new RegistLevel();
			
			r.setId(rs.getInt("id"));
			r.setRegistCode(rs.getString("RegistCode"));
			r.setRegistName(rs.getString("registName"));
			r.setSequenceNo(rs.getInt("sequenceNo"));
			r.setRegistFee(rs.getDouble("registFee"));
			r.setRegistQuota(rs.getInt("registQuota"));
			
			list.add(r);
		}
		DBConnection.closeAll(rs, pstmt, connection);
		return list;
	}

}
