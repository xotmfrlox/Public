<%@page import="spms.vo.Project"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<!doctype html>
<html>
<head>
	<meta charset="utf-8" />
	<title>프로젝트 등록</title>
	<script src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
		<h1>프로젝트 등록</h1>
		<form action="add.do" method="post">
		제목<input type ="text" name="title"><br>
		내용<input type ="text" name="content"><br>
		시작일<input type="text" name="startDate"><br>
		종료일<input type="text" name="endDate"><br>
		태그<input type= "text" name="tags"><br>
		
		<input type="submit" value='추가'>
		<input type="reset" value='취소'>
		
		</form>
	</div>
</body>
</html>



