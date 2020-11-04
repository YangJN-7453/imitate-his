package com.neuedu.hisunder.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.neuedu.hisunder.entity.RegistLevel;
import com.neuedu.hisunder.service.RegistLevelService;
import com.neuedu.hisunder.service.impl.RegistLevelServiceImpl;
import com.neuedu.hisunder.util.PageBean;
import com.neuedu.hisunder.util.StringUtil;

/**
 * Servlet implementation class FindAllRegistLevel
 */
@WebServlet("/FindAllRegistLevel")
public class FindAllRegistLevel extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindAllRegistLevel() {
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
		// 分页对象
		PageBean<RegistLevel> pageBean = new PageBean<RegistLevel>();
		
		String currStr = request.getParameter("currentPage");
		int currentPage = StringUtil.isBlank(currStr)? 1 : Integer.parseInt(currStr);
		pageBean.setCurrentPage(currentPage);
		pageBean.setPageSize(10);
		
		RegistLevelService service = new RegistLevelServiceImpl();
		
		try {
			int totalCount = service.getTotalCount();
			
			List<RegistLevel> list = service.findAllRegistLevel(pageBean.getBeginIndex(), pageBean.getPageSize());
			
			pageBean.setTotalCount(totalCount);
			pageBean.setPageData(list);
			
			ObjectMapper om = new ObjectMapper();
			String json = om.writeValueAsString(pageBean);
//			System.out.println(json);
			
			response.setContentType("application/json");
			response.getWriter().print(json);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
	}

}
