
package man.svc.balance.schema;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="InvestorAcctBalance" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="BankCode" minOccurs="0">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;maxLength value="5"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="InvestorName" minOccurs="0">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;maxLength value="120"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="Acctno" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="CurrencyCode" minOccurs="0">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;maxLength value="3"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="Balance" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                   &lt;element name="ValDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="TimeStamp" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="InvestorID" minOccurs="0">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;maxLength value="45"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="SubAccount" minOccurs="0">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;maxLength value="42"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "investorAcctBalance"
})
@XmlRootElement(name = "InvestorAcctBalanceResponse")
public class InvestorAcctBalanceResponse {

    @XmlElement(name = "InvestorAcctBalance")
    protected List<InvestorAcctBalanceResponse.InvestorAcctBalance> investorAcctBalance;

    /**
     * Gets the value of the investorAcctBalance property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the investorAcctBalance property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInvestorAcctBalance().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link InvestorAcctBalanceResponse.InvestorAcctBalance }
     * 
     * 
     */
    public List<InvestorAcctBalanceResponse.InvestorAcctBalance> getInvestorAcctBalance() {
        if (investorAcctBalance == null) {
            investorAcctBalance = new ArrayList<InvestorAcctBalanceResponse.InvestorAcctBalance>();
        }
        return this.investorAcctBalance;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="BankCode" minOccurs="0">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;maxLength value="5"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="InvestorName" minOccurs="0">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;maxLength value="120"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="Acctno" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="CurrencyCode" minOccurs="0">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;maxLength value="3"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="Balance" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *         &lt;element name="ValDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="TimeStamp" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="InvestorID" minOccurs="0">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;maxLength value="45"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="SubAccount" minOccurs="0">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;maxLength value="42"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "bankCode",
        "investorName",
        "acctno",
        "currencyCode",
        "balance",
        "valDate",
        "timeStamp",
        "investorID",
        "subAccount"
    })
    public static class InvestorAcctBalance {

        @XmlElement(name = "BankCode")
        protected String bankCode;
        @XmlElement(name = "InvestorName")
        protected String investorName;
        @XmlElement(name = "Acctno", required = true)
        protected String acctno;
        @XmlElement(name = "CurrencyCode", defaultValue = "IDR")
        protected String currencyCode;
        @XmlElement(name = "Balance")
        protected BigDecimal balance;
        @XmlElement(name = "ValDate")
        protected String valDate;
        @XmlElement(name = "TimeStamp")
        protected String timeStamp;
        @XmlElement(name = "InvestorID")
        protected String investorID;
        @XmlElement(name = "SubAccount")
        protected String subAccount;

        /**
         * Gets the value of the bankCode property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getBankCode() {
            return bankCode;
        }

        /**
         * Sets the value of the bankCode property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setBankCode(String value) {
            this.bankCode = value;
        }

        /**
         * Gets the value of the investorName property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getInvestorName() {
            return investorName;
        }

        /**
         * Sets the value of the investorName property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setInvestorName(String value) {
            this.investorName = value;
        }

        /**
         * Gets the value of the acctno property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAcctno() {
            return acctno;
        }

        /**
         * Sets the value of the acctno property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAcctno(String value) {
            this.acctno = value;
        }

        /**
         * Gets the value of the currencyCode property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCurrencyCode() {
            return currencyCode;
        }

        /**
         * Sets the value of the currencyCode property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCurrencyCode(String value) {
            this.currencyCode = value;
        }

        /**
         * Gets the value of the balance property.
         * 
         * @return
         *     possible object is
         *     {@link BigDecimal }
         *     
         */
        public BigDecimal getBalance() {
            return balance;
        }

        /**
         * Sets the value of the balance property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigDecimal }
         *     
         */
        public void setBalance(BigDecimal value) {
            this.balance = value;
        }

        /**
         * Gets the value of the valDate property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getValDate() {
            return valDate;
        }

        /**
         * Sets the value of the valDate property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setValDate(String value) {
            this.valDate = value;
        }

        /**
         * Gets the value of the timeStamp property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getTimeStamp() {
            return timeStamp;
        }

        /**
         * Sets the value of the timeStamp property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTimeStamp(String value) {
            this.timeStamp = value;
        }

        /**
         * Gets the value of the investorID property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getInvestorID() {
            return investorID;
        }

        /**
         * Sets the value of the investorID property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setInvestorID(String value) {
            this.investorID = value;
        }

        /**
         * Gets the value of the subAccount property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSubAccount() {
            return subAccount;
        }

        /**
         * Sets the value of the subAccount property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSubAccount(String value) {
            this.subAccount = value;
        }

    }

}
