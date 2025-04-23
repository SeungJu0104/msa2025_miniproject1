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
</head>
<body data-context="${pageContext.request.contextPath}">
  <h1>회원 정보 수정</h1>
  <div class="container">
    <form id="updateForm">
        <div class="mb-3">
          <label for="userid" class="form-label">아이디</label>
          <input type="text" class="form-control" id="userid" name="id" readonly="readonly" value="${member.id}">
        </div>
        <div class="mb-3">
          <label for="password" class="form-label">비밀번호</label>
          <input type="password" class="form-control" id="password" name="password" value="${member.password}" required>
        </div>
        <div class="mb-3">
          <label for="name" class="form-label">이름</label>
          <input type="text" class="form-control" id="name" name="name" value="${member.name}" required>
        </div>
        <div class="mb-3">
          <label for="handphone" class="form-label">전화번호</label>
          <input type="tel" class="form-control" id="handphone" name="phoneNumber" value="${member.phoneNumber}" required>
        </div>
        <div class="mb-3">
            <label for="post_code" class="form-label">우편번호</label>
            <input type="text" class="form-control" id="postCode" name="postCode" value="${member.postCode}" required>
        </div>
        <div class="mb-3">
            <label for="address" class="form-label">주소</label>
            <input type="text" class="form-control" id="address" name="address" value="${member.address}" required>
        </div>
        <div class="mb-3">
            <label for="detail_address" class="form-label">상세 주소</label>
            <input type="text" class="form-control" id="detailAddress" name="detailAddress" value="${member.detailAddress}" required>
        </div>
        <!-- <label for="address" class="form-label">취미</label>
        <div class="form-check">
          <input type="checkbox" name="hobbyName" value="reading" id="reading">
          <label for="reading">독서</label>

          <input type="checkbox" name="hobbyName" value="trip" id="trip">
          <label for="biking">여행</label>

          <input type="checkbox" name="hobbyName" value="game" id="game">
          <label for="hiking">게임</label>

          <input type="checkbox" name="hobbyName" value="cooking" id="cooking">
          <label for="jogging">요리</label>

          <input type="checkbox" name="hobbyName" value="exercies" id="exercise">
          <label for="running">운동</label>
        </div> -->
        <div class="d-flex flex-column align-items-center gap-2 mt-4">
          <button type="submit" class="btn btn-primary col-6" id="memberUpdate">전송</button>
          <button type="button" class="btn btn-outline-danger col-6" id="memberDel">삭제</button>
        </div>

    </form>
  </div>
  <script src="${pageContext.request.contextPath }/resources/js/miniJs.js"></script>
</body>
</html>