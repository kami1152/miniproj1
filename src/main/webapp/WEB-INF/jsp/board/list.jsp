<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>board Information</title>
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

	<h1>게시글목록</h1>

	<form id="searchForm" action="board.do" method="get"
		style="margin-bottom: 20px;">
		<label>검색 : </label> <input type="text" id="searchKey"
			name="searchKey" value="${param.searchKey}"> <input
			type="submit" value="검색">
	</form>

	<form id="listForm" action="board.do" method="post">
		<input type="hidden" id="action" name="action" value="view"> <input
			type="hidden" id="bno" name="bno">
	</form>

	<table border="1">
		<tr>
			<th>bno</th>
			<th>title</th>
			<th>writer</th>
			<th>date</th>
			<th>view</th>
			<th>recommend</th>
		</tr>
		<c:forEach var="board" items="${list}">
			<tr>
				<td onclick="jsView('${board.bno}')" style="cursor: pointer;">${board.bno}</td>
				<td><a href="board.do?action=view&bno=${board.bno}">${board.btitle}</a></td>
				<td>${board.bwriter}</td>
				<td>${board.bdate}</td>
				<td>${board.views}</td>
				<td>${board.recommend}</td>
			</tr>
		</c:forEach>
	</table>


	<form id="insertForm" method="post" action="board.do">
		<input type="hidden" id="action" name="action" value="insertForm">
		<input type="button" value="등록"
			onclick="jsinsertForm('${loginVO.userid}')" class="custom-button">
	</form>



	<script type="text/javascript" src="<c:url value='/js/common.js'/>"></script>


	<script>
		function jsView(uid) {
			//인자의 값을 설정한다 
			bno.value = uid;

			//양식을 통해서 서버의 URL로 값을 전달한다
			listForm.submit();

		}

		function jsinsertForm(userid) {
			
			if (userid != null) {
				insertForm.submit();
			} else{
				alert("로그인이 필요한 서비스 입니다.");
			}
		}
	</script>

</body>
</html>