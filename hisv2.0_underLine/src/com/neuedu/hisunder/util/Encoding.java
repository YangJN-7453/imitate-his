package com.neuedu.hisunder.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class Encoding
 */
@WebFilter("/*")
public class Encoding implements Filter {

    /**
     * Default constructor. 
     */
    public Encoding() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		HttpServletResponse response1 = (HttpServletResponse) response;  
//		response.setContentType("text/html;charset=utf-8");
		
        /* 允许跨域的主机地址 */ 
        response1.setHeader("Access-Control-Allow-Origin", "*");
        /* 允许跨域的请求方法GET, POST, HEAD 等 */
        response1.setHeader("Access-Control-Allow-Methods", "*");
        /* 重新预检验跨域的缓存时间 (s) */
        response1.setHeader("Access-Control-Max-Age", "3600");  
        /* 允许跨域的请求头 */
        response1.setHeader("Access-Control-Allow-Headers", "*");  
        /* 是否携带cookie */
        response1.setHeader("Access-Control-Allow-Credentials", "true");  
       
		
		chain.doFilter(request, response1);
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
