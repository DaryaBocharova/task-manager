/**
 * 
 *
 * Generated by <a href="http://enunciate.webcohesion.com">Enunciate</a>.
 */
package ru.bocharova.tm.api.endpoint.jaxws;

import javax.xml.namespace.QName;

/**
 * Response bean for the editProjectResponse operation.
 */
@javax.xml.bind.annotation.XmlRootElement (
  name = "editProjectResponse",
  namespace = "http://endpoint.api.tm.bocharova.ru/"
)
@javax.xml.bind.annotation.XmlType (
  name = "editProjectResponse",
  namespace = "http://endpoint.api.tm.bocharova.ru/",
  propOrder = { "_retval" }
)
 @javax.xml.bind.annotation.XmlAccessorType ( javax.xml.bind.annotation.XmlAccessType.FIELD )
public class EditProjectResponse {

  @javax.xml.bind.annotation.XmlElement (
    name = "return"
  )
  protected ru.bocharova.tm.entity.Project _retval;

  /**
   * 
   */
  public ru.bocharova.tm.entity.Project getReturn() {
    return this._retval;
  }

  /**
   * 
   */
  public void setReturn(ru.bocharova.tm.entity.Project value) {
    this._retval = value;
  }

}
