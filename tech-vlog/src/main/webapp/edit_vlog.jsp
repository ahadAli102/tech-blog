
<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="home_profile_nav.jsp"%>
<html>
<head>
<title>Edit vlog</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/myStyle.css">
</head>
<body>
<body>
	<div class="row">
		<div class="col">
			<p class="text-center h1 fw-bold mb-5 mx-1 mx-md-4 mt-4">Add Vlog</p>
			<form action="editvlog" method="post" class="mx-1 mx-md-4">
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
				<input type="hidden" name="vlogId"
					value="<%=request.getParameter("vlogId")%>" />

				<div class="d-flex justify-content-center mx-4 mb-3 mb-lg-4">
					<input type="submit" value="ADD"
						class="btn btn-primary btn-lg ms-2 me-2" /> <input type="reset"
						value="RESET" class="btn btn-primary btn-lg" />
				</div>
			</form>
		</div>
	</div>
	<script src="js/jquery.js"></script>
	<script src="js/bootstrap.js"></script>
</body>
</html>