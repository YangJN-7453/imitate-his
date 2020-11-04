package com.neuedu.hisunder.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.neuedu.hisunder.dao.UserDao;
import com.neuedu.hisunder.entity.User;
import com.neuedu.hisunder.util.DBConnection;

public class UserDaoImpl implements UserDao {

	private static UserDaoImpl dao = new UserDaoImpl();
	
	private UserDaoImpl() {
		// TODO Auto-generated constructor stub
	}
	
	public static UserDaoImpl getUserDaoImpl() {
		return dao;
	}
	
	@Override
	public int addUser(User user) throws Exception {
		String sql = "insert into user(username,password,realname,usertype,doctitleID,isScheduling,deptid,registleID) "
				+ "values (?,?,?,?,?,?,?,?)";
		Connection connection = DBConnection.getConnection();
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setString(1, user.getUserName());
		pstmt.setString(2, user.getPassword());
		pstmt.setString(3, user.getRealName());
		pstmt.setInt(4, user.getUserType());
		pstmt.setInt(5, user.getDocTitleID());
		pstmt.setString(6, user.getIsScheduling());
		pstmt.setInt(7, user.getDeptID());
		pstmt.setInt(8, user.getRegistLeID());
		
		int i = pstmt.executeUpdate();
		DBConnection.closeAll(null, pstmt, connection);
		return i;
	}

	@Override
	public int update(User user) throws Exception {
		String sql = "update user set username=?,password=?,realname=?,usertype=?,"
				+ "doctitleID=?,isScheduling=?,deptid=?,registleID=? where id=?";
		Connection connection = DBConnection.getConnection();
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setString(1, user.getUserName());
		pstmt.setString(2, user.getPassword());
		pstmt.setString(3, user.getRealName());
		pstmt.setInt(4, user.getUserType());
		pstmt.setInt(5, user.getDocTitleID());
		pstmt.setString(6, user.getIsScheduling());
		pstmt.setInt(7, user.getDeptID());
		pstmt.setInt(8, user.getRegistLeID());
		pstmt.setInt(9, user.getId());
		
		int i = pstmt.executeUpdate();
		DBConnection.closeAll(null, pstmt, connection);
		return i;
	}

	@Override
	public int delete(int userid) throws Exception {
		String sql = "update user set delmark=0 where id=?";
		Connection connection = DBConnection.getConnection();
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setInt(1, userid);
		int i = pstmt.executeUpdate();
		DBConnection.closeAll(null, pstmt, connection);
		return i;
	}

	@Override
	public List<User> findAll(int beginIndex, int pageSize) throws Exception {
		// 使用分页查询
		List<User> list = new ArrayList<User>();
		StringBuffer sub = new StringBuffer();
		sub.append("select u.*,");
		sub.append(" (select deptname from department d where d.id = u.deptid) deptname,");
		sub.append(" (select constantName from constantitem i where i.id = u.userType) userTypeName,");
		sub.append(" (select constantName from constantItem i where i.id = u.docTitleID) docTitleName,");
		sub.append(" (select registName from registlevel r where r.id = u.registLeID) registLeName");
		sub.append(" from user u");
		sub.append(" where delmark = 1");
		sub.append(" limit ?,?");
		
		
		
		String sql = sub.toString();
		Connection connection = DBConnection.getConnection();
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setInt(1, beginIndex);
		pstmt.setInt(2, pageSize);
		ResultSet rs = pstmt.executeQuery();
		
		while (rs.next()) {
			User user = null;
			
			user = new User();
			user.setId(rs.getInt("id"));
			user.setUserName(rs.getString("userName"));
			user.setPassword(rs.getString("password"));
			user.setRealName(rs.getString("realName"));
			user.setUserType(rs.getInt("userType"));
			user.setDocTitleID(rs.getInt("docTitleID"));
			user.setIsScheduling(rs.getString("isScheduling"));
			user.setDeptID(rs.getInt("deptID"));
			user.setRegistLeID(rs.getInt("registLeID"));
			user.setDelMark(rs.getInt("delMark"));
			
			// 扩充属性
			user.setUserTypeName(rs.getString("userTypeName"));
			user.setDocTitleName(rs.getString("docTitleName"));
			user.setRegistLeName(rs.getString("registLeName"));
			user.setDeptname(rs.getString("deptname"));
			
			list.add(user);
		}
		DBConnection.closeAll(rs, pstmt, connection);
		return list;
	}

	@Override
	public int getTotalCount() throws Exception {
		String sql = "select count(*) from user where delmark = 1";
		Connection connection = DBConnection.getConnection();
		PreparedStatement pstmt = connection.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		rs.next();
		int i = rs.getInt(1);
		DBConnection.closeAll(rs, pstmt, connection);
		return i;
	}

	@Override
	public List<User> findUserByUname(String username) throws Exception {
		StringBuffer sub = new StringBuffer();
		sub.append("select u.*,");
		sub.append(" (select deptname from department d where d.id = u.deptid) deptname,");
		sub.append(" (select constantName from constantitem i where i.id = u.userType) userTypeName,");
		sub.append(" (select constantName from constantItem i where i.id = u.docTitleID) docTitleName,");
		sub.append(" (select registName from registlevel r where r.id = u.registLeID) registLeName");
		sub.append(" from user u");
		sub.append(" where delmark = 1 and userName like ?");
		String sql = sub.toString();
//		String sql = "select * from user where delmark = 1 and userName like ?";
		List<User> list = new ArrayList<User>();
		Connection connection = DBConnection.getConnection();
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setString(1, '%' + username + '%');
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			User user = null;
			
			user = new User();
			user.setId(rs.getInt("id"));
			user.setUserName(rs.getString("userName"));
			user.setPassword(rs.getString("password"));
			user.setRealName(rs.getString("realName"));
			user.setUserType(rs.getInt("userType"));
			user.setDocTitleID(rs.getInt("docTitleID"));
			user.setIsScheduling(rs.getString("isScheduling"));
			user.setDeptID(rs.getInt("deptID"));
			user.setRegistLeID(rs.getInt("registLeID"));
			user.setDelMark(rs.getInt("delMark"));
			
			// 扩充属性
			user.setUserTypeName(rs.getString("userTypeName"));
			user.setDocTitleName(rs.getString("docTitleName"));
			user.setRegistLeName(rs.getString("registLeName"));
			user.setDeptname(rs.getString("deptname"));
			
			list.add(user);
		}
		DBConnection.closeAll(rs, pstmt, connection);
		return list;
	}

	@Override
	public List<User> findUserByDeptID(int deptid) throws Exception {
		StringBuffer sub = new StringBuffer();
		sub.append("select u.id,u.realName,d.deptName from user u,department d");
		sub.append(" where u.delmark = 1 and d.delmark = 1");
		sub.append(" and u.deptid = d.id and d.id = ?");
		String sql = sub.toString();
		List<User> list = new ArrayList<User>();
		Connection connection = DBConnection.getConnection();
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setInt(1, deptid);
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			User u = new User();
			u.setId(rs.getInt("id"));
			u.setRealName(rs.getString("realName"));
			u.setDeptname(rs.getString("deptname"));
			
			list.add(u);
		}
		DBConnection.closeAll(rs, pstmt, connection);
		return list;
	}

	@Override
	public List<User> findClinicalUser() throws Exception {
		StringBuffer sub = new StringBuffer();
		sub.append("select id,realname,deptID from user");
		sub.append(" where delmark = 1 and deptid in");
		sub.append(" (select id from department where delmark = 1 and depttype = 19)");
		
		String sql = sub.toString();
		List<User> list = new ArrayList<User>();
		Connection connection = DBConnection.getConnection();
		PreparedStatement pstmt = connection.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			User u = new User();
			u.setId(rs.getInt(1));
			u.setRealName(rs.getString(2));
			u.setDeptID(rs.getInt(3));

			list.add(u);
		}
		return list;
	}

	@Override
	public List<User> findUnameByDeptAndReg(int deptid,int regid) throws Exception {
		StringBuffer sub = new StringBuffer();
		sub.append("select id,realname,deptID from user u");
		sub.append(" where delmark = 1");
		
		if (deptid != 0 && regid != 0) {
			sub.append(" and deptid = ?");
			sub.append(" and RegistLeID = ?");
		} else if (deptid != 0 && regid == 0) {
			sub.append(" and deptid = ?");
		} else if (deptid == 0 && regid != 0) {
			sub.append(" and RegistLeID = ?");
		} else {
			return findClinicalUser();
		}

		String sql = sub.toString();
		List<User> list = new ArrayList<User>();
		Connection connection = DBConnection.getConnection();
		PreparedStatement pstmt = connection.prepareStatement(sql);
		if (deptid != 0 && regid != 0) {
			pstmt.setInt(1, deptid);
			pstmt.setInt(2, regid);
		} else if (deptid != 0 && regid == 0) {
			pstmt.setInt(1, deptid);
		} else if (deptid == 0 && regid != 0) {
			pstmt.setInt(1, regid);
		}
		
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			User u = new User();
			u.setId(rs.getInt(1));
			u.setRealName(rs.getString(2));
			u.setDeptID(rs.getInt(3));

			list.add(u);
		}
		return list;
	}

}
