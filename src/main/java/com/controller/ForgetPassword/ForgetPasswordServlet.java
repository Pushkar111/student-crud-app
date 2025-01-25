package com.controller.ForgetPassword;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class ForgetPasswordServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		request.getRequestDispatcher("forgetPassword.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String email = request.getParameter("email");

		// email Validation
		if (email == null || email.trim().isEmpty() || !email.matches("^[A-Za-z0-9+_.-]+@(.+)$"))
		{
			request.setAttribute("emailError", "Invalid email format");
			request.getRequestDispatcher("forgetPassword.jsp").forward(request, response);
			return;
		}
	
		// Generate and Send OTP to email
		String otp = OtpSendByEmail.generateOTP(6);
		OtpSendByEmail.sendMail(email, otp);

		// Save OTP in session
		HttpSession session = request.getSession();
		session.setAttribute("otp", otp);
		session.setAttribute("email", email);

		request.setAttribute("message", "A reset password OTP has been sent to your email");
		request.getRequestDispatcher("verify-otp.jsp").forward(request, response);
	}


}
