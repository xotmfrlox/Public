<%-- <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<!doctype html>
<html>
<head>
	<meta charset="utf-8" />
	<title>프로젝트 정보</title>
	<script src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>

		<h1>프로젝트 정보</h1>
		<form action='update.do' method='post'>
		번호: <input id='no' type='text' name='no' value="${project.no}" readonly><br>
		제목: <input id = 'title' type='text' name='name' value="${project.title}"><br>
		<li><label for="title">제목</label> <input id="title" type='text'
				name='title' size="50" value="${project.title}"></li>
		내용: <textarea id="content" name='content' rows="5" cols="40">${project.content}</textarea><br>
		시작일: <input id  ='startDate' type='text' name='startDate' value="${project.startDate}"><br>
		종료일: <input id ='endDate' type='text' name='endDate' value="${project.endDate}"><br>
		상태: <input id ='state' type= 'text' name='state' value="${project.state}"><br>
		태그: <input id='tags' type='text' name='tags' value="${project.tags}"><br>
		
		<input type='submit' value='저장'> 
		<input type='button' value='삭제' onclick='location.href="delete.do?no=${project.no}";'>
		<input type='button' value='취소' onclick='location.href="list.do"'>
		</form>

</body>
</html> --%>

 <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>프로젝트 정보</title>
<!-- <style>
ul {
	padding: 0;
}

li {
	list-style: none;
}

label {
	float: left;
	text-align: right;
	width: 60px;
}
</style> -->
</head>
<body>
	<%-- <jsp:include page="/Header.jsp" /> --%>
	<h1>프로젝트 정보</h1>
	<form action='update.do' method='post'>
		<ul>
			<li><label for="no">번호</label> <input id="no" type='text'
				name='no' size="5" value="${project.no}" readonly></li>
			<!-- <li><label for="title" -->제목<!-- </label> --> <input id="title" type='text'
				name='title'  value="${project.title}"><!-- </li> -->
			<li><label for="content">내용</label> <textarea id="content"
					name='content' rows="5" cols="40">${project.content}</textarea></li>
			<li><label for="sdate">시작일</label> <input id="sdate" type='text'
				name='startDate' placeholder="예)2013-01-01"
				value="${project.startDate}"></li>
			<li><label for="edate">종료일</label> <input id="edate" type='text'
				name='endDate' placeholder="예)2013-01-01" value="${project.endDate}"></li>
			<li><label for="state">상태</label> <select id="state"
				name="state">
					<option value="0" ${project.state == 0 ? "selected" : ""}>준비</option>
					<option value="1" ${project.state == 1 ? "selected" : ""}>진행</option>
					<option value="2" ${project.state == 2 ? "selected" : ""}>완료</option>
					<option value="3" ${project.state == 3 ? "selected" : ""}>취소</option>
			</select></li>
			<li><label for="tags">태그</label> <input id="tags" type='text'
				name='tags' placeholder="예)태그1 태그2 태그3" size="50"
				value="${project.tags}"></li>
		</ul>
		<input type='submit' value='저장'> 
		<input type='button' value='삭제' onclick='location.href="delete.do?no=${project.no}";'>
		<input type='button' value='취소' onclick='location.href="list.do"'>
	</form>
	<%-- <jsp:include page="/Tail.jsp" /> --%>
</body>
</html>