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
	<h1>축구 게시판</h1>
	<jsp:include page="/WEB-INF/views/inc/menuBar.jsp" />
	<div class="container">
	<h3>${pageResponse.board}</h3>
	<form name="searchForm" id="searchForm" class="d-flex align-items-center gap-2 mb-3 flex-wrap">
		<input type="hidden" name="board" id="board" value="${pageResponse.board}">
		<input type="hidden" name="pageNo" id="pageNo" value="${pageResponse.pageNo}">
		<input type="hidden" name="type" value="board">
		<div class="input-group" style="width: 250px;">
			<input type="text" id="searchValue" name="searchValue" class="form-control" 
					value="${param.searchValue}" placeholder="내용을 입력해주세요.">
			<button class="btn btn-outline-success" type="submit">검색</button>
		</div>
		<select name="size" id="size" class="form-select form-select-sm w-auto">
			<c:forTokens items="10,30,50" delims="," var="size">
				<option value="${size}" ${pageResponse.size == size ? 'selected' : ''}>${size}</option>
			</c:forTokens>
		</select>
  	</form>  
		<table class="table table-striped">
			<thead>
			  <tr>
				<th scope="col">번호</th>
				<th scope="col">제목</th>
				<th scope="col">게시판</th>
				<th scope="col">작성자</th>
				<th scope="col">조회수</th>
				<th scope="col">작성 일시</th>
			  </tr>
			</thead>
			<tbody>
				<c:if test="${empty pageResponse.list}">
					<tr>
						<td colspan="7" >검색결과가 없습니다</td>
					</tr>
				</c:if>
				<c:forEach items="${pageResponse.list}" var="item" varStatus="status">
				    <tr class="rows" data-post-no="${item.postNo}" data-board-name="${item.boardName}" data-post-id="${item.id}">
				    	<td id="postNum">${pageResponse.totalCount - ((pageResponse.pageNo - 1) * pageResponse.size + status.count) + 1}</td>
				        <td>${item.title}</td>
						<td>${item.boardName}</td>
				        <td>${item.id}</td>
				        <td>${item.views}</td>
				        <td>${item.createdAt}</td>
				    </tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="text-start my-2 boardReg">
			<button class="btn btn-outline-success" type="button">게시글 작성</button>
		</div>
		<c:import url="/WEB-INF/views/inc/pageNav.jsp" />
	</div>
<script src="${pageContext.request.contextPath }/resources/js/miniJs.js"></script>
</body>
</html>