package com.neuedu.hisunder.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neuedu.hisunder.service.DiseaseService;
import com.neuedu.hisunder.service.impl.DiseaseServiceImpl;
import com.neuedu.hisunder.util.StringUtil;

/**
 * Servlet implementation class DeleteDiseaseById
 */
@WebServlet("/DeleteDiseaseById")
public class DeleteDiseaseById extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteDiseaseById() {
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
		
		String disId = request.getParameter("diseaseId");
		if (! StringUtil.isBlank(disId)) {
			DiseaseService service = new DiseaseServiceImpl();
			
			try {
				int i = service.deleteDisease(Integer.parseInt(disId));
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
