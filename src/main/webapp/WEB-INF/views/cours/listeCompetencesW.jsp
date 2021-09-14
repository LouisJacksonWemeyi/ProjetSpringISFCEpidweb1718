<!DOCTYPE html>
<%@ page session="false" language="java"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%-- <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> --%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<html>
<jsp:include page="../fragments/header.jsp">
	<jsp:param name="titre" value="Cours Résultats ISFCE" />
</jsp:include>

<div class="container">
<h1>Liste des competences du cours ${coursCode}</h1>

<c:if test="${fn:length(coursCompetenceList) == 0}">
	<h2>Liste Vide</h2>
</c:if> 
<h2>La liste contient: ${fn:length(coursCompetenceList)} competences</h2>

<table class="table table-striped">
			<thead>
				<tr>
					<th>CODE</th>
					<th>COMPETENCE</th>
					<th>COURS</th>
				</tr>
			</thead>
			<c:forEach items="${coursCompetenceList}" var="competence">
			    <tr>
					<td>${competence.code}</td>
					<td>${competence.competence}</td>
					<td>${competence.cours.code}</td>
				</tr>
			</c:forEach>
		</table>
  		
</div>
<jsp:include page="../fragments/footer.jsp" />
</html>