package com.neuedu.hisunder.dao;

import java.util.List;

import com.neuedu.hisunder.entity.Department;

public interface DepartmentDao {

	/*
	 * 统计记录条数
	 * */
	int getTotalCount() throws Exception;
	
	/*
	 * 添加部门
	 * */
	int addDept(Department department) throws Exception;
	
	/*
	 * 修改部门
	 * */
	int updateDept(Department department) throws Exception;

	/*
	 * 删除部门
	 * */
	int deleteDept(int id) throws Exception;
	
	/*
	 * 查询所有部门,分页查询
	 * */
	List<Department> findAllDept(int beginIndex,int pageSize) throws Exception;
	
	/*
	 * 根据部门的拼音助记码，进行模糊查询
	 * */
	List<Department> findDeptByCode(String deptCode) throws Exception;

	/*
	 * 不分页，用于添加或修改用户表时，获取dept表相关下拉框内容
	 * */
	List<Department> finAllDepartments() throws Exception;
	
}
