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
		<h3>내 정보</h3>
		<table class="table table-striped-columns">
			<tbody>
				<tr>
				  <td>회원 번호</td>
				  <td>${item.memberNo}</td>
				</tr>
				<tr>
				  <td>아이디</td>
				  <td>${item.id}</td>
				</tr>
				<tr>
				  <td>이름</td>
				  <td>${item.name}</td>
				</tr>
				<tr>
				  <td>전화번호</td>
				  <td>${item.phoneNumber}</td>
				</tr>
				<tr>
				  <td>우편번호</td>
				  <td>${item.postCode}</td>
				</tr>
				<tr>
				  <td>주소</td>
				  <td>${item.address}</td>
				</tr>
				<tr>
					<td>상세 주소</td>
					<td>${item.detailAddress}</td>
				</tr>
				<tr>
					<td>가입일</td>
					<td>${item.createdAt}</td>
				</tr>				  				
			  </tbody>
		</table>
		<div class="text-center my-2 boardReg">
			<button class="btn btn-outline-success me-2 login" id="updateMember" type="button">정보수정</button>
			<button type="button" class="btn btn-outline-danger" id="memberDel" type="button">탈퇴</button>
		</div>
	</div>
	<script src="${pageContext.request.contextPath }/resources/js/miniJs.js"></script>
</body>
</html>