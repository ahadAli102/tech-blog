package com.ahad.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ahad.util.ServiceProvider;

@WebServlet("/deletevlog")
public class DeleteVlogServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			int vlogId = Integer.parseInt(request.getParameter("vlogId"));
			ServiceProvider.getVlogService().deleteVlog(vlogId);
			request.getSession().setAttribute("delete_vlog_status", "Vlog is deleted sucessfully");
			System.out.println("add vlog insert succesfully");
		} catch (Exception e) {
			request.getSession().setAttribute("delete_vlog_status", e.getMessage());
			System.out.println("Error occured: " + e.getMessage());
		}finally {
			response.sendRedirect("home_profile.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}
}