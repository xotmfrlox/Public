<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
	<meta charset="utf-8" />
	<title>프로젝트 목록</title>
	<script src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
		<h1>프로젝트 목록</h1>
		<p>
		<a href='add.do'>신규 프로젝트</a>
		</p>
		<table border="1">
		<tr>
		<th>번호</th>
		<th>제목</th>
		<th>시작일</th>
		<th>종료일</th>
		<th>상태</th>
		<c:forEach var="project" items="${projects}">
		<tr>
		<td>${project.no}</td>
		<td><a href='update.do?no=${project.no}'>${project.title}</a></td>
		<td>${project.startDate}</td>
		<td>${project.endDate}</td>
		<td>${project.state}</td>
		<td><a href='delete.do?no=${project.no}'>[삭제]</a></td>
		</tr>
		</c:forEach>
		
		</table>
	</div>
</body>
</html>



