package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import com.bean.StudentBean;
import com.dao.StudentDao;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class EditStudentServlet extends HttpServlet
{

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		PrintWriter out = response.getWriter();
		
		int rno = Integer.parseInt(request.getParameter("rno"));
		StudentDao dao = new StudentDao();
		StudentBean sbean = dao.getStudentByRno(rno);

		if (sbean != null)
		{
			request.setAttribute("sbean", sbean);
			request.getRequestDispatcher("editstudent.jsp").forward(request, response);
		} else
		{
			out.println("<h3>Student not found for Roll Number: " + rno + "</h3>");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		doGet(request, response);
	}

}
