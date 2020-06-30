<%@page import="spms.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="member" scope="session" class="spms.vo.Member" />
<div 
	style="background-color: #00008b; color: #ffffff; height: 20px; padding: 5px;">
	<span style="float: right:"> <%=member.getName()%> <a
		style="color: white;" href="<%=request.getContextPath()%>/auth/logout">로그아웃</a>
	</span>

</div>



