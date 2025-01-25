package com.controller.ForgetPassword;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class VerifyOtpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String otp = request.getParameter("otp");
		HttpSession session = request.getSession();
		String sessionOtp = (String) session.getAttribute("otp");

		if (otp == null || !otp.equals(sessionOtp)) {
			request.setAttribute("otpError", "Invalid OTP");
			request.getRequestDispatcher("verify-otp.jsp").forward(request, response);
			return;
		}

		// OTP is correct, forward to reset password page
		request.getRequestDispatcher("reset-password.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
