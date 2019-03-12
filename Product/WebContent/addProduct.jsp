<!--

생산관리 등록 화면
제품의 정보를 입력하는 화면
그룹의 경우 그룹 코드를 통해 외래키로 연결되어있으며,
select 메뉴에서 사용됨

comment : 다시 보니까 파일명이 마음에 안든다.

-->
<%@page import="net.product.model.GroupcodeVO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	ArrayList<GroupcodeVO> list = (ArrayList<GroupcodeVO>)request.getAttribute("list"); 
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="create.pro" method="get">

				<h2>생산관리 등록화면</h2>
	
				<h4>생산관리 등록화면</h4>

	<ul>	
		<li>제품코드<input type="text" name = "code"></li>
		<li>제품이름<input type="text" name = "pname"></li>
		<li>제품원가<input type="text" name = "cost"></li>
		<li>목표수량<input type="text" name = "pnum"></li>
		<li>재고수량<input type="text" name = "inum"></li>
		<li>출고가격<input type="text" name = "sale"></li>
		<li>그룹이름<select name="gcode">
				<%for(int i=0 ; i<list.size();i++) {%>
					<%GroupcodeVO gvo = list.get(i); %>
					<option value="<%=gvo.getG_code() %>"><%=gvo.getG_name() %></option>
				<%} %>
				</select></li>
	</ul>	
	<input type="submit" value="등    록">
	<input type="button" onclick="location.href='main.jsp'" value="메인화면">
	
</form>
</body>
</html>
