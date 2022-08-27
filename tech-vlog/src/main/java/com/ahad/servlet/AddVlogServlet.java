package com.ahad.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ahad.entity.User;
import com.ahad.util.ServiceProvider;

/*
 * Author MD. AHAD ALI
 * Email: linkonahad10@gmail.com
 */

@WebServlet("/addVlog")
public class AddVlogServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String title = request.getParameter("title");
		String description = request.getParameter("description");
		RequestDispatcher dispatcher = request.getRequestDispatcher("home_profile.jsp");
		String email = ((User) request.getSession().getAttribute("user")).getEmail();
		try {
			if (ServiceProvider.getVlogService().addVlog(title, description, email) != -1) {
				request.setAttribute("vlog_status", title + " has been inserted succesfully");
				System.out.println("add vlog insert succesfully");
			} else {
				request.setAttribute("vlog_status", "Internal server error occured");
				System.out.println("Internal server error occured");
			}
		} catch (Exception e) {
			request.setAttribute("vlog_status", e.getMessage());
			System.out.println("Error occured: " + e.getMessage());
		} finally {
			dispatcher.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}
}