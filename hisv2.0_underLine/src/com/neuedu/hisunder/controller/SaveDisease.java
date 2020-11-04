package com.neuedu.hisunder.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neuedu.hisunder.entity.Disease;
import com.neuedu.hisunder.service.DiseaseService;
import com.neuedu.hisunder.service.impl.DiseaseServiceImpl;
import com.neuedu.hisunder.util.StringUtil;

/**
 * Servlet implementation class SaveDisease
 */
@WebServlet("/SaveDisease")
public class SaveDisease extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaveDisease() {
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
		
		// 接受请求数据
		String disId = request.getParameter("disId");
		String disCode = request.getParameter("disCode");
		String disName = request.getParameter("disName");
		String disICD = request.getParameter("disICD");
		String diseCategoryId = request.getParameter("diseCategoryId");
		
		Disease disease = new Disease();
		disease.setDiseaseCode(disCode);
		disease.setDiseaseName(disName);
		disease.setDiseaseICD(disICD);
		disease.setDiseCategoryID(Integer.parseInt(diseCategoryId));
		
		DiseaseService service = new DiseaseServiceImpl();
		int i = 0;
		try {
			if (StringUtil.isBlank(disId)) {
				// 添加
				i = service.addDisease(disease);
			} else {
				// 修改
				disease.setId(Integer.parseInt(disId));
				i = service.updateDisease(disease);
			}
			
			if (i > 0) {
				response.getWriter().print("{\"msg\":1}");
			} else {
				response.getWriter().print("{\"msg\":0}");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
