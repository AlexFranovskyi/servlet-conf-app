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
	
		<c:choose>
		    <c:when test="${sessionScope.role=='ADMIN'}">
		   		<div class="mb-2"><h4><fmt:message key="adminCabinet" /></h4></div>
		    </c:when>    
		    <c:when test="${sessionScope.role=='SPEAKER'}">
		        <div class="mb-2"><h4><fmt:message key="speakerCabinet" /></h4></div>
		    </c:when>
		    <c:when test="${sessionScope.role=='USER'}">
		        <div class="mb-2"><h4><fmt:message key="userPage" /></h4></div>
		    </c:when>
		</c:choose>
		
		<%@ include file="/WEB-INF/views/jspf/conferenceList.jspf" %>
			
		<c:if test="${sessionScope.role=='ADMIN'}">
			<a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
		  		<fmt:message key="createNewEvent" />
		  	</a>
		  	
			<div class="collapse" id="collapseExample">
				<div class="form-group mt-3">
			    	<form method="post" action="post_conference">
			    		<div class="form-group my-2 w-50">
			            	<input type="text" class="form-control" name="name" required placeholder="<fmt:message key='name' />"/>
			    		</div>
			            <div class="form-group my-2 w-50">
			            	<input type="text" class="form-control" name="localDateTime" required placeholder="yyyy-mm-dd hh:mm"/>
			    		</div>
			    		<div class="form-group my-2 w-50">
			            	<input type="text" class="form-control" name="location" required placeholder="<fmt:message key='location' />"/>
			    		</div>	            
			            <div class="form-group my-2">
				            <button type="submit" class="btn btn-primary"><fmt:message key="postNewConference" /></button>
			            </div>
			        </form>
			    </div>
			</div>
			
		  	<div><strong> <c:if test="${requestScope.message != null}"> <fmt:message key="${requestScope.message}" /></c:if></strong></div>
		</c:if>
	
	</div>
	</jsp:body>
</t:commonHtml>