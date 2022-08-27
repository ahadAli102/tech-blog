package com.ahad.servlet;

import java.io.IOException;

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

@WebServlet("/rateartice")
public class VlogRatingServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = request.getHeader("referer"); // previous page url
		System.out.println("Url is : " + url);
		String urlParts[] = url.split("/");
		try {
			int vlogRating = Integer.parseInt(request.getParameter("rate"));
			String raterEmail = ((User) request.getSession().getAttribute("user")).getEmail();
			int vlogId = Integer.parseInt(request.getParameter("vlogId"));
			ServiceProvider.getVlogService().rateVlog(vlogId, raterEmail, vlogRating);
			request.getSession().setAttribute("rate_vlog_status", "Vlog had been rated sucesfully");
		} catch (RuntimeException e) {
			System.out.println("Show rate vlog " + e.getMessage());
			request.getSession().setAttribute("rate_vlog_status", e.getMessage());
		} catch (Exception e) {
			System.out.println("Show rate vlog " + e.getMessage());
			request.setAttribute("rate_vlog_status", e.getMessage());
		} finally {
			response.sendRedirect(urlParts[urlParts.length - 1]);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}
}