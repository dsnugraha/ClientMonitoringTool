package dsn.cmon.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import dsn.cmon.dao.ValidationDAO;
import dsn.cmon.dao.ValidationDAOImpl;
import dsn.cmon.secure.Authentication;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static Logger log = Logger.getLogger(LoginServlet.class);
	private ValidationDAO dao_validation;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
		dao_validation = new ValidationDAOImpl();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String msg = "";

		Authentication auth = new Authentication();

		if (auth.isValid()) {
			if (dao_validation.checkUser(username, password)) {
				HttpSession session = request.getSession(true);
				session.setAttribute("currentSessionUser", username);

				response.sendRedirect("dashboard.do"); // logged-in page
				log.info("User have logged in successfully.");

			} else {
				if (username.isEmpty() || username == null)
					msg = "Username cannot be empty.<br />";
				if (password.isEmpty() || password == null)
					msg += "Password cannot be empty.<br />";
				if (username.isEmpty() == false) {
					if (password.isEmpty() == false)
						msg += "Login failed bad username or password.";
				}

				log.info(msg);
				
				request.setAttribute("msg", msg);
				request.getRequestDispatcher("./").forward(request, response);
				log.info("Login failed.");
			}
		}else{
			PrintWriter out = response.getWriter();
			out.println("<p align=\"center\">Application Authentication Failure.</p>");
			log.info("Application Authentication Failure.");
			
		}
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
