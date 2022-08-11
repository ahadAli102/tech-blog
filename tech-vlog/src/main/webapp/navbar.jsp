<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Insert title here</title>
		<link rel="stylesheet" href="css/bootstrap.min.css">
		<link rel="stylesheet" href="css/myStyle.css">
	</head>
	<body>
		<nav class="navbar navbar-expand-lg navbar-light bg-light base-background">
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
							aria-current="page" href="#">Home</a></li>
						<li class="nav-item"><a class="nav-link" href="#">Contact</a></li>
						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
							role="button" data-bs-toggle='dropdown' aria-expanded="false">
								Explore </a>
							<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
								<li><a class="dropdown-item" href="#">Java</a></li>
								<li><a class="dropdown-item" href="#">Kotlin</a></li>
								<li><hr class="dropdown-divider"></li>
								<li><a class="dropdown-item" href="#">MySql</a></li>
							</ul></li>
						<li class="nav-item"><a class="nav-link disabled" href="#"
							tabindex="-1" aria-disabled="true">More...</a></li>
					</ul>
					<form class="d-flex">
						<input class="form-control me-2" type="search" placeholder="Search"
							aria-label="Search">
						<button class="btn btn-outline-success" type="submit">Search</button>
					</form>
				</div>
			</div>
		</nav>
		<script src="js/jquery.js"></script>        
		<script src="js/bootstrap.js"></script>
	</body>
</html>