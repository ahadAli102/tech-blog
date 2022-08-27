package com.ahad.servlet;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.ahad.entity.User;
import com.ahad.service.UserService;
import com.ahad.util.ServiceProvider;

/*
 * Author MD. AHAD ALI
 * Email: linkonahad10@gmail.com
 */

@WebServlet("/uploadImage")
@MultipartConfig
public class InsertProfileImageServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try {
			Part part = request.getPart("file");
			String fileName = part.getSubmittedFileName();
			String type = part.getContentType();
			InputStream is = part.getInputStream();
			byte[] byt = is.readAllBytes();
			String email = ((User) request.getSession().getAttribute("user")).getEmail();
			System.out.println("Name is :" + fileName + " size is : " + byt.length + " email: " + email);
			UserService userService = ServiceProvider.getUserService();

			if (userService.insertProfileImage(part, email) != -1) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("home_profile.jsp");
				request.setAttribute("status", "Upload sucessfull");
				System.out.println("Upload sucessfull");
				dispatcher.forward(request, response);
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher("home_profile.jsp");
				request.setAttribute("status", "Internal server error");
				System.out.println("Internal server error");
				dispatcher.forward(request, response);
			}
		} catch (Exception e) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("home_profile.jsp");
			request.setAttribute("status", e.getMessage());
			System.out.println("Please select file");
			dispatcher.forward(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}
}
