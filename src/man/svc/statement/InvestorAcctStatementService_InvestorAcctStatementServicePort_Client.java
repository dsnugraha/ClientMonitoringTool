
package man.svc.statement;

/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 2.7.10
 * 2016-05-16T22:12:11.721+07:00
 * Generated source version: 2.7.10
 * 
 */
public final class InvestorAcctStatementService_InvestorAcctStatementServicePort_Client {

    private static final QName SERVICE_NAME = new QName("http://www.mandiri.co.id/services/InvestorAcctStatement", "InvestorAcctStatementService");

    private InvestorAcctStatementService_InvestorAcctStatementServicePort_Client() {
    }

    public static void main(String args[]) throws java.lang.Exception {
        URL wsdlURL = InvestorAcctStatementService_Service.WSDL_LOCATION;
        if (args.length > 0 && args[0] != null && !"".equals(args[0])) { 
            File wsdlFile = new File(args[0]);
            try {
                if (wsdlFile.exists()) {
                    wsdlURL = wsdlFile.toURI().toURL();
                } else {
                    wsdlURL = new URL(args[0]);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
      
        InvestorAcctStatementService_Service ss = new InvestorAcctStatementService_Service(wsdlURL, SERVICE_NAME);
        InvestorAcctStatementService port = ss.getInvestorAcctStatementServicePort();  
        
        {
        System.out.println("Invoking requestStatement...");
        man.svc.statement.schema.InvestorAcctStatementRequest _requestStatement_part = new man.svc.statement.schema.InvestorAcctStatementRequest();
        _requestStatement_part.setUsername("AR001");
        _requestStatement_part.setPassword("bebas");
        man.svc.statement.schema.InvestorAcctStatementResponse _requestStatement__return = port.requestStatement(_requestStatement_part);
        System.out.println("requestStatement.result=" + _requestStatement__return);


        }

        System.exit(0);
    }

}
