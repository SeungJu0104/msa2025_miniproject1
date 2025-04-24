<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-SgOJa3DmI69IUzQ2PVdRZhwQ+dy64/BUtbMJw1MZ8t5HZApcHrRKUc4W0kG879m7" crossorigin="anonymous">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/mini.css">
</head>
<body data-context="${pageContext.request.contextPath}">
    <c:if test="${not empty errorMsg }">
        <script type="text/javascript">
            alert("${errorMsg}");
        </script>
        </c:if>
	<h1>로그인</h1>
	<div class="container">
        <form id="loginForm">
            <div class="form-group">
                <label for="login_id" class="col-form-label">아이디</label>
                <input type="text" id="login_id" name="id" class="form-control">
            </div>           
            <div class="form-group">
                <label for="login_password" class="col-form-label">비밀번호</label>
                <input type="password" id="login_password" name="password" class="form-control">
            </div>
            <div class="form-group">
                <button type="submit" class="btn btn-primary col-6" id="login">로그인</button>
            </div>
        </form>
    </div>
<script src="${pageContext.request.contextPath }/resources/js/miniJs.js"></script>
</body>
</html>