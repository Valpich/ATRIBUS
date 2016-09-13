<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>

	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="msapplication-TileColor" content="#ffffff">
	<meta name="msapplication-TileImage" content="/ms-icon-144x144.png">
	<meta name="theme-color" content="#ffffff">

	<title>Consulter les progrès d'un élève</title>
	<link rel="apple-touch-icon" sizes="57x57" href="<c:url value="/resources/images/apple-icon-57x57.png"/>">
	<link rel="apple-touch-icon" sizes="60x60" href="<c:url value="/resources/images/apple-icon-60x60.png"/>">
	<link rel="apple-touch-icon" sizes="72x72" href="<c:url value="/resources/images/apple-icon-72x72.png"/>">
	<link rel="apple-touch-icon" sizes="76x76" href="<c:url value="/resources/images/apple-icon-76x76.png"/>">
	<link rel="apple-touch-icon" sizes="114x114" href="<c:url value="/resources/images/apple-icon-114x114.png"/>">
	<link rel="apple-touch-icon" sizes="120x120" href="<c:url value="/resources/images/apple-icon-120x120.png"/>">
	<link rel="apple-touch-icon" sizes="144x144" href="<c:url value="/resources/images/apple-icon-144x144.png"/>">
	<link rel="apple-touch-icon" sizes="152x152" href="<c:url value="/resources/images/apple-icon-152x152.png"/>">
	<link rel="apple-touch-icon" sizes="180x180" href="<c:url value="/resources/images/apple-icon-180x180.png"/>">
	<link rel="icon" type="image/png" sizes="192x192" href="<c:url value="/resources/images/android-icon-192x192.png"/>">
	<link rel="icon" type="image/png" sizes="32x32" href="<c:url value="/resources/images/favicon-32x32.png"/>">
	<link rel="icon" type="image/png" sizes="96x96" href="<c:url value="/resources/images/favicon-96x96.png"/>">
	<link rel="icon" type="image/png" sizes="16x16" href="<c:url value="/resources/images/favicon-16x16.png"/>">
	<link rel="manifest" href="<c:url value="/resources/images/manifest.json"/>">
	
	<link href="<c:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet"> <!-- Bootstrap CSS -->
	<link href="<c:url value="/resources/fonts/css/font-awesome.min.css"/>" rel="stylesheet"> <!-- Custom Font CSS -->
	
	<link href="<c:url value="/resources/css/custom.css"/>" rel="stylesheet"> <!-- Custom CSS -->
	
	<link rel="stylesheet" href="<c:url value="/resources/css/ion_range/nouislider.min.css"/>" /> <!-- Slider -->
	
	<script src="<c:url value="/resources/js/jquery.min.js"/>"></script>	
	<script src="<c:url value="/resources/js/ion_range/nouislider.js"/>"></script>	<!-- Slider -->

</head>

<body class="nav-md">

	<div class="container body">
		<div class="main_container">

			<!-- sidebar gauche -->
			<jsp:include page="../sidebar-gauche.jsp" />
			<jsp:include page="../topbar-navigation.jsp" />

			<!-- page content -->
			<div class="right_col" role="main">
				<div class="row">
					<div class="col-md-12 col-sm-12 col-xs-12">
						<div class="x_panel tile">
							<div class="x_title">
							
								<h2>Consulter les progrès d'un élève</h2>
								<ul class="nav navbar-right panel_toolbox" style="min-width: 0px;">
									<li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a></li>
									<li><a class="close-link"><i class="fa fa-close"></i></a></li>
								</ul>
								<div class="clearfix"></div>
								
							</div>
							
							<div class="x_content">	
			
				
								<div class="col-md-10 col-sm-10 col-xs-10" style="margin-left: -10px;">
									Du <label><c:out value="${eleveProgres.dateInscription}"/></label>
								</div>
								
								<div class="col-md-2 col-sm-2 col-xs-2">
									<p style="margin-left: 10px;">Au <strong><c:out value="2016-05-30 13:34:59.0"/></strong></p>
								</div>
								<br>
							
								<div id="slider"></div>
								<br>
								<div id="chartContainer" style="height: 350px; width: 100%;">	
							</div>

								<ul class="nav nav-list">
									<li class="divider"></li>
								</ul>

								<div class="text-center">
									<span class="glyphicon glyphicon-home"></span> 
									<a href="<c:url value="/"/>"> Revenir à l'index</a>
								</div>

							</div>
	
								</div>
			
							</div>
							
						</div>

					<div class="clearfix"></div>
					<!-- footer content -->
					<jsp:include page="../footer.jsp"/>
					<!-- /footer content -->
				</div>
				<!-- /page content -->
			</div>
		</div>

		<div id="custom_notifications" class="custom-notifications dsp_none">
			<ul class="list-unstyled notifications clearfix" data-tabbed_notifications="notif-group"></ul>
		<div class="clearfix"></div>
		<div id="notif-group" class="tabbed_notifications"></div>
		
	</div>
	
	<script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
	<script src="<c:url value="/resources/js/custom.js"/>"></script>
	
    <script src="<c:url value="/resources/js/chart/jquery.canvasjs.min.js"/>"></script>	

	<script>	
	
	window.onload = function () {
		
		var dps = [
		     <c:forEach var="competenceEleve" items="${requestScope.competenceEleve}" varStatus="status">
		     	{y: parseInt("${competenceEleve.getNiveauCompetenceEleve()}"), label: "${competenceEleve.getNom()}"},
		     </c:forEach>
		];	

		var chart = new CanvasJS.Chart("chartContainer",{	
			
			exportEnabled: true,
			animationEnabled: true,
			
			title: {
				text: "${eleveProgres.getPrenom()} ${eleveProgres.getNom()}"		
			},
		    axisX:{    
		        labelFontWeight: "lighter",
		        labelFontColor: "black"
		   	},
			data: [
			{
			    type: "bar",
	      		name: "competences",
	      		axisYType: "primary",
	      		color: "#2A3F54",
				dataPoints: dps					
			}
			]
		});
	
		var updateChart = function () {
				
			var i = 0;
			
			<c:forEach var="competenceEleve" items="${requestScope.competenceEleve}" varStatus="status">
			
				var niveauEleve = parseInt("${competenceEleve.getNiveauCompetenceEleve()}");
				var max = 5;
				var min = 0.1;
				var random = parseFloat((Math.random() * (max - min) + min).toFixed(4));
				
				dps[i] = {y: niveauEleve + random, label: "${competenceEleve.getNom()}"};
				i = i + 1;
				
			</c:forEach>

			chart.render();
			
		};
		
		var slider = document.getElementById('slider');

		noUiSlider.create(slider, {
			start: 1,
			connect: 'lower',
			step: 0.2,
			range: {
			  'min': 0,
			  'max': 5
			}
		});
		

		slider.noUiSlider.on('update', function(values, handle) {

		  var value = values[handle];

		  if (handle) {
		    updateChart();
		  } else {
			updateChart();
		  }
		  
		});

	}
	
	</script>
	

</body>

</html>