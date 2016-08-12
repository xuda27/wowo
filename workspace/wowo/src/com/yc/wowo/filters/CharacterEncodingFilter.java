package com.yc.wowo.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 编码集过滤器
 * @author xxx
 *
 */
public class CharacterEncodingFilter implements Filter {
	private String encoding = "utf-8";
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		if(filterConfig.getInitParameter("encoding") != null){
			encoding = filterConfig.getInitParameter("encoding");
		}
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		req.setCharacterEncoding(encoding);
		res.setCharacterEncoding(encoding);
		
		chain.doFilter(req, res);
	}

	@Override
	public void destroy() {
		
	}

}
