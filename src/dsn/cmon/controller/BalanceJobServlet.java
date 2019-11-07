package dsn.cmon.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import dsn.cmon.job.BalanceSchedule;

/**
 * Servlet implementation class BalanceJobServlet
 */
@WebServlet("/BalanceJobServlet")
public class BalanceJobServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static Logger logapp = Logger.getLogger(BalanceJobServlet.class);
	static Logger log = Logger.getLogger("JOB");

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BalanceJobServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		BalanceSchedule balanceSchedule = new BalanceSchedule();
		String action = request.getParameter("act");

		if (action.equals("start")) {
			log.info("Trying to start BalanceJob...");
			try {
				balanceSchedule.start();

			} catch (Exception e) {
				log.warn("BalanceJob cannot start.");
				throw new ServletException("ERROR balanceSchedule.start()");
			}
			log.info("BalanceJob has been started.");
			logapp.info("BalanceJob is RUNNING.");

		} else {
			log.info("Trying to stop BalanceJob...");
			balanceSchedule.stop();
			if (balanceSchedule.isStarted()){
				log.info("Trying to stop BalanceJob...");
			}else{
				log.info("BalanceJob has been stopped.");
			}
			logapp.info("BalanceJob is STOPPED.");

		}

		response.sendRedirect("dashboard.do");

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
