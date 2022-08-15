<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/myStyle.css">
<style>
.card {
	position: relative;
	display: flex;
	flex-direction: column;
	min-width: 0;
	word-wrap: break-word;
	background-color: #fff;
	background-clip: border-box;
	border: 1px solid rgba(0, 0, 0, 0.04);
	border-radius: .25rem;
}

.card .card-header {
	background-color: #fff;
	border-bottom: none;
}
</style>
</head>
<body>

	<div class="row justify-content-center">
		<div class="col-md-12 col-sm-12">
			<div class="card shadow-lg border-0 rounded-lg mt-5 mx-auto"
				style="width: 30rem;">
				<h3 class="card-header display-1 text-muted text-center">404</h3>

				<span class="card-subtitle mb-2 text-muted text-center"> <%=exception.getLocalizedMessage()%>
				</span>

				<div class="card-body mx-auto">
					<a type="button" href="http://localhost:8080/tech-vlog/"
						class="btn btn-sm btn-info text-white"> Back To Home </a>
				</div>
			</div>
		</div>
	</div>
	<script src="js/jquery.js"></script>
	<script src="js/bootstrap.js"></script>
</body>
</html>