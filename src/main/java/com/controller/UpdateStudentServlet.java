package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import com.bean.StudentBean;
import com.dao.StudentDao;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class UpdateStudentServlet extends HttpServlet
{
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		int rno = Integer.parseInt(request.getParameter("rno"));
		String name = request.getParameter("name");
		int std = Integer.parseInt(request.getParameter("std"));
		int marks = Integer.parseInt(request.getParameter("marks"));

		StudentBean sbean = new StudentBean(rno, name, std, marks);

		StudentDao dao = new StudentDao();
		int rowsAffected = dao.updateStudent(sbean);

		if (rowsAffected > 0)
		{
			request.getRequestDispatcher("listStudentServlet").forward(request, response);
		} else
		{
			response.getWriter().println("<h3>Update failed for Roll Number: " + rno + "</h3>");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request, response);
	}
}
