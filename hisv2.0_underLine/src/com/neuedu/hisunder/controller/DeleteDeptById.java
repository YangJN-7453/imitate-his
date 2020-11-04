package com.neuedu.hisunder.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neuedu.hisunder.service.DepartmentService;
import com.neuedu.hisunder.service.impl.DepartmentServiceImpl;
import com.neuedu.hisunder.util.StringUtil;

/**
 * Servlet implementation class DeleteDeptById
 */
@WebServlet("/DeleteDeptById")
public class DeleteDeptById extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteDeptById() {
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
		String deptID = request.getParameter("deptid");
//		System.out.println(deptID);
		response.setContentType("application/json");
		if (! StringUtil.isBlank(deptID)) {
			DepartmentService service = new DepartmentServiceImpl();
			try {
				int i = service.deleteDept(Integer.parseInt(deptID));
//				System.out.println(i);
				if (i > 0) {
					response.getWriter().print("{\"msg\" : 1}");
				} else {
					response.getWriter().print("{\"msg\" : 2}");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			response.getWriter().print("{\"msg\" : 0}");
		}
	}

}
