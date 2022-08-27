<%@page import="com.ahad.entity.Vlog"%>
<%@page import="java.util.List"%>
<%@page import="com.ahad.util.ServiceProvider"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@include file="home_nav.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Vlogs</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/myStyle.css">
</head>
<body>
	<%
	try {
		User user = (User) session.getAttribute("user");
		if (user == null) {
			System.out.println("home feed user is null");
			response.sendRedirect("login.jsp");
		}

	} catch (Exception e) {
		System.out.println("home feed " + e.getMessage());
		response.sendRedirect("login.jsp");
	}
	%>

	<div class="row row-cols-1 row-cols-md-3 g-4 p-2">

		<%
			List<Vlog> vlogs;
			if(request.getParameter("query") != null){
				vlogs = ServiceProvider.getVlogService().getVlogs((request.getParameter("query").trim()));
			}else{
				vlogs = ServiceProvider.getVlogService().getVlogs();
			}
			
			for (int i = 0; i < vlogs.size(); i++) {
		%>
		<div class="col">
			<div class="card h-100">
				<div class="card-body">
					<h5 class="card-title"><%=vlogs.get(i).getTitle()%></h5>
					<p class="card-text"><%=vlogs.get(i).getDescription()%></p>
					<p class="card-text">
						<small class="text-muted"><%=vlogs.get(i).getLastUpdate()%></small>
					</p>
					<div class="card-text text-muted d-flex">
						<a
							href="http://localhost:8080/tech-vlog/showvlog?vlogId=<%=vlogs.get(i).getId()%>"
							class="ms-2 me-2 link-primary"> SHOW </a>
					</div>
				</div>
			</div>
		</div>
		<%
			}
		%>


	</div>
	<script src="js/jquery.js"></script>
	<script src="js/bootstrap.js"></script>
</body>
</html>