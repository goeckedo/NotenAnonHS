<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<title>Prüfungsverwaltung</title>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css" />
</head>
<body>
	<!-- Abstand nach Oben -->
	<div class="container">
		<div class="row">
			<div class="col-md-12"
				style="height: 16px; margin-top: 2px; margin-left: -10px;"></div>
		</div>
	</div>

	<!-- MainContainer hier kommt ales rein -->
	<div class="container" id="maincontainer"
		style="background-color: #e5e5e5">
		<!--Header row-->
		<div class="row">
			<div class="col-md-12">
				<header>
					<!-- Bild Header Links -->
					<div class="col-md-2" id="logo"
						style="background-image: url(<%=request.getContextPath()%>/images/logo3.png); background-repeat: no-repeat;">
						<!--<img src="<%=request.getContextPath()%>/images/logo2.png" alt="logo.png" style="position: relative;" />-->
					</div>
					<!-- Überschrift Header -->
					<div class="col-md-10" id="header" style="padding-left: 0;">
						<div id="pagetitle" style="float: left; padding-left: 20px;">
							<h1>Anonymisierung - Anmeldung</h1>
						</div>
					</div>
				</header>
			</div>
		</div>

		<!-- Header Ende-->

		<div class="row" style="margin-top: 8px;">

			<!-- NaV leiste -->
			<div class="col-md-2" id="content-page">
				<nav>
					<ul id="navigation">
						<li><a href="index.jsp">Start</a></li>
					</ul>
				</nav>
			</div>

			<!-- Body für Seiteninhanlt-->
			<div class="col-md-10" style="padding-left: 10px;">
				<div id="content">
					<div class="row">
						<div class="col-md-12">
							<p>Bitte geben Sie Ihren Benutzernamen und Ihr Passwort ein.
							</p>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12">
							<form action="<%=response.encodeURL("j_security_check")%>" method="POST" role="form"
								class="form-horizontal">
								<div class="form-group" id="login-form">
									<div class="col-md-10">
										<input type="text" class="form-control" id="username"
											name="j_username" placeholder="Benutzername" />
									</div>
								</div>
								<div class="form-group">
									<div class="col-md-10">
										<input type="password" class="form-control" id="password"
											placeholder="Password" name="j_password" />
									</div>
								</div>
								<div class="form-group">
									<div class="col-md-3">
										<button type="submit" class="btn btn-primary" name="submit">Anmelden</button>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>

			</div>
		</div>
		<div class="row">
			<div class="col-md-2" style="text-align: center;">
				<footer>
					
				</footer>
			</div>
		</div>
	</div>
</body>
</html>