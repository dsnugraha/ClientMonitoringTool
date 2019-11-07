<%@ page language="java" contentType="text/html; charset=windows-1256"
	pageEncoding="windows-1256"%>
<%@ page import="org.apache.log4j.Logger"%>
<%@ page import="dsn.cmon.secure.Authentication"%>

<%
Authentication auth = new Authentication();

if (!auth.isValid()){
	out.println("<p align=\"center\">Application Authentication Failure.</p>");

}else{
	response.addHeader("Cache-Control",
			"no-cache,no-store,private,must-revalidate,max-stale=0,post-check=0,pre-check=0");
	response.addHeader("Pragma", "no-cache");
	response.addDateHeader("Expires", 0);

	String msg = (String) request.getAttribute("msg");
	String userLogged = (String) session.getAttribute("currentSessionUser");
	String urlpage = request.getParameter("page");

	Logger log = Logger.getRootLogger();

	if (session.getAttribute("currentSessionUser") == null) {
		response.sendRedirect("./");
		log.info("Session timeout.");
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1256">
<title>C'MON | Client Monitoring Tool</title>
<link rel="icon" href="images/favicon.ico" type="image/x-icon">
<link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon"> 
<link rel="stylesheet" type="text/css" href="css/log4j-style.css">
<style type="text/css">
<!--
.style3 {
	color: #FFFFFF
}
-->
</style>
</head>
<body leftmargin="0" topmargin="0">
	<div
		style="background-image: url(images/header_menu.jpg); height: 94px; vertical-align: middle"
		align="right"></div>
	<div
		style="background-image: url(images/bg_dark_yellow.jpg); height: 40px; vertical-align: text-bottom; min-width: 700px; letter-spacing: 1pt">
		<font size="2"><span
			style="float: left; margin-left: 25px; margin-top: 7px"><a
				href="dashboard.do">Dashboard</a> <strong>|</strong> <a
				href="setting.do">Setting</a> <strong>|</strong> <a href="admin.do">Change
					Password</a> <strong>|</strong> <a href="logout.do">Logout</a> </span> <span
			style="width: 50px"></span> <span
			style="float: right; margin-right: 25px; margin-top: 7px">
				Logged user as <strong><%=userLogged%></strong>
		</span> </font>
	</div>
	<div style="background-color: #999999; height: 2px"></div>
	<div style="height: 5px"></div>
	<div id="error" class="fieldMargin error smallText">
		<p align="center">
			<label id="errorText" for=""> <%
 	if (msg != null) {
 		out.println(msg);
 	}
 %>
			</label>
		</p>
	</div>

	<%
		if (urlpage.equals("dashboard")) {
	%>
	<jsp:include page="includes/inc_dashboard.jsp" />
	<%
		}
		if (urlpage.equals("setting")) {
	%>
	<jsp:include page="includes/inc_setting.jsp" />
	<%
		}
		if (urlpage.equals("setting.restart")) {
	%>
	<jsp:include page="includes/inc_setting_restart.jsp" />
	<%
		}
		if (urlpage.equals("admin")) {
	%>
	<jsp:include page="includes/inc_admin.jsp" />
	<%
		}
	%>

<!--- start footer -->
	<div id="footer" style="margin-top: 250px">
		<div id="footerLinks" class="floatReverse">
			<div>
				<span id="copyright">© 2016 Mitra Integrasi Informatika</span>
			</div>
		</div>
	</div>
<!--- end footer -->
</body>
</html>
<%}%>