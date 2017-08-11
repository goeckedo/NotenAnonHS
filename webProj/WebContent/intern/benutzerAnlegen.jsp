<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!doctype html>
<html xmlns:c="http://java.sun.com/jsp/jstl/core">
<head>
<!-- Benennung im Tab -->
<title>Prüfungsverwaltung</title>

<!-- Einbinden: -->
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
							<h1>Prüfungsverwaltung - Benutzer anlegen</h1>
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
				<%@ include file="navi.jspf" %>
			</div>

			<!-- Body für Seiteninhanlt-->
			<div class="col-md-10" style="padding-left: 10px;">
				<div id="content">
					<div class="row" style="padding-left: 10px">
						<!-- TODO - Seiten Content ???style="font-size: 15px"-->
						<!--<form action=-->
						<p>
							Bitte füllen Sie alle folgenden Felder aus.<br>Der
							Benutzername wird für den späteren Login verwendet und entspricht
							dem HS Benutzernamen.<br> Falls der neue User keine
							Lehrkraft ist, geben Sie bitte "Sekretariat" als Fakultät ein.
						</p>
						
						<form action="<%=request.getContextPath()%>/addUser" method="POST" role="form">
							<div class="col-md-12">
								<ul id="anlegen">
									<li id="anlegen"><input class="form-control" type="text"
										name="username" id="username" placeholder="Benutzername"
										size="50%" required></li>
									<li id="anlegen"><input class="form-control"
										type="password" name="passwort" id="passwort"
										placeholder="Passwort" size="50%" required> <br /> <input
										class="form-control" type="password" name="passwordRep"
										id="passwordRep" placeholder="Passwort wiederholen" size="50%" required>
									</li>
								</ul>
							</div>
							<div class="col-md-12">
								<ul id="anlegen">
									<li id="anlegen"><input class="form-control" type="text"
										name="nachname" id="nachname" placeholder="Nachname"
										size="50%" required></li>
									<li id="anlegen"><input class="form-control" type="text"
										name="vorname" id="vorname" placeholder="Vorname" size="50%" required>
									</li>
									<li id="anlegen"><input class="form-control" type="text"
										name="personalNummer" id="personalNummer"
										placeholder="Personal Nummer" size="50%" required></li>
									<li id="anlegen"><select class="form-control" type="text"
										name="fakultaet" id="fakultaet"
										size="1" required>
										
										<%@ page import="entity.Faculty" %>
										<%@ page import="java.util.Collection" %>
										<%@ page import="java.util.ArrayList" %>
										
										<%
										//TODO Am besten mit Jqery machen siehe kuti beispiel						
										Collection<Faculty> facCol = new ArrayList<Faculty>();
										
										if (request.getAttribute("facCol") != null) facCol = (Collection<Faculty>)request.getAttribute("facCol");
										
										for(Faculty fa : facCol){ %>
											<option value="<%= fa.getFacultyID()%>"><%=fa.getName() %></option>
										<% }%>
										</select>
									</li>
								</ul>
								<ul id="anlegen">
									<li id="anlegen">
										<button type="submit" class="btn btn-primary">Speichern</button>
									</li>
								</ul>
							</div>
						</form>
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