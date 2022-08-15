package com.ahad.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ahad.entity.User;
import com.ahad.service.UserService;
import com.ahad.util.ServiceProvider;

@WebServlet("/processLogin")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email").trim();
		String password = request.getParameter("password").trim();

		System.out.println(email + "    " + password);
		try {
			UserService userService = ServiceProvider.getUserService();
			User user = userService.getUser(email, password);
			System.out.println("login serv "+user);
			if (user != null) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("home_feed.jsp");
				request.getSession().setAttribute("user", user);
				dispatcher.forward(request, response);
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
				request.setAttribute("status", "User not found for the email and password");
				System.out.println("Internal servier error occured");
				dispatcher.forward(request, response);
			}
		} catch (Exception e) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
			request.setAttribute("status", e.getMessage());
			System.out.println(e.getMessage());
			dispatcher.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
