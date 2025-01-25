package com.controller;

import java.io.IOException;


import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class SessionFilter implements Filter
{
	@Override
	public void init(FilterConfig filterConfig) throws ServletException
	{
		// Initialization code, if needed
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
	        throws IOException, ServletException
	{
       HttpServletRequest httpRequest = (HttpServletRequest) request;
       HttpServletResponse httpResponse = (HttpServletResponse) response;
       HttpSession session = httpRequest.getSession(false);

       if (session == null || session.getAttribute("ubean") == null) {
           httpResponse.sendRedirect(httpRequest.getContextPath() + "/login.jsp");
       } else {
           chain.doFilter(request, response);
       }
	}

	@Override
	public void destroy()
	{
		// Cleanup code, if needed
	}
}
