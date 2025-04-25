<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>축구 게시판</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-SgOJa3DmI69IUzQ2PVdRZhwQ+dy64/BUtbMJw1MZ8t5HZApcHrRKUc4W0kG879m7" crossorigin="anonymous">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/mini.css">
</head>
<body data-context="${pageContext.request.contextPath}">
	<jsp:include page="/WEB-INF/views/inc/showErr.jsp" />
	<h1>축구 게시판</h1>
	<jsp:include page="/WEB-INF/views/inc/menuBar.jsp" />
	<div class="container">
		<h3>회원 목록</h3>
		<form id="searchForm" class="d-flex align-items-center gap-2 mb-3 flex-wrap">
			<input type="hidden" name="pageNo" id="pageNo" value="${pageResponse.pageNo}">
			<input type="hidden" name="type" id="type" value="member">
			<div class="input-group" style="width: 250px;">
				<input type="text" id="searchValue" name="searchValue" class="form-control" value="${param.searchValue}" placeholder="아이디를 입력해주세요.">
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
			  	<th scope="col">회원 번호</th>
				<th scope="col">아이디</th>
				<th scope="col">이름</th>
				<th scope="col">전화번호</th>
				<th scope="col">우편번호</th>
				<th scope="col">주소</th>
				<th scope="col">상세 주소</th>
				<th scope="col">가입일</th>
				<th scope="col">잠금</th>
				<th scope="col">권한</th>
			  </tr>
			</thead>
			<tbody>
				<c:if test="${empty pageResponse.list}">
					<tr>
						<td colspan="10" >검색결과가 없습니다</td>
					</tr>
				</c:if>
				<c:forEach items="${pageResponse.list}" var="item">
				    <tr>
				        <td>${item.memberNo}</td>
				        <td>${item.id}</td>
				        <td>${item.name}</td>
				        <td>${item.phoneNumber}</td>
				        <td>${item.postCode}</td>
						<td>${item.address}</td>
						<td>${item.detailAddress}</td>
						<td>${item.createdAt}</td>
						<td>
						    <input class="lockYn" type="checkbox" ${item.lockYn == 'Y' ? 'checked' : ''}/>
						</td>
						<td>${item.boardAuth}</td>
				    </tr>
				</c:forEach>
			</tbody>
		</table>
		<c:import url="/WEB-INF/views/inc/pageNav.jsp" />
	</div>
	<script src="${pageContext.request.contextPath }/resources/js/miniJs.js"></script>
</body>
</html>