<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>User Signup Page</title>
<!-- Font Awesome CDN -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css"
    integrity="sha512-Evv84Mr4kqVGRNSgIGL/F/aIDqQb7xQ2vcrdIwxfjThSH8CSR7PBEakCr51Ck+w+/U6swU2Im1vVX0SVk9ABhg=="
    crossorigin="anonymous" referrerpolicy="no-referrer" />
    
<!-- Tailwind CDN -->
<link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">

<style>
/* Submit Btn */
.submitBtn {
    background-color: #2D73BE;
}

.submitBtn:hover {
    background-color: #1B2C51;
}
</style>
</head>

<body class="min-h-screen flex flex-col bg-gray-100">

    <!-- Signup Panel -->
    <div class="mx-auto w-full max-w-md min-h-screen flex items-center justify-center px-4 sm:px-6 lg:px-8">

        <div class="w-full bg-white rounded-3xl shadow-2xl p-8 transform transition-all duration-300 hover:scale-105">
            <div class="text-center mb-6">
                <h1 class="text-3xl font-extrabold text-gray-800">Student Registration</h1>
                <p class="text-gray-600 mt-2">Fill in the details to register as a Student.</p>
            </div>

            <!-- Social Media Buttons -->
            <div class="flex justify-center gap-4 mb-6">
                <button aria-label="Sign up with Facebook"
                    class="w-12 h-12 bg-blue-600 rounded-full hover:bg-blue-700 shadow-md transition-all duration-300 flex items-center justify-center">
                    <i class="fab fa-facebook-f text-white"></i>
                </button>
                <button aria-label="Sign up with Apple"
                    class="w-12 h-12 bg-black rounded-full hover:bg-gray-800 shadow-md transition-all duration-300 flex items-center justify-center">
                    <i class="fab fa-apple text-white"></i>
                </button>
                <button aria-label="Sign up with Google"
                    class="w-12 h-12 bg-red-600 rounded-full hover:bg-red-700 shadow-md transition-all duration-300 flex items-center justify-center">
                    <i class="fab fa-google text-white"></i>
                </button>
            </div>

            <p class="text-center mb-6 text-gray-600 font-medium">or</p>

            <!-- Signup Form -->
            <form id="signupForm" action="insertUserController" method="post">
                <div class="mb-4 relative">
                    <input aria-label="Name" name="full_name" placeholder="Full Name"
                        value="<%=request.getAttribute("full_name") != null ? request.getAttribute("full_name") : ""%>"
                        class="w-full pl-12 py-2 border border-gray-300 rounded-lg shadow-sm focus:ring-2 focus:ring-blue-500 focus:outline-none" type="text" /> 
                    <span class="absolute left-3 top-2 text-gray-500"> 
                        <i class="fas fa-user"></i>
                    </span>
                    <%
                    String full_nameError = (String) request.getAttribute("full_nameError");
                    if (full_nameError != null) {
                    %>
                    <div class="text-red-500 text-sm mt-1">
                        <%=full_nameError%>
                    </div>
                    <%
                    }
                    %>
                </div>
                <div class="mb-4 relative">
                    <input aria-label="Email" name="email" value="<%=request.getAttribute("email") != null ? request.getAttribute("email") : ""%>"
                        placeholder="Email Address"
                        class="w-full pl-12 py-2 border border-gray-300 rounded-lg shadow-sm focus:ring-2 focus:ring-blue-500 focus:outline-none" type="email" /> 
                    <span class="absolute left-3 top-2 text-gray-500"> 
                        <i class="fas fa-envelope"></i>
                    </span>
                    <%
                    String emailError = (String) request.getAttribute("emailError");
                    if (emailError != null) {
                    %>
                    <div class="text-red-500 text-sm mt-1">
                        <%=emailError%>
                    </div>
                    <%
                    }
                    %>
                </div>
                <div class="mb-4 relative">
                    <input aria-label="Password" name="password" value="<%=request.getAttribute("password") != null ? request.getAttribute("password") : ""%>"
                        placeholder="Password" class="w-full pl-12 py-2 border border-gray-300 rounded-lg shadow-sm focus:ring-2 focus:ring-blue-500 focus:outline-none"
                        type="password" /> 
                    <span class="absolute left-3 top-2 text-gray-500"> 
                        <i class="fas fa-lock"></i>
                    </span> 
                    <span class="absolute right-3 top-2 text-gray-500 cursor-pointer" id="togglePassword"> 
                        <i class="fas fa-eye"></i>
                    </span>
                    <%
                    String passwordError = (String) request.getAttribute("passwordError");
                    if (passwordError != null) {
                    %>
                    <div class="text-red-500 text-sm mt-1">
                        <%=passwordError%>
                    </div>
                    <%
                    }
                    %>
                </div>
                <div class="flex items-center mb-4">
                    <input type="checkbox" class="w-4 h-4 border-gray-300 rounded focus:ring-blue-500" id="terms"> 
                    <label for="terms" class="ml-2 mb-0 text-sm text-gray-700">I agree to the 
                        <a href="#" class="text-blue-600 hover:underline">Terms and Conditions</a>
                    </label>
                </div>
                <input type="submit" value="Sign Up"
                    class="submitBtn w-full py-3 text-white font-bold uppercase rounded-lg shadow-md hover:shadow-xl transform hover:scale-105 transition-all duration-300" />
            </form>

            <p class="mt-4 text-center text-sm text-gray-600">
                Already have an account? <a href="login.jsp" class="text-blue-600 hover:underline font-semibold">Sign In</a>
            </p>
        </div>

    </div>

    <!-- JS -->
    <script>
        // Toggle Password Visibility
        document.getElementById('togglePassword').addEventListener('click',	function() {
                const passwordField = document.querySelector('input[name="password"]');
                const isPasswordVisible = passwordField.type === 'text';
                passwordField.type = isPasswordVisible ? 'password' : 'text';
                this.innerHTML = isPasswordVisible ? '<i class="fas fa-eye"></i>' : '<i class="fas fa-eye-slash"></i>';
        });
    </script>

    <!-- Include Font Awesome -->
    <script src="https://kit.fontawesome.com/a076d05399.js" crossorigin="anonymous"></script>
</body>

</html>