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
    <h3>회원 정보 수정</h3>
    <form id="updateForm">
        <div class="mb-3">
          <label for="userid" class="form-label">아이디</label>
          <input type="text" class="form-control" id="userid" name="id" readonly="readonly" value="${member.id}">
        </div>
        <div class="mb-3">
          <label for="password" class="form-label">비밀번호</label>
          <input type="password" class="form-control pwChk" id="password" name="password" placeholder="비밀번호" value="${member.password}" required>
          <input type="password" class="form-control mt-2 pwChk" id="passwordValid" placeholder="비밀번호확인" required>
          <small class="pwInfo" style="display : none">비밀번호는 8~12자 사이이며, 영문자, 숫자, 특수문자를 각각 1개 이상 포함해야 합니다.</small>
          <small class="pwInfo2" style="display : none">비밀번호와 비밀번호 확인이 일치하지 않습니다.</small>
        </div>
        <div class="mb-3">
          <label for="name" class="form-label">이름</label>
          <input type="text" class="form-control" id="name" name="name" value="${member.name}" required>
        </div>
        <div class="mb-3">
          <label for="handphone" class="form-label">전화번호</label>
          <input type="tel" class="form-control" id="handphone" name="phoneNumber" value="${member.phoneNumber}" required maxlength="13">
          <small class="phoneInfo" style="display : none">010-0000-0000 형식으로 입력해주세요.</small>
        </div>
        <div class="mb-3">
            <label for="post_code" class="form-label">우편번호</label>
            <input type="text" class="form-control" id="postCode" name="postCode" value="${member.postCode}" required maxlength="5">
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
          <button type="submit" class="btn btn-primary col-6" id="memberUpdate">수정</button>
        </div>
    </form>
  </div>
  <script src="${pageContext.request.contextPath }/resources/js/miniJs.js"></script>
</body>
</html>