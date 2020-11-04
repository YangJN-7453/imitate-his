package com.neuedu.hisunder.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neuedu.hisunder.entity.Fmeditem;
import com.neuedu.hisunder.service.FmeditemService;
import com.neuedu.hisunder.service.impl.FmeditemServiceImpl;
import com.neuedu.hisunder.util.StringUtil;

/**
 * Servlet implementation class SaveFmeditem
 */
@WebServlet("/SaveFmeditem")
public class SaveFmeditem extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaveFmeditem() {
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
		
		String fmeditemId = request.getParameter("fmeditemId");
		String itemCode = request.getParameter("itemCode");
		String itemName = request.getParameter("itemName");
		String price = request.getParameter("price");
		String format = request.getParameter("format");
		String expClassId = request.getParameter("expClassId");
		String deptId = request.getParameter("expClassId");
		String mnemonicCode = request.getParameter("mnemonicCode");
		String recordType = request.getParameter("recordType");
		
		// 获取当前时间
		Date nowDate = new Date();
		
		Fmeditem fm = new Fmeditem();
		fm.setItemCode(itemCode);
		fm.setItemName(itemName);
		fm.setFormat(format);
		fm.setPrice(Double.parseDouble(price));
		fm.setExpClassID(Integer.parseInt(expClassId));
		fm.setDeptID(Integer.parseInt(deptId));
		fm.setMnemonicCode(mnemonicCode);
		fm.setLastUpdateDate(nowDate);
		fm.setRecordType(Integer.parseInt(recordType));
		
		FmeditemService service = new FmeditemServiceImpl();
		int i = 0;
		try {
			if (StringUtil.isBlank(fmeditemId)) {
				// 添加
				fm.setCreationDate(nowDate);
				i = service.addFmeditem(fm);
			} else {
				// 修改
				fm.setId(Integer.parseInt(fmeditemId));
				i = service.updateFmeditem(fm);
			}
//			System.out.println(i);
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
