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

public class InsertStudentServlet extends HttpServlet
{

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException
	{
		PrintWriter out = response.getWriter();

		String name = request.getParameter("name");
		// int std = Integer.parseInt(request.getParameter("std"));
		String stdStr = request.getParameter("std");
		// int marks = Integer.parseInt(request.getParameter("marks"));
		String marksStr = request.getParameter("marks");

		RequestDispatcher rd = null;

		boolean hasError = false;

		// Validate input parameters
		if (name == null || name.trim().isEmpty())
		{
			request.setAttribute("nameError", "Name is required.");
			hasError = true;
		} else if (!name.matches("[a-zA-Z ]+"))
		{
			request.setAttribute("nameError", "Name must contain only alphabetic characters.");
			hasError = true;
		}

		if (stdStr == null || stdStr.trim().isEmpty())
		{
			request.setAttribute("stdError", "Standard is required.");
			hasError = true;
		}

		if (marksStr == null || marksStr.trim().isEmpty())
		{
			request.setAttribute("marksError", "Marks are required.");
			hasError = true;
		}

		int std = 0;
		int marks = 0;

		if (!hasError)
		{
			try
			{
				std = Integer.parseInt(stdStr);
				marks = Integer.parseInt(marksStr);
			} catch (NumberFormatException e)
			{
				request.setAttribute("stdError", "Standard must be a valid number.");
				request.setAttribute("marksError", "Marks must be a valid number.");
				hasError = true;
			}
		}

		// Additional validation for std and marks
		if (!hasError)
		{
			if (std <= 0 || std > 12)
			{
				request.setAttribute("stdError", "Standard must be a positive number between 1 and 12.");
				hasError = true;
			}

			if (marks < 0 || marks > 100)
			{
				request.setAttribute("marksError", "Marks must be between 0 and 100.");
				hasError = true;
			}
		}

		if (hasError)
		{
			// Retain previously entered data
			request.setAttribute("name", name);
			request.setAttribute("std", stdStr);
			request.setAttribute("marks", marksStr);

			rd = request.getRequestDispatcher("registration.jsp");
			rd.forward(request, response);
			return;
		}

		StudentBean sbean = new StudentBean(0, name, std, marks);

		StudentDao dao = new StudentDao();

		int rowsAffected = dao.insertStudent(sbean);

		if (rowsAffected > 0)
		{
			rd = request.getRequestDispatcher("listStudentServlet");

		} else
		{
			rd = request.getRequestDispatcher("registration.jsp");
			request.setAttribute("error", "Failed to insert student.");
		}
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException
	{
		doGet(request, response);
	}

}
