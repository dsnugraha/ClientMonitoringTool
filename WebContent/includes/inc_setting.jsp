<%@page import="dsn.cmon.model.CronScheduler"%>
<%@page import="java.util.ArrayList"%>
<div align="center">
	<table width="800" border="0">
		<tr>
			<td colspan="2">&nbsp;</td>
		</tr>
		<tr valign="top">
			<td width="350">
				<form method="post" action="setting.do">
					<table width="350" border="0" cellpadding="0" cellspacing="10">
						<%
							int i = 1;
							ArrayList<CronScheduler> list = (ArrayList<CronScheduler>) request.getAttribute("listjob");
							for (CronScheduler cronScheduler : list) {
						%>
						<tr>
							<td><%=cronScheduler.getAliasName()%><input type="hidden"
								name="job_<%=i%>" value="<%=cronScheduler.getJobname()%>" /></td>
							<td><input type="text" name="cron_expression_<%=i%>"
								value="<%=cronScheduler.getCronexpression()%>" /></td>
						</tr>

						<%
							i++;
							}
						%>
						<tr>
							<td></td>
							<td><input type="submit" value="Update" /></td>
						</tr>
					</table>
					<input type="hidden" name="action" value="update" /> <input
						type="hidden" name="totLoop" value="<%=list.size()%>" />
				</form>
			</td>
			<td><font size="2"> Cron expressions can be as simple as
					<strong>* * * * ? *</strong> or as complex as <strong>0
						0/5 14,18,3-39,52 ? JAN,MAR,SEP MON-FRI 2002-2010</strong>.<br /> <br />
					Here are some more examples:
			</font>
				<table width="450" border="0" style="font-size: 12px">
					<tr>
						<td width="150"><strong>Expression</strong></td>
						<td><strong>Means</strong></td>
					</tr>
					<tr>
						<td>0 0 12 * * ?</td>
						<td>Fire at 12:00 PM (noon) every day</td>
					</tr>
					<tr>
						<td>0 15 10 ? * *</td>
						<td>Fire at 10:15 AM every day</td>
					</tr>
					<tr>
						<td>0 15 10 * * ?</td>
						<td>Fire at 10:15 AM every day</td>
					</tr>
					<tr>
						<td>0 15 10 * * ? *</td>
						<td>Fire at 10:15 AM every day</td>
					</tr>
					<tr>
						<td>0 15 10 * * ? 2005</td>
						<td>Fire at 10:15 AM every day during the year 2005</td>
					</tr>
					<tr>
						<td>0 * 14 * * ?</td>
						<td>Fire every minute starting at 2:00 PM and ending at 2:59
							PM, every day</td>
					</tr>
					<tr>
						<td>0 0/5 14 * * ?</td>
						<td>Fire every 5 minutes starting at 2:00 PM and ending at
							2:55 PM, every day</td>
					</tr>
					<tr>
						<td>0 0/5 14,18 * * ?</td>
						<td>Fire every 5 minutes starting at 2:00 PM and ending at
							2:55 PM, AND fire every 5 minutes starting at 6:00 PM and ending
							at 6:55 PM, every day</td>
					</tr>
					<tr>
						<td>0 0-5 14 * * ?</td>
						<td>Fire every minute starting at 2:00 PM and ending at 2:05
							PM, every day</td>
					</tr>
					<tr>
						<td>0 10,44 14 ? 3 WED</td>
						<td>Fire at 2:10 PM and at 2:44 PM every Wednesday in the
							month of March</td>
					</tr>
					<tr>
						<td>0 15 10 ? * MON-FRI</td>
						<td>Fire at 10:15 AM every Monday, Tuesday, Wednesday,
							Thursday and Friday</td>
					</tr>
					<tr>
						<td>0 15 10 15 * ?</td>
						<td>Fire at 10:15 AM on the 15th day of every month</td>
					</tr>
					<tr>
						<td>0 15 10 L * ?</td>
						<td>Fire at 10:15 AM on the last day of every month</td>
					</tr>
					<tr>
						<td>0 15 10 ? * 6L</td>
						<td>Fire at 10:15 AM on the last Friday of every month</td>
					</tr>
					<tr>
						<td>0 15 10 ? * 6L 2002-2005</td>
						<td>Fire at 10:15 AM on every last friday of every month
							during the years 2002, 2003, 2004, and 2005</td>
					</tr>
					<tr>
						<td>0 15 10 ? * 6#3</td>
						<td>Fire at 10:15 AM on the third Friday of every month</td>
					</tr>
					<tr>
						<td>0 0 12 1/5 * ?</td>
						<td>Fire at 12 PM (noon) every 5 days every month, starting
							on the first day of the month</td>
					</tr>
					<tr>
						<td>0 11 11 11 11 ?</td>
						<td>Fire every November 11 at 11:11 AM</td>
					</tr>
				</table></td>
		</tr>
	</table>
</div>