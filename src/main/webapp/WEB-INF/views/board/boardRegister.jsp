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
        <h3>게시글 등록</h3>
        <form id="postRegister">
            <div class="mb-3">
                <label for="title" class="form-label">제목</label>
                <input type="text" class="form-control" id="boardTitle" name="title" placeholder="제목을 입력하세요" required>
            </div>
            <div class="mb-3">
                <label for="board" class="form-label">게시판</label>
                <select class="form-select form-select-sm" id="boardSelect" name="board">
                    <option selected>${board}</option>
                    <c:forEach items="${others}" var="ob">
                        <option value="${ob}">${ob}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="mb-3">
                <label for="content" class="form-label">내용</label>
                <textarea class="form-control" id="boardContent" name="content" rows="10" placeholder="내용을 입력하세요" required></textarea>
            </div>
            <div class="mb-3 row align-items-center">
                <label for="postPassword" class="col-auto col-form-label">게시물 비밀번호</label>
                <div class="col-auto">
                    <input type="password" id="postPassword" name="password" class="form-control" aria-describedby="passwordHelpInline" maxlength="4" required>
                </div>
                <div class="col-auto">
                    <span id="passwordHelpInline" class="form-text">
                        비밀번호는 숫자 4자리입니다.
                    </span>
                </div>
            </div>            
            <!-- <div class="row g-3 align-items-center">
                <div class="mb-3">
                  <label for="inputPassword" class="col-form-label">게시물 비밀번호</label>
                </div>
                <div class="col-auto">
                  <input type="password" id="postPassword" name="password" class="form-control" aria-describedby="passwordHelpInline" maxlength="4" required>
                </div>
                <div class="col-auto">
                  <span id="passwordHelpInline" class="form-text">
                    숫자 4자리를 입력해주세요.
                  </span>
                </div>
            </div>             -->
            <div class="d-flex flex-column align-items-center gap-2 mt-4">
                <button id="postRegBtn" class="btn btn-outline-success">게시글 등록</button>
            </div>
        </form>
    </div>
<script src="${pageContext.request.contextPath }/resources/js/miniJs.js"></script>
</body>
</html>