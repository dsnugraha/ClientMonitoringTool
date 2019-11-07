package dsn.cmon.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Balance {
	private String acctno;
	private Timestamp datetime;
	private String bankcode;
	private String investorname;
	private String currencycode;
	private BigDecimal balance;
	private int valdate;
	private BigDecimal timestamp;
	private String investorid;
	private String subaccount;
	private String participantid;

	public String getAcctno() {
		return acctno;
	}

	public void setAcctno(String acctno) {
		this.acctno = acctno;
	}

	public Timestamp getDatetime() {
		return datetime;
	}

	public void setDatetime(Timestamp datetime) {
		this.datetime = datetime;
	}

	public String getBankcode() {
		return bankcode;
	}

	public void setBankcode(String bankcode) {
		this.bankcode = bankcode;
	}

	public String getInvestorname() {
		return investorname;
	}

	public void setInvestorname(String investorname) {
		this.investorname = investorname;
	}

	public String getCurrencycode() {
		return currencycode;
	}

	public void setCurrencycode(String currencycode) {
		this.currencycode = currencycode;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public int getValdate() {
		return valdate;
	}

	public void setValdate(int valdate) {
		this.valdate = valdate;
	}

	public BigDecimal getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(BigDecimal timestamp) {
		this.timestamp = timestamp;
	}

	public String getInvestorid() {
		return investorid;
	}

	public void setInvestorid(String investorid) {
		this.investorid = investorid;
	}

	public String getSubaccount() {
		return subaccount;
	}

	public void setSubaccount(String subaccount) {
		this.subaccount = subaccount;
	}

	public String getParticipantid() {
		return participantid;
	}

	public void setParticipantid(String participantid) {
		this.participantid = participantid;
	}

	public Balance() {
		// TODO Auto-generated constructor stub
	}

}
