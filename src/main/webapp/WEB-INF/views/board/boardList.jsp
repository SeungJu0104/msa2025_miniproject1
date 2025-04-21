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
<script src="${pageContext.request.contextPath }/resources/js/miniJs.js"></script>
<title>게시판</title>
</head>
<body>
	<h1>게시판 목록</h1>
	<div class="container">
		<form name="searchForm" id="searchForm" action="/board/goBoard">
			<input type="hidden" name="pageNo" id="pageNo" value="${pageResponse.pageNo}">
			<select name="size" id="size">
				<c:forTokens items="10,30,50" delims="," var="size">
					<option value="${size}" ${pageResponse.size == size ? 'selected' : ''}>${size}</option>
				</c:forTokens>
			</select>
			<div class="row g-3 align-items-center">
				<div class="col-auto">
					<input type="text" id="searchValue" name="searchValue" class="form-control" value="${searchValue }"aria-describedby="searchValue">
					<button class="btn btn-outline-success me-2" type="submit">검색</button>
				</div>
    		</div>
		</form>
		<table class="table table-striped">
			<thead>
			  <tr>
				<th scope="col">게시글 번호</th>
				<th scope="col">게시판</th>
				<th scope="col">제목</th>
				<th scope="col">아이디</th>
				<th scope="col">조회수</th>
				<th scope="col">작성 일시</th>
			  </tr>
			</thead>
			<tbody>
				<c:if test="${empty pageResponse.list}">
					<tr>
						<td colspan="6" >검색결과가 없습니다</td>
					</tr>
				</c:if>
				<c:forEach items="${pageResponse.list}" var="item">
				    <tr>
				        <td>${item.postNo}</td>
				        <td>${item.boardName}</td>
				        <td>${item.title}</td>
				        <td>${item.id}</td>
				        <td>${item.views}</td>
				        <td>${item.createdAt}</td>
				    </tr>
				</c:forEach>
			</tbody>
		</table>
		<c:import url="/WEB-INF/views/inc/pageNav.jsp" />
	</div>
</body>
</html>