<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script>
    // 페이지 로드 시 실행되는 JavaScript 코드
    window.onload = function() {
        // userid 값을 가져옴
        var userid = document.getElementById('userid').value;
        // mypagehome으로 이동하는 URL을 생성하고 userid 파라미터를 추가
        var url = 'user.do?action=mypagehome&userid=' + encodeURIComponent(userid);
        // 0초 후에 생성한 URL로 이동
        window.location.href = url;
    };
</script>
<title>Insert title here</title>
</head>
<body>
<input type="hidden" id="userid" name="userid" value="${loginVO.userid}">
</body>
</html>