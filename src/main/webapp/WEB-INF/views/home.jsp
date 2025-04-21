<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>Home</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-SgOJa3DmI69IUzQ2PVdRZhwQ+dy64/BUtbMJw1MZ8t5HZApcHrRKUc4W0kG879m7" crossorigin="anonymous">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/mini.css">
<script src="${pageContext.request.contextPath}/resources/js/miniJs.js"></script>
<c:if test="${not empty errorMsg }">
	<script type="text/javascript">
		alert("${errorMsg}");
	</script>
</c:if>
<!-- 나중에 별도 jsp로 빼서 코드 중복 줄이기? -->
</head>
<body data-context="${pageContext.request.contextPath}">
<div class="container">
	<nav class="navbar navbar-expand-lg bg-body-tertiary">
		<div class="container-fluid">
		  <a class="navbar-brand" id="navbar-home">커뮤니티</a>
		  <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
		    <span class="navbar-toggler-icon"></span>
		  </button>
		  <div class="collapse navbar-collapse" id="navbarNav">
		    <ul class="navbar-nav me-auto w-100" id="nav-items">
		      <li class="nav-item">
		        <a class="nav-link" href="#">K리그</a>
		      </li>
		      <li class="nav-item">
		        <a class="nav-link" href="#">EPL</a>
		      </li>
		      <li class="nav-item">
		        <a class="nav-link" href="#">분데스리가</a>
		      </li>
		      <li class="nav-item">
		        <a class="nav-link" href="#">라리가</a>
		      </li>
		      <li class="nav-item">
		        <a class="nav-link" href="#">자유게시판</a>
		      </li>
		    </ul>
		  </div>
		  <button class="btn btn-outline-success me-2" type="button">로그인</button>
		  <button class="btn btn-outline-success me-2" type="button">회원가입</button>
		</div>
	</nav>
</div>
</body>
</html>
