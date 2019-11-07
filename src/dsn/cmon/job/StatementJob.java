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

import dsn.cmon.dao.StatementBrokerDAO;
import dsn.cmon.dao.StatementBrokerDAOImpl;
import dsn.cmon.dao.UserBrokerDAOImpl;
import dsn.cmon.model.StatementBroker;
import dsn.cmon.model.UserBroker;
import dsn.cmon.secure.Authentication;
import dsn.cmon.util.GlobalVariable;
import man.svc.statement.InvestorAcctStatementService;
import man.svc.statement.InvestorAcctStatementService_Service;
import man.svc.statement.schema.InvestorAcctStatementRequest;
import man.svc.statement.schema.InvestorAcctStatementResponse;
import man.svc.statement.schema.ksei.MessageType;
import man.svc.statement.schema.ksei.MessageType.Field;

@DisallowConcurrentExecution
public class StatementJob implements Job {
	static Logger log = Logger.getLogger("JOB");
	private static final QName SERVICE_NAME = new QName("http://www.mandiri.co.id/services/InvestorAcctStatement",
			"InvestorAcctStatementService");
	private static StatementBrokerDAO dao;
	private static UserBrokerDAOImpl userBrokerDAOImpl;

	public StatementJob() {
		// TODO Auto-generated constructor stub
		dao = new StatementBrokerDAOImpl();
		userBrokerDAOImpl = new UserBrokerDAOImpl();
	}

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		// TODO Auto-generated method stub
		URL wsdlURL = InvestorAcctStatementService_Service.WSDL_LOCATION;
		Date currentFireTime;
		currentFireTime = context.getFireTime();

		try {
			Authentication auth = new Authentication();

			if (auth.isValid()) {
				InvestorAcctStatementService_Service service = new InvestorAcctStatementService_Service(wsdlURL,
						SERVICE_NAME);
				InvestorAcctStatementService port = service.getInvestorAcctStatementServicePort();

				int counter = 0;
				Date currDate = new Date();

				String extref = "ExtRef";
				String seqnum = "SeqNum";
				String acctno = "AC";
				String curcod = "CurCod";
				String valdate = "ValDate";
				String openbal = "OpenBal";
				String trxtype = "TrxType";
				String dc = "DC";
				String cashval = "CashVal";
				String description = "Description";
				String closebal = "CloseBal";
				String acctnote = "Notes";
				String participantid = "Kode_AB";

				UserBroker userBroker = userBrokerDAOImpl.getUserBroker();

				log.info("Loading Statement Job...");
				log.info("--------------------------------------------------------------------------------");
				log.info("Instance ID " + context.getFireInstanceId() + " Statement Job was fired.");
				log.info("Statement Job Scheduled Fire Time " + currentFireTime);
				log.info("--------------------------------------------------------------------------------");

				long startloop = System.currentTimeMillis();
				for (int i = 1; i < i + 1; i++) {
					/**
					 * call web service
					 * 
					 * 
					 */
					InvestorAcctStatementRequest req = new InvestorAcctStatementRequest();
					req.setUsername(userBroker.getUsername());
					req.setPassword(userBroker.getPassword());

					long startcallws = System.currentTimeMillis();
					InvestorAcctStatementResponse res = port.requestStatement(req);
					long endcallws = System.currentTimeMillis();
					long elapsedcallws = endcallws - startcallws;
					log.info(wsdlURL + " elapsed time " + elapsedcallws + " ms.");

					List<MessageType> listmsg = res.getMessage();

					counter = listmsg.size();
					if (counter > 0) {
						log.info("Statement Job " + counter + " record(s) should be executed.");
						long startmsgloop = System.currentTimeMillis();
						List<StatementBroker> statementbrokers = new ArrayList<StatementBroker>();
						for (int j = 0; j < counter; j++) {
							StatementBroker statementbroker = new StatementBroker();
							List<Field> listfield = listmsg.get(j).getField();
							for (int k = 0; k < listfield.size(); k++) {
								statementbroker.setDatetime(new java.sql.Timestamp(currDate.getTime()));
								if (listfield.get(k).getName().compareToIgnoreCase(extref) == 0) {
									statementbroker.setExtref(listfield.get(k).getValue().trim());
								}
								if (listfield.get(k).getName().compareToIgnoreCase(seqnum) == 0) {
									statementbroker.setSeqnum(Integer.parseInt(listfield.get(k).getValue().trim()));
								}
								if (listfield.get(k).getName().compareToIgnoreCase(acctno) == 0) {
									statementbroker.setAcctno(listfield.get(k).getValue().trim());
								}
								if (listfield.get(k).getName().compareToIgnoreCase(curcod) == 0) {
									statementbroker.setCurrencycode(listfield.get(k).getValue().trim());
								}
								if (listfield.get(k).getName().compareToIgnoreCase(valdate) == 0) {
									statementbroker.setValdate(Integer.parseInt(listfield.get(k).getValue().trim()));
								}
								if (listfield.get(k).getName().compareToIgnoreCase(openbal) == 0) {
									String openbalvalue = listfield.get(k).getValue().trim();
									if ((openbalvalue == null) || (openbalvalue.isEmpty())) {
										statementbroker.setOpenbal(new BigDecimal(0));
									}else{
										BigDecimal openbaldec = new BigDecimal(openbalvalue);
										statementbroker.setOpenbal(openbaldec);
									}
								}
								if (listfield.get(k).getName().compareToIgnoreCase(trxtype) == 0) {
									statementbroker.setTrxtype(listfield.get(k).getValue().trim());
								}
								if (listfield.get(k).getName().compareToIgnoreCase(dc) == 0) {
									statementbroker.setDc(listfield.get(k).getValue().trim());
								}
								if (listfield.get(k).getName().compareToIgnoreCase(cashval) == 0) {
									String cashvalue = listfield.get(k).getValue().trim();
									if ((cashvalue == null) || (cashvalue.isEmpty())) {
										statementbroker.setCashval(new BigDecimal(0));
									}else{
										BigDecimal cashvaldec = new BigDecimal(cashvalue);
										statementbroker.setCashval(cashvaldec);
									}
								}
								if (listfield.get(k).getName().compareToIgnoreCase(description) == 0) {
									statementbroker.setDescription(listfield.get(k).getValue().trim());
								}
								if (listfield.get(k).getName().compareToIgnoreCase(closebal) == 0) {
									String closebalvalue = listfield.get(k).getValue().trim();
									if ((closebalvalue == null) || (closebalvalue.isEmpty())) {
										statementbroker.setClosebal(new BigDecimal(0));
									}else{
										BigDecimal closebaldec = new BigDecimal(closebalvalue);
										statementbroker.setClosebal(closebaldec);
									}
								}
								if (listfield.get(k).getName().compareToIgnoreCase(acctnote) == 0) {
									statementbroker.setAcctnote(listfield.get(k).getValue().trim());
								}
								if (listfield.get(k).getName().compareToIgnoreCase(participantid) == 0) {
									statementbroker.setParticipantid(listfield.get(k).getValue().trim());
								}
							}
							statementbrokers.add(statementbroker);
						}
						
						dao.addStatementBroker(statementbrokers);

						long endmsgloop = System.currentTimeMillis();
						long elapsedtimemsgloop = endmsgloop - startmsgloop;
						log.info("Store Statement Job to table. Elapsed time " + elapsedtimemsgloop + " ms.");

					} else {
						log.info("Statement Job no record(s).");
					}

					if ((counter <= 0) || (GlobalVariable.STOP_STATEMENT_LOOP)) {
						break;
					}
				}
				long endloop = System.currentTimeMillis();
				long elapsedtimeloop = endloop - startloop;
				log.info("Instance ID " + context.getFireInstanceId() + " Statement Job was executed. Elapsed time "
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
