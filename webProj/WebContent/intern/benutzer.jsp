<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>

<!doctype html>
<html>
<head>
<title>Prüfungsverwaltung</title>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>

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

					<!-- TODO - Seiten Content-->
					<div class="row" style="margin-top: 40px">
						<div class="col-md-10">
						<!-- Formular weg?  -->
							<form id="searchForm" role="form">
								<table>
									<tr>
										<td><input type="text" name="pid" class="form-control">
										</td>
										<td>
											<input type="button" id="searchButton" class="btn btn-primary"
												name="search" style="margin-left: 5%;" value="Suchen">												
										</td>
									</tr>
								</table>
							</form>
						</div>
						<div class="col-md-2">
							<a type="button" class="btn btn-primary"
								href="<%=request.getContextPath()%>/initAddUser">Benutzer anlegen </a>
						</div>
					</div>

					<div class="row">
						<div class="col-md-12">
							<table id="usertable" class="table table-striped" style="margin-top: 5%">
								<tr>
									<th>Name</th>
									<th>Benutzername</th>
									<th>Fakultät</th>
									<th style="width: 170px">&nbsp;</th>
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
	
	<!-- Um den ContextPath beim Zusammenbau der Zeilen als Variable einzubinden -->
	<%
	 	out.println("<script>");
	
		out.println("var contextpath=\"" + request.getContextPath() + "/\";");
	
		out.println("</script>");
	
	%>
	
	
	<script>
		// call servlet when document ready
		$(document).ready(function() {
			
			$.ajax({
				url: '../benutzer',
				method: 'POST',
				async: 'true',
				dataType: 'JSON',
				success: function(data, textStatus, jqXHR) {
					
					// simplify response, if only one object, it is no array, so convert it
					if (!$.isArray(data)) data = {0 : data};
					
					console.log("servlet response received, number of elements " + data.length);
		
					var payload = "";
					
					if (data.length == 0) {
							
					} else {
						
						$.each(data, function(key, row) {
							// zusammensetzen zu einer zeile
							payload = "<tr><td>" + row.name + "</td>";
							payload += "<td>" + row.username + "</td>";
							payload += "<td>" + row.faculty + "</td>";
							payload += "<td>";
							
							payload += "<a href=\"" + contextpath + "UserInformation?id=" + row.id + "\" class=\"btn btn-link\" style=\"padding: 0px;\"><img src=\"" + contextpath + "images/icon/008-info.png\" title=\"Info\"></a> ";
							payload += "<a href=\"" + contextpath + "benutzerBA?id=" + row.id + "\" class=\"btn btn-link\" style=\"padding: 0px;\"><img src=\"" + contextpath + "images/icon/018-tool-1.png\" title=\"Bearbeiten\"></a> ";
							payload += "<a href=\"" + contextpath + "Sperre?id=" + row.id + "\" class=\"btn btn-link\" style=\"padding: 0px;\"><img src=\"" + contextpath + "images/icon/013-lightning.png\" title=\"Sperren/Aktivieren\"></a> ";
							payload += "<a href=\"" + contextpath + "delete?id=" + row.id + "\"  class=\"btn btn-link\" style=\"padding: 0px;\"><img src=\"" + contextpath + "images/icon/020-tool.png\" title=\"Löschen\"></a> ";
							
							payload += "</td></tr>\n";
							
							// in tabelle unten anhaengen
							$("#usertable").append(payload);
						});
						
					}
					
				},
				error: function(jqXHR, textStatus, errorThrown) {
					console.log("error" + jqXHR.responseText);
					
					var payload = "<tr><td colspan=\"4\">" + jqXHR.responseText + "</td></tr>";
					$("#usertable").append(payload);
				}
			});
		});
		
		</script>
		
		<script >
	//Suche Scriptlet
	//TODO: auf Enter wird Seite immer neu geladen, anstatt zu suchen?? 
	//	  : select erweitern auf nutzername und fakultät
		
	$(document).ready(function() {
		
		//alert("Search function working");
		console.log("document is ready");
		
		$('#searchButton').click(function() {
			
			console.log("search button clicked");
			
			$.ajax({
				url: contextpath + 'Suchen',
				method: 'POST',
				async: 'true',
				data: $('#searchForm').serialize(),
				dataType: 'JSON',
				success: function(data, textStatus, jqXHR) {
					
					var payload = "";
					
					if (!$.isArray(data)) {
						//wird eig nicht benutzt?!
						console.log("kein array");
						$("#usertable").find("tr:gt(0)").remove();
						alert("Bitte geben Sie einen Suchbegriff ein!");
						window.location.reload(true);
					} 
					else if (data.length == 0) {
						console.log("array leer");
						$("#usertable").find("tr:gt(0)").remove();
						alert("Ihre Suche ergab keine Treffer!");
						window.location.reload(true); 
					} 
					else {
						$.each(data, function(key, row) {
							$("#usertable").find("tr:gt(0)").remove();
							
							payload += "<tr><td>" + row.name + "</td>";
							payload += "<td>" + row.username + "</td>";
							payload += "<td>" + row.faculty + "</td>";
							payload += "<td>";
							
							payload += "<a href=\"" + contextpath + "UserInformation?id=" + row.id + "\" class=\"btn btn-link\" style=\"padding: 0px;\"><img src=\"" + contextpath + "images/icon/008-info.png\" title=\"Info\"></a> ";
							payload += "<a href=\"" + contextpath + "benutzerBA?id=" + row.id + "\" class=\"btn btn-link\" style=\"padding: 0px;\"><img src=\"" + contextpath + "images/icon/018-tool-1.png\" title=\"Bearbeiten\"></a> ";
							payload += "<a href=\"" + contextpath + "Sperre?id=" + row.id + "\" class=\"btn btn-link\" style=\"padding: 0px;\"><img src=\"" + contextpath + "images/icon/013-lightning.png\" title=\"Sperren/Aktivieren\"></a> ";
							payload += "<a href=\"" + contextpath + "delete?id=" + row.id + "\"  class=\"btn btn-link\" style=\"padding: 0px;\"><img src=\"" + contextpath + "images/icon/020-tool.png\" title=\"Löschen\"></a> ";
							
							payload += "</td></tr>\n";
							
						});
					}
					
					// in tabelle unten anhaengen
					$("#usertable").append(payload);
					//TODO:
					//wenn user gefunden, dann kann man nur durch falschen string wieder zur kompletten nutzerübersicht
					//--> ein if für das reload (unter welchen bedingungen wird neu geladen??)
				},
				error: function(jqXHR, textStatus, errorThrown) {
					console.log("error" + jqXHR.responseText);
					
					var payload = "<tr><td colspan=\"4\">" + jqXHR.responseText + "</td></tr>";
					$("#usertable").append(payload);
				}
			});
			
		});
		
	});
	
	
	</script>
	
	
</body>
</html>