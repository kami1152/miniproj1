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
	<jsp:include page="../navbar.jsp"/>
	<h2>게시글</h2>
	<div class=board_box>
		<form id="rForm" action="board.do" method="post">
			<input type="hidden" name="action" value="update"> <input
				type="hidden" name="bno" value="${board.bno}">
			<h2 class="post-title-box">
				<label>제목 <textarea id="btitle" name="btitle"
						required="required"></textarea>
				</label>
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
				<textarea id="bcontent" name="bcontent" required="required"></textarea>
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

		myFetch("board.do", "rForm", json => {
			if(json.status == 0){
				alert("ok");
				location = "board.do?action=list";
			}else{
				alert(json.statusMessage);
			}
		});
	});
	function redirectToUserPage(bno){
		if(confirm("수정을 취소하시겠습니까?")){
	    var url = 'board.do?action=view&bno=' + bno;
	    window.location.href = url;
	    }
	}
	
    </script>
</body>
</html>
