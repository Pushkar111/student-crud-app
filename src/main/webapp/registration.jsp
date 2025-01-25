<%@page import="jakarta.servlet.http.HttpSession"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@include file="navbar.html" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="UTF-8">
<title>Student Registration</title>
<link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="bg-gray-100">
    <div class="flex justify-center items-center mt-10">
        <div class="max-w-md w-full bg-white rounded-lg shadow-md overflow-hidden p-8 mx-4 sm:mx-6 md:mx-8 lg:mx-10">
            <h1 class="text-2xl font-bold text-center mb-6">Student Registration</h1>
            <form action="insertStudentServlet" method="post" class="space-y-4">
                <div>
                    <label class="block text-sm font-medium text-gray-700">Student Name</label>
                    <input type="text" name="name" value="<%= request.getAttribute("name") != null ? request.getAttribute("name") : "" %>" class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm">
                    <%
                        String nameError = (String) request.getAttribute("nameError");
                        if (nameError != null) {
                    %>
                        <div class="text-red-500 text-sm mt-1">
                            <%= nameError %>
                        </div>
                    <%
                        }
                    %>
                </div>

                <div>
                    <label class="block text-sm font-medium text-gray-700">Standard</label>
                    <input type="text" name="std" value="<%= request.getAttribute("std") != null ? request.getAttribute("std") : "" %>" class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm">
                    <%
                        String stdError = (String) request.getAttribute("stdError");
                        if (stdError != null) {
                    %>
                        <div class="text-red-500 text-sm mt-1">
                            <%= stdError %>
                        </div>
                    <%
                        }
                    %>
                </div>

                <div>
                    <label class="block text-sm font-medium text-gray-700">Marks</label>
                    <input type="text" name="marks" value="<%= request.getAttribute("marks") != null ? request.getAttribute("marks") : "" %>" class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm">
                    <%
                        String marksError = (String) request.getAttribute("marksError");
                        if (marksError != null) {
                    %>
                        <div class="text-red-500 text-sm mt-1">
                            <%= marksError %>
                        </div>
                    <%
                        }
                    %>
                </div>

                <button type="submit" class="w-full py-2 px-4 text-sm font-medium rounded-md text-white bg-indigo-600 hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500">Register</button>
            </form>
        </div>
    </div>
</body>
</html>