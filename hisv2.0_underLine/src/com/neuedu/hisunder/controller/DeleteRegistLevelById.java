package com.neuedu.hisunder.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neuedu.hisunder.service.RegistLevelService;
import com.neuedu.hisunder.service.impl.RegistLevelServiceImpl;
import com.neuedu.hisunder.util.StringUtil;

/**
 * Servlet implementation class DeleteRegistLevelById
 */
@WebServlet("/DeleteRegistLevelById")
public class DeleteRegistLevelById extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteRegistLevelById() {
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
		String regId = request.getParameter("registLevelId");
//		System.out.println(regId);
		response.setContentType("application/json");
		if (! StringUtil.isBlank(regId)) {
			RegistLevelService service = new RegistLevelServiceImpl();
			try {
				int i = service.deleteRegistLevel(Integer.parseInt(regId));
				if (i > 0) {
					response.getWriter().print("{\"msg\" : 1}");
				} else {
					response.getWriter().print("{\"msg\" : 2}");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			response.getWriter().print("{\"msg\" : 0}");
		}
	}

}
