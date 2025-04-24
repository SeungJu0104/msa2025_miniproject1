<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <nav class="navbar navbar-expand-lg bg-body-tertiary">
        <div class="container-fluid">
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav me-auto w-100" id="nav-items">
            <li class="nav-item">
                <a class="nav-link" href="#">K리그</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">EPL</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">분데스리가</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">라리가</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">자유게시판</a>
            </li>
            <li class="nav-item" id="memberList">
                <a class="nav-link" href="#">회원목록</a>
            </li>
            </ul>
        </div>
        <button class="btn btn-outline-success me-2 nologin" id="goLogin" type="button">로그인</button>
        <button class="btn btn-outline-success me-2 login" id="goLogOut" type="button" >로그아웃</button>
        <button class="btn btn-outline-success me-2 login" id="updateMember" type="button">정보수정</button>
        <button class="btn btn-outline-success me-2 login" id="detailMember" type="button">상세보기</button>
        <button class="btn btn-outline-success me-2 nologin" id="goRegister" type="button">회원가입</button>
        </div>
    </nav>

<script src="${pageContext.request.contextPath}/resources/js/miniJs.js"></script>