<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 상세보기</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-SgOJa3DmI69IUzQ2PVdRZhwQ+dy64/BUtbMJw1MZ8t5HZApcHrRKUc4W0kG879m7" crossorigin="anonymous">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/mini.css">
</head>
<body data-context="${pageContext.request.contextPath}">
	<c:if test="${not empty errorMsg }">
	<script type="text/javascript">
		alert("${errorMsg}");
	</script>
	</c:if>
	<h1>커뮤니티</h1>
	<div class="container">
		<h3>내 정보</h3>
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
			  </tr>
			</thead>
			<tbody>
				<c:if test="${empty item}">
					<tr>
						<td colspan="8" >검색결과가 없습니다</td>
					</tr>
				</c:if>
				    <tr>
				        <td>${item.memberNo}</td>
				        <td>${item.id}</td>
				        <td>${item.name}</td>
				        <td>${item.phoneNumber}</td>
				        <td>${item.postCode}</td>
						<td>${item.address}</td>
						<td>${item.detailAddress}</td>
						<td>${item.createdAt}</td>
				    </tr>
			</tbody>
		</table>
	</div>
	<script src="${pageContext.request.contextPath }/resources/js/miniJs.js"></script>
</body>
</html>