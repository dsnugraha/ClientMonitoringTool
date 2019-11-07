package dsn.cmon.controller;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.namespace.QName;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;

import dsn.cmon.dao.UserBrokerDAO;
import dsn.cmon.dao.UserBrokerDAOImpl;
import dsn.cmon.job.HeartbeatSchedule;
import dsn.cmon.model.UserBroker;
import man.svc.validation.BrokerValidationService;
import man.svc.validation.BrokerValidationService_Service;
import man.svc.validation.schema.BrokerValidationRequest;
import man.svc.validation.schema.BrokerValidationResponse;
import man.svc.validation.schema.ValidationType;

/**
 * Servlet implementation class ActivationServlet
 */
@WebServlet("/ActivationServlet")
public class ActivationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static Logger log = Logger.getLogger(ActivationServlet.class);
	private static final QName SERVICE_NAME = new QName("http://www.mandiri.co.id/services/BrokerValidation",
			"BrokerValidationService");

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ActivationServlet() {
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
		String partid = request.getParameter("partid").trim().toUpperCase();
		String serialnum1 = request.getParameter("snum1").trim().toUpperCase();
		String serialnum2 = request.getParameter("snum2").trim().toUpperCase();
		String serialnum3 = request.getParameter("snum3").trim().toUpperCase();
		String serialnum4 = request.getParameter("snum4").trim().toUpperCase();

		String serialnum = serialnum1.concat("-").concat(serialnum2).concat("-").concat(serialnum3).concat("-")
				.concat(serialnum4);
		String msg = "";

		if (partid.isEmpty() || serialnum1.isEmpty() || serialnum2.isEmpty() || serialnum3.isEmpty()
				|| serialnum4.isEmpty()) {
			msg = "Activation failed.";

			request.setAttribute("msg", msg);
			request.getRequestDispatcher("/registration.jsp").forward(request, response);
			log.info("Activation failed.");

		} else {
			BrokerValidationResponse res = null;

			try {
				URL wsdlURL = BrokerValidationService_Service.WSDL_LOCATION;

				BrokerValidationService_Service service = new BrokerValidationService_Service(wsdlURL, SERVICE_NAME);
				BrokerValidationService port = service.getBrokerValidationServicePort();

				BrokerValidationRequest req = new BrokerValidationRequest();

				req.setUsername(partid);
				req.setSerialNumber(serialnum);

				res = port.requestBrokerValidation(req);

			} catch (Exception e) {
				msg = "Connection to BMAN Server failed.";

				request.setAttribute("msg", msg);
				request.getRequestDispatcher("/registration.jsp").forward(request, response);
				log.info("Connection to BMAN Server failed.");
			}

			List<ValidationType> list = res.getValidation();

			if (list.size() > 0) {
				UserBroker userBroker = new UserBroker();
				UserBrokerDAO dao = new UserBrokerDAOImpl();
				String wsusr = list.get(0).getWsUser().trim().toUpperCase();
				String wspwd = list.get(0).getWsPasswd().trim();
				userBroker.setUsername(wsusr);
				userBroker.setPassword(wspwd);
				userBroker.setSerialnum(serialnum);
				userBroker.setActivation(1);
				dao.upsertUserBroker(userBroker);

				UserBroker ub = dao.getUserBroker();

				String ubusr = ub.getUsername().trim();
				String ubpwd = ub.getPassword().trim();
				String ubsn = ub.getSerialnum().trim();

				String content = ubusr.concat(ubpwd).concat(ubsn);

				String dir = System.getProperty("user.home");
				String fc = DigestUtils.md5Hex(content);

				File file = new File(dir + File.separator + ".cmon");
				
				if (!file.exists()){
					file.mkdir();
				}

				DataOutputStream output = new DataOutputStream(new FileOutputStream(dir + File.separator + ".cmon" + File.separator + "CMON.DAT"));
				output.writeUTF(fc);
				output.close();
				
				//start heartbeat
				HeartbeatSchedule heartbeatSchedule = new HeartbeatSchedule();
				heartbeatSchedule.start();

				log.info("Activation succeessfully completed.");
				request.getRequestDispatcher("./").forward(request, response);

			} else {
				msg = "Activation failed.";

				request.setAttribute("msg", msg);
				request.getRequestDispatcher("/registration.jsp").forward(request, response);
				log.info("Activation failed.");
			}
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
