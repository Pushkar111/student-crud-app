<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="UTF-8">
<title>Verify OTP</title>
<!-- Tailwind CSS -->
<link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="bg-gray-900">
    <div class="flex justify-center items-center h-screen">
        <div class="bg-white bg-opacity-90 shadow-lg rounded-lg max-w-md w-full p-8 mx-4 sm:mx-6 md:mx-8 lg:mx-10">
            <h1 class="text-2xl font-bold mb-6 text-center text-gray-800">Verify OTP</h1>
            <form action="verifyOtpServlet" method="post" class="space-y-6">
                <div>
                    <label for="otp" class="block text-sm font-medium text-gray-700">OTP</label>
                    <input type="text" name="otp" class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm" placeholder="Enter OTP">
                    <% 
                        String otpError = (String) request.getAttribute("otpError"); 
                        if (otpError != null) { %>
                            <div class="text-red-500 text-sm mt-1">
                                <%= otpError %>
                            </div>
                    <% 
                        } 
                    %>
                </div>
                <button type="submit" class="w-full py-2 px-4 text-sm font-medium rounded-md text-white bg-indigo-600 hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500">Verify OTP</button>
            </form>
            <% 
                String message = (String) request.getAttribute("message"); 
                if (message != null) { 
            %>
                    <p class="mt-4 text-green-600 text-sm text-center">
                        <%= message %>
                    </p>
            <% 
                } 
            %>
        </div>
    </div>
</body>
</html>