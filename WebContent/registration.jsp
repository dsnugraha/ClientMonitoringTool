<%@ page language="java" contentType="text/html; charset=windows-1256"
	pageEncoding="windows-1256"%>
<%
	response.addHeader("Cache-Control",
			"no-cache,no-store,private,must-revalidate,max-stale=0,post-check=0,pre-check=0");
	response.addHeader("Pragma", "no-cache");
	response.addDateHeader("Expires", 0);

	String msg = (String) request.getAttribute("msg");
%>
<html>
<head>
<title>C'MON | Client Monitoring Tool v.1.0</title>
<link rel="icon" href="images/favicon.ico" type="image/x-icon">
<link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon"> 
<link rel="stylesheet" type="text/css" href="css/log4j-style.css">
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<style>
input.uppercase {
    text-transform: uppercase;
}
</style>
</head>
<body class="body" style="margin: 0px;">
	<div id="fullPage">
		<div id="brandingWrapper" class="float">
			<div id="branding" class="illustrationClass"></div>
		</div>
		<div id="contentWrapper" class="float">
			<div id="content">
				<div id="header" style="height: 131px">
					<img src="images/logo_mandiri.jpg" alt="Bank Mandiri" width="275"
						height="131" class="logoImage">
				</div>
				<div id="workArea" class="groupMargin">
					<div id="authArea" class="groupMargin">
						<div id="loginArea">
							<div id="loginMessage" class="groupMargin" style="color: #cadce6; font-weight: bold;">C'MON | Client
								Monitoring Tool v.1.0</div>

							<form action="activation.do" method="POST">
								<div id="error" class="fieldMargin error smallText">
									<label id="errorText" for=""> <%
 	if (msg != null) {
 		out.println(msg);
 	}
 %></label>
								</div>
								<div id="formsAuthenticationArea">
									PartID
									<div id="userNameArea">
										<input class="uppercase" type="text" name="partid" />
									</div>
									Serial Number
									<div id="passwordArea">
										<input class="uppercase" type="text" name="snum1" size="4" maxlength="4" />-
										<input class="uppercase" type="text" name="snum2" size="4" maxlength="4" />-
										<input class="uppercase" type="text" name="snum3" size="4" maxlength="4" />-
										<input class="uppercase" type="text" name="snum4" size="4" maxlength="4" />
									</div>
									<div id="submissionArea" class="submitMargin">
										<input type="submit" value="Activate" />
									</div>
								</div>
							</form>

							<div id="introduction" class="groupMargin">
								<p></p>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div id="footer">
				<div id="footerLinks" class="floatReverse">
					<div>
						<span id="copyright">© 2016 Mitra Integrasi Informatika</span>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
