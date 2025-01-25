package com.controller;

import java.io.IOException;

import com.bean.UserBean;
import com.dao.UserDao;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Retrieve parameters
		String full_name = request.getParameter("full_name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		boolean hasError = false;

		// Validate name
		if (full_name == null || full_name.trim().isEmpty()) {
			request.setAttribute("full_nameError", "Full Name is required.");
			hasError = true;
		} else if (!full_name.matches("[a-zA-Z ]+")) {
			request.setAttribute("full_nameError", "Full Name must contain only alphabetic characters.");
			hasError = true;
		}

		// Validate email
		if (email == null || email.trim().isEmpty()) {
			request.setAttribute("emailError", "Email is required.");
			hasError = true;
		} else if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
			request.setAttribute("emailError", "Invalid email format");
			hasError = true;
		}

		// Validate password
		if (password == null || password.trim().isEmpty()) {
			request.setAttribute("passwordError", "Password is required.");
			hasError = true;
		} else if (password.length() < 8) {
			request.setAttribute("passwordError", "Password must be at least 8 characters long");
			hasError = true;
		} else if (!password.matches(".*[A-Z].*")) {
			request.setAttribute("passwordError", "Password must contain at least one uppercase letter.");
			hasError = true;
		} else if (!password.matches(".*[a-z].*")) {
			request.setAttribute("passwordError", "Password must contain at least one lowercase letter.");
			hasError = true;
		} else if (!password.matches(".*\\d.*")) {
			request.setAttribute("passwordError", "Password must contain at least one digit.");
			hasError = true;
		} else if (!password.matches(".*[!@#$%^&*].*")) {
			request.setAttribute("passwordError", "Password must contain at least one special character (!@#$%^&*).");
			hasError = true;
		}

		// Check if email is already registered
		UserDao dao = new UserDao();
		if (dao.isEmailRegistered(email)) {
			request.setAttribute("emailError", "Email is already registered.");
			request.setAttribute("full_name", full_name);
			request.setAttribute("email", email);
			request.setAttribute("password", password);
			request.getRequestDispatcher("signup.jsp").forward(request, response);
			return;
		}

		if (hasError) {
			// Retain previously entered data
			request.setAttribute("full_name", full_name);
			request.setAttribute("email", email);
			request.setAttribute("password", password);

			request.getRequestDispatcher("signup.jsp").forward(request, response);
			return;
		} else {
			// Create a UserBean object
			UserBean ubean = new UserBean(0, full_name, email, password);

			// Insert user using DAO
			int rowsAffected = 0;
			rowsAffected = dao.insertUser(ubean);

			// Get the session object
			HttpSession session = request.getSession();

			// Redirect based on the result
			if (rowsAffected > 0) {
				session.setAttribute("successMsg", "User Register Successfully");
				response.sendRedirect("signup.jsp");
			} else {
				session.setAttribute("errorMsg", "Something went wrong on the server");
				response.sendRedirect("signup.jsp");
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
