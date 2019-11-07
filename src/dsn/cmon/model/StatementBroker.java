package dsn.cmon.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class StatementBroker {
	private int id;
	private Timestamp datetime;
	private String extref;
	private int seqnum;
	private String acctno;
	private String currencycode;
	private int valdate;
	private BigDecimal openbal;
	private String trxtype;
	private String dc;
	private BigDecimal cashval;
	private String description;
	private BigDecimal closebal;
	private String acctnote;
	private String participantid;

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

	public int getSeqnum() {
		return seqnum;
	}

	public void setSeqnum(int seqnum) {
		this.seqnum = seqnum;
	}

	public String getAcctno() {
		return acctno;
	}

	public void setAcctno(String acctno) {
		this.acctno = acctno;
	}

	public String getCurrencycode() {
		return currencycode;
	}

	public void setCurrencycode(String currencycode) {
		this.currencycode = currencycode;
	}

	public int getValdate() {
		return valdate;
	}

	public void setValdate(int valdate) {
		this.valdate = valdate;
	}

	public BigDecimal getOpenbal() {
		return openbal;
	}

	public void setOpenbal(BigDecimal openbal) {
		this.openbal = openbal;
	}

	public BigDecimal getClosebal() {
		return closebal;
	}

	public void setClosebal(BigDecimal closebal) {
		this.closebal = closebal;
	}

	public String getAcctnote() {
		return acctnote;
	}

	public void setAcctnote(String acctnote) {
		this.acctnote = acctnote;
	}

	public String getParticipantid() {
		return participantid;
	}

	public void setParticipantid(String participantid) {
		this.participantid = participantid;
	}

	public StatementBroker() {
		// TODO Auto-generated constructor stub
	}

	public String getTrxtype() {
		return trxtype;
	}

	public void setTrxtype(String trxtype) {
		this.trxtype = trxtype;
	}

	public String getDc() {
		return dc;
	}

	public void setDc(String dc) {
		this.dc = dc;
	}

	public BigDecimal getCashval() {
		return cashval;
	}

	public void setCashval(BigDecimal cashval) {
		this.cashval = cashval;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
