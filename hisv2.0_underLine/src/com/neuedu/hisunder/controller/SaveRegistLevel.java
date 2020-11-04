package com.neuedu.hisunder.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neuedu.hisunder.entity.RegistLevel;
import com.neuedu.hisunder.service.RegistLevelService;
import com.neuedu.hisunder.service.impl.RegistLevelServiceImpl;
import com.neuedu.hisunder.util.StringUtil;

/**
 * Servlet implementation class SaveRegistLevel
 */
@WebServlet("/SaveRegistLevel")
public class SaveRegistLevel extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaveRegistLevel() {
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
		String regId = request.getParameter("regId");
		String regCode = request.getParameter("regCode");
		String regName = request.getParameter("regName");
		String sequenceNo = request.getParameter("sequenceNo");
		String regFee = request.getParameter("regFee");
		String regQuota = request.getParameter("regQuota");

		RegistLevel r = new RegistLevel();
		
		r.setRegistCode(regCode);
		r.setRegistName(regName);
		r.setSequenceNo(Integer.parseInt(sequenceNo));
		r.setRegistFee(Double.parseDouble(regFee));
		r.setRegistQuota(Integer.parseInt(regQuota));
		
		RegistLevelService service = new RegistLevelServiceImpl();
		int i = 0;
		try {
			if (StringUtil.isBlank(regId)) {
				// 添加
				i = service.addRegistLevel(r);
			} else {
				// 修改
				r.setId(Integer.parseInt(regId));
				i = service.updateRegistLevel(r);
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
