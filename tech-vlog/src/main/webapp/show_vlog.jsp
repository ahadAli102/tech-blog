<%@page import="com.ahad.util.ServiceProvider"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
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
		Vlog vlog = (Vlog) request.getSession().getAttribute("show_vlog");
		Map<String, Object> vlogRating = ServiceProvider.getVlogService().getVlogRating(vlog.getId());
		User vlogAuthor = ServiceProvider.getVlogService().getVlogAuthor(vlog.getEmail());
		
		Map<String, Object> authorRating = ServiceProvider.getUserService().getAuthorRating(vlogAuthor.getEmail());
		System.out.println(authorRating);

	%>

	<div class="container">
		<div class="row pt-2 pb-2">
			<div class="col">
				<p class="fs-3"><%=vlog.getTitle()%></p>
				<p class="fs-5"><%=vlog.getDescription()%></p>
			</div>
			<div class="col ps-3">
				<div class="row">
					<div class="card pb-2 mb-2">
						<div class="row pt-2 pb-1">
							<div class="col">
								<p class="fs-3">
									Author
									<%=vlogAuthor.getName()%></p>
								<p class="fs-5">
									Email
									<%=vlogAuthor.getEmail()%></p>
							</div>
						</div>

						<div class="row">
							<p class="fs-5">
								Author Average Rating :
								<%=authorRating.get("avg_rating")%></p>
						</div>
						<div class="row">
							<p class="fs-5">
								Total votes:
								<%=authorRating.get("total_votes")%></p>
						</div>
						<div class="row">
							<form action="rateauthor" method="GET">
								<label for="customRange2" class="form-label">Rate the
									author (OUT OF 10)</label> <input type="range" class="form-range"
									min="0" max="10" id="customRange2" name="rate"> <input
									type="hidden" name="authorEmail" value="<%=vlogAuthor.getEmail()%>" />
								<%
									String authorStatus = (String) session.getAttribute("rate_author_status");
								if (authorStatus != null) {
								%>
								<label class="form-label"><%=authorStatus%></label>
								<%
								session.removeAttribute("rate_author_status");
								}
								%>
								<div class="text-center">
									<button class="btn btn-outline-success" type="submit">
										POST</button>
								</div>
							</form>
						</div>
					</div>
				</div>

				<div class="row">
					<div class="col card pb-2 mb-2">
						<div class="row">
							<p class="fs-5">
								Vlog Average Rating :
								<%=vlogRating.get("avg_rating")%></p>
						</div>
						<div class="row">
							<p class="fs-5">
								Total votes:
								<%=vlogRating.get("total_votes")%></p>
						</div>
						<div class="row">
							<form action="rateartice" method="POST">
								<label for="customRange2" class="form-label">Rate the
									article (OUT OF 10)</label> <input type="range" class="form-range"
									min="0" max="10" id="customRange2" name="rate"> <input
									type="hidden" name="vlogId" value="<%=vlog.getId()%>" />
								<%
									String status = (String) session.getAttribute("rate_vlog_status");
								if (status != null) {
								%>
								<label class="form-label"><%=status%></label>
								<%
								session.removeAttribute("rate_vlog_status");
								}
								%>
								<div class="text-center">
									<button class="btn btn-outline-success" type="submit">
										POST</button>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>