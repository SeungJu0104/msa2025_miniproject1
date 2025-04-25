<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${not empty param.errorMsg}">
	<script type="text/javascript">
		alert("${param.errorMsg}");
	</script>
</c:if>
