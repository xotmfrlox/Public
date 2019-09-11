<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../include/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<script type="text/javascript"
		src="http://code.jquery.com/jquery-2.1.4.js"></script>
	<!-- --------------조회용 페이지 작성 시작--------- -->
	<form role="form" method="post">
		<input type='hidden' name='bno' value="${boardVO.bno}" />
	</form>

	<div class="box-body">
		<div class="form-group">
			<label for="exampleInputEmail1">Title</label> <input type="text"
				name='title' class="form-control" value="${boardVO.title}"
				readonly="readonly" />
		</div>

		<div class="form-group">
			<label for="exampleInputPassword1">Content</label>
			<textarea class="form-control" name="content" rows="3"
				readonly="readonly">${boardVO.content}</textarea>
		</div>

		<div class="form-group">
			<label for="exampleInputEmail1">Writer</label> </input type="text" name="writer" class="form-control" value="${boardVO.writer}" readonly="readonly">
		</div>


		<!-- /.box-body -->

		<div class="box-footer">
			<button type="submit" class="btn btn-warning">Modify</button>
			<button type="submit" class="btn btn-danger">REMOVE</button>
			<button type="submit" class="btn btn-primary">LIST ALL</button>
		</div>

		<!-- --------------조회용 페이지 작성 끝--------- -->

		<script>
			$(document).ready(function() {
				var formObj = $("form[role='form']");
				console.log(formObj);

				/* 수정 페이지로 이동 /board/modify로 이동*/
				$(".btn-warning").on("click", function() {
					formObj.attr("action", "/modify");
					formObj.attr("method", "get");
					formObj.submit();
				});

				/* 삭제 페이지로 이동  /board/remove로 이동*/
				$(".btn-danger").on("click", function() {
					formObj.attr("action", "/remove");
					formObj.submit();
				});

				/* 다시 목록으로 가는 작업 /board/listAll로 이동 */
				$(".btn-primary").on("click", function() {
					self.location = "/listAll";
				});
			});
		</script>
</body>
</html>
<%@include file="../include/footer.jsp"%>