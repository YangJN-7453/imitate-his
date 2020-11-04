package com.neuedu.hisunder.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.neuedu.hisunder.dao.FmeditemDao;
import com.neuedu.hisunder.entity.ExpenseClass;
import com.neuedu.hisunder.entity.Fmeditem;
import com.neuedu.hisunder.util.DBConnection;

public class FmeditemDaoImpl implements FmeditemDao {
	
	private static FmeditemDaoImpl dao = new FmeditemDaoImpl();
	
	private FmeditemDaoImpl() {
		// TODO Auto-generated constructor stub
	}
	
	public static FmeditemDaoImpl getFmeditemDaoImpl() {
		return dao;
	}

	@Override
	public int getTotalCount() throws Exception {
		String sql = "select count(*) from Fmeditem where delmark = 1";
		Connection connection = DBConnection.getConnection();
		PreparedStatement pstmt = connection.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		rs.next();
		int i = rs.getInt(1);
		DBConnection.closeAll(rs, pstmt, connection);
		return i;
	}

	@Override
	public int addFmeditem(Fmeditem fmeditem) throws Exception {
		String sql = "insert into Fmeditem (ItemCode,ItemName,Format,Price,ExpClassID,DeptID,MnemonicCode,CreationDate,LastUpdateDate,RecordType) values(?,?,?,?,?,?,?,?,?,?)";
		java.sql.Date sqlDate = new java.sql.Date(fmeditem.getCreationDate().getTime());
		Connection connection = DBConnection.getConnection();
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setString(1, fmeditem.getItemCode());
		pstmt.setString(2, fmeditem.getItemName());
		pstmt.setString(3, fmeditem.getFormat());
		pstmt.setDouble(4, fmeditem.getPrice());
		pstmt.setInt(5, fmeditem.getExpClassID());
		pstmt.setInt(6, fmeditem.getDeptID());
		pstmt.setString(7, fmeditem.getMnemonicCode());
		pstmt.setDate(8, sqlDate);
		pstmt.setDate(9, sqlDate);
		pstmt.setInt(10, fmeditem.getRecordType());
		
		int i = pstmt.executeUpdate();
		DBConnection.closeAll(null, pstmt, connection);
		return i;
	}

	@Override
	public int updateFmeditem(Fmeditem fmeditem) throws Exception {
		String sql = "update Fmeditem set ItemCode=?,ItemName=?,Format=?,Price=?,ExpClassID=?,DeptID=?,MnemonicCode=?,LastUpdateDate=?,RecordType=? where id = ?";
		Connection connection = DBConnection.getConnection();
		PreparedStatement pstmt = connection.prepareStatement(sql);
		
		java.sql.Date lastDate = new java.sql.Date(fmeditem.getLastUpdateDate().getTime());
		pstmt.setString(1, fmeditem.getItemCode());
		pstmt.setString(2, fmeditem.getItemName());
		pstmt.setString(3, fmeditem.getFormat());
		pstmt.setDouble(4, fmeditem.getPrice());
		pstmt.setInt(5, fmeditem.getExpClassID());
		pstmt.setInt(6, fmeditem.getDeptID());
		pstmt.setString(7, fmeditem.getMnemonicCode());
		pstmt.setDate(8, lastDate);
		pstmt.setInt(9, fmeditem.getRecordType());
		pstmt.setInt(10, fmeditem.getId());
		int i = pstmt.executeUpdate();
		DBConnection.closeAll(null, pstmt, connection);
		return i;
	}

	@Override
	public int deleteFmeditem(int id) throws Exception {
		String sql = "update Fmeditem set delmark=0 where id = ?";
		Connection connection = DBConnection.getConnection();
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setInt(1, id);
		int i = pstmt.executeUpdate();
		DBConnection.closeAll(null, pstmt, connection);
		return i;
	}

	@Override
	public List<Fmeditem> findAllFmeditem(int beginIndex, int pageSize)
			throws Exception {
		List<Fmeditem> list = new ArrayList<Fmeditem>();
		StringBuffer sub = new StringBuffer();
		sub.append("select f.*,");
		sub.append(" (select expName from ExpenseClass exp where exp.id = f.ExpClassID) as ExpClassName,");
		sub.append(" (select deptName from department dept where dept.id = f.deptID) as deptName");
		sub.append(" from Fmeditem f where f.delmark = 1");
		sub.append(" limit ?,?");
		
		String sql = sub.toString();
		Connection connection = DBConnection.getConnection();
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setInt(1, beginIndex);
		pstmt.setInt(2, pageSize);
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			 Fmeditem f = new Fmeditem();
			 f.setId(rs.getInt("id"));
			 f.setItemCode(rs.getString("itemCode"));
			 f.setItemName(rs.getString("itemName"));
			 f.setFormat(rs.getString("format"));
			 f.setPrice(rs.getDouble("price"));
			 f.setExpClassID(rs.getInt("expClassID"));
			 f.setDeptID(rs.getInt("deptID"));
			 f.setMnemonicCode(rs.getString("mnemonicCode"));
			 f.setCreationDate(rs.getDate("creationDate"));
			 f.setLastUpdateDate(rs.getDate("lastUpdateDate"));
			 f.setRecordType(rs.getInt("recordType"));
			 f.setExpClassName(rs.getString("ExpClassName"));
			 f.setDeptName(rs.getString("deptName"));
			 
			 list.add(f);
		}
		DBConnection.closeAll(rs, pstmt, connection);
		return list;
	}

	@Override
	public List<Fmeditem> findFmeditemByCode(String mnemonicCode)
			throws Exception {
		List<Fmeditem> list = new ArrayList<Fmeditem>();
		StringBuffer sub = new StringBuffer();
		sub.append("select f.*,");
		sub.append(" (select expName from ExpenseClass exp where exp.id = f.ExpClassID) as ExpClassName,");
		sub.append(" (select deptName from department dept where dept.id = f.deptID) as deptName");
		sub.append(" from Fmeditem f where f.delmark = 1 and mnemonicCode like ?");
		
		String sql = sub.toString();
		Connection connection = DBConnection.getConnection();
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setString(1, '%' + mnemonicCode + '%');
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			 Fmeditem f = new Fmeditem();
			 f.setId(rs.getInt("id"));
			 f.setItemCode(rs.getString("itemCode"));
			 f.setItemName(rs.getString("itemName"));
			 f.setFormat(rs.getString("format"));
			 f.setPrice(rs.getDouble("price"));
			 f.setExpClassID(rs.getInt("expClassID"));
			 f.setDeptID(rs.getInt("deptID"));
			 f.setMnemonicCode(rs.getString("mnemonicCode"));
			 f.setCreationDate(rs.getDate("creationDate"));
			 f.setLastUpdateDate(rs.getDate("lastUpdateDate"));
			 f.setRecordType(rs.getInt("recordType"));
			 f.setExpClassName(rs.getString("ExpClassName"));
			 f.setDeptName(rs.getString("deptName"));
			 
			 list.add(f);
		}
		DBConnection.closeAll(rs, pstmt, connection);
		return list; 
	}

	@Override
	public List<ExpenseClass> findAllExpenseClass() throws Exception {
		List<ExpenseClass> list = new ArrayList<ExpenseClass>();
		String sql = "select id,expName from expenseclass where delmark = 1";
		Connection connection = DBConnection.getConnection();
		PreparedStatement pstmt = connection.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			ExpenseClass exp = new ExpenseClass();
			exp.setId(rs.getInt(1));
			exp.setExpName(rs.getString(2));
			list.add(exp);
		}
		DBConnection.closeAll(rs, pstmt, connection);
		return list;
	}

}
