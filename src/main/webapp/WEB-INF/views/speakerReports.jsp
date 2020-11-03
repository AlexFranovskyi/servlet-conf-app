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
	
		<h2><fmt:message key="speakerReports" /></h2>
		
		<c:choose>
		<c:when test="${requestScope.page != null && requestScope.page.getList().size() != 0}">
	    	<table class="table table-striped">
			  <thead>
			    <tr>
			      <th scope="col"><fmt:message key="reportName" /></th>
			      <th scope="col"><fmt:message key="conferenceName" /></th>
			      <th scope="col"><fmt:message key="status" /></th>
			    </tr>
			  </thead>
			  <tbody>
			  	<c:forEach var="report" items="${requestScope.page.getList()}">
			    	<tr>
				      <td>${report.name}</td>
				      <td>${report.conferenceName}</td>
				      
				      <td>
				      	<c:choose>
					     	 <c:when test="${report.approved == 'true'}">
				      			<fmt:message key="approved" />
				      		 </c:when>
				      		 <c:otherwise>
				      		 	<fmt:message key="notApproved" />
				      		 </c:otherwise>
				      	  </c:choose>
				      </td>
				    </tr>
			  	</c:forEach>
			   </tbody>
			</table>
			<%@ include file="jspf/paginator.jspf" %>
		</c:when>
		<c:otherwise>
			<div>
				<b><fmt:message key="noReportsToDisplay" /></b>
			</div>
		</c:otherwise>
	</c:choose>
		
	</div>
	</jsp:body>
</t:commonHtml>