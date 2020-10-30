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
	
		<c:if test="${requestScope.message != null}">
		<p><fmt:message key="${requestScope.message}" /></p>
		</c:if>
	
		<form action="login" method="post">
        <div class="form-group row">
	        <label class="col-sm-2 col-form-label"> <fmt:message key="username" />: </label>
	        <div class="col-sm-6">
	        	<input type="text" name="username" class="form-control" required/>
	        </div>
        </div>
        
        <div class="form-group row">
        	<label class="col-sm-2 col-form-label"> <fmt:message key="password" />: </label>
	        <div class="col-sm-6">
	        	<input type="password" name="password" class="form-control" required/>
	        </div>
        </div>        
        <button class="btn btn-primary mr-3" type="submit"> <fmt:message key="signIn" /> </button>
        <a href="registration"> <fmt:message key="register" /> </a>
    </form>
	</div>
	</jsp:body>
</t:commonHtml>