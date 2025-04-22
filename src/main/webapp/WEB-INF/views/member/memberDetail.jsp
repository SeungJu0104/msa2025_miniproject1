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
<body data-context="${pageContext.request.contextPath}">
	<h1>회원 상세보기</h1>
	<div class="container">
		<table class="table table-striped-columns">
			<tbody>
				<tr>
					<td>회원번호</td>
					<td>1</td>
				</tr>
				<tr>
					<td>아이디</td>
					<td>Mark</td>
				</tr>
				<tr>
					<td>이름</td>
					<td>Otto</td>
				</tr>
				<tr>
					<td>휴대전화번호</td>
					<td>010-1111-2222</td>
				</tr>
				<tr>
					<td>우편번호</td>
					<td>05050</td>
				</tr>
				<tr>
					<td>주소</td>
					<td>서울시 송파구</td>
				</tr>
				<tr>
					<td>상세주소</td>
					<td>중대로 135</td>
				</tr>
				<tr>
					<td>취미</td>
					<td>수영</td>
				</tr>
				<tr>
					<td>가입일자</td>
					<td>2025-04-03</td>
				</tr>
				<tr>
					<td>탈퇴여부</td>
					<td>N</td>
				</tr>
				<tr>
					<td>탈퇴일시</td>
					<td>-</td>
				</tr>
				<tr>
					<td>회원 잠금 여부</td>
					<td>N</td>
				</tr>
				<tr>
					<td>로그인 실패 횟수</td>
					<td>0</td>
				</tr>
				<tr>
					<td>게시판 권한</td>
					<td>Y</td>
				</tr>
			</tbody>
		</table>
	</div>
</body>
</html>