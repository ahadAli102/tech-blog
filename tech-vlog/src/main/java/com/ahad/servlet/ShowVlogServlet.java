package com.ahad.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ahad.entity.Vlog;
import com.ahad.util.ServiceProvider;

@WebServlet("/showvlog")
public class ShowVlogServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
			int id = Integer.parseInt(request.getParameter("vlogId"));
			Vlog vlog =  ServiceProvider.getVlogService().getVlog(id);
			System.out.println(vlog);
			if(vlog!=null) {
				System.out.println("show vlog "+vlog);
				request.getSession().setAttribute("show_vlog", vlog);
				RequestDispatcher dispatcher = request.getRequestDispatcher("show_vlog.jsp");
				dispatcher.forward(request, response);
			}else {
				System.out.println("Show vlog vlog is null");
				response.sendRedirect("home_profile.jsp");
			}
		} catch (RuntimeException e) {
			System.out.println("Show vlog"+e.getMessage());
			response.sendRedirect("home_profile.jsp");
		} catch (Exception e) {
			System.out.println("Show vlog"+e.getMessage());
			response.sendRedirect("home_profile.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}
}