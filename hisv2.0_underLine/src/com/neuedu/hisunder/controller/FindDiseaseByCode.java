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
import com.neuedu.hisunder.entity.Disease;
import com.neuedu.hisunder.service.DiseaseService;
import com.neuedu.hisunder.service.impl.DiseaseServiceImpl;
import com.neuedu.hisunder.util.StringUtil;

/**
 * Servlet implementation class FindDiseaseByCode
 */
@WebServlet("/FindDiseaseByCode")
public class FindDiseaseByCode extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindDiseaseByCode() {
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
		List<Disease> list = new ArrayList<Disease>();
		String code = request.getParameter("code");
		if (! StringUtil.isBlank(code)) {
			DiseaseService service = new DiseaseServiceImpl();
			try {
				list = service.findDiseaseByCode(code);
				
				ObjectMapper om = new ObjectMapper();
				String json = om.writeValueAsString(list);
//				System.out.println(json);
				
				response.setContentType("application/json");
				response.getWriter().print(json);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}

}
