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
	height: 200px; /* 내용 부분이 아래로 늘어날 수 있도록 높이를 지정합니다. */
	overflow-y: auto; /* 내용이 높이를 초과할 경우 스크롤이 나타나도록 설정합니다. */
}

.board_box textarea {
	font-size: 16px;
	border: none;
	width: 100%;
	height: 100%;
	resize: none;
	padding: 10px;
	box-sizing: border-box;
	outline: none;
	border: none;
}

[type="text"] {
	border: none;
	font-size: 16px;
	width: 100%;
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
	<div class=board_box>
		<form id="rForm" action="board.do" method="post">
			<input type="hidden" name="action" value="insert"> <input
				type="hidden" name="bno" value="${board.bno}">
			<h2 class="post-title-box">
				<label>제목 <textarea id="btitle" name="btitle"></textarea>
				</label>
			</h2>
			<div class="post-content">
				<textarea id="bcontent" name="bcontent"></textarea>
			</div>
			<button type="submit" class="custom-button">확인</button>
			<button type="button" onclick="redirectToUserPage('${board.bno}')"
				class="custom-button">취소</button>
		</form>
	</div>

	<script type="text/javascript" src="<c:url value='/js/common.js'/>"></script>
	<script type="text/javascript">
	const rForm = document.getElementById("rForm");
	rForm.addEventListener("submit" , e=> {
		e.preventDefault();

		if(confirm("작성을 완료하였습니까?")){
		myFetch("board.do", "rForm", json => {
			if(json.status == 0){
				alert("ok");
				location = "board.do?action=list";
			}else{
				alert(json.statusMessage);
			}
		});
		}
	});
	function redirectToUserPage(bno){
		if(confirm("작성을 취소하시겠습니까?")){
	    var url = 'board.do?action=list';
	    window.location.href = url;
	    }
	}
	
    </script>
</body>
</html>
