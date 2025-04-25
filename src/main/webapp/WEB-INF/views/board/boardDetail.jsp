<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-SgOJa3DmI69IUzQ2PVdRZhwQ+dy64/BUtbMJw1MZ8t5HZApcHrRKUc4W0kG879m7" crossorigin="anonymous">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/mini.css">
<title>축구 게시판</title>
</head>
<body data-context="${pageContext.request.contextPath}">
	<jsp:include page="/WEB-INF/views/inc/showErr.jsp" />
	<h1>축구 게시판</h1>
	<jsp:include page="/WEB-INF/views/inc/menuBar.jsp" />
	<div class="container">
		<h3>게시글 상세</h3>
		<table class="table table-striped-columns">
			<tbody>
				<tr data-post-no="${post.postNo}">
				  <td>게시글 번호</td>
				  <td>${post.postNo}</td>
				</tr>
				<tr data-board-name="${post.boardName}">
				  <td>게시판 종류</td>
				  <td>${post.boardName}</td>
				</tr>
				<tr>
				  <td>제목</td>
				  <td>${post.title}</td>
				</tr>
				<tr data-post-id="${post.id}">
				  <td>작성자</td>
				  <td>${post.id}</td>
				</tr>
				<tr>
				  <td>조회수</td>
				  <td>${post.views}</td>
				</tr>
				<tr>
				  <td>작성일</td>
				  <td>${post.createdAt}</td>
				</tr>
			  </tbody>
		</table>
		
		<p>내용</p>
		<div id="boardDetail_content">
			${post.content}
		</div>
		<div id="boardUCBtn">
			<div class="d-flex justify-content-center gap-2 mt-4" >
				<button class="btn btn-outline-success boardDetail ">수정</button>
				<button class="btn btn-outline-danger boardDetail ">삭제</button>
			</div>
		</div>
	</div>
	<script src="${pageContext.request.contextPath }/resources/js/miniJs.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/js/bootstrap.bundle.min.js" integrity="sha384-k6d4wzSIapyDyv1kpU366/PK5hCdSbCRGRCMv+eplOQJWyd1fbcAu9OCUj5zNLiq" crossorigin="anonymous"></script>
</body>
</html>