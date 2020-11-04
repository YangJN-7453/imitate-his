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
import com.neuedu.hisunder.entity.User;
import com.neuedu.hisunder.service.RegistLevelService;
import com.neuedu.hisunder.service.impl.RegistLevelServiceImpl;
import com.neuedu.hisunder.util.StringUtil;

/**
 * Servlet implementation class FindUserByRegistLevelId
 */
@WebServlet("/FindUserByRegistLevelId")
public class FindUserByRegistLevelId extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindUserByRegistLevelId() {
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
		String regIdStr = request.getParameter("registLeId");
		RegistLevelService service = new RegistLevelServiceImpl();
		List<User> list = new ArrayList<User>();
		if (! StringUtil.isBlank(regIdStr)) {
			int registId = Integer.parseInt(regIdStr);
			try {
				list = service.findUserByRegistLevelId(registId);
				
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
