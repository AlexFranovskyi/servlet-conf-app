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
	
		<div class="mb-2"><h4>${conferenceDetails}</h4></div>
		<strong><c:if test="${requestScope.message != null}"> <fmt:message key="${requestScope.message}" /></c:if></strong>
		
		<c:choose>
			<c:when test="${requestScope.conference != null}">
				<%@ include file="/WEB-INF/views/jspf/conferenceItem.jspf" %>
			</c:when>
			<c:otherwise>
				<h4><fmt:message key="nothingToDisplay" /></h4>
			</c:otherwise>
		</c:choose>
		
	</div>
	</jsp:body>
</t:commonHtml>