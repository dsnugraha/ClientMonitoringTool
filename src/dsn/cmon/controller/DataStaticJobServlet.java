package dsn.cmon.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import dsn.cmon.job.DataStaticSchedule;

/**
 * Servlet implementation class DataStaticJobServlet
 */
@WebServlet("/DataStaticJobServlet")
public class DataStaticJobServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static Logger logapp = Logger.getLogger(DataStaticJobServlet.class);
	static Logger log = Logger.getLogger("JOB");
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DataStaticJobServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		DataStaticSchedule dataStaticSchedule = new DataStaticSchedule();
		String action = request.getParameter("act");

		if (action.equals("start")) {
			log.info("Trying to start DataStaticJob...");			
			try {
				dataStaticSchedule.start();

			} catch (Exception e) {
				log.warn("DataStaticJob cannot start.");
				throw new ServletException("ERROR dataStaticSchedule.start()");
			}
			log.info("DataStaticJob has been started.");
			logapp.info("DataStaticJob is RUNNING.");

		} else {
			log.info("Trying to stop DataStaticJob...");
			dataStaticSchedule.stop();
			if (dataStaticSchedule.isStarted()){
				log.info("Trying to stop DataStaticJob...");
			}else{
				log.info("DataStaticJob has been stopped.");
			}
			logapp.info("DataStaticJob is STOPPED.");

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
