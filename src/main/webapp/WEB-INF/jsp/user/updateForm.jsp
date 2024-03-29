<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>수정화면</title>
<link rel="stylesheet" type="text/css" href="style.css">
<style>
[type="text"] {
	border: none;
	font-size: 16px;
	width: 100%;
}
</style>

</head>
<body>
		<jsp:include page="../navbar.jsp"/>
	<h2>회원 수정 화면</h2>
	<form id="rForm" action="user.do" method="post">
		<input type="hidden" name="action" value="update"> <input
			type="hidden" name="userid" value="${user.userid}">
		<table border="1">
			<tr>
				<th>userid</th>
				<th>username</th>
				<th>userpassword</th>
				<th>useremail</th>
				<th>userage</th>
				<th>userdate</th>
			</tr>

			<tr>
				<td>${user.userid}</td>
				<td><input type="text" id="username" name="username"
					value="${user.username}"></td>
				<td><input type="text" id="userpassword" name="userpassword"
					value=""></td>
				<td><input type="text" id="useremail" name="useremail"
					value="${user.useremail}"></td>
				<td><input type="text" id="userage" name="userage"
					value="${user.userage}"></td>
				<td>${user.userdate}</td>
			</tr>
		</table>
			<div class="hobby_box">
			<input type="hidden" id="userhobbies" name="userhobbies" value="-1">
			<c:forEach var="hobby" items="${hobbylist}">
				<label>${hobby}:</label> 
				<input style="margin-right: 10px;" type="checkbox" id="${hobby}" name="userhobbies" value="${hobby}">
			</c:forEach>
		</div>
		<button type="submit" class="custom-button">확인</button>
		<button type="button" onclick="redirectToUserPage('${user.userid}')"
			class="custom-button">취소</button>

	</form>







	<script type="text/javascript" src="<c:url value='/js/common.js'/>"></script>

	<script type="text/javascript">
const rForm = document.getElementById("rForm");
rForm.addEventListener("submit" , e=> {
	e.preventDefault();

	myFetch("user.do", "rForm", json => {
		if(json.status == 0){
			alert("ok");
			location = "user.do?action=list";
		}else{
			alert(json.statusMessage);
		}
	});
});

function redirectToUserPage(userid){
	if(confirm("수정을 취소하시겠습니까?")){
    var url = 'user.do?action=view&userid=' + userid;
    window.location.href = url;
    }
}



</script>
</body>
</html>