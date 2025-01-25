package com.controller;

import java.io.IOException;

import com.bean.UserBean;
import com.dao.UserDao;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		boolean hasError = false;

		// Validate input parameters
		if (email == null || email.trim().isEmpty())
		{
			request.setAttribute("emailError", "Email is required.");
			hasError = true;
		} else if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$"))
		{
			request.setAttribute("emailError", "Invalid email format");
			hasError = true;
		}

		if (password == null || password.trim().isEmpty())
		{
			request.setAttribute("passwordError", "Password is required.");
			hasError = true;
		} else if (password.length() < 8)
		{
			request.setAttribute("passwordError", "Password must be at least 8 characters long");
			hasError = true;
		} else if (!password.matches(".*[A-Z].*"))
		{
			request.setAttribute("passwordError", "Password must contain at least one uppercase letter.");
			hasError = true;
		} else if (!password.matches(".*[a-z].*"))
		{
			request.setAttribute("passwordError", "Password must contain at least one lowercase letter.");
			hasError = true;
		} else if (!password.matches(".*\\d.*"))
		{
			request.setAttribute("passwordError", "Password must contain at least one digit.");
			hasError = true;
		}

		if (hasError)
		{
			request.setAttribute("email", email);
			request.setAttribute("password", password);

			request.getRequestDispatcher("login.jsp").forward(request, response);
		} else
		{
			// Check user login details with users table
			UserBean userBean = new UserBean();
			
			userBean.setEmail(email);
			userBean.setPassword(password);

			UserDao dao = new UserDao();

			UserBean ubean = dao.checkUser(userBean);
			if (ubean != null)
			{
				HttpSession session = request.getSession();
				session.setAttribute("ubean", ubean);
				response.sendRedirect("registration.jsp");
			} else
			{
				request.setAttribute("loginError", "Invalid email or password.");
				request.getRequestDispatcher("login.jsp").forward(request, response);	
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request, response);
	}

}
