<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="${pageContext.request.contextPath }/resources/js/miniJs.js"></script>
<nav class="page_navigation" aria-label="page_navigation">
	<ul class="pagination" id="page-items">
	<c:if test="${pageResponse.prev}">
		<li class="page-item">
			<a class="page-link" href="#" aria-label="Previous" data-page="${pageResponse.startPage - 1}">
				<span aria-hidden="true">&laquo;</span>
			</a>
		</li>
	</c:if>
	<c:forEach begin="${pageResponse.startPage}" end="${pageResponse.endPage}" var="pageNo">
		<li class="page-item">
			<a class="page-link" href="#" data-page="${pageNo}">
				<c:choose>
					<c:when test="${pageNo == pageResponse.pageNo}"><b>${pageNo}</b></c:when>
					<c:otherwise>${pageNo}</c:otherwise>
				</c:choose> 
			</a>
		</li>
	</c:forEach>
	<c:if test="${pageResponse.next}">
		<li class="page-item">
			<a class="page-link" href="#" aria-label="Next" data-page="${pageResponse.endPage + 1}">
				<span aria-hidden="true">&raquo;</span>
			</a>
		</li>
	</c:if>
	</ul>
</nav>