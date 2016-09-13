<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<div class="top_nav">

	<div class="nav_menu">
		<nav class="" role="navigation">
			<div class="nav toggle">
				<a id="menu_toggle">
					<i class="fa fa-bars"></i>
				</a>
			</div>

			<ul class="nav navbar-nav navbar-right">

				<li class="">
					<!-- Utilisateur -->
					<a href="javascript:;" class="user-profile dropdown-toggle"
						data-toggle="dropdown" aria-expanded="false">
						<i class="fa fa-user"></i>
						<c:out
							value="${sessionScope.sessionUtilisateur.get(0).getPrenom()} " />
						<c:out value="${sessionScope.sessionUtilisateur.get(0).getNom()}" />
						<span class=" fa fa-angle-down"></span>
					</a>
					<!-- /Utilisateur -->

					<!-- Menu de l'utilisateur -->
					<ul
						class="dropdown-menu dropdown-usermenu animated fadeInDown pull-right">
						<li>
							<a href="<c:url value="/MonProfil"/>">
								<i class="fa fa-user pull-right"></i>
								Profil
							</a>
						</li>
						<li>
							<a href="<c:url value="/deconnexion"/>">
								<i class="fa fa-sign-out pull-right"></i>
								Déconnexion
							</a>
						</li>
					</ul>
					<!-- /Menu de l'utilisateur -->
				</li>

				<!-- Alertes (messages) -->
				<li role="presentation" class="dropdown">
					<a href="javascript:;" class="dropdown-toggle info-number"
						data-toggle="dropdown" aria-expanded="false">
						<i class="fa fa-bell-o"></i>
						<c:if
							test="${fn:length(sessionScope.sessionUtilisateur.get(0).notifications) > 0 }">
							<span class="badge bg-green">${fn:length(sessionScope.sessionUtilisateur.get(0).notifications)}</span>
							<!-- Nombre de notifications -->
						</c:if>
					</a>
					<ul id="menu1"
						class="dropdown-menu list-unstyled msg_list animated fadeInDown"
						role="menu">
						<c:set var="nbNotif"
							value="${fn:length(sessionScope.sessionUtilisateur.get(0).notifications)}" />
						<c:forEach var="notification"
							items="${sessionScope.sessionUtilisateur.get(0).notifications}" varStatus="loopIndex">
							<c:if test="${nbNotif - loopIndex.index <= 6}">
								<li>
									<a>
										<span class="image">
											<i class="fa fa-user"></i>
										</span>
										<span>
											<span>
												<c:out value="${ notification.nomEmetteur}" />
											</span>

											<span class="time">
												<fmt:formatDate type="both" dateStyle="short"
													timeStyle="short" value="${notification.dateCreation}" />
											</span>
										</span>
										<span class="message">
											<c:out value="${ notification.message}" />
										</span>
									</a>
								</li>
							</c:if>
						</c:forEach>
						<c:if
							test="${not empty sessionScope.sessionUtilisateur.get(0).notifications}">
							<li>
								<div class="text-center">
									<a href="<c:url value="/Notifications"/>">
										<strong>Voir toutes les alertes</strong>
										<i class="fa fa-angle-right"></i>
									</a>
								</div>
							</li>
						</c:if>
					</ul>
				</li>
				<!-- Alertes (messages) -->

			</ul>
		</nav>
	</div>
</div>