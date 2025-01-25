<%@page import="jakarta.servlet.http.HttpSession"%>
<%@page import="com.bean.StudentBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="navbar.html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="UTF-8">
<title>Edit Student</title>
<!-- <link rel="stylesheet" href="css/styles.css"> -->
<link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
</head>
<body>

	<div class="bg-gray-100 flex items-center justify-center min-h-screen">
		<div class="bg-white shadow-lg rounded-lg p-8 max-w-md w-full">
			<div class="text-center mb-6">
				<h3 class="text-2xl font-bold">Edit Student Details</h3>
			</div>

			<%
			StudentBean sbean = (StudentBean) request.getAttribute("sbean");
			%>

			<form action="updateStudentServlet" method="post" class="space-y-4">
				<div>
					<label for="rno" class="block text-sm font-medium text-gray-700">Roll Number:</label> <input type="text" id="rno" name="rno" readonly
						value="<%=sbean.getRno()%>"
						class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm">
				</div>
				<div>
					<label for="name" class="block text-sm font-medium text-gray-700">Name:</label> <input type="text" id="name" name="name"
						value="<%=sbean.getName()%>" required
						class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm">
				</div>
				<div>
					<label for="std" class="block text-sm font-medium text-gray-700">Class (Standard):</label> <input type="text" id="std" name="std"
						value="<%=sbean.getStd()%>" required
						class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm">
				</div>
				<div>
					<label for="marks" class="block text-sm font-medium text-gray-700">Marks:</label> <input type="text" id="marks" name="marks"
						value="<%=sbean.getMarks()%>" required
						class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm">
				</div>
				<div class="text-center">
					<button type="submit"
						class="w-full inline-flex justify-center py-2 px-4 border border-transparent shadow-sm text-sm font-medium rounded-md text-white bg-indigo-600 hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500">
						Update</button>
				</div>
			</form>
		</div>
	</div>

</body>
</html>
