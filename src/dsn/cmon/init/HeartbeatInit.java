package dsn.cmon.init;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;

import dsn.cmon.job.HeartbeatSchedule;

public class HeartbeatInit extends HttpServlet {
	static Logger log = Logger.getLogger("HEARTBEAT");

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public HeartbeatInit() {
		// TODO Auto-generated constructor stub
		super();
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("HeartbeatInit is initializing job");

		HeartbeatSchedule heartbeatSchedule = new HeartbeatSchedule();
		heartbeatSchedule.start();

	}

}
