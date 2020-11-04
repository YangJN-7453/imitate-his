package com.neuedu.hisunder.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.neuedu.hisunder.dao.RuleDao;
import com.neuedu.hisunder.entity.Rule;
import com.neuedu.hisunder.util.DBConnection;

public class RuleDaoImpl implements RuleDao {
	
	private static RuleDaoImpl dao = new RuleDaoImpl();
	
	private RuleDaoImpl() {
		
	}
	
	public static RuleDaoImpl getRuleDaoImpl() {
		return dao;
	}

	@Override
	public int getTotalCount() throws Exception {
		String sql = "select count(*) from rule where delmark = 1";
		Connection connection = DBConnection.getConnection();
		PreparedStatement pstmt = connection.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		rs.next();
		int i = rs.getInt(1);
		DBConnection.closeAll(rs, pstmt, connection);
		return i;
	}

	@Override
	public List<Rule> findAllRule(int beginIndex, int pageSize)
			throws Exception {
		List<Rule> list = new ArrayList<Rule>();
		StringBuffer sub = new StringBuffer();
		sub.append("select r.*,");
		sub.append(" (select realname from user u where u.id = r.userID) as username,");
		sub.append(" (select deptName from department d where d.id = r.deptID) as deptname");
		sub.append(" from rule r where delmark = 1");
		sub.append(" limit ?,?");
		
		String sql = sub.toString();
		Connection connection = DBConnection.getConnection();
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setInt(1, beginIndex);
		pstmt.setInt(2, pageSize);
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			Rule r = new Rule();
			r.setId(rs.getInt("id"));
			r.setRuleName(rs.getString("ruleName"));
			r.setDeptID(rs.getInt("deptid"));
			r.setUserID(rs.getInt("userid"));
			r.setWeek(rs.getString("week"));
			r.setDelMark(rs.getInt("delmark"));
			r.setDeptName(rs.getString("deptname"));
			r.setUserName(rs.getString("username"));
			
			list.add(r);
		}
		DBConnection.closeAll(rs, pstmt, connection);
		return list;
	}

	@Override
	public List<Rule> findRuleByUname(String uname) throws Exception {
		List<Rule> list = new ArrayList<Rule>();
		StringBuffer sub = new StringBuffer();
		sub.append("select r.*,");
		sub.append(" (select realname from user u where u.id = r.userID) as username,");
		sub.append(" (select deptName from department d where d.id = r.deptID) as deptname");
		sub.append(" from rule r where delmark = 1 and userid =");
		sub.append(" (select id from user where delmark = 1 and realname like ?)");
		
		String sql = sub.toString();
		Connection connection = DBConnection.getConnection();
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setString(1, '%' + uname + '%');
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			Rule r = new Rule();
			r.setId(rs.getInt("id"));
			r.setRuleName(rs.getString("ruleName"));
			r.setDeptID(rs.getInt("deptid"));
			r.setUserID(rs.getInt("userid"));
			r.setWeek(rs.getString("week"));
			r.setDelMark(rs.getInt("delmark"));
			r.setDeptName(rs.getString("deptname"));
			r.setUserName(rs.getString("username"));
			
			list.add(r);
		}
		return list;
	}

	@Override
	public int addRule(Rule rule) throws Exception {
		String sql = "insert into rule (ruleName,deptID,userID,week) values(?,?,?,?)";
		Connection connection = DBConnection.getConnection();
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setString(1, rule.getRuleName());
		pstmt.setInt(2, rule.getDeptID());
		pstmt.setInt(3, rule.getUserID());
		pstmt.setString(4, rule.getWeek());
		int i = pstmt.executeUpdate();
		DBConnection.closeAll(null, pstmt, connection);
		return i;
	}

}
