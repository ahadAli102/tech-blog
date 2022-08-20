<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.ahad.entity.User"%>
<!DOCTYPE html>


<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/myStyle.css">
</head>
<body>
	<%
		try {
		User user = (User) session.getAttribute("user");
		log("home nav user is : " + user);
		if (user == null) {
			System.out.println("home nav user is null");
			response.sendRedirect("login.jsp");
			return;
		}
		System.out.println("home nav "+user);

	} catch (Exception e) {
		System.out.println("home nav "+e.getMessage());
		response.sendRedirect("login.jsp");
	}
	%>
	<nav
		class="navbar navbar-expand-lg navbar-light bg-light base-background text-center">
		<div class="container-fluid">
			<a class="navbar-brand" href="#">Tech vlog</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0">
					<li class="nav-item"><a class="nav-link active"
						aria-current="page"
						href="http://localhost:8080/tech-vlog/home_feed.jsp">Feed</a></li>

					<li class="nav-item"><a class="nav-link active"
						aria-current="page"
						href="http://localhost:8080/tech-vlog/home_profile.jsp">Profile</a></li>


					<li class="nav-item"><a class="nav-link active"
						aria-current="page"
						href="http://localhost:8080/tech-vlog/home_article.jsp">Article</a></li>

				</ul>
				<form class="d-flex">
					<input class="form-control me-2" type="search" placeholder="Search"
						aria-label="Search">
					<button class="btn btn-outline-success me-2" type="submit">Search</button>
				</form>
				<form class="ml-2" method="get" action="logout">
					<button class="btn btn-outline-success" type="submit">Log
						Out</button>
				</form>
			</div>
		</div>
	</nav>
	<script src="js/jquery.js"></script>
	<script src="js/bootstrap.js"></script>
</body>
</html>

</body>
</html>