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
	<div id="navigation" style="margin-bottom: 25px"></div>
	<h2>회원 가입 화면</h2>
	<form id="rForm" action="user.do" method="post">
		<input type="hidden" name="action" value="insert">
		<table border="1">
			<tr>
				<th style="text-align: center;">userid : <input type="button" id="duplicateId" value="중복확인" class="dupbtn"></th>
				<th>username</th>
				<th>userpassword</th>
				<th>useremail</th>
				<th>userage</th>
			</tr>

			<tr>
				<td><input type="text" id="userid" name="userid" value=""
					required="required"></td>
				<td><input type="text" id="username" name="username" value=""
					required="required"></td>
				<td><input type="text" id="userpassword" name="userpassword"
					value="" required="required"></td>
				<td><input type="email" id="useremail" name="useremail"
					value="" required="required"></td>
				<td><input type="number" id="userage" name="userage" value=""
					required="required"></td>
			</tr>
		</table>
		<div class="hobby_box">
			<label style="margin-right: 10px;">취미 : </label> <input type="radio"
				id="userhobby" name="userhobby" value="축구"><label for="축구">축구</label> <input
				type="radio" id="userhobby" name="userhobby" value="게임"><label for="게임">게임</label>
			<input type="radio" id="userhobby" name="userhobby" value="영화"><label
				for="영화">영화</label>
		</div><br>
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
    var url = 'user.do?action=list'
    window.location.href = url;
    }
}

const duplicateId = document.getElementById("duplicateId");
//click 이벤트 핸들러 등록
duplicateId.addEventListener("click", () => {
	const userid = document.getElementById("userid");
	if (userid.value == "") {
		alert("아이디를 입력해주세요");
		userid.focus();
		return;
	}
	const param = {
		action : "existUserId",
		userid : userid.value
	}
	fetch("user.do", {
		method:"POST",
		body:JSON.stringify(param),
		headers : {"Content-type" : "application/json; charset=utf-8"}
	}).then(res => res.json()).then(json => {
		//서버로 부터 받은 결과를 사용해서 처리 루틴 구현  
		console.log("json ", json );
		if (json.existUser == true) {
			alert("해당 아이디는 사용 중 입니다.");
			validUserId = "";
		} else {
			alert("사용가능한 아이디 입니다.");
			validUserId = userid.value;
		}
	});
});

window.addEventListener('DOMContentLoaded', function() {
	  fetch('navigation.html')
	    .then(response => response.text())
	    .then(data => {
	      document.getElementById('navigation').innerHTML = data;
	    })
	    .catch(error => {
	      console.error('네비게이션 로드 중 오류가 발생했습니다:', error);
	    });
	});

</script>
</body>
</html>