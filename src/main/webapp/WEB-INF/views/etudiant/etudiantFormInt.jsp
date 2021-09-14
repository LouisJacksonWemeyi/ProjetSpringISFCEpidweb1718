<!DOCTYPE html>
<%@ page session="false" language="java"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<html>
<jsp:include page="../fragments/header.jsp">
	<jsp:param name="titre" value="Formulaire Ajout Etudiant ISFCE" />
</jsp:include>

<div class="container">

  <a href="ajoutform?locale=fr">Français</a> | <a href="ajoutform?locale=en">English</a>

	<c:choose>
		<c:when test="${empty savedId}">
			<h1><s:message code="etudiant.ajout"/></h1>
		</c:when>
		<c:otherwise>
			<h1><s:message code="etudiant.ajours"/></h1>
		</c:otherwise>
	</c:choose>
	<br />

	<spring:url value="/etudiant/addEtudiant" var="etudiantActionUrl" />

	<form:form class="form-horizontal" method="post"
                modelAttribute="etudiantForm" action="${etudiantActionUrl}">

		
		<spring:bind path="username">
		  <div class="form-group ${status.error ? 'has-error' : ''}">
			<label path="username" class="col-sm-2 control-label"><s:message code="etudiant.user"/></label>
			<div class="col-sm-10">
				<form:input path="username" type="text" class="form-control"
                                id="username" placeholder="username" />
				<form:errors path="username" class="control-label" />
			</div>
		  </div>
		</spring:bind>
		
		<spring:bind path="password">
		  <div class="form-group ${status.error ? 'has-error' : ''}">
			<label class="col-sm-2 control-label"><s:message code="etudiant.pass"/></label>
			<div class="col-sm-10">
				<form:password path="password" class="form-control"
                                id="password" placeholder="password" />
				<form:errors path="password" class="control-label" />
			</div>
		  </div>
		</spring:bind>

		<spring:bind path="nom">
		  <div class="form-group ${status.error ? 'has-error' : ''}">
			<label class="col-sm-2 control-label"><s:message code="etudiant.nom"/></label>
			<div class="col-sm-10">
				<form:input path="nom" type="text" class="form-control"
                                id="nom" placeholder="nom" />
				<form:errors path="nom" class="control-label" />
			</div>
		  </div>
		</spring:bind>
		
		<spring:bind path="prenom">
		  <div class="form-group ${status.error ? 'has-error' : ''}">
			<label class="col-sm-2 control-label"><s:message code="etudiant.prenom"/></label>
			<div class="col-sm-10">
				<form:input path="prenom" class="form-control"
                                id="prenom" placeholder="prenom" />
				<form:errors path="prenom" class="control-label" />
			</div>
		  </div>
		</spring:bind>

		<spring:bind path="email">
		  <div class="form-group ${status.error ? 'has-error' : ''}">
			<label class="col-sm-2 control-label"><s:message code="etudiant.mail"/></label>
			<div class="col-sm-10">
				<form:input path="email" class="form-control"
                                id="email" placeholder="email" />
				<form:errors path="email" class="control-label" />
			</div>
		  </div>
		</spring:bind>

		<spring:bind path="tel">
		  <div class="form-group ${status.error ? 'has-error' : ''}">
			<label class="col-sm-2 control-label"><s:message code="etudiant.tel"/></label>
			<div class="col-sm-10">
				<form:textarea path="tel" rows="5" class="form-control"
                                id="tel" placeholder="tel" />
				<form:errors path="tel" class="control-label" />
			</div>
		  </div>
		</spring:bind>

		<div class="form-group">
		  <div class="col-sm-offset-2 col-sm-10">
			<c:choose>
			  <c:when test="${empty savedId}">
			     <button type="submit" class="btn-lg btn-primary pull-right"><s:message code="etudiant.btajout"/></button>
			  </c:when>
			  <c:otherwise>
			     <button type="submit" class="btn-lg btn-primary pull-right"><s:message code="etudiant.btvalide"/></button>
			  </c:otherwise>
			</c:choose>
		  </div>
		</div>
	</form:form>

</div>

<jsp:include page="../fragments/footer.jsp" />

</body>
</html>