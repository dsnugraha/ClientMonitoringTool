
package man.svc.validation.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ValidationType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ValidationType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="wsUser">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="50"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="wsPasswd">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="50"/>
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
@XmlType(name = "ValidationType", propOrder = {
    "wsUser",
    "wsPasswd"
})
public class ValidationType {

    @XmlElement(required = true)
    protected String wsUser;
    @XmlElement(required = true)
    protected String wsPasswd;

    /**
     * Gets the value of the wsUser property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWsUser() {
        return wsUser;
    }

    /**
     * Sets the value of the wsUser property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWsUser(String value) {
        this.wsUser = value;
    }

    /**
     * Gets the value of the wsPasswd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWsPasswd() {
        return wsPasswd;
    }

    /**
     * Sets the value of the wsPasswd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWsPasswd(String value) {
        this.wsPasswd = value;
    }

}
