package man.svc.validation;

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
 * 2016-05-25T08:44:26.986+07:00
 * Generated source version: 2.7.10
 * 
 */
@WebServiceClient(name = "BrokerValidationService", 
                  wsdlLocation = "file:/C:/Eclipse/workspace/ClientMonitoringTool/WebContent/WEB-INF/wsdl/Validation.wsdl",
                  targetNamespace = "http://www.mandiri.co.id/services/BrokerValidation") 
public class BrokerValidationService_Service extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://www.mandiri.co.id/services/BrokerValidation", "BrokerValidationService");
    public final static QName BrokerValidationServicePort = new QName("http://www.mandiri.co.id/services/BrokerValidation", "BrokerValidationServicePort");
    static {
        URL url = null;
        try {
    		ConfigProperty configProperty = new ConfigProperty();
    		url = new URL(configProperty.getConfigProperty("VALIDATION_WSDL"));
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(BrokerValidationService_Service.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "file:/C:/Eclipse/workspace/ClientMonitoringTool/WebContent/WEB-INF/wsdl/Validation.wsdl");
        }
        WSDL_LOCATION = url;
    }

    public BrokerValidationService_Service(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public BrokerValidationService_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public BrokerValidationService_Service() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public BrokerValidationService_Service(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public BrokerValidationService_Service(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public BrokerValidationService_Service(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     *
     * @return
     *     returns BrokerValidationService
     */
    @WebEndpoint(name = "BrokerValidationServicePort")
    public BrokerValidationService getBrokerValidationServicePort() {
        return super.getPort(BrokerValidationServicePort, BrokerValidationService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns BrokerValidationService
     */
    @WebEndpoint(name = "BrokerValidationServicePort")
    public BrokerValidationService getBrokerValidationServicePort(WebServiceFeature... features) {
        return super.getPort(BrokerValidationServicePort, BrokerValidationService.class, features);
    }

}