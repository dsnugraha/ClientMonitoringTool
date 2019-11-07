package man.svc.balance;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;

import dsn.cmon.util.ConfigProperty;

import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 2.7.10
 * 2016-05-16T22:10:43.772+07:00
 * Generated source version: 2.7.10
 * 
 */
@WebServiceClient(name = "InvestorAcctBalanceService", 
                  wsdlLocation = "file:/C:/Eclipse/workspace/ClientMonitoringTool/WebContent/WEB-INF/wsdl/Balance.wsdl",
                  targetNamespace = "http://www.mandiri.co.id/services/InvestorAcctBalance") 
public class InvestorAcctBalanceService_Service extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://www.mandiri.co.id/services/InvestorAcctBalance", "InvestorAcctBalanceService");
    public final static QName InvestorAcctBalanceServicePort = new QName("http://www.mandiri.co.id/services/InvestorAcctBalance", "InvestorAcctBalanceServicePort");
    static {
        URL url = null;
        try {
    		ConfigProperty configProperty = new ConfigProperty();
    		url = new URL(configProperty.getConfigProperty("BALANCE_WSDL"));
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(InvestorAcctBalanceService_Service.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", url);
        }
        WSDL_LOCATION = url;
    }

    public InvestorAcctBalanceService_Service(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public InvestorAcctBalanceService_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public InvestorAcctBalanceService_Service() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public InvestorAcctBalanceService_Service(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public InvestorAcctBalanceService_Service(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public InvestorAcctBalanceService_Service(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     *
     * @return
     *     returns InvestorAcctBalanceService
     */
    @WebEndpoint(name = "InvestorAcctBalanceServicePort")
    public InvestorAcctBalanceService getInvestorAcctBalanceServicePort() {
        return super.getPort(InvestorAcctBalanceServicePort, InvestorAcctBalanceService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns InvestorAcctBalanceService
     */
    @WebEndpoint(name = "InvestorAcctBalanceServicePort")
    public InvestorAcctBalanceService getInvestorAcctBalanceServicePort(WebServiceFeature... features) {
        return super.getPort(InvestorAcctBalanceServicePort, InvestorAcctBalanceService.class, features);
    }

}