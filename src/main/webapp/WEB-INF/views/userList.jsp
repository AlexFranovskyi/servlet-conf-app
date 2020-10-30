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
	
		<h3><fmt:message key="listOfAccounts" /></h3>
	
		<table class="table table-striped">
			<thead>
			<tr>
				<th><fmt:message key="user" /></th>
				<th><fmt:message key="role" /></th>
			</tr>
			</thead>
			<tbody>
			
			<c:forEach var="user" items="${users}">
				<tr>
					<td>
					<span>${user.username}</span>
					</td>
					<td>
					<span>${user.role}</span>
					</td>
				</tr>
            </c:forEach>
			</tbody>
		</table>
	</div>
	</jsp:body>
</t:commonHtml>