<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>



<script type="text/javascript" src="<c:url value='/js/common.js'/>"></script>


<c:if test="${loginVO.username == null}">
	<div id="navigation"></div>
	<script>
		loadNavigation();
	</script>
</c:if>
<c:if test="${loginVO.username != null && loginVO.userid != 'admin'}">
	<div id="navigation2"></div>
	<label>로그인 유저 : ${loginVO.username}</label>
	<script>
			loadNavigation2();
		</script>
</c:if>
<c:if test="${loginVO.username != null && loginVO.userid == 'admin'}">
	<div id="navigation3"></div>
	<label>로그인 유저 : ${loginVO.username}</label>
	<script>
			loadNavigation3();
		</script>
</c:if>