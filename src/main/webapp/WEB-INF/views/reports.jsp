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
	
		<div><strong><c:if test="${requestScope.message != null}"> <fmt:message key="${requestScope.message}" /></c:if></strong></div>

			<div class="form-row mt-2">
				<div class="form-group">
				    <form method="get" action="reports" class="form-inline">
				    <input type="hidden" name="conferenceId" value="${requestScope.conferenceId}">
				    	<button type="submit" class="btn btn-primary mr-5"><fmt:message key="showReports" /></button>
				    </form>
				</div>
			</div>
			
		<%@ include file="/WEB-INF/views/jspf/reportList.jspf" %>
		
	<a class="btn btn-primary mt-4" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
	  	<fmt:message key="createNewReport" />
	</a>
	  	
		<div class="collapse" id="collapseExample">
			<div class="form-group mt-3">
		    	<form method="post" action="post_report">
		    		<input type="hidden" name="conferenceId" value="${requestScope.conferenceId}">
		    		<div class="form-group my-2 w-50">
		            	<input type="text" class="form-control" name="name" required placeholder="<fmt:message key="reportName" />"/>
		    		</div>
		            <div class="form-group my-2">
			            <button type="submit" class="btn btn-primary"><fmt:message key="postNewReport" /></button>
		            </div>
		        </form>
		    </div>
		</div>
	
	</div>
	</jsp:body>
</t:commonHtml>