<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%--  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> --%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ page session="false" language="java"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<head>
<title>${param.titre}</title>
<!-- Required meta tags for bootstrap-->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- chargement des styles avec bootstrap first -->
<%-- <s:url value="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" var="bootstrapCss" /> --%>
<s:url value="/resources/css/bootstrap.min.css" var="bootstrapCss" />
<s:url value="/resources/css/style.css" var="styleCss" />
<link rel="stylesheet" href="${bootstrapCss}" />
<link rel="stylesheet" href="${styleCss}" />

<s:url value="/login" var="loginUrl" />
<s:url value="/logout" var="logoutUrl" />
<sec:authorize access="isAuthenticated()" var="logger" />
<sec:authorize access="hasRole('ROLE_PROF')" var="isProf" />

</head>
<body>
	<!-- Image and text -->
	<nav class="navbar navbar-inverse  ">
		<div class="container">
			<div class="navbar-header">
				<img src="<s:url value="/resources/img/logoISFCE.png"/>" width="50"
					height="50" class="d-inline-block align-top-left" /> <a
					href="http://isfce.org" target="blank">ISFCE</a>
			</div>
			<ul class="nav navbar-nav">
				<li class="active"><a href="<s:url value="/"/>">Home</a></li>
				<li class="dropdown"><a class="dropdown-toggle"
					data-toggle="dropdown" href="#">Cours <span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="<s:url value = "/cours/liste"/>">liste</a></li>
						<li  class="${isProf ? '':'disabled'}">
						  <a href="<s:url value = "${isProf ? '/cours/add':'#'}" />">add</a>
						</li>
					</ul></li>
				<li><a href="#">Etudiants</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
			 <sec:authorize access="isAuthenticated()">
				<li><a
					href="#"onclick="post('${logoutUrl}',{'${_csrf.parameterName}': '${_csrf.token}'})">
					<span class="glyphicon glyphicon-log-out"></span>
						Logout <sec:authentication property="principal.username" /></a>
				</li>
			</sec:authorize>
			<sec:authorize access="!isAuthenticated()">
				<li><a
					href="${loginUrl}"><span class="glyphicon glyphicon-log-in"></span>
						Login</a></li>
			</sec:authorize>
			</ul>
		</div>
	</nav>