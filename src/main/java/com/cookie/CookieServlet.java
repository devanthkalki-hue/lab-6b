//Build a servlet program to create a cookie to get your name through text box and press submit button(
//through HTML) to display the message by greeting Welcome back your name ! , you have visited this page
//n times ( n = number of your visit ) and demonstrate the expiry of cookie also.



package com.cookie;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/welcome")
public class CookieServlet extends HttpServlet {

protected void doPost(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException {

response.setContentType("text/html");
PrintWriter out = response.getWriter();

// Get name from form
String name = request.getParameter("username");

int visitCount = 1;

// Get cookies
Cookie[] cookies = request.getCookies();

if (cookies != null) {
for (Cookie c : cookies) {
if (c.getName().equals("visitCount")) {
visitCount = Integer.parseInt(c.getValue());
visitCount++;
}
}
}

// Create/Update cookies
Cookie nameCookie = new Cookie("username", name);
Cookie countCookie = new Cookie("visitCount", String.valueOf(visitCount));

// Set expiry (example: 30 seconds for demo)
nameCookie.setMaxAge(30);
countCookie.setMaxAge(30);

// Add cookies to response
response.addCookie(nameCookie);
response.addCookie(countCookie);

// HTML Output
out.println("<html><body>");
out.println("<h2 style='color:blue;'>Welcome back " + name + "!</h2>");
out.println("<p>You have visited this page <b>" + visitCount + "</b> times.</p>");
out.println("<p style='color:red;'>Note: Cookie will expire in 30 seconds.</p>");
out.println("<a href='index.html'>Go Back</a>");
out.println("</body></html>");
}

protected void doGet(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException {
response.sendRedirect("index.html");
}
}