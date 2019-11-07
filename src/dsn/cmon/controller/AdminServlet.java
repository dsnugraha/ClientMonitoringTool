package dsn.cmon.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import dsn.cmon.dao.UserDAO;
import dsn.cmon.dao.UserDAOImpl;
import dsn.cmon.dao.ValidationDAO;
import dsn.cmon.dao.ValidationDAOImpl;

import dsn.cmon.model.User;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static Logger log = Logger.getLogger(AdminServlet.class);
	private UserDAO dao;
	private ValidationDAO dao_validation;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminServlet() {
		super();
		// TODO Auto-generated constructor stub
		dao = new UserDAOImpl();
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
		String oldpassword = request.getParameter("oldpassword");
		String newpassword = request.getParameter("newpassword");
		String confirmpassword = request.getParameter("confirmpassword");
		
		String changed = "0";

		String action = request.getParameter("action");
		String msg = "";

		if (action == null)
			action = "form";
		
		if (action.equalsIgnoreCase("update")) {
			if (oldpassword.isEmpty() || oldpassword == null)
				msg = "Old Password cannot be empty.<br />";
			if (newpassword.isEmpty() || newpassword == null)
				msg += "New Password cannot be empty.<br />";
			if (confirmpassword.isEmpty() || confirmpassword == null)
				msg += "Confirm Password cannot be empty.<br />";
			if (newpassword.equals(confirmpassword)== false) {
				msg += "New Password and Confirm Password do not match.<br />";
			}
						
			if (dao_validation.checkUser(username, oldpassword)==false)
				msg += "Old Password do not match.";

			if (msg.length()<=0){
				User user = new User();
				user.setUsername(username);
				user.setPassword(username.concat(newpassword));
				
				if (!dao.changePassword(user)){
					log.error("ERROR dao.changePassword(user)");
					throw new ServletException("ERROR dao.changePassword(user)");
				}
				changed = "1";

				log.info(msg);

			}
		}
		
		request.setAttribute("msg", msg);
		HttpSession session = request.getSession(true); 

		request.setAttribute("changed", changed);
		request.setAttribute("username", session.getAttribute("currentSessionUser"));
		request.getRequestDispatcher("main.do?page=admin").forward(request, response);
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