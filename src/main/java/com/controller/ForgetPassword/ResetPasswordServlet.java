package com.controller.ForgetPassword;

import java.io.IOException;

import com.dao.UserDao;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class ResetPasswordServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException
	{
		request.getRequestDispatcher("reset-password.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException
	{
		String newPassword = request.getParameter("newPassword");
		String confirmPassword = request.getParameter("confirmPassword");

		// Validate passwords
		if (newPassword == null || newPassword.trim().isEmpty() || newPassword.length() < 8 || !newPassword.matches(".*[A-Z].*") || !newPassword.matches(".*[a-z].*") || !newPassword.matches(".*\\d.*"))
		{
			request.setAttribute("passwordError", "Password must be at least 8 characters long, contain at least one uppercase letter, one lowercase letter, and one digit.");
			request.getRequestDispatcher("reset-password.jsp").forward(request, response);
			return;
		}

		if (!newPassword.equals(confirmPassword))
		{
			request.setAttribute("confirmPasswordError", "Passwords do not match.");
			request.getRequestDispatcher("reset-password.jsp").forward(request, response);
			return;
		}

		// Update the password in the database (implement database update logic)
		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("email");

		UserDao dao = new UserDao();
		int rowsAffected = dao.updatePassword(email, newPassword);

		if (rowsAffected > 0)
		{
			request.setAttribute("message", "Your password has been reset successfully.");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		} else
		{
			request.setAttribute("message", "Password reset failed");
			request.getRequestDispatcher("reset-password.jsp").forward(request, response);
		}
	}
}
