package com.neuedu.hisunder.dao;

import java.util.List;

import com.neuedu.hisunder.entity.User;

public interface UserDao {

	/*
	 * 添加新用户
	 * */
	int addUser(User user) throws Exception;
	
	/*
	 * 修改用户信息
	 * */
	int update(User user) throws Exception;
	
	/*
	 * 删除用户
	 * */
	int delete(int userid) throws Exception;
	
	/*
	 * 查询所有用户,分页查询
	 * */
	List<User> findAll(int beginIndex,int pageSize) throws Exception;
	
	/*
	 * 统计记录条数
	 * */
	int getTotalCount() throws Exception;
	
	/*
	 * 根据用户的username的值，进行模糊查询
	 * */
	List<User> findUserByUname(String username) throws Exception;
	
	/*
	 * 根据部门编号，查询部门下属员工
	 * */
	List<User> findUserByDeptID(int deptid) throws Exception;
	
	/*
	 * 查询所有临床科室的医生真实姓名，用于生成排班规则
	 * */
	List<User> findClinicalUser() throws Exception;
	
	/*
	 * 寻找指定部门及挂号级别的人员的 真实姓名
	 * */
	List<User> findUnameByDeptAndReg(int deptid,int regid) throws Exception;

}
