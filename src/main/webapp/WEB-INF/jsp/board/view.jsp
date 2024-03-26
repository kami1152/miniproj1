<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="style.css">
<style>
body {
	font-family: Arial, sans-serif;
	line-height: 1.6;
}

.post-title-box {
	border: 2px solid #ccc;
	border-radius: 10px; /* 테두리를 둥글게 만듭니다. */
	padding: 20px;
}

.post-info {
	font-size: 12px;
	display: flex;
	justify-content: flex-end;
	align-items: center;
	justify-content: flex-end;
}

.post-content {
	border: 2px solid #ccc;
	border-radius: 10px;
	align-content: center;
}
</style>

</head>
<body>
	<c:if test="${loginVO.username != null}">
		<div id="navigation2"></div>
		<label>로그인 유저 : ${loginVO.username}</label>
	</c:if>
	<c:if test="${loginVO.username == null}">
		<div id="navigation"></div>
	</c:if>
	<h2>게시글</h2>
	<label></label>
	<div class=board_box>
		<h2 class="post-title-box">
			<span>${board.btitle}</span>
			<div class="post-info">
				<p style="padding-right: 20px;">
					작성자: <span id="author">${board.bwriter} </span>
				</p>
				<p style="padding-right: 20px;">
					작성일: <span id="date">${board.bdate}</span>
				</p>
				<p style="padding-right: 20px;">조회수 : ${board.views}</p>
				<p>추천수 : ${board.recommend}</p>
			</div>
		</h2>
		<div class="post-content">
			<p style="margin: 20px;">${board.bcontent}</p>
		</div>
	</div>


	<form id="viewForm" method="post" action="board.do">
		<input type="hidden" id="action" name="action" value=""> <input
			type="hidden" id="bno" name="bno" value="${board.bno}"> <input
			type="button" value="수정" onclick="jsUpdateForm()"
			class="custom-button"> <input type="button" value="삭제"
			onclick="jsDelete()" class="custom-button"> <input
			type="button" value="목록" onclick="jsback()" class="custom-button">
	</form>





	<script type="text/javascript" src="<c:url value='/js/common.js'/>"></script>
	<script>
	
	function jsback(){
		location = "board.do?action=list";
	}	
	
	function jsDelete() {	
		if (confirm("정말로 삭제하시겠습니까?")) {

			action.value = "delete";
			myFetch("board.do", "viewForm", json => {
				if(json.status == 0) {
					//성공
					alert("회원정보를 삭제 하였습니다");
					location = "board.do?action=list";
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