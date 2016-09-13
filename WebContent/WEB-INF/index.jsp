<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>

	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">

	<title>Accueil</title>
<link rel="apple-touch-icon" sizes="57x57"
	href="<c:url value="/resources/images/apple-icon-57x57.png"/>">
<link rel="apple-touch-icon" sizes="60x60"
	href="<c:url value="/resources/images/apple-icon-60x60.png"/>">
<link rel="apple-touch-icon" sizes="72x72"
	href="<c:url value="/resources/images/apple-icon-72x72.png"/>">
<link rel="apple-touch-icon" sizes="76x76"
	href="<c:url value="/resources/images/apple-icon-76x76.png"/>">
<link rel="apple-touch-icon" sizes="114x114"
	href="<c:url value="/resources/images/apple-icon-114x114.png"/>">
<link rel="apple-touch-icon" sizes="120x120"
	href="<c:url value="/resources/images/apple-icon-120x120.png"/>">
<link rel="apple-touch-icon" sizes="144x144"
	href="<c:url value="/resources/images/apple-icon-144x144.png"/>">
<link rel="apple-touch-icon" sizes="152x152"
	href="<c:url value="/resources/images/apple-icon-152x152.png"/>">
<link rel="apple-touch-icon" sizes="180x180"
	href="<c:url value="/resources/images/apple-icon-180x180.png"/>">
<link rel="icon" type="image/png" sizes="192x192"
	href="<c:url value="/resources/images/android-icon-192x192.png"/>">
<link rel="icon" type="image/png" sizes="32x32"
	href="<c:url value="/resources/images/favicon-32x32.png"/>">
<link rel="icon" type="image/png" sizes="96x96"
	href="<c:url value="/resources/images/favicon-96x96.png"/>">
<link rel="icon" type="image/png" sizes="16x16"
	href="<c:url value="/resources/images/favicon-16x16.png"/>">
<link rel="manifest"
	href="<c:url value="/resources/images/manifest.json"/>">
<meta name="msapplication-TileColor" content="#ffffff">
<meta name="msapplication-TileImage" content="/ms-icon-144x144.png">
<meta name="theme-color" content="#ffffff">

<link href="<c:url value="/resources/css/bootstrap.min.css"/>"
	rel="stylesheet">
<link href="<c:url value="/resources/fonts/css/font-awesome.min.css"/>"
	rel="stylesheet">
<link href="<c:url value="/resources/css/animate.min.css"/>"
	rel="stylesheet">
<link href="<c:url value="/resources/css/custom.css"/>" rel="stylesheet">
<link href="<c:url value="/resources/css/floatexamples.css"/>"
	rel="stylesheet">

<script src="<c:url value="/resources/js/jquery.min.js"/>"></script>


<!-- Météo -->
<link href="<c:url value="/resources/css/forecast/example_jquerytools.css"/>" rel="stylesheet">
<script src="<c:url value="/resources/js/forecast/jquery.zweatherfeed.min.js"/>" type="text/javascript"></script>


</head>

<body class="nav-md">

	<div class="container body">
		<div class="main_container">

			<jsp:include page="sidebar-gauche.jsp" />
			<jsp:include page="topbar-navigation.jsp" />

			<!-- page content -->
			<div class="right_col" role="main">
			
			
					<div class="col-md-12 col-sm-12 col-xs-12">
						<div class="x_panel tile">
	
							
							<div class="x_content">
								
								<h3 class="text-center">Bienvenue sur le nouveau portail en ligne de l'ESEO</h3>
					
									<ul class="nav nav-list">
									<li class="divider"></li>
								</ul>
								
								<br>
								<p class="text-center" style="font-size: 12px;">En vous connectant sur le portail ATRIBUS de l'ESEO, vous acceptez de respecter les lois en vigueur* (droits d'auteur, plagiat, ...) pour un usage réservé à la pédagogie, et vous vous engagez à ne pas diffuser un document sans autorisation préalable de l'ESEO.</p>	
								
									
							</div>
						</div>
					</div>
					
					
												<div class="col-md-6 col-sm-6 col-xs-6">
						<div class="x_panel tile">
							<div class="x_title">
							
								<h2>Twitter de l'ESEO</h2>
								<ul class="nav navbar-right panel_toolbox"
									style="min-width: 0px;">
									<li><a class="collapse-link"><i
											class="fa fa-chevron-up"></i></a></li>
									<li><a class="close-link"><i class="fa fa-close"></i></a>
									</li>
								</ul>
								<div class="clearfix"></div>
								
							</div>
							
							<div class="x_content">

							<a class="twitter-timeline" href="https://twitter.com/Groupe_ESEO" data-widget-id="735583918053818368">Tweets de @Groupe_ESEO</a>
							<script>
								!function(d, s, id) {
									var js, fjs = d.getElementsByTagName(s)[0], p = /^http:/
											.test(d.location) ? 'http'
											: 'https';
									if (!d.getElementById(id)) {
										js = d.createElement(s);
										js.id = id;
										js.src = p
												+ "://platform.twitter.com/widgets.js";
										fjs.parentNode.insertBefore(js, fjs);
									}
								}(document, "script", "twitter-wjs");
							</script>

						</div>
						</div>
					</div>
			
			

		

	
					<div class="col-md-6 col-sm-6 col-xs-6">
						<div class="x_panel tile">
							<div class="x_title">
							
								<h2>Facebook de l'ESEO</h2>
								<ul class="nav navbar-right panel_toolbox"
									style="min-width: 0px;">
									<li><a class="collapse-link"><i
											class="fa fa-chevron-up"></i></a></li>
									<li><a class="close-link"><i class="fa fa-close"></i></a>
									</li>
								</ul>
								<div class="clearfix"></div>
								
							</div>
							
							<div class="x_content">

							<div id="fb-root"></div>
							<script>
								(function(d, s, id) {
									var js, fjs = d.getElementsByTagName(s)[0];
									if (d.getElementById(id))
										return;
									js = d.createElement(s);
									js.id = id;
									js.src = "//connect.facebook.net/fr_FR/sdk.js#xfbml=1&version=v2.6";
									fjs.parentNode.insertBefore(js, fjs);
								}(document, 'script', 'facebook-jssdk'));
							</script>

							<div class="fb-page" data-href="https://www.facebook.com/GroupeESEO" data-tabs="timeline" data-height="310" data-width="500" data-small-header="false" data-adapt-container-width="true" data-hide-cover="false" data-show-facepile="true"><div class="fb-xfbml-parse-ignore"><blockquote cite="https://www.facebook.com/GroupeESEO"><a href="https://www.facebook.com/GroupeESEO">Groupe ESEO</a></blockquote></div></div>
						

							</div>
						</div>
					</div>
					
					
															<div class="col-md-3 col-sm-3 col-xs-3">
	
					</div>
					
					
					<div class="col-md-6 col-sm-6 col-xs-6">
						<div class="x_panel tile">
							<div class="x_title">
							
								<h2>Météo du jour à Angers</h2>
								<ul class="nav navbar-right panel_toolbox"
									style="min-width: 0px;">
									<li><a class="collapse-link"><i
											class="fa fa-chevron-up"></i></a></li>
									<li><a class="close-link"><i class="fa fa-close"></i></a>
									</li>
								</ul>
								<div class="clearfix"></div>
								
							</div>
							
							<div class="x_content">
					
									<script>
							  			$(document).ready(function () {
											$('#meteo').weatherfeed(['576106'],{
						
											woeid: true,
											image: true,
											country: true,
											link: false
											});
										});
									</script>
									
									<div id="meteo"></div>
							
									
	
							</div>
						</div>
					</div>
					
					
									<!-- footer content -->
									
									

									
									
									
				<jsp:include page="footer.jsp" />
				<!-- /footer content -->
				</div>


				<div class="clearfix"></div>
			</div>
			<!-- /page content -->
		</div>


	<div id="custom_notifications" class="custom-notifications dsp_none">
		<ul class="list-unstyled notifications clearfix"
			data-tabbed_notifications="notif-group">
		</ul>
		<div class="clearfix"></div>
		<div id="notif-group" class="tabbed_notifications"></div>
	</div>
	
	
	<script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
	<script src="<c:url value="/resources/js/custom.js"/>"></script>

	<!-- PNotify -->
	<script src="<c:url value="/resources/js/notify/pnotify.core.js"/>"></script>
	<script src="<c:url value="/resources/js/notify/pnotify.buttons.js"/>"></script>
	<script src="<c:url value="/resources/js/notify/pnotify.nonblock.js"/>"></script>

	<c:forEach items="${sessionScope.sessionUtilisateur}" var="utilisateur">
		<c:set var="message"
			value="${requestScope.message} \n     ${utilisateur.getClass().getSimpleName()}"
			scope="request" />
	</c:forEach>

	<script type="text/javascript">
	
		var permanotice, tooltip, _alert;
		$(function() {
			
			var stack_bottomright = {"dir1": "up", "dir2": "left", "firstpos1": 25, "firstpos2": 25};
			
			new PNotify({
				title : 'Succès de la connexion',
				type : 'success',
				text : 'Droits : <c:out value="${requestScope.message}" />',
				styling : 'bootstrap3',
				delay: 1000,
				addclass: "stack-bottomright",
		        stack: stack_bottomright,
				nonblock : {
					nonblock : false,
					nonblock_opacity : .2
				}
			});
		});
		
	</script>



</body>

</html>