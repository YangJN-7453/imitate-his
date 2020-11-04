package com.neuedu.hisunder.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.neuedu.hisunder.entity.User;
import com.neuedu.hisunder.service.UserService;
import com.neuedu.hisunder.service.impl.UserServiceImpl;
import com.neuedu.hisunder.util.StringUtil;

/**
 * Servlet implementation class FindUserByDeptAndReg
 */
@WebServlet("/FindUserByDeptAndReg")
public class FindUserByDeptAndReg extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindUserByDeptAndReg() {
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
		String deptIdStr = request.getParameter("deptid");
		String regIdStr = request.getParameter("regid");
		int deptId = StringUtil.isBlank(deptIdStr)? 0 : Integer.parseInt(deptIdStr);
		int regId = StringUtil.isBlank(regIdStr)? 0 : Integer.parseInt(regIdStr);
		
		UserService service = new UserServiceImpl();
		try {
			List<User> list = service.findUnameByDeptAndReg(deptId, regId);
			
			ObjectMapper om = new ObjectMapper();
			String json = om.writeValueAsString(list);
//			System.out.println(json);
			
			response.setContentType("application/json");
			response.getWriter().print(json);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
