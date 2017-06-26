<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	//String username = request.getAttribute("username").toString();
	/* Kanban kanban = (Kanban) request.getAttribute("kanban"); */
%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8" />
	<link rel="stylesheet" href="css/style.css">
	<title>Kanban | Home</title>
</head>
<body id="home">
	<h1>KanBan on the Rocks</h1>
	<c:choose>
		<c:when test='${username != ""}'>
			<jsp:include page="header.jsp" />
			<jsp:include page="kanban.jsp" />
		</c:when>
		<c:otherwise>
			<jsp:include page="login.jsp" />
		</c:otherwise>
	</c:choose>
</body>
</html>