<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<title>Prüfungsverwaltung</title>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css" />
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
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
					<div class="col-md-8" id="header" style="padding-left: 0;">
						<div id="pagetitle" style="float: left; padding-left: 20px;">
							<h1>Anonymisierung - Import</h1>
						</div>
					</div>
					<div class="col-md-2" style="background-color: white; height: 90px">
						<div class="row">
							<ul>
								<li><a class="btn btn-default btn-sm" style="float: right"
									type="button" href="<%=request.getContextPath()%>/logout">Abmelden</a> <a
									class="btn btn-default btn-sm" style="float: right"
									type="button" href="pwAendern.jsp">PW ändern</a></li>
							</ul>
						</div>
						<div class="row">
							<% 
							String username = request.getRemoteUser();
							out.println("Eingeloggt als: " + username);
							%>
						</div>
					</div>
				</header>
			</div>
		</div>

		<!-- Header Ende-->

		<div class="row" style="margin-top: 8px;">

			<!-- NaV leiste -->
			<div class="col-md-2" id="content-page">
				<%@ include file=	"navi.jspf" %>
			</div>

			<!-- Body für Seiteninhanlt-->
			<div class="col-md-10" style="padding-left: 10px;">
				<div id="content">
										<%
					if(request.getAttribute("import") != null){
					String importMsg = (String)request.getAttribute("import");
					if (importMsg.equals("true")){
										
					%>
					 <div class="alert alert-success alert-dismissable">
					    <a href="#" class="close" data-dismiss="alert" aria-label="close">×</a>
					    <strong>Success!</strong> Import wurde erfolgreich durchgeführt !!
					  </div>
					<%}	if (importMsg.equals("false")){ %>
					  <div class="alert alert-danger alert-dismissable">
					    <a href="#" class="close" data-dismiss="alert" aria-label="close">×</a>
					    <strong>ERROR</strong> Es ist ein Fehler aufgetreten. Bitte überprüfen Sie die Datei und versuchen es erneut !!
					  </div>
					 <% } }%>
					<!-- TODO - Seiten Content-->
					<p>Hier können Sie eine aus dem QIS exportiere Seite hochladen
					</p>
					<p style="font-size: 75%;">Wählen Sie eine Exel/CSV Datei aus
						Ihrem Dateisystem aus!</p>
					<br />
					<!--  Mit tag multiple=true kann man mehrere Dateien auf einmal erlauben -->
					<form action="<%=request.getContextPath()%>/import" method="POST" enctype="multipart/form-data">
						<div class="row">
							<div class="col-md-8">
								<input class="form-control" type="text"
								name="persnr" id="persnr" placeholder="Prüfer-Personalnummer">
							</div>
						</div>
						<br />
						<div class="row">
							<div class="col-md-8">
								<input type="file" class="" id="import"	name="import" accept="application/vnd.ms-excel"  />
							</div>
							<div class="col-md-2">
								<button type="submit" class="btn btn-primary" name="submit">Upload</button>
							</div>
						</div>
					</form>
					<br />
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-2" style="text-align: center;">
				<footer>
					<a href="<%=request.getContextPath()%>/intern/impressum.jsp">Impressum</a>
				</footer>
			</div>
		</div>
	</div>
</body>
</html>