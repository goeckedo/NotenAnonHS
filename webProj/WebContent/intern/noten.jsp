<%@page import="java.util.ArrayList"%>
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
							<h1>Anonymisierung -
							<%@ page import="entity.Exam" %>
							<%
								Exam exam= (Exam)request.getAttribute("exam");
							%>
							<%= exam.getpNR() %>
							: 
							<%= exam.getSortname() %>
							</h1>
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
							<!--  Dynamisch -->
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
					<div class="row">
						<div class="col-md-12">
							<p>In folgender Tabelle können Sie die Note einer Studenten
								ID zuweisen. Die ID's unterscheiden sich je nach Prüfung und
								Student. Nach dem Eintragen der Noten können diese exportiert
								und im QIS reimpotiert werden.</p>
						</div>
					</div>
					<br />
					<div class="row">
						<div class="col-md-12">
							<form method="POST" role="form"
								class="form-horizontal">
								<div class="form-group">
									<div class="col-md-4">
										<form id="searchForm" role="form">
											<table>
												<tr>
													<td><input type="text" name="pid" class="form-control">
													</td>
													<td>
														<input type="button" id="suchenButton" class="btn btn-primary"
															name="search" style="margin-left: 5%;" value="Suchen">												
													</td>
												</tr>
											</table>
										</form>
									</div>
								</div>
							</form>
							<br />
<%-- 							<form action="<%=request.getContextPath()%>/saveNoten" method="POST" role="form"> --%>
<!-- 								<div> -->
<!-- 									<div class="col-md-10"></div> -->
<!-- 									<div class="col-md-2"> -->
<!-- 										<button type="submit" class="btn btn-primary" name="submit">Speichern -->
<!-- 										</button> -->
<!-- 									</div> -->
<!-- 								</div> -->
<!-- 							</form> -->
						</div>
					</div>
					<form action="<%=request.getContextPath()%>/saveNoten" method="POST" role="form">
						<div class="row">
							<div class="col-md-12">
								<table id ="examtable" class="table table-hover" style="margin-top: 5%">
									<tr>
										<th>ID</th>
										<th style="width: 170px">Note</th>
										<th></th>
									</tr>
									<%@ page import="entity.Exam" %>
									<%@ page import="entity.ExamDetails" %>
									<%@ page import="java.util.Collection" %>
									
								<%
									Collection<ExamDetails> coll = new ArrayList<ExamDetails>();
									coll = (Collection<ExamDetails>) request.getAttribute("examDetails");
									for(ExamDetails e: coll){
								%>
									<tr>
										<td><input type="hidden" name="id" value="<%= e.getExamLotto() %>"><%= e.getExamLotto() %></input></td>
								<% if(e.getBewertung() > 0){ %>
										<td id="noteFest"><%= e.getBewertung() %></td>
										<td id="bearbeiten" style="width: 170px"><a id="bearbeitenIcon"
											class="btn btn-link" style="padding: 0px;"><img
												src="<%=request.getContextPath()%>/images/icon/017-editing.png"></a></td>
									</tr>									
								<%} else { %>
									<tr>
										<td><input type="text" class="form-control" id="note"
											placeholder="Note" name="Note" value="<%= e.getBewertung() %>"/></td>
										<td style="width: 170px"></td>
									</tr>
								<% }} %>	
								</table>
							</div>
						</div>
						<div class="row">
							<div class="col-md-9"></div>
							<div class="col-md-1">
							<!-- 	<button type="submit" class="btn btn-primary" name="submit">Export</button> -->
							</div>
							<div class="col-md-2">
								<button type="submit" class="btn btn-primary" name="submit">Speichern
								</button>
							</div>
						</div>
					</form>
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
	
		
	<%
	 	out.println("<script>");
	
		out.println("var contextpath=\"" + request.getContextPath() + "/\";");
	
		out.println("</script>");
	
	%>


	<script>	
	$(document).ready(function() {
		
		//alert("Search function working");
		console.log("document is ready");
		
		$('#suchenButton').click(function() {
			
			console.log("search button clicked");
			
			$.ajax({
				url: contextpath + 'Search', //Search nach "x" übergeben
				method: 'POST',
				async: 'true',
				data: $('#searchForm').serialize(),
				dataType: 'JSON',
				success: function(data, textStatus, jqXHR) {
					
					var payload = "";
					
					if (!$.isArray(data)) {
						//wird eig nicht benutzt?!
						console.log("kein array");
						$("#examtable").find("tr:gt(0)").remove();
						alert("Bitte geben Sie einen Suchbegriff ein!");
						window.location.reload(true);
					} 
					else if (data.length == 0) {
						console.log("array leer");
						$("#examtable").find("tr:gt(0)").remove();
						alert("Ihre Suche ergab keine Treffer!");
						window.location.reload(true); 
					} 
					else {
						$.each(data, function(key, row) {
							$("#examtable").find("tr:gt(0)").remove();
							
							payload += "<tr><td>" + row.bewertung + "</td>";
							payload += "<td>" + row.examLotto + "</td>";
							payload += "<td>";
							
							payload += "</td></tr>\n";
							
						});
					}
					
					// in tabelle unten anhaengen
					$("#examtable").append(payload);
					//TODO:
					//wenn user gefunden, dann kann man nur durch falschen string wieder zur kompletten nutzerübersicht
					//--> ein if für das reload (unter welchen bedingungen wird neu geladen??)
				},
				error: function(jqXHR, textStatus, errorThrown) {
					console.log("error" + jqXHR.responseText);
					
					var payload = "<tr><td colspan=\"4\">" + jqXHR.responseText + "</td></tr>";
					$("#examtable").append(payload);
				}
			});
			
		});
		
	});
	
	</script>
	
	
<!-- Note bearbeiten-->
	<script>	
			
		$('#bearbeiten').click(function() {
			
			var inhalt = document.getElementById('noteFest');
			console.log(inhalt.innerHTML);
			
			var payload = "<td><input type=\"text\" class=\"form-control\" id=\"note\" placeholder=\"note\" value=\'" + inhalt.innerHTML + "\' name=\"note\" /></td>"
				
			$('#noteFest').html(payload);
			document.getElementById('bearbeitenIcon').style.visibility = 'hidden';
		
		});
	
	</script>
	
</body>
</html>