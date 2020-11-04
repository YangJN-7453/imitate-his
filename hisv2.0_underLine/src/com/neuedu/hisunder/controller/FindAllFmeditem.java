package com.neuedu.hisunder.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.neuedu.hisunder.entity.Fmeditem;
import com.neuedu.hisunder.service.FmeditemService;
import com.neuedu.hisunder.service.impl.FmeditemServiceImpl;
import com.neuedu.hisunder.util.PageBean;
import com.neuedu.hisunder.util.StringUtil;

/**
 * Servlet implementation class FindAllFmeditem
 */
@WebServlet("/FindAllFmeditem")
public class FindAllFmeditem extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindAllFmeditem() {
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
		PageBean<Fmeditem> pageBean = new PageBean<Fmeditem>();
		
		String currentpageStr = request.getParameter("currentPage");
		int currentPage = StringUtil.isBlank(currentpageStr)?1:Integer.parseInt(currentpageStr);
		pageBean.setCurrentPage(currentPage);
		pageBean.setPageSize(10);
		
		FmeditemService service = new FmeditemServiceImpl();
		
		try {
			int totalCount = service.getTotalCount();
			
			List<Fmeditem> list = service.findAllFmeditem(pageBean.getBeginIndex(), pageBean.getPageSize());
			
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
