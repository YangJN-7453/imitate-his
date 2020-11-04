package com.neuedu.hisunder.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neuedu.hisunder.entity.Rule;
import com.neuedu.hisunder.service.RuleService;
import com.neuedu.hisunder.service.impl.RuleServiceImpl;

/**
 * Servlet implementation class SaveRule
 */
@WebServlet("/SaveRule")
public class SaveRule extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaveRule() {
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
		
		String ruleName = request.getParameter("ruleName");
		String uid = request.getParameter("uid");
		String deptId = request.getParameter("deptid");
		String week = request.getParameter("week");
		System.out.println(ruleName + "--" + uid + "-" + deptId + "-" + week);
		
		Rule r = new Rule();
		r.setRuleName(ruleName);
		r.setUserID(Integer.parseInt(uid));
		r.setDeptID(Integer.parseInt(deptId));
		r.setWeek(week);
		
		RuleService service = new RuleServiceImpl();
		int i = 0;
		try {
			i = service.addRule(r);
			
			if (i > 0) {
				response.getWriter().print("{\"msg\":1}");
			} else {
				response.getWriter().print("{\"msg\":0}");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
