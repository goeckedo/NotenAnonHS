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
					<div class="col-md-8" id="header" style="padding-left: 0;">
						<div id="pagetitle" style="float: left; padding-left: 20px;">
							<h1>Anonymisierung - Benutzerverwaltung</h1>
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
							<!-- TODO Dynamisch -->
							<p>Eingeloggt als ....</p>
						</div>
					</div>
				</header>
			</div>
		</div>

		<!-- Header Ende-->

		<div class="row" style="margin-top: 8px;">

			<!-- NaV leiste -->
			<div class="col-md-2" id="content-page">
				<%@ include file="navi.jspf" %>
			</div>

			<!-- Body für Seiteninhanlt-->
			<div class="col-md-10" style="padding-left: 10px;">
				<div id="content">

					<!-- TODO - Seiten Content-->
					<div class="row" style="margin-top: 40px">
						<div class="col-md-10">
							<form action="TODO" method="POST" role="form">
								<table>
									<tr>
										<td><input type="text" name="search" class="form-control">
										</td>
										<td>
											<button type="submit" class="btn btn-primary"
												name="submitSearch" style="margin-left: 5%;">Suchen</button>
										</td>
									</tr>
								</table>
							</form>
						</div>
						<div class="col-md-2">
							<a type="button" class="btn btn-primary"
								href="benutzerAnlegen.jsp">Benutzer anlegen </a>
						</div>
					</div>

					<div class="row">
						<div class="col-md-12">
							<table class="table table-striped" style="margin-top: 5%">
								<tr>
									<th>Name</th>
									<th>Benutzername</th>
									<th>Fakultät</th>
									<th></th>
								</tr>
								<tr>
									<td>Mustermann</td>
									<td>musterma</td>
									<td>INF</td>
									<td style="width: 170px"><a
										href="benutzerInformation.jsp" class="btn btn-link"
										style="padding: 0px;"><img
											src="<%=request.getContextPath()%>/images/icon/008-info.png" title="Info"></a> <a
										href="benutzerBA.jsp" class="btn btn-link"
										style="padding: 0px;"><img
											src="<%=request.getContextPath()%>/images/icon/018-tool-1.png" title="Bearbeiten"></a>
										<a href="TODO sperre/aktiv" class="btn btn-link"
										style="padding: 0px;"><img
											src="<%=request.getContextPath()%>/images/icon/013-lightning.png"
											title="Sperren/Aktivieren"></a> <a href="TODO delete"
										class="btn btn-link" style="padding: 0px;"><img
											src="<%=request.getContextPath()%>/images/icon/020-tool.png" title="Löschen"></a></td>
								</tr>
								<tr>
									<td>Mustermann</td>
									<td>musterma</td>
									<td>ENG</td>
									<td style="width: 170px"><a href="TODO Benutzerseite"
										class="btn btn-link" style="padding: 0px;"><img
											src="<%=request.getContextPath()%>/images/icon/008-info.png"></a> <a
										href="TODO bearbeiten" class="btn btn-link"
										style="padding: 0px;"><img
											src="<%=request.getContextPath()%>/images/icon/018-tool-1.png"></a> <a
										href="TODO sperre/aktiv" class="btn btn-link"
										style="padding: 0px;"><img
											src="<%=request.getContextPath()%>/images/icon/013-lightning.png"></a> <a
										href="TODO delete" class="btn btn-link" style="padding: 0px;"><img
											src="<%=request.getContextPath()%>/images/icon/020-tool.png"></a></td>
								</tr>
							</table>
						</div>
					</div>

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