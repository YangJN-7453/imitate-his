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
import com.neuedu.hisunder.entity.ConstantItem;
import com.neuedu.hisunder.service.ConstantTypeService;
import com.neuedu.hisunder.service.impl.ConstantTypeServiceImpl;
import com.neuedu.hisunder.util.StringUtil;

/**
 * Servlet implementation class FindConstantItemByTypeCode
 */
@WebServlet("/FindConstantItemByTypeCode")
public class FindConstantItemByTypeCode extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindConstantItemByTypeCode() {
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
		List<ConstantItem> list = new ArrayList<ConstantItem>();
		String code = request.getParameter("code");
		if (! StringUtil.isBlank(code)) {
			ConstantTypeService service = new ConstantTypeServiceImpl();
			try {
				list = service.findConstantItem(code);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		response.setContentType("application/json");
		ObjectMapper om = new ObjectMapper();
		response.getWriter().print(om.writeValueAsString(list));
	}

}
