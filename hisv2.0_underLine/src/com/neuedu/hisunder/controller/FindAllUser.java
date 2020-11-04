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
import com.neuedu.hisunder.util.PageBean;
import com.neuedu.hisunder.util.StringUtil;

/**
 * Servlet implementation class findAllUser
 */
@WebServlet("/FindAllUser")
public class FindAllUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindAllUser() {
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
		// 定义分页对象
		PageBean<User> pageBean = new PageBean<User>();
		
		// 从request中获取当前页
		String curpageStr = request.getParameter("currentPage");
		int currentPage = StringUtil.isBlank(curpageStr)? 1 : Integer.parseInt(curpageStr);
		pageBean.setCurrentPage(currentPage);
		// 定义每页记录数
		pageBean.setPageSize(10);
		
		// 定义业务层，获取总记录条数
		UserService service = new UserServiceImpl();
		try {
			int totalCount = service.getTotalCount();
			
			List<User> list = service.findAll(pageBean.getBeginIndex(), pageBean.getPageSize());
			
			// 将获取到的数据存放到pageBean中
			pageBean.setTotalCount(totalCount);
			pageBean.setPageData(list);
			
			// pageBean转成json对象
			ObjectMapper om = new ObjectMapper();
			String json = om.writeValueAsString(pageBean);
			
//			System.out.println(json);
			response.setContentType("application/json");
			response.getWriter().print(json);
//			System.out.println("servlet success");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
