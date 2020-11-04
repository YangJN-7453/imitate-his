package com.neuedu.hisunder.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.neuedu.hisunder.entity.ConstantType;
import com.neuedu.hisunder.service.ConstantTypeService;
import com.neuedu.hisunder.service.impl.ConstantTypeServiceImpl;

/**
 * Servlet implementation class FindConstantTypeByCode
 */
@WebServlet("/FindConstantTypeByCode")
public class FindConstantTypeByCode extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindConstantTypeByCode() {
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
		String constantTypeCode = request.getParameter("constantTypeCode");
//		System.out.println(constantTypeCode);
		
//		if (! StringUtil.isBlank(constantTypeCode)) {
			// 定义业务层对象，并获取总记录条数
			ConstantTypeService service = new ConstantTypeServiceImpl();

			try {
				List<ConstantType> list = service.findConstantTypeByCode(constantTypeCode);
				
				// 将结果转换成json对象
				ObjectMapper omMapper = new ObjectMapper();
				String json = omMapper.writeValueAsString(list);
				
				response.setContentType("application/json");
				response.getWriter().print(json);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
//		}
		
	}

}
