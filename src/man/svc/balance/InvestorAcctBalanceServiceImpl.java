
/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

package man.svc.balance;

import java.util.logging.Logger;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 2.7.10
 * 2016-05-16T22:10:43.746+07:00
 * Generated source version: 2.7.10
 * 
 */

@javax.jws.WebService(
                      serviceName = "InvestorAcctBalanceService",
                      portName = "InvestorAcctBalanceServicePort",
                      targetNamespace = "http://www.mandiri.co.id/services/InvestorAcctBalance",
                      wsdlLocation = "file:/C:/Eclipse/workspace/ClientMonitoringTool/WebContent/WEB-INF/wsdl/Balance.wsdl",
                      endpointInterface = "man.svc.balance.InvestorAcctBalanceService")
                      
public class InvestorAcctBalanceServiceImpl implements InvestorAcctBalanceService {

    private static final Logger LOG = Logger.getLogger(InvestorAcctBalanceServiceImpl.class.getName());

    /* (non-Javadoc)
     * @see man.svc.balance.InvestorAcctBalanceService#requestBalance(man.svc.balance.schema.InvestorAcctBalanceRequest  part )*
     */
    public man.svc.balance.schema.InvestorAcctBalanceResponse requestBalance(man.svc.balance.schema.InvestorAcctBalanceRequest part) { 
        LOG.info("Executing operation requestBalance");
        System.out.println(part);
        try {
            man.svc.balance.schema.InvestorAcctBalanceResponse _return = new man.svc.balance.schema.InvestorAcctBalanceResponse();
            java.util.List<man.svc.balance.schema.InvestorAcctBalanceResponse.InvestorAcctBalance> _returnInvestorAcctBalance = new java.util.ArrayList<man.svc.balance.schema.InvestorAcctBalanceResponse.InvestorAcctBalance>();
            man.svc.balance.schema.InvestorAcctBalanceResponse.InvestorAcctBalance _returnInvestorAcctBalanceVal1 = new man.svc.balance.schema.InvestorAcctBalanceResponse.InvestorAcctBalance();
            _returnInvestorAcctBalanceVal1.setBankCode("BankCode1600545257");
            _returnInvestorAcctBalanceVal1.setInvestorName("InvestorName-1197851460");
            _returnInvestorAcctBalanceVal1.setAcctno("Acctno1812372534");
            _returnInvestorAcctBalanceVal1.setCurrencyCode("CurrencyCode225677731");
            _returnInvestorAcctBalanceVal1.setBalance(new java.math.BigDecimal("-7229952869300557893.4007437025105504865"));
            _returnInvestorAcctBalanceVal1.setValDate("ValDate29156080");
            _returnInvestorAcctBalanceVal1.setTimeStamp("TimeStamp-2114116069");
            _returnInvestorAcctBalanceVal1.setInvestorID("InvestorID473194055");
            _returnInvestorAcctBalanceVal1.setSubAccount("SubAccount566767509");
            _returnInvestorAcctBalance.add(_returnInvestorAcctBalanceVal1);
            _return.getInvestorAcctBalance().addAll(_returnInvestorAcctBalance);
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

}