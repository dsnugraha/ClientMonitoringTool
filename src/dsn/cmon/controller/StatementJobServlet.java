package dsn.cmon.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import dsn.cmon.job.StatementSchedule;

/**
 * Servlet implementation class StatementJobServlet
 */
@WebServlet("/StatementJobServlet")
public class StatementJobServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static Logger logapp = Logger.getLogger(StatementJobServlet.class);
	static Logger log = Logger.getLogger("JOB");
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StatementJobServlet() {
        super();
        // TODO Auto-generated constructor stub

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		StatementSchedule statementSchedule = new StatementSchedule();
		String action = request.getParameter("act");

		if (action.equals("start")) {
			log.info("Trying to start StatementJob...");			
			try {
				statementSchedule.start();

			} catch (Exception e) {
				log.warn("StatementJob cannot start.");
				throw new ServletException("ERROR statementSchedule.start();");
			}
			log.info("StatementJob has been started.");
			logapp.info("StatementJob is RUNNING.");

		} else {
			log.info("Trying to stop StatementJob...");
			statementSchedule.stop();
			if (statementSchedule.isStarted()){
				log.info("Trying to stop StatementJob...");
			}else{
				log.info("StatementJob has been stopped.");
			}
			logapp.info("StatementJob is STOPPED.");

		}
		
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
