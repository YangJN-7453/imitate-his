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
import com.neuedu.hisunder.entity.RegistLevel;
import com.neuedu.hisunder.service.RegistLevelService;
import com.neuedu.hisunder.service.impl.RegistLevelServiceImpl;
import com.neuedu.hisunder.util.StringUtil;

/**
 * Servlet implementation class FindRegistLevelByCode
 */
@WebServlet("/FindRegistLevelByCode")
public class FindRegistLevelByCode extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindRegistLevelByCode() {
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
		List<RegistLevel> list = new ArrayList<RegistLevel>();
		String code = request.getParameter("regCode");
//		System.out.println(code);
		if (! StringUtil.isBlank(code)) {
			RegistLevelService service = new RegistLevelServiceImpl();
			try {
				list = service.findRegistLevelByCode(code);
				
				ObjectMapper om = new ObjectMapper();
				String json = om.writeValueAsString(list);
//				System.out.println("json" + json);
				
				response.setContentType("application/json");
				response.getWriter().print(json);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
