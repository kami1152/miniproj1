<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>



	<script type="text/javascript" src="<c:url value='/js/common.js'/>"></script>


	<form id="viewForm" method="post" action="board.do">
		<input type="hidden" id="action" name="action" value=""> <input
			type="hidden" id="bno" name="bno" value="${board.bno}"> <input
			type="button" value="수정" onclick="jsUpdateForm()"
			class="custom-button"> <input type="button" value="삭제"
			onclick="jsDelete()" class="custom-button"> <input
			type="button" value="목록" onclick="jsback()" class="custom-button">
	</form>

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