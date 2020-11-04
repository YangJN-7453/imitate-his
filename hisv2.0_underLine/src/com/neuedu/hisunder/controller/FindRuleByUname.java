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
import com.neuedu.hisunder.entity.Rule;
import com.neuedu.hisunder.service.RuleService;
import com.neuedu.hisunder.service.impl.RuleServiceImpl;
import com.neuedu.hisunder.util.StringUtil;

/**
 * Servlet implementation class FindRuleByUname
 */
@WebServlet("/FindRuleByUname")
public class FindRuleByUname extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindRuleByUname() {
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
		List<Rule> list = new ArrayList<Rule>();
		String uname = request.getParameter("uname");
		if (! StringUtil.isBlank(uname)) {
			RuleService service = new RuleServiceImpl();
			try {
				list = service.findRuleByUname(uname);
				
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
