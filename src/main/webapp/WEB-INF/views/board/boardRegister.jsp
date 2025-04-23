<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 등록</title>
<!-- 외부 자바 스크립트 파일에 <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous"> 담기 -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-SgOJa3DmI69IUzQ2PVdRZhwQ+dy64/BUtbMJw1MZ8t5HZApcHrRKUc4W0kG879m7" crossorigin="anonymous">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/mini.css">
<script src="${PageContext.request.pageContext }/resources/js/miniJs.js"></script>
</head>
<body data-context="${pageContext.request.contextPath}">
    <h1>게시글 등록</h1>
    <div class="container">
        <form action="" method="post">
            <div class="mb-3">
                <label for="title" class="form-label">제목</label>
                <input type="text" class="form-control" id="board_title" name="title" placeholder="제목을 입력하세요">
            </div>
            <div class="mb-3">
                <label for="board" class="form-label">게시판 선택</label>
                <select class="form-select form-select-sm" id="board_select" name="board">
                    <option selected>게시판 선택</option>
                    <option value="notice">공지사항</option>
                    <option value="q&a">Q&A</option>
                    <option value="freeboard">자유 게시판</option>
                </select>
            </div>
            <div class="mb-3">
                <label for="content" class="form-label">내용</label>
                <textarea class="form-control" id="board_content" name="content" rows="10" placeholder="내용을 입력하세요"></textarea>
            </div>
            <button type="submit" class="btn-submit">게시글 등록</button>
        </form>
    </div>
</body>
</html>