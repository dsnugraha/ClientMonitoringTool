package dsn.cmon.job;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.namespace.QName;

import org.apache.log4j.Logger;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import dsn.cmon.dao.DataStaticDAOImpl;
import dsn.cmon.dao.UserBrokerDAOImpl;
import dsn.cmon.model.DataStatic;
import dsn.cmon.model.UserBroker;
import dsn.cmon.secure.Authentication;
import dsn.cmon.util.GlobalVariable;
import man.svc.datastatic.DataStaticInvestorService;
import man.svc.datastatic.DataStaticInvestorService_Service;
import man.svc.datastatic.schema.DataStaticInvestorRequest;
import man.svc.datastatic.schema.DataStaticInvestorResponse;
import man.svc.datastatic.schema.ksei.MessageType;
import man.svc.datastatic.schema.ksei.MessageType.Field;

@DisallowConcurrentExecution
public class DataStaticJob implements Job {
	static Logger log = Logger.getLogger("JOB");
	private static final QName SERVICE_NAME = new QName("http://www.mandiri.co.id/services/DataStaticInvestor",
			"DataStaticInvestorService");
	private static DataStaticDAOImpl dataStaticDAOImpl;
	private static UserBrokerDAOImpl userBrokerDAOImpl;

	public DataStaticJob() {
		// TODO Auto-generated constructor stub
		dataStaticDAOImpl = new DataStaticDAOImpl();
		userBrokerDAOImpl = new UserBrokerDAOImpl();
	}

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		// TODO Auto-generated method stub
		URL wsdlURL = DataStaticInvestorService_Service.WSDL_LOCATION;
		Date currentFireTime;
		currentFireTime = context.getFireTime();

		try {
			Authentication auth = new Authentication();

			if (auth.isValid()) {
				DataStaticInvestorService_Service service = new DataStaticInvestorService_Service(wsdlURL,
						SERVICE_NAME);
				DataStaticInvestorService port = service.getDataStaticInvestorServicePort();

				int counter = 0;
				Date currDate = new Date();
				SimpleDateFormat df_acktime = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

				String extref = "externalReference";
				String participantid = "participantID";
				String participanname = "participantName";
				String investorname = "investorName";
				String sidno = "sidNumber";
				String acctno = "accountNumber";
				String bankacctno = "bankAccountNumber";
				String bankcode = "bankCode";
				String activitydate = "activityDate";
				String activity = "activity";
				String ackstatus = "ackStatus";
				String acktime = "ackTime";

				UserBroker userBroker = userBrokerDAOImpl.getUserBroker();

				log.info("Loading Data Static Job...");
				log.info("--------------------------------------------------------------------------------");
				log.info("Instance ID " + context.getFireInstanceId() + " Data Static Job was fired.");
				log.info("Data Static Job Scheduled Fire Time " + currentFireTime);
				log.info("--------------------------------------------------------------------------------");

				long startloop = System.currentTimeMillis();
				for (int i = 1; i < i + 1; i++) {
					/**
					 * call web service
					 * 
					 * 
					 */
					DataStaticInvestorRequest req = new DataStaticInvestorRequest();
					req.setUsername(userBroker.getUsername());
					req.setPassword(userBroker.getPassword());

					long startcallws = System.currentTimeMillis();
					DataStaticInvestorResponse res = port.requestDataStaticInvestor(req);
					long endcallws = System.currentTimeMillis();
					long elapsedcallws = endcallws - startcallws;
					log.info(wsdlURL + " elapsed time " + elapsedcallws + " ms.");

					List<MessageType> listmsg = res.getMessage();

					counter = listmsg.size();
					if (counter > 0) {
						log.info("Data Static Job " + counter + " record(s) should be executed.");
						long startmsgloop = System.currentTimeMillis();
						List<DataStatic> datastatics = new ArrayList<DataStatic>();
						for (int j = 0; j < listmsg.size(); j++) {
							DataStatic datastatic = new DataStatic();
							List<Field> listfield = listmsg.get(j).getField();
							for (int k = 0; k < listfield.size(); k++) {
								datastatic.setDatetime(new java.sql.Timestamp(currDate.getTime()));
								if (listfield.get(k).getName().compareToIgnoreCase(extref) == 0) {
									datastatic.setExtref(listfield.get(k).getValue().trim());
								}
								if (listfield.get(k).getName().compareToIgnoreCase(participantid) == 0) {
									datastatic.setParticipantid(listfield.get(k).getValue().trim());
								}
								if (listfield.get(k).getName().compareToIgnoreCase(participanname) == 0) {
									datastatic.setParticipanname(listfield.get(k).getValue().trim());
								}
								if (listfield.get(k).getName().compareToIgnoreCase(investorname) == 0) {
									datastatic.setInvestorname(listfield.get(k).getValue().trim());
								}
								if (listfield.get(k).getName().compareToIgnoreCase(sidno) == 0) {
									datastatic.setSidno(listfield.get(k).getValue().trim());
								}
								if (listfield.get(k).getName().compareToIgnoreCase(acctno) == 0) {
									datastatic.setAcctno(listfield.get(k).getValue().trim());
								}
								if (listfield.get(k).getName().compareToIgnoreCase(bankacctno) == 0) {
									datastatic.setBankacctno(listfield.get(k).getValue().trim());
								}
								if (listfield.get(k).getName().compareToIgnoreCase(bankcode) == 0) {
									datastatic.setBankcode(listfield.get(k).getValue().trim());
								}
								if (listfield.get(k).getName().compareToIgnoreCase(activitydate) == 0) {
									datastatic.setActivitydate(listfield.get(k).getValue().trim());
								}
								if (listfield.get(k).getName().compareToIgnoreCase(activity) == 0) {
									datastatic.setActivity(listfield.get(k).getValue().trim());
								}
								if (listfield.get(k).getName().compareToIgnoreCase(ackstatus) == 0) {
									datastatic.setAckstatus(listfield.get(k).getValue().trim());
								}
								if (listfield.get(k).getName().compareToIgnoreCase(acktime) == 0) {
									if (listfield.get(k).getValue().trim().compareToIgnoreCase("null") == 0){
										datastatic.setAcktime(null);																			
									}else{
										Date acktime_date = df_acktime.parse(listfield.get(k).getValue().trim());
										datastatic.setAcktime(new java.sql.Date(acktime_date.getTime()));										
									}
								}
							}
							datastatics.add(datastatic);
						}
						
						dataStaticDAOImpl.addDataStatic(datastatics);

						long endmsgloop = System.currentTimeMillis();
						long elapsedtimemsgloop = endmsgloop - startmsgloop;
						log.info("Store Data Static Job to table. Elapsed time " + elapsedtimemsgloop + " ms.");

					} else {
						log.info("Data Static Job no record(s).");
					}

					if ((counter <= 0) || (GlobalVariable.STOP_DATASTATIC_LOOP)) {
						break;
					}
				}
				long endloop = System.currentTimeMillis();
				long elapsedtimeloop = endloop - startloop;
				log.info("Instance ID " + context.getFireInstanceId() + " Data Static Job was executed. Elapsed time "
						+ elapsedtimeloop + " ms.");
			} else {
				log.info("Application Authentication Failure.");

			}

		} catch (ArrayIndexOutOfBoundsException e) {
			// TODO Auto-generated catch block
			log.error("StackTrace: " + e.getStackTrace());
			log.error("Message: " + e.getMessage());
			e.printStackTrace();

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			log.error("StackTrace: " + e.getStackTrace());
			log.error("Message: " + e.getMessage());
			e.printStackTrace();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("StackTrace: " + e.getStackTrace());
			log.error("Message: " + e.getMessage());
			e.printStackTrace();
		}
	}
}