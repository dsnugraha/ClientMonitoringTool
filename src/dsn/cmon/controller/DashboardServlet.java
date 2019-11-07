package dsn.cmon.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import dsn.cmon.dao.BalanceDAOImpl;
import dsn.cmon.dao.DataStaticDAOImpl;
import dsn.cmon.dao.JobDAOImpl;
import dsn.cmon.dao.StatementBrokerDAOImpl;
import dsn.cmon.model.Job;

/**
 * Servlet implementation class DashboardServlet
 */
@WebServlet("/DashboardServlet")
public class DashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static Logger log = Logger.getLogger(DashboardServlet.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DashboardServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub	
		List<Job> listJobProperties = new JobDAOImpl().getProperties();
		
		int balanceRec = new BalanceDAOImpl().getCountRec();
		int statementRec = new StatementBrokerDAOImpl().getCountRec();
		int datastaticRec = new DataStaticDAOImpl().getCountRec();
		
		response.setContentType("text/html;charset=UTF-8");	
		request.setAttribute("listJob", listJobProperties);
		request.setAttribute("balanceRec", balanceRec);
		request.setAttribute("statementRec", statementRec);
		request.setAttribute("datastaticRec", datastaticRec);
		request.getRequestDispatcher("main.do?page=dashboard").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}