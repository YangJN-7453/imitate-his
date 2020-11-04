package com.neuedu.hisunder.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.neuedu.hisunder.entity.Disease;
import com.neuedu.hisunder.service.DiseaseService;
import com.neuedu.hisunder.service.impl.DiseaseServiceImpl;
import com.neuedu.hisunder.util.PageBean;
import com.neuedu.hisunder.util.StringUtil;

/**
 * Servlet implementation class FindAllDisease
 */
@WebServlet("/FindAllDisease")
public class FindAllDisease extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindAllDisease() {
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
		PageBean<Disease> pageBean = new PageBean<Disease>();
		
		String currentPageStr = request.getParameter("currentPage");
		int currentPage = StringUtil.isBlank(currentPageStr)? 1 : Integer.parseInt(currentPageStr);
		pageBean.setCurrentPage(currentPage);
		pageBean.setPageSize(10);
		
		DiseaseService service = new DiseaseServiceImpl();
		
		try {
			int totalCount = service.getTotalCount();
			
			List<Disease> list = service.findAllDisease(pageBean.getBeginIndex(), pageBean.getPageSize());
			
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
		}
	}

}
