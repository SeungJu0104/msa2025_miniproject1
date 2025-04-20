<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-SgOJa3DmI69IUzQ2PVdRZhwQ+dy64/BUtbMJw1MZ8t5HZApcHrRKUc4W0kG879m7" crossorigin="anonymous">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/mini.css">
<script src="${PageContext.request.pageContext }/resources/js/miniJs.js"></script>
<title>Insert title here</title>
</head>
<body>
	<h1>게시판 상세보기</h1>
	<div class="container">
		<table class="table table-striped-columns">
			<tbody>
				<tr>
				  <td>게시글 번호</td>
				  <td>1</td>
				</tr>
				<tr>
				  <td>게시판 종류</td>
				  <td>공지사항</td>
				</tr>
				<tr>
				  <td>제목</td>
				  <td>규칙</td>
				</tr>
				<tr>
				  <td>작성자</td>
				  <td>Mark</td>
				</tr>
				<tr>
				  <td>조회수</td>
				  <td>1</td>
				</tr>
				<tr>
				  <td>작성일</td>
				  <td>2025-04-03</td>
				</tr>
				<tr>
				  <td>삭제여부</td>
				  <td>N</td>
				</tr>
				<tr>
				  <td>삭제일</td>
				  <td>-</td>
				</tr>
			  </tbody>
		</table>
		
		<p>내용</p>
		<div id="boardDetail_content">
			규칙을 지켜주세요.
		</div>

		</div>
	</div>
	
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/js/bootstrap.bundle.min.js" integrity="sha384-k6d4wzSIapyDyv1kpU366/PK5hCdSbCRGRCMv+eplOQJWyd1fbcAu9OCUj5zNLiq" crossorigin="anonymous"></script>
	
</body>
</html>