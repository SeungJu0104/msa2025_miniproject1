<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<nav class="page_navigation" aria-label="page_navigation">
	<ul class="pagination">
	<c:if test="${pageResponse.prev}">
		<li class="page_item">
			<a class="page-link" href="#" aria-label="Prev"><!-- 외부 자바 스크립트로 동작 설정 -->
				<span aria-hidden="true">&laquo;</span>
			</a>
		</li>
	</c:if>
	<c:forEach begin="${pageResponse.startPage}" end="${pageResponse.endPage}" var="pageNo">
		<li class="page_item">
			<a class="page-link" href="javascript:pageMove(${pageNo })">
				<c:choose>
					<c:when test="${pageNo == pageResponse.pageNo}"><b>${pageNo}</b></c:when>
					<c:otherwise>${pageNo}</c:otherwise>
				</c:choose> 
			</a>
		</li>
	</c:forEach>
	<c:if test="${pageResponse.next}">
		<li class="page_item">
			<a class="page-link" href="#" aria-label="Next"><!-- 외부 자바 스크립트로 동작 설정 -->
				<span aria-hidden="true">&laquo;</span>
			</a>
		</li>
	</c:if>
		
		<!-- <li class="page_item"><a class="page-link" href="#">2</a></li>
		<li class="page_item"><a class="page-link" href="#">3</a></li>
		<li class="page_item">
			<a class="page-link" href="#" aria-label="Next">
				<span aria-hidden="true">&raquo;</span>
			</a>
		</li> -->
	</ul>
</nav>