<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-SgOJa3DmI69IUzQ2PVdRZhwQ+dy64/BUtbMJw1MZ8t5HZApcHrRKUc4W0kG879m7" crossorigin="anonymous">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/mini.css">
<title>게시글</title>
</head>
<body data-context="${pageContext.request.contextPath}">
	<c:if test="${not empty errorMsg }">
	<script type="text/javascript">
		alert("${errorMsg}");
	</script>
	</c:if>
	<h1>게시글</h1>
	<div class="container">
		<form id="updatePost">
			<input type="hidden" name="board" value="${post.boardName}">
			<input type="hidden" name="postNo" value="${post.postNo}">
		<table class="table table-striped-columns">
			<tbody>
				<tr>
				  <td>게시글 번호</td>
				  <td>${post.postNo}</td>
				</tr>
				<tr>
				  <td>게시판 종류</td>
				  <td>${post.boardName}</td>
				</tr>
				<tr>
				  <td>제목</td>
				  <td><input name="title" id="postTitle" value="${post.title}"></td>
				</tr>
				<tr>
				  <td>작성자</td>
				  <td><input name="writer" id="postWriter" value="${post.writer}"></td>
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
		
		<div class="mb-3">
			<label for="exampleFormControlTextarea1" class="form-label">내용</label>
			<textarea class="form-control" id="exampleFormControlTextarea1" rows="10" id="postContent" name="content">${post.content}</textarea>
		  </div>
		<div class="d-flex flex-column align-items-center gap-2 mt-4">
			<button class="btn btn-primary col-6" type="submit">수정</button>
		</div>
		</form>
	</div>
<script src="${pageContext.request.contextPath}/resources/js/miniJs.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/js/bootstrap.bundle.min.js" integrity="sha384-k6d4wzSIapyDyv1kpU366/PK5hCdSbCRGRCMv+eplOQJWyd1fbcAu9OCUj5zNLiq" crossorigin="anonymous"></script>
</body>
</html>