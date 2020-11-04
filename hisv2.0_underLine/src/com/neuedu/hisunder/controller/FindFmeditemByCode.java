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
import com.neuedu.hisunder.entity.Fmeditem;
import com.neuedu.hisunder.service.FmeditemService;
import com.neuedu.hisunder.service.impl.FmeditemServiceImpl;
import com.neuedu.hisunder.util.StringUtil;

/**
 * Servlet implementation class FindFmeditemByCode
 */
@WebServlet("/FindFmeditemByCode")
public class FindFmeditemByCode extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindFmeditemByCode() {
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
		List<Fmeditem> list = new ArrayList<Fmeditem>();
		String code = request.getParameter("mnemonicCode");
		if (! StringUtil.isBlank(code)) {
			FmeditemService service = new FmeditemServiceImpl();
			try {
				list = service.findFmeditemByCode(code);
				
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
