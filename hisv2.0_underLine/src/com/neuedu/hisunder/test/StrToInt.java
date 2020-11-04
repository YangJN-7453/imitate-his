package com.neuedu.hisunder.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.neuedu.hisunder.dao.FmeditemDao;
import com.neuedu.hisunder.dao.impl.FmeditemDaoImpl;
import com.neuedu.hisunder.entity.ExpenseClass;
import com.neuedu.hisunder.util.StringUtil;

public class StrToInt {

	@Test
	public void test() {
		String str = "60元/号";
		String str2 = "";
//		int i = Integer.parseInt(str);
//		System.out.println(i);
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) >= 48 && str.charAt(i) <=57) {
				str2 += str.charAt(i);
			}
		}
//		System.out.println(Integer.parseInt(str2));
		
		System.out.println(StringUtil.strToInt(str) + 50);
	}
	
	@Test
	public void testDate() {
		Date utildate = new Date();
		System.out.println(utildate.toString());
		java.sql.Date sqldate = new java.sql.Date(utildate.getTime());
		System.out.println(sqldate.toString());
		
	}
	
	@Test
	public void testFmeditemDao() throws Exception {
		FmeditemDao dao = FmeditemDaoImpl.getFmeditemDaoImpl();
		List<ExpenseClass> list = new ArrayList<ExpenseClass>();
		list = dao.findAllExpenseClass();
		ObjectMapper om = new ObjectMapper();
		System.out.println(om.writeValueAsString(list));
		
	}

}
