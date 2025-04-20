<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- css, js로 부트스트랩 붙이기 -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-SgOJa3DmI69IUzQ2PVdRZhwQ+dy64/BUtbMJw1MZ8t5HZApcHrRKUc4W0kG879m7" crossorigin="anonymous">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/mini.css">
<script src="${PageContext.request.pageContext }/resources/js/miniJs.js"></script>
<title>Insert title here</title>
</head>
<body>
	<h1>게시판 목록</h1>
	<div class="container">
		<table class="table table-striped">
			<thead>
			  <tr>
				<th scope="col">게시글 번호</th>
				<th scope="col">제목</th>
				<th scope="col">내용</th>
				<th scope="col">아이디</th>
				<th scope="col">작성 일시</th>
			  </tr>
			</thead>
			<tbody>
				<c:forEach items="${pageResponse.list }" var="val">
					<tr>
						<td>${val.postNo }</td>
						<td>${val.title }</td>
						<td>${val.content }</td>
						<td>${val.id }</td>
						<td>${val.createdAt }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<nav class="page_navigation" aria-label="page_navigation">
		<ul class="pagination">
		  <li class="page_item">
			<a class="page-link" href="#" aria-label="Previous">
			  <span aria-hidden="true">&laquo;</span>
			</a>
		  </li>
		  <li class="page_item"><a class="page-link" href="#">1</a></li>
		  <li class="page_item"><a class="page-link" href="#">2</a></li>
		  <li class="page_item"><a class="page-link" href="#">3</a></li>
		  <li class="page_item">
			<a class="page-link" href="#" aria-label="Next">
			  <span aria-hidden="true">&raquo;</span>
			</a>
		  </li>
		</ul>
	  </nav>
</body>
</html>