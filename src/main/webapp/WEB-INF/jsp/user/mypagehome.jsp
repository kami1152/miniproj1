<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
	<h2>test user list</h2>
	<table border="1">
		<tr>
			<th>userid</th>
			<th>username</th>
			<th>useremail</th>
			<th>userage</th>
			<th>userdate</th>
			<th>userhobby</th>			

		</tr>

		<tr>
			<td>${user.userid}</td>
			<td>${user.username}</td>
			<td>${user.useremail}</td>
			<td>${user.userage}</td>
			<td>${user.userdate}</td>
			<td>${user.userhobby}</td>				
			
			
		</tr>

	</table>
	<form id="viewForm" method="post" action="user.do">
		<input type="hidden" id="action" name="action" value=""> <input
			type="hidden" id="userid" name="userid" value="${user.userid}">
		<input type="button" value="수정" onclick="jsUpdateForm()"
			class="custom-button"> <input type="button" value="삭제"
			onclick="jsDelete()" class="custom-button"> <input
			type="button" value="목록" onclick="jsback()" class="custom-button">
	</form>

	<script type="text/javascript" src="<c:url value='/js/common.js'/>"></script>
	<script>
	
function jsback(){
	location = "user.do?action=list";
}	

function jsDelete() {	
	if (confirm("정말로 삭제하시겠습니까?")) {

		action.value = "delete";
		myFetch("user.do", "viewForm", json => {
			if(json.status == 0) {
				//성공
				alert("회원정보를 삭제 하였습니다");
				location = "user.do?action=list";
			} else {
				alert(json.statusMessage);
			}
		});

	}
}
function jsUpdateForm() {

	if (confirm("정말로 수정하시겠습니까?")) {
		//서버의 URL을 설정한다 
		action.value = "updateForm";
	
		//서버의 URL로 전송한다 
		viewForm.submit();
	}	
}


</script>
</body>
</html>