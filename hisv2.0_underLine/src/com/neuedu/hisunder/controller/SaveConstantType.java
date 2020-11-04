package com.neuedu.hisunder.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neuedu.hisunder.entity.ConstantType;
import com.neuedu.hisunder.service.ConstantTypeService;
import com.neuedu.hisunder.service.impl.ConstantTypeServiceImpl;
import com.neuedu.hisunder.util.StringUtil;

/**
 * Servlet implementation class SaveConstantType
 */
@WebServlet("/SaveConstantType")
public class SaveConstantType extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaveConstantType() {
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
		String vid = request.getParameter("id");
		String code = request.getParameter("code");
		String name = request.getParameter("name");
		
		ConstantType constantType = new ConstantType();
		constantType.setConstantTypeCode(code);
		constantType.setConstantTypeName(name);
		
		ConstantTypeService service = new ConstantTypeServiceImpl();
		int i = 0;
		try {
			if (StringUtil.isBlank(vid)) {
				// 调用添加业务
				i = service.add(constantType);
			} else {
				// 修改添加业务
				constantType.setId(Integer.parseInt(vid));
				i = service.update(constantType);
			}
			
			if (i > 0) {
				response.getWriter().print("{\"msg\":1}");
			} else {
				response.getWriter().print("{\"msg\":0}");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
