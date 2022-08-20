<%@page import="com.ahad.entity.Vlog"%>
<%@page import="com.ahad.entity.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="home_profile_nav.jsp"%>

<!DOCTYPE html>
<html>
<head>
<title>Vlog</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/myStyle.css">
</head>
<body>
<body>
	<%
		Vlog vlog = (Vlog) request.getAttribute("show_vlog");
	/* Vlog vlog = new Vlog("Core Java",
			"The word Core describes the basic concept of something, and here, the phrase 'Core Java' defines the basic Java that covers the basic concept of Java programming language.",
			"a@gmail.com"); */
	%>

	<div class="container">
		<div class="row pt-2 pb-2">
			<div class="col">
				<p class="fs-3"><%=vlog.getTitle()%></p>
				<p class="fs-5"><%=vlog.getDescription()%></p>
			</div>
			<div class="col ps-3">
				<div class="row">
					<p class="fs-5">Average Rating: 9.54</p>
				</div>
				<div class="row">
					<p class="fs-5">Total votes: 100</p>
				</div>
				<div class="row">
					<form action="rateartice" method="POST">
						<label for="customRange2" class="form-label">Rate the
							article (OUT OF 10)</label> <input type="range" class="form-range"
							min="0" max="10" id="customRange2" name="rate">
						<div class="text-center">
							<button class="btn btn-outline-success" type="submit">
								POST</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>

</body>
</html>