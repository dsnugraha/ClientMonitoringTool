
package man.svc.validation.schema;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the man.svc.validation.schema package. 
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
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: man.svc.validation.schema
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link BrokerValidationRequest }
     * 
     */
    public BrokerValidationRequest createBrokerValidationRequest() {
        return new BrokerValidationRequest();
    }

    /**
     * Create an instance of {@link BrokerValidationResponse }
     * 
     */
    public BrokerValidationResponse createBrokerValidationResponse() {
        return new BrokerValidationResponse();
    }

    /**
     * Create an instance of {@link ValidationType }
     * 
     */
    public ValidationType createValidationType() {
        return new ValidationType();
    }

}
