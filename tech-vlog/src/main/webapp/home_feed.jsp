<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@include file="home_nav.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
Feed
<%
		try {
		User user = (User) session.getAttribute("user");
		if (user == null) {
			System.out.println("home feed user is null");
			/* RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
			dispatcher.forward(request, response); */
			response.sendRedirect("login.jsp");
		}

	} catch (Exception e) {
		System.out.println("home feed "+e.getMessage());
		/* RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
		dispatcher.forward(request, response); */
		response.sendRedirect("login.jsp");
	}
	%>
</body>
</html>