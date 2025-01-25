<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta charset="UTF-8">
    <title>Login</title>
    <!-- Font Awesome CDN -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css"
        integrity="sha512-Evv84Mr4kqVGRNSgIGL/F/aIDqQb7xQ2vcrdIwxfjThSH8CSR7PBEakCr51Ck+w+/U6swU2Im1vVX0SVk9ABhg=="
        crossorigin="anonymous" referrerpolicy="no-referrer" />
    <!-- Tailwind CDN -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css">
    <!-- Particles js CDN -->
    <script src="https://cdn.jsdelivr.net/particles.js/2.0.0/particles.min.js"></script>

    <style>
        body {
            margin: 0;
        }

        #particles-js {
            position: absolute;
            height: 100%;
            width: 100%;
        }
    </style>
</head>

<body class="relative min-h-screen flex items-center justify-center bg-gray-900">
    <!-- particles-js -->
    <div id="particles-js"></div>

    <!-- Login Container -->
    <div class="bg-white bg-opacity-90 shadow-lg rounded-lg max-w-md w-full p-8 z-10 mx-4 sm:mx-6 md:mx-8 lg:mx-10">
    	
        <h1 class="text-2xl font-bold mb-6 text-center text-gray-800">Login</h1>

        <form action="login" method="post" class="space-y-6">
            <!-- Email Input -->
            <div>
                <label for="email" class="block text-sm font-medium text-gray-700">Email</label>
                <input type="email" name="email" value="<%= request.getAttribute("email") != null ? request.getAttribute("email") : "" %>"
                    class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm"
                    placeholder="Enter your email">
                <% String emailError = (String) request.getAttribute("emailError");
                    if (emailError != null) { %>
                <div class="text-red-500 text-sm mt-1">
                    <%= emailError %>
                </div>
                <% } %>
            </div>
            <!-- Password Input -->
            <div class="relative">
                <label for="password" class="block text-sm font-medium text-gray-700">Password</label>
                <input type="password" name="password" value="<%= request.getAttribute("password") != null ? request.getAttribute("password") : "" %>"
                    class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm"
                    placeholder="Enter your password">
                <span class="absolute right-3 top-7 text-gray-500 cursor-pointer" id="togglePassword">
                    <i class="fas fa-eye"></i>
                </span>
                <% String passwordError = (String) request.getAttribute("passwordError");
                    if (passwordError != null) { %>
                <div class="text-red-500 text-sm mt-1">
                    <%= passwordError %>
                </div>
                <% } %>
            </div>
            <!-- Submit Button -->
            <button type="submit"
                class="w-full py-2 px-4 text-sm font-medium rounded-md text-white bg-indigo-600 hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500">
                Login
            </button>
        </form>
        <!-- Not registered? Sign up -->
        <p class="mt-4 text-center text-sm text-gray-600">
            Not registered? <a href="signup.jsp" class="text-indigo-600 hover:text-indigo-800">Sign up</a>
        </p>
        <!-- Forget Password -->
        <p class="mt-4 text-center text-sm text-gray-600">
            <a href="forget-password.jsp" class="text-indigo-600 hover:text-indigo-800">Forgot Password?</a>
        </p>
        <!-- Error Message -->
        <% String loginError = (String) request.getAttribute("loginError");
            if (loginError != null) { %>
        <p class="mt-4 text-red-600 text-sm text-center">
            <%= loginError %>
        </p>
        <% } %>
    </div>

    <!-- Include Font Awesome -->
    <script src="https://kit.fontawesome.com/a076d05399.js" crossorigin="anonymous"></script>

    <script>
        // Toggle Password Visibility
        document.getElementById('togglePassword').addEventListener('click', function () {
            const passwordField = document.querySelector('input[name="password"]');
            const isPasswordVisible = passwordField.type === 'text';
            passwordField.type = isPasswordVisible ? 'password' : 'text';
            this.innerHTML = isPasswordVisible ? '<i class="fas fa-eye"></i>' : '<i class="fas fa-eye-slash"></i>';
        });

        /* ---- particles.js config ---- */
        particlesJS("particles-js", {
            particles: {
                number: {
                    value: 80,
                    density: {
                        enable: true,
                        value_area: 800
                    }
                },
                color: {
                    value: ["#2EB67D", "#ECB22E", "#E01E5B", "#36C5F0"]
                },
                shape: {
                    type: ["circle"],
                    stroke: {
                        width: 0,
                        color: "#fff"
                    },
                    polygon: {
                        nb_sides: 5
                    },
                    image: {
                        src: "https://cdn.freebiesupply.com/logos/large/2x/slack-logo-icon.png",
                        width: 100,
                        height: 100
                    }
                },
                opacity: {
                    value: 1,
                    random: false,
                    anim: {
                        enable: false,
                        speed: 1,
                        opacity_min: 0.1,
                        sync: false
                    }
                },
                size: {
                    value: 8,
                    random: true,
                    anim: {
                        enable: false,
                        speed: 10,
                        size_min: 10,
                        sync: false
                    }
                },
                line_linked: {
                    enable: true,
                    distance: 150,
                    color: "#808080",
                    opacity: 0.4,
                    width: 1
                },
                move: {
                    enable: true,
                    speed: 5,
                    direction: "none",
                    random: false,
                    straight: false,
                    out_mode: "out",
                    bounce: false,
                    attract: {
                        enable: false,
                        rotateX: 600,
                        rotateY: 1200
                    }
                }
            },
            interactivity: {
                detect_on: "canvas",
                events: {
                    onhover: {
                        enable: true,
                        mode: "grab"
                    },
                    onclick: {
                        enable: true,
                        mode: "push"
                    },
                    resize: true
                },
                modes: {
                    grab: {
                        distance: 140,
                        line_linked: {
                            opacity: 1
                        }
                    },
                    bubble: {
                        distance: 400,
                        size: 40,
                        duration: 2,
                        opacity: 8,
                        speed: 3
                    },
                    repulse: {
                        distance: 200,
                        duration: 0.4
                    },
                    push: {
                        particles_nb: 4
                    },
                    remove: {
                        particles_nb: 2
                    }
                }
            },
            retina_detect: true
        });
    </script>
</body>

</html>