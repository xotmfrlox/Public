<%@page import="spms.vo.Member"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
	"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8" />
<title>회원등록</title>
<script src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
	<h1>회원등록</h1>
	<form action="add" method="post">
		이메일:<input type ="text" name="email"><br>
		암호: <input type="text" name="password"><br>
		이름: <input type="text" name="name"><br>
		<input type="submit" value='추가'>
		<input type="reset" value='취소'>
	</form>
</body>
</html>

