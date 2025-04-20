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
  <h1>회원 정보 수정</h1>
  <div class="container">
    <form action="" method="post">
        <div class="mb-3">
          <label for="userid" class="form-label">아이디</label>
          <input type="text" class="form-control" id="userid" name="userid" placeholder="아이디">
        </div>
        <div class="mb-3">
          <label for="password" class="form-label">비밀번호</label>
          <input type="password" class="form-control" id="password" name="password" placeholder="비밀번호">
          <input type="password" class="form-control" id="password_valid" name="password_valid" placeholder="비밀번호확인">
        </div>
        <div class="mb-3">
          <label for="name" class="form-label">이름</label>
          <input type="text" class="form-control" id="name" name="name" placeholder="이름">
        </div>
        <div class="mb-3">
          <label for="email" class="form-label">이메일주소</label>
          <input type="email" class="form-control" id="email" name="email" placeholder="이메일주소">
        </div>
        <div class="mb-3">
          <label for="birthday" class="form-label">생년월일</label>
          <input type="date" class="form-control" id="birthday" name="birthday" placeholder="생년월일">
        </div>
        <div class="mb-3">
            <input type="radio" class="btn-radio" id="btn-male" name="sex" autocomplete="off" value="M" checked>
            <label class="btn btn-primary btn-radio-label" for="btn-male">남자</label>

            <input type="radio" class="btn-radio" id="btn-female" name="sex" autocomplete="off" value="F">
            <label class="btn btn-primary btn-radio-label" for="btn-female">여자</label>
        </div>
        <div class="mb-3">
          <label for="handphone" class="form-label">휴대전화번호</label>
          <input type="tel" class="form-control" id="handphone" name="handphone" placeholder="휴대전화번호">
        </div>
        <div class="mb-3">
            <label for="post_code" class="form-label">우편번호</label>
            <input type="text" class="form-control" id="post_code" name="post_code" placeholder="우편번호">
        </div>
        <div class="mb-3">
            <label for="address" class="form-label">주소</label>
            <input type="text" class="form-control" id="address" name="address" placeholder="주소">
        </div>
        <div class="mb-3">
            <label for="detail_address" class="form-label">상세 주소</label>
            <input type="text" class="form-control" id="detail_address" name="detail_address" placeholder="상세 주소">
        </div>
        <div class="form-check">
          <input type="checkbox" name="hobby" value="swimming" id="swimming">
          <label for="swimming">수영</label>

          <input type="checkbox" name="hobby" value="biking" id="biking">
          <label for="biking">자전거</label>

          <input type="checkbox" name="hobby" value="hiking" id="hiking">
          <label for="hiking">등산</label>

          <input type="checkbox" name="hobby" value="jogging" id="jogging">
          <label for="jogging">조깅</label>

          <input type="checkbox" name="hobby" value="running" id="running">
          <label for="running">런닝</label>
        </div>
        <button type="submit" class="btn btn-primary btn-submit" id="memberUpdate">전송</button>
    </form>
  </div>
</body>
</html>