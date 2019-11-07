package dsn.cmon.model;

import java.sql.Date;
import java.sql.Timestamp;

public class DataStatic {
	private int id;
	private Timestamp datetime;
	private String extref;
	private String participantid;
	private String participanname;
	private String investorname;
	private String sidno;
	private String acctno;
	private String bankacctno;	
	private String bankcode;	
	private String activitydate;
	private String activity;
	private String ackstatus;
	private Date acktime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getDatetime() {
		return datetime;
	}

	public void setDatetime(Timestamp datetime) {
		this.datetime = datetime;
	}

	public String getExtref() {
		return extref;
	}

	public void setExtref(String extref) {
		this.extref = extref;
	}

	public String getParticipantid() {
		return participantid;
	}

	public void setParticipantid(String participantid) {
		this.participantid = participantid;
	}

	public String getParticipanname() {
		return participanname;
	}

	public void setParticipanname(String participanname) {
		this.participanname = participanname;
	}

	public String getInvestorname() {
		return investorname;
	}

	public void setInvestorname(String investorname) {
		this.investorname = investorname;
	}

	public String getSidno() {
		return sidno;
	}

	public void setSidno(String sidno) {
		this.sidno = sidno;
	}

	public String getAcctno() {
		return acctno;
	}

	public void setAcctno(String acctno) {
		this.acctno = acctno;
	}

	public String getBankacctno() {
		return bankacctno;
	}

	public void setBankacctno(String bankacctno) {
		this.bankacctno = bankacctno;
	}

	public String getBankcode() {
		return bankcode;
	}

	public void setBankcode(String bankcode) {
		this.bankcode = bankcode;
	}

	public String getActivitydate() {
		return activitydate;
	}

	public void setActivitydate(String activitydate) {
		this.activitydate = activitydate;
	}

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

	public String getAckstatus() {
		return ackstatus;
	}

	public void setAckstatus(String ackstatus) {
		this.ackstatus = ackstatus;
	}

	public Date getAcktime() {
		return acktime;
	}

	public void setAcktime(Date acktime) {
		this.acktime = acktime;
	}

	public DataStatic() {
		// TODO Auto-generated constructor stub
	}

}
