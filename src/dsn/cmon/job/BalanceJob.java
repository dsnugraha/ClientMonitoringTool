package dsn.cmon.job;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.namespace.QName;

import org.apache.log4j.Logger;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import dsn.cmon.dao.BalanceDAOImpl;
import dsn.cmon.dao.UserBrokerDAOImpl;
import dsn.cmon.model.Balance;
import dsn.cmon.model.UserBroker;
import dsn.cmon.secure.Authentication;
import dsn.cmon.util.GlobalVariable;
import man.svc.balance.InvestorAcctBalanceService;
import man.svc.balance.InvestorAcctBalanceService_Service;
import man.svc.balance.schema.InvestorAcctBalanceRequest;
import man.svc.balance.schema.InvestorAcctBalanceResponse;
import man.svc.balance.schema.InvestorAcctBalanceResponse.InvestorAcctBalance;

@DisallowConcurrentExecution
public class BalanceJob implements Job {
	static Logger log = Logger.getLogger("JOB");
	private static final QName SERVICE_NAME = new QName("http://www.mandiri.co.id/services/InvestorAcctBalance",
			"InvestorAcctBalanceService");
	private static BalanceDAOImpl balanceDAOImpl;
	private static UserBrokerDAOImpl userBrokerDAOImpl;

	public BalanceJob() {
		// TODO Auto-generated constructor stub
		balanceDAOImpl = new BalanceDAOImpl();
		userBrokerDAOImpl = new UserBrokerDAOImpl();
	}

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		// TODO Auto-generated method stub
		URL wsdlURL = InvestorAcctBalanceService_Service.WSDL_LOCATION;
		Date currentFireTime;
		currentFireTime = context.getFireTime();

		try {
			Authentication auth = new Authentication();

			if (auth.isValid()) {
				InvestorAcctBalanceService_Service service = new InvestorAcctBalanceService_Service(wsdlURL,
						SERVICE_NAME);
				InvestorAcctBalanceService port = service.getInvestorAcctBalanceServicePort();

				int counter = 0;
				Date currDate = new Date();

				UserBroker userBroker = userBrokerDAOImpl.getUserBroker();

				log.info("Loading Balance Job...");
				log.info("--------------------------------------------------------------------------------");
				log.info("Instance ID " + context.getFireInstanceId() + " Balance Job was fired.");
				log.info("Balance Job Scheduled Fire Time " + currentFireTime);
				log.info("--------------------------------------------------------------------------------");

				long startloop = System.currentTimeMillis();
				for (int i = 1; i <= i + 1; i++) {
					/**
					 * call web service
					 */
					InvestorAcctBalanceRequest req = new InvestorAcctBalanceRequest();
					req.setUsername(userBroker.getUsername());
					req.setPassword(userBroker.getPassword());

					long startcallws = System.currentTimeMillis();
					InvestorAcctBalanceResponse res = port.requestBalance(req);
					long endcallws = System.currentTimeMillis();
					long elapsedcallws = endcallws - startcallws;
					log.info(wsdlURL + " elapsed time " + elapsedcallws + " ms.");

					List<InvestorAcctBalance> listbal = res.getInvestorAcctBalance();

					counter = listbal.size();
					if (counter > 0) {
						log.info("Balance Job " + counter + " record(s) should be executed.");
						long startmsgloop = System.currentTimeMillis();
						List<Balance> balances = new ArrayList<Balance>();
						for (int j = 0; j < counter; j++) {
							Balance balance = new Balance();

							int datevalue = Integer.parseInt((listbal.get(j).getValDate()));
							BigDecimal timestampvalue = new BigDecimal((listbal.get(j).getTimeStamp()));

							if (listbal.get(j).getAcctno()!=null){
								balance.setAcctno(listbal.get(j).getAcctno().trim());
							}else{
								balance.setAcctno(null);
							}
							
							balance.setDatetime(new java.sql.Timestamp(currDate.getTime()));
							
							if (listbal.get(j).getBankCode()!=null){
								balance.setBankcode(listbal.get(j).getBankCode().trim());	
							}else{
								balance.setBankcode(null);
							}
							
							if (listbal.get(j).getInvestorName()!=null){
								balance.setInvestorname(listbal.get(j).getInvestorName().trim());
							}else{
								balance.setInvestorname(null);
							}
							
							if (listbal.get(j).getCurrencyCode()!=null){
								balance.setCurrencycode(listbal.get(j).getCurrencyCode().trim());
							}else{
								balance.setCurrencycode(null);
							}
							
							if (listbal.get(j).getBalance()!=null){
								balance.setBalance(listbal.get(j).getBalance());
							}else{
								balance.setBalance(null);
							}
							
							balance.setValdate(datevalue);								
							balance.setTimestamp(timestampvalue);
							
							if (listbal.get(j).getInvestorID()!=null){
								balance.setInvestorid(listbal.get(j).getInvestorID().trim());
							}else{
								balance.setInvestorid(null);
							}

							if (listbal.get(j).getSubAccount()!=null){
								balance.setSubaccount(listbal.get(j).getSubAccount().trim());
							}else{
								balance.setSubaccount(null);
							}
							balance.setParticipantid(userBroker.getUsername());
							balances.add(balance);							
						}
						
						balanceDAOImpl.upsertBalance(balances);
						
						long endmsgloop = System.currentTimeMillis();
						long elapsedtimemsgloop = endmsgloop - startmsgloop;
						log.info("Store Balance Job to table. Elapsed time " + elapsedtimemsgloop + " ms.");

					} else {
						log.info("Balance Job no record(s).");
					}

					if ((counter <= 0) || (GlobalVariable.STOP_BALANCE_LOOP)) {
						break;
					}
				}
				long endloop = System.currentTimeMillis();
				long elapsedtimeloop = endloop - startloop;
				log.info("Instance ID " + context.getFireInstanceId() + " Balance Job was executed. Elapsed time "
						+ elapsedtimeloop + " ms.");
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
