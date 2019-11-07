<%
	String username = (String) request.getAttribute("username");
	String ischanged = (String) request.getAttribute("changed");
%>
<div align="center">
	<%
		if (ischanged == "0") {
	%>
	<form method="post" action="admin.do">
		<table width="500" border="0">
			<tr>
				<td colspan="2">&nbsp;</td>
			</tr>
			<tr>
				<td>Old Password</td>
				<td><input type="password" name="oldpassword" maxlength="8" />
					<font size="1">(Max. 8 character)</font></td>
			</tr>
			<tr>
				<td>New Password</td>
				<td><input type="password" name="newpassword" maxlength="8" />
					<font size="1">(Max. 8 character)</font></td>
			</tr>
			<tr>
				<td>Confirm Password</td>
				<td><input type="password" name="confirmpassword" maxlength="8" />
					<font size="1">(Max. 8 character)</font></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="Update" /></td>
			</tr>
		</table>
		<input type="hidden" name="action" value="update" /> <input
			type="hidden" name="username" value="<%=username%>" />
	</form>
	<%
		} else {
	%>
	<div class="fieldMargin error smallText">
		<p align="center">
			<font color="#000000"> Password has been change successfully.
			</font>
		</p>
	</div>
	<%
		}
	%>
</div>