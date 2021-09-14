<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="../fragments/header.jsp" />

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

	<h1>Etudiant Detail</h1>
	<br />

	<div class="row">
		<label class="col-sm-2">Username</label>
		<div class="col-sm-10">${etudiant.username}</div>
	</div>

	<div class="row">
		<label class="col-sm-2">Nom</label>
		<div class="col-sm-10">${etudiant.nom}</div>
	</div>
	<div class="row">
		<label class="col-sm-2">Prenom</label>
		<div class="col-sm-10">${etudiant.prenom}</div>
	</div>

	<div class="row">
		<label class="col-sm-2">Email</label>
		<div class="col-sm-10">${etudiant.email}</div>
	</div>

	<div class="row">
		<label class="col-sm-2">Telephone</label>
		<div class="col-sm-10">${etudiant.tel}</div>
	</div>


</div>
	<jsp:include page="../fragments/footer.jsp" />

</body>
</html>
