<%@page import="entity.User_"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
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
							<h1>Anonymisierung-Benutzerinformation</h1>
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

					<div class="row">
					
						<div id="Benutzername" class="col-md-10">
							<h1>
								<%@ page import="entity.User_" %>
							    <%@ page import="entity.Faculty" %>
								<%
									User_ benutzer= (User_)request.getAttribute("benutzer");
									Faculty faculty = benutzer.getFaculty();
								%>
								<%= benutzer.getName() %>
							</h1>
						</div>
						<div class="col-md-2">
							<h1>
								<a href="<%=request.getContextPath()%>/benutzerBA?id=<%= benutzer.getUserID() %>" class="btn btn-link"
									style="padding: 0px;"> <img
									src="<%=request.getContextPath()%>/images/icon/018-tool-1.png" style="float: right;"
									title="Bearbeiten">
								</a> <a href="<%=request.getContextPath()%>/activ" class="btn btn-link"
									style="padding: 0px;"> <img
									src="<%=request.getContextPath()%>/images/icon/013-lightning.png" style="float: right;"
									title="Sperren/Aktivieren">
								</a> <a href="<%=request.getContextPath()%>/delete?id=<%= benutzer.getUserID() %>" class="btn btn-link"
									style="padding: 0px;"> <img
									src="<%=request.getContextPath()%>/images/icon/020-tool.png" style="float: right;"
									title="Löschen">
								</a>
							</h1>
						</div>
						<div class="col-md-12">
							<hr>
						</div>
					</div>
					<div class="row">
						<div class="col-md-4">
							<label>Fakultät: </label>
						</div>
						<div class="col-md-8">
							<p><%= faculty.getName() %></p>
						</div>
					</div>
					<div class="row">
						<div class="col-md-4">
							<label>Mitarbeiternummer: </label>
						</div>
						<div class="col-md-8">
							<p><%= benutzer.getPersonalnummer() %></p>
						</div>
					</div>

					<div class="row">
						<div class="col-md-12">
						
<!-- 						Tabelle an die die Prüfungen angehängt werden -->
							<table id=pruefungsTabelle class="table table-striped" style="margin-top: 5%">
								<tr>
									<th>Prüfung</th>
									<th>ID</th>
									<th></th>
								</tr>
							</table>
							
							<a class="btn btn-primary" style="float: right" type="button"
							href="<%=request.getContextPath()%>/intern/benutzer.jsp">Zurück</a>
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
				url: contextpath+'pruefungen?id='+<%= benutzer.getUserID() %>,
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
							payload = "<tr><td>" + row.exam + "</td>";
							payload += "<td>" + row.pNR + "</td>";
							
 							payload += "<td>";
							payload += "<a href=\"" + contextpath + "noten?id="+ row.id +"\" class=\"btn btn-link\" style=\"padding: 0px; float: right\"><img src=\"" + contextpath + "images/icon/008-info.png\" title=\"Info\"></a> ";
 							payload += "<a href=\"" + contextpath + "export?id="+ row.id +"&name=" + row.exam + "\" class=\"btn btn-link\" style=\"padding: 0px; float: right\"><img src=\"" + contextpath + "images/icon/001-arrows.png\" title=\"Export\"></a> ";
 							payload += "</td></tr>\n";
							
							// in tabelle unten anhaengen
							$("#pruefungsTabelle").append(payload);
						});
						
					}
					
				},
				error: function(jqXHR, textStatus, errorThrown) {
					console.log("error" + jqXHR.responseText);
					
					var payload = "<tr><td colspan=\"4\">" + jqXHR.responseText + "</td></tr>";
					$("#pruefungsTabelle").append(payload);
				}
			});
		});
		
		</script>
	
</body>
</html>




