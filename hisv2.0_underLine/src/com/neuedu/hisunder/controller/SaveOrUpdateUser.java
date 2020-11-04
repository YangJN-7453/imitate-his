package com.neuedu.hisunder.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neuedu.hisunder.entity.User;
import com.neuedu.hisunder.service.UserService;
import com.neuedu.hisunder.service.impl.UserServiceImpl;
import com.neuedu.hisunder.util.StringUtil;

/**
 * Servlet implementation class SaveOrUpdateUser
 */
@WebServlet("/SaveOrUpdateUser")
public class SaveOrUpdateUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaveOrUpdateUser() {
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
//		System.out.println("stop1");
		// 接受请求数据
		String userId = request.getParameter("userId");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String realname = request.getParameter("realname");
		String userType = request.getParameter("userType");
		String isScheduling = request.getParameter("isScheduling");
		String docTitleID = request.getParameter("docTitleID");
		String deptId = request.getParameter("deptId");
		String registLeID = request.getParameter("registLeID");
		
		User u = new User();
		u.setUserName(username);
		u.setPassword(password);
		u.setRealName(realname);
		u.setUserType(Integer.parseInt(userType));
		u.setIsScheduling(isScheduling);
		u.setDocTitleID(Integer.parseInt(docTitleID));
		u.setDeptID(Integer.parseInt(deptId));
		u.setRegistLeID(Integer.parseInt(registLeID));
		
		UserService service = new UserServiceImpl();
		int i = 0;
		try {
			if (StringUtil.isBlank(userId)) {
				// 添加
				i = service.addUser(u);
			} else {
				// 修改
				u.setId(Integer.parseInt(userId));
				i = service.update(u);
			}
			
			if (i > 0) {
				response.getWriter().print("{\"msg\":1}");
			} else {
				response.getWriter().print("{\"msg\":0}");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
//		System.out.println("Save Or Update User Ended");
	}

}
