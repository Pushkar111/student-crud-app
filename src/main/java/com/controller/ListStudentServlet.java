package com.controller;

import java.io.IOException;
import java.util.ArrayList;

import com.bean.StudentBean;
import com.dao.StudentDao;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ListStudentServlet extends HttpServlet
{

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		response.setContentType("text/html");

		StudentDao dao = new StudentDao();

		ArrayList<StudentBean> list = dao.getAllStudents();

		request.setAttribute("list", list);

		request.getRequestDispatcher("liststudent.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request, response);
	}

}
