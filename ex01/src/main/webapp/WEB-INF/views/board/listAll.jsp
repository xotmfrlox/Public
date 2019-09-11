<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page session="false"%>
<!-- header.jsp와 footer.jsp를 추가하면 위아래 이상한 글씨들이 추가됨 -->
<%@include file="../include/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<!-- board List가 추가 시작
<section class="content">
	<div class="row">
		left column


		<div class="col-md-12">
			general form elements
			<div class='box'>
				<div class="box-header with-border">
					<h3 class="box-title">Board List</h3>
				</div>
				board List가 추가 끝 -->

	<!-- LIST PAGING 시작 -->
	<div class="box">
		<div class="box-header with-border">
			<h3 class="box-title">LIST PAGING</h3>
		</div>
		<div class="box-body">
			<!-- LIST PAGING 끝 -->

			<table class="table table-bordered">
				<tr>
					<th style="width: 10px">BNO></th>
					<th>TITLE</th>
					<th>WRITER></th>
					<th>REGDATE></th>
					<th style="width: 40px">VIEWCNT</th>
				</tr>


				<c:forEach items="${list}" var="boardVO">
					<tr>
						<td>${boardVO.bno}</td>
						<td><a href='/read?bno=${boardVO.bno}'>${boardVO.title}</a></td>
						<td>${boardVO.writer}</td>
						<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm"
								value="${boardVO.regdate}" /></td>
						<td><span class="badge bg-red">${boardVO.viewcnt}</span></td>
					</tr>
				</c:forEach>
			
			</table>
			<script>
				var result = '${msg}';

				if (result == 'success') {
					alert("처리 완료");
				}
			</script>
			</table>
</body>
</html>
<%@include file="../include/footer.jsp"%>
