<!DOCTYPE html>
<%@ page session="false" language="java"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://www.springframework.org/security/tags"  prefix="security"%>


<html>
<jsp:include page="../fragments/header.jsp">
	<jsp:param name="titre" value="Modules Résultats ISFCE" />
</jsp:include>
<div class="container">

<security:authorize access="isAuthenticated()">
    <security:authorize access="hasRole('ETUDIANT')">
    	<h2><b>Welcome! You are logged in as:<security:authentication property="principal.username" /></b></h2>
    
    	<h3>Evaluation des competences correspondant au module</h3>

		<c:if test="${nbCompet eq 0}">
			<h3>Probleme veuillez-vous adresser au responsable du Module </h3>
		</c:if>
		<h3>Ce module contient: ${nbCompet} compétences</h3>
    

		<h4>Evaluation des compétences</h4>

		<table class="table table-striped">
			<thead>
			
				<tr>
					<th>CODE COMPETENCE</th> 
					<th>DESCRIPTION COMPETENCE</th>
					<th>RESULTAT</th>
					<th>COURS</th>	
				</tr>
				
			</thead>
			
			<c:forEach var="competence" items="${listcompetReussi}">
			    <tr>
				<td>${competence.code}</td>
				<td>${competence.competence}</td>
				<td>REUSSI</td>	
				<td>${competence.cours.code}</td>		
			    </tr>   
			</c:forEach>
			
			<c:forEach var="competence" items="${listcompetEchec}">
			    <tr>
				<td>${competence.code}</td>
				<td>${competence.competence}</td>
				<td>ECHEC</td>
				<td>${competence.cours.code}</td>
			    </tr>
			</c:forEach>
		</table>
    
    </security:authorize>

</security:authorize>

</div>
<jsp:include page="../fragments/footer.jsp" />
</html>