<%@page import="com.ahad.util.DatabaseConnectionProvider"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@include file="navbar.jsp"%>
<%@ page errorPage="error_page.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%!Connection conn;%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Tech vlog</title>
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/myStyle.css">
<style>
.clip {
	clip-path: polygon(50% 0%, 83% 0, 100% 0, 100% 100%, 80% 90%, 50% 100%, 20% 90%, 0
		96%, 0 0, 21% 0);
}
</style>
</head>
<body>
	<div class="container-fluid clip base-background p-5">
		<h1>Welcome to tech vlog</h1>
		<p>Computer programming is the process of performing a particular
			computation (or more generally, accomplishing a specific computing
			result), usually by designing and building an executable computer
			program. Programming involves tasks such as analysis, generating
			algorithms, profiling algorithms' accuracy and resource consumption,
			and the implementation of algorithms (usually in a chosen programming
			language, commonly referred to as coding).[1][2] The source code of a
			program is written in one or more languages that are intelligible to
			programmers, rather than machine code, which is directly executed by
			the central processing unit. The purpose of programming is to find a
			sequence of instructions that will automate the performance of a task
			(which can be as complex as an operating system) on a computer, often
			for solving a given problem. Proficient programming thus usually
			requires expertise in several different subjects, including knowledge
			of the application domain, specialized algorithms, and formal logic.</p>
		<div class="col-md-12 text-center">
			<a href="login.jsp" type="button" class="btn btn-outline-secondary">login</a>
			<a href="signup.jsp" type="button" class="btn btn-outline-secondary">signup</a>

		</div>
	</div>

	<br>

	<div class="row row-cols-1 row-cols-md-3 g-4">
		<div class="col">
			<div class="card h-100">
				<div class="card-body">
					<h5 class="card-title">Java</h5>
					<p class="card-text">Java is a programming language and
						computing platform first released by Sun Microsystems in 1995. It
						has evolved from humble beginnings to power a large share of
						today's digital world, by providing the reliable platform upon
						which many services and applications are built. New innovative
						products and digital services designed for the future continue to
						rely on Java, as well.</p>
					<a href="https://www.java.com/en/download/help/whatis_java.html"
						class="stretched-link">Learn more</a>
				</div>
				<div class="card-footer">
					<small class="text-muted"> May 23, 1995</small>
				</div>
			</div>
		</div>
		<div class="col">
			<div class="card h-100">
				<div class="card-body">
					<h5 class="card-title">Kotlin</h5>
					<p class="card-text">Kotlin is a cross-platform, statically
						typed, general-purpose programming language with type inference.
						Kotlin is designed to interoperate fully with Java, and the JVM
						version of Kotlin's standard library depends on the Java Class
						Library, but type inference allows its syntax to be more concise.</p>
					<a href="https://kotlinlang.org" class="stretched-link">Learn
						more</a>
				</div>
				<div class="card-footer">
					<small class="text-muted">July 22, 2011</small>
				</div>
			</div>
		</div>
		<div class="col">
			<div class="card h-100">
				<div class="card-body">
					<h5 class="card-title">MySql</h5>
					<p class="card-text">MySQL is a relational database management
						system (RDBMS) developed by Oracle that is based on structured
						query language (SQL). A database is a structured collection of
						data. It may be anything from a simple shopping list to a picture
						gallery or a place to hold the vast amounts of information in a
						corporate network.</p>
					<a href="https://www.mysql.com" class="stretched-link">Learn
						more</a>
				</div>
				<div class="card-footer">
					<small class="text-muted">May 23, 1995</small>
				</div>
			</div>
		</div>
	</div>

	<script src="js/jquery.js"></script>
	<script src="js/bootstrap.js"></script>
</body>
</html>