/**
 * 
 *
 * Generated by <a href="http://enunciate.webcohesion.com">Enunciate</a>.
 */
package ru.bocharova.tm.api.endpoint.jaxws;

import javax.xml.namespace.QName;

/**
 * Response bean for the validateSessionResponse operation.
 */
@javax.xml.bind.annotation.XmlRootElement (
  name = "validateSessionResponse",
  namespace = "http://endpoint.api.tm.bocharova.ru/"
)
@javax.xml.bind.annotation.XmlType (
  name = "validateSessionResponse",
  namespace = "http://endpoint.api.tm.bocharova.ru/",
  propOrder = { "_retval" }
)
 @javax.xml.bind.annotation.XmlAccessorType ( javax.xml.bind.annotation.XmlAccessType.FIELD )
public class ValidateSessionResponse {

  @javax.xml.bind.annotation.XmlElement (
    name = "return"
  )
  protected boolean _retval;

  /**
   * 
   */
  public boolean getReturn() {
    return this._retval;
  }

  /**
   * 
   */
  public void setReturn(boolean value) {
    this._retval = value;
  }

}