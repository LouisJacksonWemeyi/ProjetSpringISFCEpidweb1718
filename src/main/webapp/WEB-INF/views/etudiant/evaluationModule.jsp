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
    
    	<h3>Evaluation correspondant au module</h3>

		<c:if test="${fn:length(evaluationModule) == 0}">
			<h3>Pas d'evaluation déjà disponible pour ce Module </h3>
		</c:if>
		<h3>Ce module contient: ${fn:length(evaluationModule)} evaluation</h3>
    

		<h4>Evaluation</h4>

		<table class="table table-striped">
			<thead>
				<tr>
					<th>NOTE</th>
					<th>SESSION</th>
					<th>REMARQUE</th>
					<th>EVALUATION DES COMPETENCES</th>
				</tr>
			</thead>
			<c:forEach var="evaluationModule" items="${evaluationModule}">
			    <tr>
			    
			    <td>${evaluationModule.note}</td>
				<td>${evaluationModule.session}</td>
				<td>${evaluationModule.remarque}</td>
				<td>
				  <s:url value="/etudiant/evalCompetences/${evaluationModule.evaluationId}" var="competenceUrl" />
				  <button class="btn btn-info"
                                          onclick="location.href='${competenceUrl}'">COMPETENCES</button>
				</td>
				
			    </tr>
			</c:forEach>
		</table>
    
    </security:authorize>

</security:authorize>

</div>
<jsp:include page="../fragments/footer.jsp" />
</html>