package dsn.cmon.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import dsn.cmon.job.BalanceSchedule;
import dsn.cmon.job.DataStaticSchedule;
import dsn.cmon.job.StatementSchedule;

/**
 * Servlet implementation class AutoRestartJobServlet
 */
@WebServlet("/AutoRestartJobServlet")
public class AutoRestartJobServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static Logger log = Logger.getLogger(AutoRestartJobServlet.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AutoRestartJobServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		BalanceSchedule balanceSchedule = new BalanceSchedule();
		StatementSchedule statementSchedule = new StatementSchedule();
		DataStaticSchedule dataStaticSchedule = new DataStaticSchedule();
		
		log.info("Trying to restart all of Job Scheduler...");
		
		if (balanceSchedule.isStarted()){
			balanceSchedule.stop();
			balanceSchedule.start();
		}else{
			balanceSchedule.start();
		}

		log.info("BalanceJob has been restarted.");

		if (statementSchedule.isStarted()){
			statementSchedule.stop();
			statementSchedule.start();
		}else{
			statementSchedule.start();
		}

		log.info("StatementJob has been restarted.");

		if (dataStaticSchedule.isStarted()){
			dataStaticSchedule.stop();
			dataStaticSchedule.start();
		}else{
			dataStaticSchedule.start();
		}

		log.info("DataStaticJob has been restarted.");
		
		log.info("Done. All of Job Scheduler has been restarted.");
		response.sendRedirect("dashboard.do");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
