package dsn.cmon.job;

import java.net.URL;

import javax.xml.namespace.QName;

import org.apache.log4j.Logger;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import dsn.cmon.dao.UserBrokerDAOImpl;
import dsn.cmon.model.UserBroker;
import dsn.cmon.secure.Authentication;
import man.svc.heartbeat.HeartbeatService;
import man.svc.heartbeat.HeartbeatService_Service;
import man.svc.heartbeat.schema.HeartbeatRequest;
import man.svc.heartbeat.schema.HeartbeatResponse;

@DisallowConcurrentExecution
public class HeartbeatJob implements Job {
	static Logger log = Logger.getLogger("HEARTBEAT");
	private static final QName SERVICE_NAME = new QName("http://www.mandiri.co.id/services/Heartbeat",
			"HeartbeatService");
	private static UserBrokerDAOImpl userBrokerDAOImpl;

	public HeartbeatJob() {
		// TODO Auto-generated constructor stub
		userBrokerDAOImpl = new UserBrokerDAOImpl();
	}

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		// TODO Auto-generated method stub
		URL wsdlURL = HeartbeatService_Service.WSDL_LOCATION;

		try {
			Authentication auth = new Authentication();

			if (auth.isValid()) {
				HeartbeatService_Service service = new HeartbeatService_Service(wsdlURL, SERVICE_NAME);
				HeartbeatService port = service.getHeartbeatServicePort();

				UserBroker userBroker = userBrokerDAOImpl.getUserBroker();

				HeartbeatRequest req = new HeartbeatRequest();

				req.setUsername(userBroker.getUsername());
				req.setPassword(userBroker.getPassword());

				log.info("Loading Heartbeat Job...");
				log.info("Instance ID " + context.getFireInstanceId() + " Heartbeat was fired.");

				long startcallws = System.currentTimeMillis();
				HeartbeatResponse res = port.sendHeartbeat(req);
				long endcallws = System.currentTimeMillis();
				long elapsedcallws = endcallws - startcallws;
				log.info(wsdlURL + " elapsed time " + elapsedcallws + " ms.");

				String status = res.getStatus().trim().toUpperCase();
				log.info("Server status " + status + ".");
				log.info("Instance ID " + context.getFireInstanceId() + " Heartbeat was executed.");
			}else{
				log.info("Application Authentication Failure.");

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("StackTrace: " + e.getStackTrace());
			log.error("Message: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
