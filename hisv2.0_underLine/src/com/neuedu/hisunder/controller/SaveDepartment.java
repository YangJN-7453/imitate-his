package com.neuedu.hisunder.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neuedu.hisunder.entity.Department;
import com.neuedu.hisunder.service.DepartmentService;
import com.neuedu.hisunder.service.impl.DepartmentServiceImpl;
import com.neuedu.hisunder.util.StringUtil;

/**
 * Servlet implementation class SaveDepartment
 */
@WebServlet("/SaveDepartment")
public class SaveDepartment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaveDepartment() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		
		// 接受请求数据
		String deptId = request.getParameter("deptId");
		String deptCode = request.getParameter("deptCode");
		String deptName = request.getParameter("deptName");
		String deptCategoryId = request.getParameter("deptCategoryId");
		String deptType = request.getParameter("deptType");
		
		Department dept = new Department();
		dept.setDeptCode(deptCode);
		dept.setDeptName(deptName);
		dept.setDeptCategoryID(Integer.parseInt(deptCategoryId));
		dept.setDeptType(Integer.parseInt(deptType));
		
		DepartmentService service = new DepartmentServiceImpl();
		int i = 0;
		try {
			if (StringUtil.isBlank(deptId)) {
				// 添加
				i = service.addDept(dept);
			} else {
				// 修改
				dept.setId(Integer.parseInt(deptId));
				i = service.updateDept(dept);
			}
//			System.out.println(i);
			if (i > 0) {
				response.getWriter().print("{\"msg\":1}");
			} else {
				response.getWriter().print("{\"msg\":0}");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
