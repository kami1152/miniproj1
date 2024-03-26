<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Information</title>
<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>

	<c:if test="${loginVO.username != null}">
		<div id="navigation2"></div>
		<label>로그인 유저 : ${loginVO.username}</label>
	</c:if>
	<c:if test="${loginVO.username == null}">
		<div id="navigation"></div>
	</c:if>
	<h1>회원목록</h1>

	<form id="searchForm" action="user.do" method="get"
		style="margin-bottom: 20px;">
		<label>이름 : </label> <input type="text" id="searchKey"
			name="searchKey" value="${param.searchKey}"> <input
			type="submit" value="검색">
	</form>

	<form id="listForm" action="user.do" method="post">
		<input type="hidden" id="action" name="action" value="view"> <input
			type="hidden" id="userid" name="userid">
	</form>

	<table border="1">
		<tr>
			<th>ID</th>
			<th>이름</th>
			<th>나이</th>
			<th>이메일</th>
		</tr>
		<c:forEach var="user" items="${list}">
			<tr>
				<td onclick="jsView('${user.userid}')" style="cursor: pointer;">${user.userid}</td>
				<td><a href="user.do?action=view&userid=${user.userid}">${user.username}</a></td>
				<td>${user.userage}</td>
				<td>${user.useremail}</td>
			</tr>
		</c:forEach>
	</table>


	<form id="insertForm" method="post" action="user.do">
		<input type="hidden" id="action" name="action" value="insertForm">
		<input type="button" value="등록" onclick="jsinsertForm()"
			class="custom-button">
	</form>

	<script type="text/javascript" src="<c:url value='/js/common.js'/>"></script>
	<script>
		function jsView(uid) {
			//인자의 값을 설정한다 
			userid.value = uid;

			//양식을 통해서 서버의 URL로 값을 전달한다
			listForm.submit();

		}

		function jsinsertForm() {
			insertForm.submit();
		}
	</script>

</body>
</html>