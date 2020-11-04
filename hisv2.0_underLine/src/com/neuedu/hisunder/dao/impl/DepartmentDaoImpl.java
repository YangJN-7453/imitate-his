package com.neuedu.hisunder.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.neuedu.hisunder.dao.DepartmentDao;
import com.neuedu.hisunder.entity.Department;
import com.neuedu.hisunder.util.DBConnection;

public class DepartmentDaoImpl implements DepartmentDao {

	/* 使用单例模式 */
	private static DepartmentDaoImpl dao = new DepartmentDaoImpl();

	private DepartmentDaoImpl() {

	}

	public static DepartmentDaoImpl getDepartmentDaoImpl() {
		return dao;
	}

	@Override
	public int getTotalCount() throws Exception {
		String sql = "select count(*) from department where delmark = 1";
		Connection connection = DBConnection.getConnection();
		PreparedStatement pstmt = connection.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		rs.next();
		int i = rs.getInt(1);
		DBConnection.closeAll(rs, pstmt, connection);
		return i;
	}

	@Override
	public int addDept(Department department) throws Exception {
		String sql = "insert into department (deptCode,deptName,deptCategoryID,deptType) values(?,?,?,?)";
		Connection connection = DBConnection.getConnection();
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setString(1, department.getDeptCode());
		pstmt.setString(2, department.getDeptName());
		pstmt.setInt(3, department.getDeptCategoryID());
		pstmt.setInt(4, department.getDeptType());
		int i = pstmt.executeUpdate();
		DBConnection.closeAll(null, pstmt, connection);
		return i;
	}

	@Override
	public int updateDept(Department department) throws Exception {
		String sql = "update department set deptCode=?,deptName=?,deptCategoryID=?,deptType=? where id=?";
		Connection connection = DBConnection.getConnection();
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setString(1, department.getDeptCode());
		pstmt.setString(2, department.getDeptName());
		pstmt.setInt(3, department.getDeptCategoryID());
		pstmt.setInt(4, department.getDeptType());
		pstmt.setInt(5, department.getId());
		int i = pstmt.executeUpdate();
		DBConnection.closeAll(null, pstmt, connection);
		return i;
	}

	@Override
	public int deleteDept(int id) throws Exception {
		String sql = "update department set delmark = 0 where id = ?";
		Connection connection = DBConnection.getConnection();
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setInt(1, id);
		int i = pstmt.executeUpdate();
		DBConnection.closeAll(null, pstmt, connection);
		return i;
	}

	@Override
	public List<Department> findAllDept(int beginIndex, int pageSize)
			throws Exception {
		List<Department> list = new ArrayList<Department>();
		StringBuffer sub = new StringBuffer();
		sub.append("select d.*,");
		sub.append(" (select constantname from constantitem i where i.id = d.deptCategoryID) as deptCategoryName,");
		sub.append(" (select constantname from constantitem i where i.id = d.deptType) as deptTypeName");
		sub.append(" from department d where delmark = 1");
		sub.append(" limit ?,?");
		
		String sql = sub.toString();
		Connection connection = DBConnection.getConnection();
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setInt(1, beginIndex);
		pstmt.setInt(2, pageSize);
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			Department dept = new Department();
			dept.setId(rs.getInt("id"));
			dept.setDeptCode(rs.getString("deptCode"));
			dept.setDeptName(rs.getString("deptName"));
			dept.setDeptCategoryID(rs.getInt("deptCategoryID"));
			dept.setDeptType(rs.getInt("deptType"));
			dept.setDelMark(rs.getInt("delMark"));
			dept.setDeptCategoryName(rs.getString("deptCategoryName"));
			dept.setDeptTypeName(rs.getString("deptTypeName"));
			
			list.add(dept);
		}
		DBConnection.closeAll(rs, pstmt, connection);
		return list;
	}

	@Override
	public List<Department> findDeptByCode(String deptCode) throws Exception {
		List<Department> list = new ArrayList<Department>();
		StringBuffer sub = new StringBuffer();
		sub.append("select d.*,");
		sub.append(" (select constantname from constantitem i where i.id = d.deptCategoryID) as deptCategoryName,");
		sub.append(" (select constantname from constantitem i where i.id = d.deptType) as deptTypeName");
		sub.append(" from department d where delmark = 1 and deptCode like ?");
		
		String sql = sub.toString();
		Connection connection = DBConnection.getConnection();
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setString(1, '%' + deptCode + '%');
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			Department dept = new Department();
			dept.setId(rs.getInt("id"));
			dept.setDeptCode(rs.getString("deptCode"));
			dept.setDeptName(rs.getString("deptName"));
			dept.setDeptCategoryID(rs.getInt("deptCategoryID"));
			dept.setDeptType(rs.getInt("deptType"));
			dept.setDelMark(rs.getInt("delMark"));
			dept.setDeptCategoryName(rs.getString("deptCategoryName"));
			dept.setDeptTypeName(rs.getString("deptTypeName"));
			
			list.add(dept);
		}
		DBConnection.closeAll(rs, pstmt, connection);
		return list;
	}

	@Override
	public List<Department> finAllDepartments() throws Exception {
		List<Department> list = new ArrayList<Department>();
		String sql = "select * from department where delmark = 1";
		Connection connection = DBConnection.getConnection();
		PreparedStatement pstmt = connection.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			Department dept = new Department();
			
			dept.setId(rs.getInt("id"));
			dept.setDeptCode(rs.getString("deptCode"));
			dept.setDeptName(rs.getString("deptName"));
			dept.setDeptCategoryID(rs.getInt("deptCategoryID"));
			dept.setDeptType(rs.getInt("deptType"));
			dept.setDelMark(rs.getInt("delMark"));
			
			list.add(dept);
		}
		DBConnection.closeAll(rs, pstmt, connection);
		return list;
	}

}
