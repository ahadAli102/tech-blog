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

@WebServlet("/rateauthor")
public class AuthorRatingServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = request.getHeader("referer"); // previous page url
		System.out.println("Url is : "+url);
		String urlParts[] = url.split("/");
		try {
			int rating = Integer.parseInt(request.getParameter("rate"));
			String authorEmail = request.getParameter("authorEmail");
			User user = ((User) request.getSession().getAttribute("user"));
			String raterEmail = user.getEmail();
			System.out.println("User is : "+raterEmail);
			ServiceProvider.getUserService().rateAuthor(authorEmail, raterEmail, rating);
			request.getSession().setAttribute("rate_author_status", "You have rated succesfully");
		} catch (Exception e) {
			request.getSession().setAttribute("rate_author_status", e.getMessage());
			System.out.println(e.getMessage());
		}
		finally {
			response.sendRedirect(urlParts[urlParts.length-1]);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
