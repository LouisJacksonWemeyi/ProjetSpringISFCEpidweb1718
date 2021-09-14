<!DOCTYPE html>
<%@ page session="false" language="java"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%-- <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> --%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://www.springframework.org/security/tags"  prefix="security"%>



<html>
<jsp:include page="../fragments/header.jsp">
	<jsp:param name="titre" value="Liste Etudiants ISFCE" />
</jsp:include>

<div class="container">

	<c:if test="${not empty msg}">
		<div class="alert alert-${css} alert-dismissible" role="alert">
			<button type="button" class="close" data-dismiss="alert"
                                aria-label="Close">
				<span aria-hidden="true">×</span>
			</button>
			<strong>${msg}</strong>
		</div>
	</c:if>

	<h1>Liste des étudiants</h1>

		<c:if test="${fn:length(etudiants) == 0}">
			<h2>Liste Vide</h2>
		</c:if>

	<h2>La liste contient: ${fn:length(etudiants)} étudiants</h2>
	
	
	<table class="table table-striped">
			<thead>
				<tr>
					<th>username</th>
					<th>Nom</th>
					<th>Prenom</th>
					<th>Email</th>
					<th>Telephone</th>
					<th>Action</th>
				</tr>
			</thead>
			<c:forEach var="etudiant" items="${etudiants}">
			    <tr>
					<td>${etudiant.username}</td>
					<td>${etudiant.nom}</td>
					<td>${etudiant.prenom}</td>
					<td>${etudiant.email}</td>
					<td>${etudiant.tel}</td>
					<td>
				  		<spring:url value="/etudiant/${etudiant.username}" var="etudiantUrl" />
				  		<spring:url value="/etudiant/${etudiant.username}/delete" var="deleteUrl" />
				  		<spring:url value="/etudiant/${etudiant.username}/update" var="updateUrl" />

				  		<button class="btn btn-info"
                                          onclick="location.href='${etudiantUrl}'">Detail</button>
                       <security:authorize access="isAuthenticated()">
                       <security:authorize access="hasRole('ADMIN')">
				  		<button class="btn btn-primary"
                                          onclick="location.href='${updateUrl}'">Update</button>
				  		<button class="btn btn-danger"
                                          onclick="if (confirm('Suppression de l\'etudiant ${etudiant.username}?')) {
				        this.disabled=true;post('${deleteUrl}',{'${_csrf.parameterName}': '${_csrf.token}'})}">Delete</button>
				       </security:authorize>
                       </security:authorize>
                   </td>
			    </tr>
			</c:forEach>
		</table>
  		
  		<spring:url value = "/etudiant/ajoutform" var="etudiantajoutUrl"/>
  		<security:authorize access="isAuthenticated()">
        <security:authorize access="hasRole('ADMIN')">
		<button class="btn btn-primary"
            onclick="location.href='${etudiantajoutUrl}'">Ajouter un nouvel etudiant</button>
        </security:authorize>
        </security:authorize>
	</div>
	
	<jsp:include page="../fragments/footer.jsp" />
</html>
	

	

