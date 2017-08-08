package cn.maoaixi.utils;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JSPFilter implements Filter {

	@Override
	public void doFilter(ServletRequest _request, ServletResponse _response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) _request;
		StringBuffer url = request.getRequestURL();
		if (url.toString().endsWith(".jsp")) {
			HttpServletResponse response = (HttpServletResponse) _response;
			response.sendRedirect(request.getContextPath() + "/admin/jsp/adminlogin.jsp");
			return;
		}else{
			chain.doFilter(_request, _response);
		}
	}

}
