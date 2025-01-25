package com.controller;

import java.io.IOException;

import com.bean.StudentBean;
import com.dao.StudentDao;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DeleteStudentServlet extends HttpServlet
{

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		int rno = Integer.parseInt(request.getParameter("rno"));

		StudentDao dao = new StudentDao();
		
		int rowsAffected = dao.deleteStudent(rno); 
		
		RequestDispatcher rd = null;

		if (rowsAffected > 0)
		{
			rd = request.getRequestDispatcher("listStudentServlet");
		} else
		{
			rd = request.getRequestDispatcher("registration.jsp");
		}
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request, response);
	}

}
