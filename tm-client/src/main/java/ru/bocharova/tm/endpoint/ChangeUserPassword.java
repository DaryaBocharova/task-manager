
package ru.bocharova.tm.endpoint;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for changeUserPassword complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="changeUserPassword"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="session" type="{http://endpoint.api.tm.bocharova.ru/}sessionDTO" minOccurs="0"/&gt;
 *         &lt;element name="user" type="{http://endpoint.api.tm.bocharova.ru/}userDTO" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "changeUserPassword", propOrder = {
    "session",
    "user"
})
public class ChangeUserPassword {

    protected SessionDTO session;
    protected UserDTO user;

    /**
     * Gets the value of the session property.
     * 
     * @return
     *     possible object is
     *     {@link SessionDTO }
     *     
     */
    public SessionDTO getSession() {
        return session;
    }

    /**
     * Sets the value of the session property.
     * 
     * @param value
     *     allowed object is
     *     {@link SessionDTO }
     *     
     */
    public void setSession(SessionDTO value) {
        this.session = value;
    }

    /**
     * Gets the value of the user property.
     * 
     * @return
     *     possible object is
     *     {@link UserDTO }
     *     
     */
    public UserDTO getUser() {
        return user;
    }

    /**
     * Sets the value of the user property.
     * 
     * @param value
     *     allowed object is
     *     {@link UserDTO }
     *     
     */
    public void setUser(UserDTO value) {
        this.user = value;
    }

}
