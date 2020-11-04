package com.neuedu.hisunder.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.neuedu.hisunder.entity.Rule;
import com.neuedu.hisunder.service.RuleService;
import com.neuedu.hisunder.service.impl.RuleServiceImpl;
import com.neuedu.hisunder.util.PageBean;
import com.neuedu.hisunder.util.StringUtil;

/**
 * Servlet implementation class FindAllRule
 */
@WebServlet("/FindAllRule")
public class FindAllRule extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindAllRule() {
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
		PageBean<Rule> pageBean = new PageBean<Rule>();
		
		String currpageStr = request.getParameter("currentPage");
		int currentPage = StringUtil.isBlank(currpageStr)?1:Integer.parseInt(currpageStr);
		pageBean.setCurrentPage(currentPage);
		pageBean.setPageSize(10);
		
		RuleService service = new RuleServiceImpl();
		
		try {
			int totalCount = service.getTotalCount();
			
			List<Rule> list = service.findAllRule(pageBean.getBeginIndex(), pageBean.getPageSize());
			
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
