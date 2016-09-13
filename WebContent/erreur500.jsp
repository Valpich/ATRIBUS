<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>

	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">

	<title>Erreur 500</title>

	<link href="<c:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet">
	<link href="<c:url value="/resources/fonts/css/font-awesome.min.css"/>" rel="stylesheet">
	<link href="<c:url value="/resources/css/animate.min.css"/>" rel="stylesheet">
	<link href="<c:url value="/resources/css/custom.css"/>" rel="stylesheet">
	<link href="<c:url value="/resources/css/icheck/flat/green.css"/>" rel="stylesheet">
	<link href="<c:url value="/resources/css/maps/jquery-jvectormap-2.0.3.css"/>" rel="stylesheet">
	<link href="<c:url value="/resources/css/floatexamples.css"/>" rel="stylesheet">
	<link href="<c:url value="/resources/css/bootstrap-select.css"/>" rel="stylesheet">
	<link href="<c:url value="/resources/css/form/bootstrap-formhelpers.css"/>" rel="stylesheet">

	<script src="<c:url value="/resources/js/jquery.min.js"/>"></script>
	<script src="<c:url value="/resources/js/nprogress.js"/>"></script>
	<script src="<c:url value="/resources/js/bootstrap-select.js"/>"></script>
	<script src="<c:url value="/resources/js/form/bootstrap-formhelpers.js"/>"></script>
	
	
</head>


<body class="nav-md">

  <div class="container body">

    <div class="main_container">

      <div class="col-md-12">
        <div class="col-middle">
          <div class="text-center">
            <h1 class="error-number">500</h1>
            <h2>Erreur interne au serveur</h2>
            <p>Ces erreurs sont enregistrés automatiquement, si le problème persiste,
            <br/>merci de bien vouloir nous en informer. <a href="<c:url value="/ContacterAdministrateur"/>">Signaler l'erreur ?</a></p>
            <span class="glyphicon glyphicon-home"></span>
                    <a href="<c:url value="/"/>"> Revenir à l'index</a>
            <div class="mid_center">
              <form>
                  <div class="text-center input-group">
                    
                  </div>
              </form>
            </div>
          </div>
        </div>
      </div>

    </div>

  </div>


</body>

</html>