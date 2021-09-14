<!DOCTYPE html>
<%@ page session="false" language="java"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%-- <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> --%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://www.springframework.org/security/tags"  prefix="security"%>

<html>
<jsp:include page="../fragments/header.jsp">
	<jsp:param name="titre" value="Cours Résultats ISFCE" />
</jsp:include>

<div class="container">
<h1>Liste des cours</h1>

<c:if test="${fn:length(coursList) == 0}">
	<h2>Liste Vide</h2>
</c:if>
<h2>La liste contient: ${fn:length(coursList)} cours</h2>

<table class="table table-striped">
			<thead>
				<tr>
					<th>CODE</th>
					<th>NOM</th>
					<th>LANGUE</th>
					<th>PERIODE</th>
					<th>COMPETENCES DU COURS</th>
					<th>MODULES DU COURS</th>
					<th>ACTION</th>
				</tr>
			</thead>
			<c:forEach items="${coursList}" var="cours">
			    <tr>
					<td>${cours.code}</td>
					<td>${cours.nom}</td>
					<td>${cours.langue}</td>
					<td>${cours.nbPeriodes}</td>
					<td><s:url value="/cours/liste/competences/${cours.code}" var="competCoursUrl" />

				  		<button class="btn btn-info"
                                          onclick="location.href='${competCoursUrl}'">Competences de ce cours</button>
                    </td>
                    <td><s:url value="/cours/liste/modules/${cours.code}" var="modulesCoursUrl" />

				  		<button class="btn btn-info"
                                          onclick="location.href='${modulesCoursUrl}'">modules de ce cours </button>
                    </td>
                    
					<td>
				  		<s:url value="${cours.code}" var="coursUrl" />
				  		<s:url value="/cours/${cours.code}/update" var="updateUrl" />
				  		<s:url value="/cours/${cours.code}/delete" var="deleteUrl" />

				  		<button class="btn btn-info"
                                          onclick="location.href='${coursUrl}'">Detail</button>
                       <security:authorize access="isAuthenticated()">
                       <security:authorize access="hasRole('ADMIN')">
				  		<button class="btn btn-primary"
                                          onclick="location.href='${updateUrl}'">Update</button>
				  		<button class="btn btn-danger"
										onclick="if (confirm('Suppression du cours  ?')) {this.disabled=true;
                				post('${deleteUrl}',{'${_csrf.parameterName}': '${_csrf.token}'})}">Delete</button>
                	 </security:authorize>
                     </security:authorize>
                     </td>
			    </tr>
			</c:forEach>
		</table>
		
		<a href="<s:url value = "/cours/add" />">
		<security:authorize access="isAuthenticated()">
        <security:authorize access="hasRole('ADMIN')">
		<button class="btn btn-primary">Ajout d'un cours</button>
		</security:authorize>
        </security:authorize>
		</a>
		
  		
</div>
<jsp:include page="../fragments/footer.jsp" />
</html>