<!-- 

생산관리 조회/수정 페이지
등록페이지에서 양식을 가져와서 만듬

comment :
JSP 에서 input 타입중 text 요소를
button으로 처리하기 까다로워서
조회랑 수정을 둘다 submit으로 만들고 value를 플래그값으로 이용함

역시나 파일명이 맘에 안든다

-->
<%@page import="net.product.model.ProductVO"%>
<%@page import="net.product.model.GroupcodeVO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	request.setCharacterEncoding("UTF-8");
	ArrayList<GroupcodeVO> list = (ArrayList<GroupcodeVO>)request.getAttribute("list"); 
	ProductVO vo = (ProductVO)request.getAttribute("vo");
	String code = "";
	String pname = "";
	int cost=0;
	int pnum=0;
	int inum=0;
	int sale=0;
	String gcode="A";
	
	if(vo!=null){
		code = vo.getP_code();
		pname = vo.getP_name();
		cost= vo.getCost();
		pnum= vo.getPnum();
		inum= vo.getInum();
		sale= vo.getSale();
		gcode= vo.getG_code();
	}
	
	
	
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="readAndUpdate.pro" method="get">
			<h2>생산관리 조회&amp;수정화면</h2>
	
			<h4>생산관리 조회화면</h4>

	<ul>	
		<li>제품코드<input type="text" name = "code" value = "<%=code %>"></li>
		<li>제품이름<input type="text" name = "pname"value = "<%=pname %>"></li>
		<li>제품원가<input type="text" name = "cost" <%if(vo!=null){ %> value = "<%=cost %>"<%} %>></li>
		<li>목표수량<input type="text" name = "pnum" <%if(vo!=null){ %> value = "<%=pnum %>"<%} %>></li>
		<li>재고수량<input type="text" name = "inum" <%if(vo!=null){ %> value = "<%=inum %>"<%} %>></li>
		<li>출고가격<input type="text" name = "sale" <%if(vo!=null){ %> value = "<%=sale %>"<%} %>></li>
		<li>그룹이름<select name="gcode">
				<%for(int i=0 ; i<list.size();i++) {%>
					<%GroupcodeVO gvo = list.get(i); 
						String gcode_ = gvo.getG_code();
					%>
					
					<option value="<%=gcode_ %>"<%if(gcode.equals(gcode_)){%> selected="selected" <%} %>><%=gvo.getG_name() %></option>
				<%} %>
				</select></li>
	</ul>
				<input type="submit" name="flag" value="조    회">
				<input type="submit" name="flag" value="수    정">
				<input type="button" onclick="location.href='delete.pro?code=<%=code %>'" value="삭    제">
				<input type="button" onclick="location.href='main.jsp'" value="메인화면">
	
</form>
</body>
</html>
