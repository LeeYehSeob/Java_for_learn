<%@page import="java.util.ArrayList"%>
<%@page import="net.product.model.ProductVO"%>
<%@page import="net.product.model.ProductDAOImpl"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	ProductDAOImpl dao = new ProductDAOImpl();
ProductVO vo = new ProductVO();
ArrayList<ProductVO> list = dao.readList();
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
<%-- <%=list.size() %>
<%


for(int i =0; i<list.size();i++){
	%>
	<%=list.get(i).getP_name() %>
	<%=list.get(i).getCost() %>
	<br>
	<%
}
%>
<%
vo.setP_code("C05");
vo.setP_name("더미");
vo.setG_code("C");
dao.create(vo);
%>

<%

vo.setP_code("C05");
/*dao.delete(vo); */
vo = dao.readOne(vo);
%>
<%=vo.getP_code() %>
<%=vo.getP_name() %>
<%=vo.getCost() %>
<%=vo.getPnum() %>
<%=vo.getInum() %>
<%=vo.getSale() %>
<%=vo.getG_code() %>
 --%>

<a href="test.po">테스트</a>

</body>
</html>