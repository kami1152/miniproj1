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

[type="number"] {
	border: none;
	font-size: 16px;
	width: 100%;
}

[type="email"] {
	border: none;
	font-size: 16px;
	width: 100%;
}

.hobby_box {
	display: inline-block;
	border: 2px solid black;
	padding: 10px;
	border-radius: 5px;
}

.hobby_box label {
	margin-right: 10px;
}

.dupbtn {
	background-color: transparent;
	color: white;
	border: 2px solid white;
	border-radius: 15px;
	padding: 5px 10px;
	font-weight: bold;
	font-size: 14px;
	cursor: pointer;
	transition: all 0.3s ease;
}

.dupbtn:hover {
	background-color: white;
	color: black;
}
</style>

</head>
<body>
	<jsp:include page="../navbar.jsp"/>
	<h2>로그인</h2>
	<form id="rForm" action="user.do" method="post">
		<input type="hidden" id="action" name="action" value="login">
		<table border="1">
			<tr>
				<th>userid</th>
				<th>userpassword</th>
			</tr>

			<tr>
				<td><input type="text" id="userid" name="userid" value=""
					required="required"></td>
				<td><input type="text" id="userpassword" name="userpassword"
					value="" required="required"></td>
			</tr>
		</table>
		<input type="checkbox" id="autologin" name="autologin" value="Y">
		<label for="checkbox">자동 로그인</label><br>
		<button type="submit" class="custom-button">확인</button>
		<button type="button" onclick="redirectToUserPage('${user.userid}')"
			class="custom-button">취소</button>

	</form>


	<script type="text/javascript" src="<c:url value='/js/common.js'/>"></script>

	<script type="text/javascript">
const rForm = document.getElementById("rForm");
rForm.addEventListener("submit" , e=> {
	//e.preventDefault();
/*
	myFetch("user.do", "rForm", json => {
		if(json.status == 0){
			alert("hello !");
			location = "board.do?action=list";
		}else{
			alert(json.statusMessage);
		}
	});
*/
});

function redirectToUserPage(userid){
	if(confirm("수정을 취소하시겠습니까?")){
    var url = 'user.do?action=list'
    window.location.href = url;
    }
}





</script>
</body>
</html>