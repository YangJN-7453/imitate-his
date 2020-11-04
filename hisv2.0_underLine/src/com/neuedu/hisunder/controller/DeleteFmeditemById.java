package com.neuedu.hisunder.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neuedu.hisunder.service.FmeditemService;
import com.neuedu.hisunder.service.impl.FmeditemServiceImpl;
import com.neuedu.hisunder.util.StringUtil;

/**
 * Servlet implementation class DeleteFmeditemById
 */
@WebServlet("/DeleteFmeditemById")
public class DeleteFmeditemById extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteFmeditemById() {
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
		String fmeditemId = request.getParameter("fmeditemId");
		response.setContentType("application/json");
		if (! StringUtil.isBlank(fmeditemId)) {
			FmeditemService service = new FmeditemServiceImpl();
			try {
				int i = service.deleteFmeditem(Integer.parseInt(fmeditemId));
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
