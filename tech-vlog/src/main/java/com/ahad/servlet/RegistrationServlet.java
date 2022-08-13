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

@WebServlet("/processSignup")
public class RegistrationServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("name").trim();
		String email = request.getParameter("email").trim();
		String password = request.getParameter("password").trim();
		String rePassword = request.getParameter("rePassword").trim();
		String condition = request.getParameter("condition");
		User user = new User(null, name, email, password, rePassword, condition);
		System.out.println(user);
		try {
			UserService userService = ServiceProvider.getUserService();
			int databaseResponse = userService.addUser(user);
			if (databaseResponse != -1) {
				request.setAttribute("status", "Your account has been registered");
				System.out.println("Your account has been registered");
			} else {
				request.setAttribute("status", "Internal servier error occured");
				System.out.println("Internal servier error occured");
			}
		} catch (RuntimeException e) {
			request.setAttribute("status", e.getMessage());
			System.out.println(e.getMessage());
		} catch (Exception e) {
			request.setAttribute("status", e.getMessage());
			System.out.println(e.getMessage());
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("signup.jsp");
		dispatcher.include(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}
}