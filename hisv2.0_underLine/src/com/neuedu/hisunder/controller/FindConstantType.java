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
 * Servlet implementation class FindConstantType
 */
@WebServlet("/FindConstantType")
public class FindConstantType extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindConstantType() {
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
		// 定义业务层对象，并获取总记录条数
		ConstantTypeService service = new ConstantTypeServiceImpl();
		// 获取数据
		try {
			// 获取数据
			List<ConstantType> list = service.findAll();
			
			// 将结果转换成json对象
			ObjectMapper omMapper = new ObjectMapper();
			String json = omMapper.writeValueAsString(list);
			
			//System.out.println(json);
			//将响应类型设置为json
			response.setContentType("application/json");
			response.getWriter().print(json);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
