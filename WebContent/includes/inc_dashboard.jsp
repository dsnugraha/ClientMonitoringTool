<%@ page import="java.util.List"%>
<%@ page import="dsn.cmon.model.Job"%>
<%
	List<Job> listJob = (List<Job>) request.getAttribute("listJob");
	int balanceRec = (Integer) request.getAttribute("balanceRec");
	int statementRec = (Integer) request.getAttribute("statementRec");
	int datastaticRec = (Integer) request.getAttribute("datastaticRec");
%>

<p>&nbsp;</p>
<p>&nbsp;</p>
<div align="center">
	<table width="500" border="0" cellpadding="0" cellspacing="10">
		<tr align="center">
			<%
				for (int i = 0; i < listJob.size(); i++) {
					String jobName = listJob.get(i).getName();
			%>
			<td height="100"><a href="<%=listJob.get(i).getUrl()%>"> <img
					src="images/<%=listJob.get(i).getColor()%>.jpg" height="100"
					width="100"></a>
				<p>&nbsp;</p>
				<p><%=listJob.get(i).getStatus()%><br /> <strong><%=listJob.get(i).getName()%></strong>
				</p>
				<p align="center">
					<%
						if (jobName.compareToIgnoreCase("Balance Job") == 0) {
					%>
					<%=balanceRec%>
					<%
						}
					%>
					<%
						if (jobName.compareToIgnoreCase("Statement Job") == 0) {
					%>
					<%=statementRec%>
					<%
						}
					%>
					<%
						if (jobName.compareToIgnoreCase("Data Static Job") == 0) {
					%>
					<%=datastaticRec%>
					<%
						}
					%>
					record(s)
				</p></td>
			<%
				}
			%>
		</tr>
	</table>
</div>