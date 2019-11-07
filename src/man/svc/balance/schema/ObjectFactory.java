
package man.svc.balance.schema;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the man.svc.balance.schema package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: man.svc.balance.schema
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link InvestorAcctBalanceResponse }
     * 
     */
    public InvestorAcctBalanceResponse createInvestorAcctBalanceResponse() {
        return new InvestorAcctBalanceResponse();
    }

    /**
     * Create an instance of {@link InvestorAcctBalanceRequest }
     * 
     */
    public InvestorAcctBalanceRequest createInvestorAcctBalanceRequest() {
        return new InvestorAcctBalanceRequest();
    }

    /**
     * Create an instance of {@link InvestorAcctBalanceResponse.InvestorAcctBalance }
     * 
     */
    public InvestorAcctBalanceResponse.InvestorAcctBalance createInvestorAcctBalanceResponseInvestorAcctBalance() {
        return new InvestorAcctBalanceResponse.InvestorAcctBalance();
    }

}
