<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-SgOJa3DmI69IUzQ2PVdRZhwQ+dy64/BUtbMJw1MZ8t5HZApcHrRKUc4W0kG879m7" crossorigin="anonymous">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/mini.css">
<script src="${PageContext.request.pageContext }/resources/js/miniJs.js"></script>
</head>
<body>
	<h1>회원 목록</h1>
	<div class="container">
		<table class="table table-striped">
			<thead>
			  <tr>
				<th scope="col">회원번호</th>
				<th scope="col">아이디</th>
				<th scope="col">이름</th>
				<th scope="col">주소</th>
				<th scope="col">상세 주소</th>
				<th scope="col">휴대전화번호</th>
			  </tr>
			</thead>
			<tbody>
			  <tr>
				<td>1</td>
				<td>Mark</td>
				<td>Otto</td>
				<td>서울 송파구</td>
				<td>중대로 135</td>
				<td>010-1111-2222</td>
			  </tr>
			</tbody>
		</table>
	</div>

	<nav class="page_navigation" aria-label="page_navigation">
		<ul class="pagination">
		  <li class="page-item">
			<a class="page-link" href="#" aria-label="Previous">
			  <span aria-hidden="true">&laquo;</span>
			</a>
		  </li>
		  <li class="page-item"><a class="page-link" href="">1</a></li>
		  <li class="page-item"><a class="page-link" href="">2</a></li>
		  <li class="page-item"><a class="page-link" href="">3</a></li>
		  <li class="page-item">
			<a class="page-link" href="#" aria-label="Next">
			  <span aria-hidden="true">&raquo;</span>
			</a>
		  </li>
		</ul>
	  </nav>
</body>
</html>