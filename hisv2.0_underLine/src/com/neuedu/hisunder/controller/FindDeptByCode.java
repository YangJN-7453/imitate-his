package com.neuedu.hisunder.controller;

import java.io.IOException;
import java.util.ArrayList;
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
import com.neuedu.hisunder.util.StringUtil;

/**
 * Servlet implementation class FindDeptByCode
 */
@WebServlet("/FindDeptByCode")
public class FindDeptByCode extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindDeptByCode() {
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
		List<Department> list = new ArrayList<Department>();
		String code = request.getParameter("code");
		if (! StringUtil.isBlank(code)) {
			DepartmentService service = new DepartmentServiceImpl();
			try {
				list = service.findDeptByCode(code);
				
				ObjectMapper om = new ObjectMapper();
				String json = om.writeValueAsString(list);
//				System.out.println(json);
				
				response.setContentType("application/json");
				response.getWriter().print(json);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
