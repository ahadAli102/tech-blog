<%@page import="com.ahad.entity.Vlog"%>
<%@page import="java.util.List"%>
<%@page import="com.ahad.util.ServiceProvider"%>
<%@page import="com.ahad.entity.Image"%>
<%@page import="java.util.Base64"%>
<%@ page import="java.sql.*"%>
<%@page import="com.ahad.entity.User"%>
<%@ page import="java.io.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="home_profile_nav.jsp"%>
<!DOCTYPE html>

<html>
<head>
<title>Profile</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/myStyle.css">
</head>
<body>
	<%
		User user = null;
	String myImage = "https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-profiles/avatar-1.webp";
	try {
		user = (User) session.getAttribute("user");
		log("home nav user is : " + user);
		if (user == null) {
			response.sendRedirect("login.jsp");
		}
		Image image = ServiceProvider.getUserService().getProfileImage(user.getEmail());
		myImage = "data:" + image.getType() + ";base64," + image.getTextImage();

	} catch (Exception e) {
		response.sendRedirect("login.jsp");
	}
	%>
	<%
		List<Vlog> vlogs = ServiceProvider.getVlogService().getVlogs(user.getEmail());
	%>
	<div class="container pt-1 pb-3">
		<p class="text-center h1 fw-bold mb-5 mx-1 mx-md-4 mt-4">User
			profile</p>
		<div class="row row-cols-1 row-cols-md-2 g-4">
			<!-- user profile -->
			<div class="col mt-1" style="border-radius: 15px;">
				<div class="card p-4">
					<div class="d-flex text-black">
						<div class="flex-shrink-0">
							<img src=<%=myImage%> alt="Generic placeholder image"
								class="img-fluid" style="width: 180px; border-radius: 10px;">
						</div>
						<div class="flex-grow-1 ms-3">
							<h5 class="mb-1">Danny McLoan</h5>
							<p class="mb-2 pb-1" style="color: #2b2a2a;">Senior
								Journalist</p>
							<div class="d-flex justify-content-start rounded-3 p-2 mb-2"
								style="background-color: #efefef;">
								<div>
									<p class="small text-muted mb-1">Articles</p>
									<p class="mb-0">41</p>
								</div>
								<div class="px-3">
									<p class="small text-muted mb-1">Followers</p>
									<p class="mb-0">976</p>
								</div>
								<div>
									<p class="small text-muted mb-1">Rating</p>
									<p class="mb-0">8.5</p>
								</div>
							</div>
							<div class="d-flex pt-1">
								<button type="button"
									class="btn btn-outline-primary me-1 flex-grow-1">Chat</button>
								<button type="button" class="btn btn-primary flex-grow-1">Follow</button>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col mt-1" style="border-radius: 15px;">
				<form class="card p-5" action="uploadImage" method="POST"
					action="uploadImage" enctype="multipart/form-data">
					<label class="form-label" for="customFile">Select image to
						change profile</label> <input type="file" accept="image/*"
						class="form-control" id="customFile" name="file" /> <br> <input
						type="hidden"
						value="<%="image" + ((User) session.getAttribute("user")).getName()%>"
						name="userName" />
					<%
						String imageMessage = (String) request.getAttribute("status");
					if (imageMessage != null) {
					%>
					<label class="form-label"><%=imageMessage%></label>
					<%
						request.removeAttribute("status");
					}
					%>
					<div class="text-center">
						<button type="button" class="btn btn-primary"
							data-bs-toggle="modal" data-bs-target="#exampleModal"
							style="width: 100px">UPLOAD</button>
					</div>
					<!-- Vertically centered modal -->
					<div class="modal fade" id="exampleModal" tabindex="-1"
						aria-labelledby="exampleModalLabel" aria-hidden="true">
						<div class="modal-dialog modal-dialog-centered">
							<div class="modal-content">
								<div class="modal-header">
									<h5 class="modal-title" id="exampleModalLabel"><%=((User) session.getAttribute("user")).getName()%></h5>
									<button type="button" class="btn-close" data-bs-dismiss="modal"
										aria-label="Close"></button>
								</div>
								<div class="modal-body">Do you want to change your
									existing profile picture?</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-secondary"
										data-bs-dismiss="modal">NO</button>
									<button type="submit" class="btn btn-primary">YES</button>
								</div>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
		<div class="row">
			<div class="col">
				<p class="text-center h1 fw-bold mb-5 mx-1 mx-md-4 mt-4">Add
					Vlog</p>
				<form action="addVlog" method="post" class="mx-1 mx-md-4">
					<div class="d-flex flex-row align-items-center mb-4">
						<i class="fas fa-user fa-lg me-3 fa-fw"></i>
						<div class="form-outline flex-fill mb-0">
							<input type="text" name="title" id="form3Example1c"
								class="form-control" /> <label class="form-label"
								for="form3Example1c">Title</label>
						</div>
					</div>

					<div class="d-flex flex-row align-items-center mb-4">
						<i class="fas fa-envelope fa-lg me-3 fa-fw"></i>
						<div class="form-outline flex-fill mb-0 rows=3">
							<textarea class="form-control" id="form3Example3c" rows="5"
								name="description"></textarea>
							<label class="form-label" for="form3Example3c">Description</label>
						</div>
					</div>

					<%
						String vlogMessage = (String) request.getAttribute("vlog_status");
					if (vlogMessage != null) {
					%>
					<label class="form-label"><%=vlogMessage%></label>
					<%
						request.removeAttribute("vlog_status");
					}
					%>

					<div class="d-flex justify-content-center mx-4 mb-3 mb-lg-4">
						<input type="submit" value="ADD"
							class="btn btn-primary btn-lg ms-2 me-2" /> <input type="reset"
							value="RESET" class="btn btn-primary btn-lg" />
					</div>
				</form>
			</div>
		</div>
		<p class="text-center h1 fw-bold mb-5 mx-1 mx-md-4 mt-4">Articles</p>

		<div class="row row-cols-1 row-cols-md-3 g-4">
			<%
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
							<a
								href="http://localhost:8080/tech-vlog/editvlog?vlogId=<%=vlogs.get(i).getId()%>"
								class="ms-2 me-2 link-secondary" > EDIT </a>
						</div>
					</div>
				</div>
			</div>
			<%
				}
			%>


		</div>

		<!-- 
		<div class="row">
			<div class="col">1 of 3</div>
			<div class="col">2 of 3</div>
			<div class="col">3 of 3</div>
		</div>
		 -->
	</div>

	<script src="js/jquery.js"></script>
	<script src="js/bootstrap.js"></script>


</body>
</html>