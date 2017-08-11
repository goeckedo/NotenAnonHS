<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
    <%@ page import="dao.ExamManager" %>
    <%@ page import="dao.GenericManager" %>
 	<%@ page import="dao.UserManager" %>
 	<%@ page import="entity.User_" %>
    <%@ page import="entity.Exam" %>
    <%@ page import="java.util.Collection" %>
	<%@ page import="dao.ExamDetailsManager" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Prüfungsverwaltung</title>
		<meta charset="UTF-8">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap.css" />
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css" />
 		
 		<%@ page import="entity.Faculty" %>
 		<%
 		
		//HttpSession httpSession = request.getSession(false);
		//	String userName = (String)httpSession.getAttribute("user");
		// int userID = Integer.parseInt((String)httpSession.getAttribute("userID"));
		String rolle = (String)request.getAttribute("faculty");
		//muss int sein
		int userid = (Integer)request.getAttribute("id");
		//int userId= Integer.parseInt("userid");
		String user = (String)request.getAttribute("username");
		
		
		%> 
 		
</head>
<body>
<!-- Abstand nach Oben -->
		<div class="container">
			<div class="row">
				<div class="col-md-12" style="height: 16px; margin-top: 2px; margin-left: -10px;">
				</div>
			</div>
		</div>

		<!-- MainContainer hier kommt ales rein -->
		<div class="container" id="maincontainer" style="background-color: #e5e5e5">
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
								<h1>Anonymisierung - Übersicht</h1>
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
				<%@ include file="navi.jspf" %>
			</div>

				<!-- Body für Seiteninhanlt-->
				<div class="col-md-10" style="padding-left: 10px;">
					<div id="content">
						<!--Hier beginng der Administrator Teil -->
	<% 	
		if(rolle.equals("ADMIN")){
			out.println("<div>");
			out.println("<div class=\"row\">");
			out.println("<div class=\"col-md-12\">");
			out.println("<p>Lieber Admin, Willkommen auf der Übersicht!</p>");
			out.println("</div>");
			UserManager um = new UserManager("webProj");
			Collection<User_> users = (Collection<User_>)um.list();
			int counter = 1;
			for(User_ u : users){
				out.println("<div class=\"row\">");
				out.println("<div class=\"col-md-3\">");
				out.println("<label>" + u.getName() + "</label>");
				out.println("</div>");
				
				out.println("<div class=\"col-md-6\">");
				out.println("<hr />");
				out.println("<div id=\"Button" + counter + "\" class=\"collapse\">");
			
			ExamManager em = new ExamManager("webProj"); 
			Collection<Exam> exams = em.getExamsByUserId(u.getUserID());
			if(exams.isEmpty()){
					System.out.println("Keine Prüfungen vorhanden für " + u.getName());
					out.println("<p>Keine Prüfungen vorhanden</p>");
				}else{
					out.println("<ul>");
					for(Exam e : exams)
						out.println("<li>" + e.getSortname() + "</li>");
					out.println("</ul>");
				}
				out.println("</div></div>");
				
				out.println("<div class=\"col-md-3\">");
				out.println("<button type=\"button\" class=\"btn\" data-toggle=\"collapse\" data-target=\"#Button" + counter + "\">+</button>");	
				out.println("</div>"); 
				out.println("</div>");
				counter++;
			}
		}else if(!rolle.equals("ADMIN")){
			out.println("<div>");
			out.println("<div class=\"row\">");
			out.println("<div class=\"col-md-12\">");
			out.println("<p>Liebe/r " + username + ", Wilkommen auf der Übersicht Seite!</p>");
			out.println("</div>");
			out.println("<div class=\"col-md-12\">");
			
			ExamManager em = new ExamManager("webProj");
			Collection<Exam> exams = (Collection<Exam>)request.getAttribute("examList");
			if(exams.isEmpty()){
				out.println("<p>Keine Prüfungen vorhanden</p>");
			}else{
				out.println("<ul>");
				for(Exam e : exams)
					out.println("<li>" + e.getSortname() + "</li>");
				out.println("</ul>");
			}
			out.println("</div></div></div>");
		}else{
			out.println("<p>Es ist ein Fehler aufgetreten!</p>");
		}
	%>
	
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
	</div>
	
	<script>
		alert(rolle);
	</script>
	
</body>
</html>