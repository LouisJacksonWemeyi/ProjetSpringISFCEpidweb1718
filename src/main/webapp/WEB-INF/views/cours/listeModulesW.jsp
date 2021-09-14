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
	<jsp:param name="titre" value="Cours Résultats ISFCE" />
</jsp:include>

<div class="container">
<h1>Liste des modules du cours ${coursCode}</h1>

<c:if test="${fn:length(coursModuleList) == 0}">
	<h2>Liste Vide</h2>
</c:if> 
<h2>La liste contient: ${fn:length(coursModuleList)} modules</h2>

<table class="table table-striped">
			<thead>
				<tr>
					<th>CODE</th>
					<th>DATEDEBUT</th>
					<th>DATEFIN</th>
					<th>MOMMENT</th>
					<th>COURS</th>
					<th>PROF</th>
				</tr>
			</thead>
			<c:forEach items="${coursModuleList}" var="module">
			    <tr>
			    <td>${module.code}</td>
				<td><fmt:formatDate pattern="dd/MM/yyyy" value="${module.dateDebut}"/></td>
				<td><fmt:formatDate pattern="dd/MM/yyyy" value="${module.dateFin}"/></td>
				<td>${module.moment}</td>
				<td>${module.cours.code}</td>
				<td>${module.prof.username}</td>
				</tr>
			</c:forEach>
		</table>
  		
</div>
<jsp:include page="../fragments/footer.jsp" />
</html>