<!DOCTYPE html>
<%@page import="jakarta.servlet.http.HttpSession"%>
<%@page import="com.bean.StudentBean"%>
<%@page import="java.util.ArrayList"%>
<%@include file="navbar.html" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Student List Page</title>

	<link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
<style>
.delete-icon, .edit-icon {
	width: 24px;
	height: 24px;
}
</style>
</head>
<body>
	<div class="bg-gray-100 text-gray-800 min-h-screen flex flex-col items-center py-6">
	    <!-- Page Heading -->
	    <header class="text-center mb-6">
	        <h1 class="text-3xl font-bold text-gray-800">Student List Page</h1>
	    </header>
	
	    <!-- Student Table -->
	    <div class="w-full max-w-5xl bg-white shadow-md rounded-lg p-6 overflow-x-auto">
	        <table class="table-auto w-full border-collapse border border-gray-200">
	            <thead class="bg-gray-50">
	                <tr>
	                    <th class="border border-gray-200 px-4 py-2 text-left text-sm font-semibold text-gray-600">Rno</th>
	                    <th class="border border-gray-200 px-4 py-2 text-left text-sm font-semibold text-gray-600">Name</th>
	                    <th class="border border-gray-200 px-4 py-2 text-left text-sm font-semibold text-gray-600">Std</th>
	                    <th class="border border-gray-200 px-4 py-2 text-left text-sm font-semibold text-gray-600">Marks</th>
	                    <th class="border border-gray-200 px-4 py-2 text-center text-sm font-semibold text-gray-600">Actions</th>
	                </tr>
	            </thead>
	            <tbody>
	                <%
	                ArrayList<StudentBean> list = (ArrayList<StudentBean>) request.getAttribute("list");
	                for (int i = 0; i < list.size(); i++) {
	                    StudentBean s = list.get(i);
	                %>
	                <tr class="hover:bg-gray-100">
	                    <td class="border border-gray-200 px-4 py-2 text-sm"><%= s.getRno() %></td>
	                    <td class="border border-gray-200 px-4 py-2 text-sm"><%= s.getName() %></td>
	                    <td class="border border-gray-200 px-4 py-2 text-sm"><%= s.getStd() %></td>
	                    <td class="border border-gray-200 px-4 py-2 text-sm"><%= s.getMarks() %></td>
	                    <td class="border border-gray-200 px-4 py-2 flex justify-center space-x-4">
	                        <a href="editStudentServlet?rno=<%= s.getRno() %>" class="text-blue-600 hover:text-blue-800">
	                            <svg class="edit-icon" xmlns="http://www.w3.org/2000/svg" x="0px" y="0px" viewBox="0 0 80 80">
									<path d="M 63.074219 10.003906 C 61.535156 10.003906 59.996094 10.589844 58.828125 11.757813 L 51.585938 19 L 15.140625 55.441406 L 9.257813 70.738281 L 24.558594 64.859375 L 24.707031 64.707031 L 68.242188 21.171875 C 70.578125 18.835938 70.578125 15.019531 68.242188 12.6875 L 67.3125 11.757813 C 66.148438 10.589844 64.609375 10.003906 63.074219 10.003906 Z M 63.074219 11.992188 C 64.09375 11.992188 65.113281 12.386719 65.902344 13.171875 L 66.828125 14.097656 C 68.398438 15.671875 68.398438 18.1875 66.828125 19.757813 L 66 20.585938 L 59.414063 14 L 60.242188 13.171875 C 61.027344 12.386719 62.050781 11.992188 63.074219 11.992188 Z M 58 15.414063 L 64.585938 22 L 61 25.585938 L 54.414063 19 Z M 53 20.414063 L 59.585938 27 L 24.65625 61.929688 C 24.480469 61.378906 24.207031 60.792969 23.707031 60.292969 C 22.972656 59.558594 22.046875 59.289063 21.320313 59.144531 C 21.089844 59.097656 21.089844 59.121094 20.902344 59.097656 C 20.878906 58.910156 20.902344 58.910156 20.855469 58.679688 C 20.710938 57.953125 20.441406 57.027344 19.707031 56.292969 C 19.207031 55.792969 18.621094 55.519531 18.070313 55.34375 Z M 53 23 C 52.449219 23 52 23.449219 52 24 C 52 24.550781 52.449219 25 53 25 C 53.550781 25 54 24.550781 54 24 C 54 23.449219 53.550781 23 53 23 Z M 50 26 C 49.449219 26 49 26.449219 49 27 C 49 27.550781 49.449219 28 50 28 C 50.550781 28 51 27.550781 51 27 C 51 26.449219 50.550781 26 50 26 Z M 47 29 C 46.449219 29 46 29.449219 46 30 C 46 30.550781 46.449219 31 47 31 C 47.550781 31 48 30.550781 48 30 C 48 29.449219 47.550781 29 47 29 Z M 44 32 C 43.449219 32 43 32.449219 43 33 C 43 33.550781 43.449219 34 44 34 C 44.550781 34 45 33.550781 45 33 C 45 32.449219 44.550781 32 44 32 Z M 41 35 C 40.449219 35 40 35.449219 40 36 C 40 36.550781 40.449219 37 41 37 C 41.550781 37 42 36.550781 42 36 C 42 35.449219 41.550781 35 41 35 Z M 38 38 C 37.449219 38 37 38.449219 37 39 C 37 39.550781 37.449219 40 38 40 C 38.550781 40 39 39.550781 39 39 C 39 38.449219 38.550781 38 38 38 Z M 35 41 C 34.449219 41 34 41.449219 34 42 C 34 42.550781 34.449219 43 35 43 C 35.550781 43 36 42.550781 36 42 C 36 41.449219 35.550781 41 35 41 Z M 32 44 C 31.449219 44 31 44.449219 31 45 C 31 45.550781 31.449219 46 32 46 C 32.550781 46 33 45.550781 33 45 C 33 44.449219 32.550781 44 32 44 Z M 29 47 C 28.449219 47 28 47.449219 28 48 C 28 48.550781 28.449219 49 29 49 C 29.550781 49 30 48.550781 30 48 C 30 47.449219 29.550781 47 29 47 Z M 26 50 C 25.449219 50 25 50.449219 25 51 C 25 51.550781 25.449219 52 26 52 C 26.550781 52 27 51.550781 27 51 C 27 50.449219 26.550781 50 26 50 Z M 23 53 C 22.449219 53 22 53.449219 22 54 C 22 54.550781 22.449219 55 23 55 C 23.550781 55 24 54.550781 24 54 C 24 53.449219 23.550781 53 23 53 Z M 16.660156 57.066406 C 16.753906 57.082031 16.824219 57.085938 16.929688 57.105469 C 17.453125 57.210938 18.027344 57.441406 18.292969 57.707031 C 18.558594 57.972656 18.789063 58.546875 18.894531 59.070313 C 19 59.59375 19 60 19 60 L 19 61 L 20 61 C 20 61 20.40625 61 20.929688 61.105469 C 21.453125 61.210938 22.027344 61.441406 22.292969 61.707031 C 22.558594 61.972656 22.789063 62.546875 22.894531 63.070313 C 22.914063 63.175781 22.917969 63.246094 22.933594 63.339844 L 16.003906 66.003906 L 13.996094 63.996094 Z"></path>
								</svg>
	                        </a>
	                        <a href="deleteStudentServlet?rno=<%= s.getRno() %>" class="text-red-600 hover:text-red-800">
	                            <svg class="delete-icon" xmlns="http://www.w3.org/2000/svg" xml:space="preserve" id="Delete" x="0" y="0" version="1.1" viewBox="0 0 29 29">
									<path d="M19.795 27H9.205a2.99 2.99 0 0 1-2.985-2.702L4.505 7.099A.998.998 0 0 1 5.5 6h18a1 1 0 0 1 .995 1.099L22.78 24.297A2.991 2.991 0 0 1 19.795 27zM6.604 8 8.21 24.099a.998.998 0 0 0 .995.901h10.59a.998.998 0 0 0 .995-.901L22.396 8H6.604z" fill="#e70a0d" class="color000000 svgShape"></path>
									<path d="M26 8H3a1 1 0 1 1 0-2h23a1 1 0 1 1 0 2zM14.5 23a1 1 0 0 1-1-1V11a1 1 0 1 1 2 0v11a1 1 0 0 1-1 1zM10.999 23a1 1 0 0 1-.995-.91l-1-11a1 1 0 0 1 .905-1.086 1.003 1.003 0 0 1 1.087.906l1 11a1 1 0 0 1-.997 1.09zM18.001 23a1 1 0 0 1-.997-1.09l1-11c.051-.55.531-.946 1.087-.906a1 1 0 0 1 .905 1.086l-1 11a1 1 0 0 1-.995.91z" fill="#e70a0d" class="color000000 svgShape"></path>
									<path d="M19 8h-9a1 1 0 0 1-1-1V3a1 1 0 0 1 1-1h9a1 1 0 0 1 1 1v4a1 1 0 0 1-1 1zm-8-2h7V4h-7v2z" fill="#e70a0d" class="color000000 svgShape"></path>
								</svg>	
	                        </a>
	                    </td>
	                </tr>
	                <%
	                }
	                %>
	            </tbody>
	        </table>
	    </div>
	
	    <!-- Add Student Button -->
	    <div class="mt-6">
	        <a href="registration.jsp" class="bg-blue-600 text-white px-4 py-2 rounded-lg hover:bg-blue-700">
	            Add Student
	        </a>
	    </div>
	</div>
	    
</body>
</html>
