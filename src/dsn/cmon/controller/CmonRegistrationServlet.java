package dsn.cmon.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import dsn.cmon.secure.Authentication;

/**
 * Servlet implementation class CmonRegistrationServlet
 */
@WebServlet("/CmonRegistrationServlet")
public class CmonRegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static Logger log = Logger.getLogger(CmonRegistrationServlet.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CmonRegistrationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Authentication auth = new Authentication();

		if (auth.isValid()) {
			log.info("Authentication is valid. Page will be redirect to /login.jsp.");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		} else {
			log.info("Authentication is not valid. Page will be redirect to /registration.jsp.");
			request.getRequestDispatcher("/registration.jsp").forward(request, response);
		}
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
