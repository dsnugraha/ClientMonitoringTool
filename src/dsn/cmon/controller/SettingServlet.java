package dsn.cmon.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import dsn.cmon.dao.CronSchedulerDAO;
import dsn.cmon.dao.CronSchedulerDAOImpl;
import dsn.cmon.model.CronScheduler;

/**
 * Servlet implementation class SettingServlet
 */
@WebServlet("/SettingServlet")
public class SettingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static Logger log = Logger.getLogger(SettingServlet.class);
	private CronSchedulerDAO dao;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SettingServlet() {
		super();
		// TODO Auto-generated constructor stub
		dao = new CronSchedulerDAOImpl();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		response.setContentType("text/html;charset=UTF-8");

		String action = request.getParameter("action");
		String url_page_setting = "main.do?page=setting";
		String msg = "";

		if (action == null)
			action = "list";

		if (action.equalsIgnoreCase("update")) {

			String itr = request.getParameter("totLoop");
			Map<String, String> map = new HashMap<String, String>();

			boolean isUpdate = false;

			for (int i = 1; i <= Integer.parseInt(itr); i++) {
				String jobName = request.getParameter("job_" + i);
				String cronExpression = request.getParameter("cron_expression_" + i);

				if (cronExpression.trim().length() <= 0)
					msg += jobName + " cannot be empty.<br />";

				map.put(jobName, cronExpression);
			}

			if (msg.trim().length() <= 0)
				isUpdate = true;

			if (isUpdate) {
				CronScheduler cronScheduler = new CronScheduler();

				Set<String> keys = map.keySet();
				for (String key : keys) {
					cronScheduler.setJobname(key);
					cronScheduler.setCronexpression((String) map.get(key));

						if (!dao.updateCronScheduler(cronScheduler)){
							throw new ServletException("ERROR dao.updateCronScheduler(cronScheduler)");
						}

				}
				log.info("Job Cron Expression has been successfully updated.");

				msg = "Please press the \"Continue\" button to restart the Job. Or press the \"Manual Process\" button to restart the Job manually.";
				request.setAttribute("msg", msg);
				url_page_setting = "main.do?page=setting.restart";

				log.info(msg);

			}
		}

		try {
			request.setAttribute("listjob", dao.getCronScheduler());

		} catch (Exception e) {
		}

		request.setAttribute("msg", msg);
		request.getRequestDispatcher(url_page_setting).forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
