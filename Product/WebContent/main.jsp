<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
		.button{
			width: 200px;
			border-radius : 5px;
			border: solid 2px;
			border-color: maroon;
			background-color: #f4f0cd;
		}
	</style>
</head>
<body>
<form>
	<fieldset>
		<legend>생산관리 메인메뉴</legend>
			<input type="button" onclick="location.href='toAddForm.pro'" value="제품입력" class="button">
			<input type="button" onclick="location.href='toModifyForm.pro'" value="제품조회"class="button"><br>
			<input type="button" onclick="" value="우선생산제품"class="button">
			<input type="button" onclick="" value="이익순위"class="button"><br>
			<input type="button" onclick="" value="그룹별재고수량"class="button">
	</fieldset>
</form>
</body>
</html>