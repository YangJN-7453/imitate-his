package com.neuedu.hisunder.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.neuedu.hisunder.entity.Department;
import com.neuedu.hisunder.service.DepartmentService;
import com.neuedu.hisunder.service.impl.DepartmentServiceImpl;
import com.neuedu.hisunder.util.PageBean;
import com.neuedu.hisunder.util.StringUtil;

/**
 * Servlet implementation class FindAllDept
 */
@WebServlet("/FindAllDept")
public class FindAllDept extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindAllDept() {
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
		PageBean<Department> pageBean = new PageBean<Department>();
		
		String currpagrStr = request.getParameter("currentPage");
		int currentPage = StringUtil.isBlank(currpagrStr)? 1 : Integer.parseInt(currpagrStr);
		pageBean.setCurrentPage(currentPage);
		pageBean.setPageSize(10);
		
		DepartmentService service = new DepartmentServiceImpl();
		
		try {
			int totalCount = service.getTotalCount();
			
			List<Department> list = service.findAllDept(pageBean.getBeginIndex(), pageBean.getPageSize());
			
			pageBean.setTotalCount(totalCount);
			pageBean.setPageData(list);
			
			ObjectMapper om = new ObjectMapper();
			String json = om.writeValueAsString(pageBean);
//			System.out.println(json);
			
			response.setContentType("application/json");
			response.getWriter().print(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
