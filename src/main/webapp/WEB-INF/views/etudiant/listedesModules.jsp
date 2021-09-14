<!DOCTYPE html>
<%@ page session="false" language="java"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://www.springframework.org/security/tags"  prefix="security"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>



<html>
<jsp:include page="../fragments/header.jsp">
	<jsp:param name="titre" value="Modules Résultats ISFCE" />
</jsp:include>
<div class="container">

<security:authorize access="isAuthenticated()">
    <security:authorize access="hasRole('ETUDIANT')">
    	<h2><b>Welcome! You are logged in as:<security:authentication property="principal.username" /></b></h2>
    
    	<h3>Liste des modules aux quels vous êtes inscrit</h3>

		<c:if test="${fn:length(moduleListEtudiant) == 0}">
			<h3>Liste Vide</h3>
		</c:if>
		<h3>La liste contient: ${fn:length(moduleListEtudiant)} modules</h3>
    

		<h4>All Modules</h4>

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

			<c:forEach var="module" items="${moduleListEtudiant}">
			    <tr>
				<td>
				  <s:url value="/etudiant/evaluation/${module.code}" var="evaluationUrl" />
				  <button class="btn btn-info"
                                          onclick="location.href='${evaluationUrl}'">${module.code}</button>
				</td>
				<td><fmt:formatDate pattern="dd/MM/yyyy" value="${module.dateDebut}"/></td>
				<td><fmt:formatDate pattern="dd/MM/yyyy" value="${module.dateFin}"/></td>
				<td>${module.moment}</td>
				<td>${module.cours.code}</td>
				<td>${module.prof.username}</td>
			    </tr>
			</c:forEach>
		</table>
    
    </security:authorize>

</security:authorize>

<security:authorize access="isAuthenticated()">
    <security:authorize access="hasRole('ADMIN')">
    <spring:url value="/" var="redirectAthentication" />
    	<h2><b>Welcome! You are logged in as:<security:authentication property="principal.username" /></b></h2>
    <p>Click below button to redirect the result to home page</p>
      
      <form:form method = "GET" action = "${redirectAthentication}">
         <table>
            <tr>
               <td>
                  <input type = "submit" value = "Redirect to Home Page"/>
               </td>
            </tr>
         </table>  
      </form:form>

</security:authorize>
</security:authorize>

<security:authorize access="isAuthenticated()">
    <security:authorize access="hasRole('PROF')">
    <spring:url value="/" var="redirectAthentication" />
    	<h2><b>Welcome! You are logged in as:<security:authentication property="principal.username" /></b></h2>
    <p>Click below button to redirect the result to home page</p>
      
      <form:form method = "GET" action = "${redirectAthentication}">
         <table>
            <tr>
               <td>
                  <input type = "submit" value = "Redirect to Home Page"/>
               </td>
            </tr>
         </table>  
      </form:form>

</security:authorize>
</security:authorize>


</div>
<jsp:include page="../fragments/footer.jsp" />
</html>