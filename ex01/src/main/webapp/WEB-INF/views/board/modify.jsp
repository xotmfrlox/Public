<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../include/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<script type="text/javascript"
		src="http://code.jquery.com/jquery-2.1.4.js"></script>
	<form role="form" method="post">
		<div class="box-body">
			<div class="form-group">
				<label for="exampleInputEmail1">BNO</label> <input
					type="name='bno' class=" form-control" value="${boardVO.bno}"
					readonly="readonly">
			</div>
			<div class="form-group">
				<label for="exampleInputEmail1">Title</label> <input type="text"
					name='title' class="form-control" value="${boardVO.title}">
			</div>
			<div class="form-group">
				<label for="exampleInputEmail1">Writer</label><input type="text"
					name="writer" class="form-control" value="${boardVO.writer}">
			</div>
		</div>
		<!-- /.box-body -->
	</form>

	<div class="box-footer">
		<button type="submit" class="btn btn-primary">SAVE</button>
		<button type="submit" class="btn btn-warning">CANCEL</button>
	</div>

	<script>
		$(document).ready(function() {
			var formObj = $("form[role='form']");
			console.log(formObj);

			$("btn-warning").on("click", function() {
				self.location = "/listAll";
			});

			$(".btn-primary").on("click", function() {
				formObj.submit();
			});

		});
	</script>
</body>
</html>
<%@include file="../include/footer.jsp"%>