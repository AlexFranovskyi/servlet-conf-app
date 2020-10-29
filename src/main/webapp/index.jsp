<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<%@ page session="true" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<t:commonHtml>
	<jsp:body>
		<div class="mb-5">
			<%@ include file="/WEB-INF/views/jspf/navbar.jspf" %>
		</div>
		
	<div class="container">
		<%@ include file="/WEB-INF/views/jspf/task.jspf" %>
	
		<c:if test="${sessionScope.role == 'GUEST'}">
        	<Strong><fmt:message key="messageForGuest" /> <a href="${pageContext.request.contextPath}/authentication"><fmt:message key="signInProposal" /></a></Strong>
        </c:if>
	</div>
	</jsp:body>
</t:commonHtml>
