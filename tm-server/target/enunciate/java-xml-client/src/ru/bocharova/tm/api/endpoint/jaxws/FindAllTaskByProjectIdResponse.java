/**
 * 
 *
 * Generated by <a href="http://enunciate.webcohesion.com">Enunciate</a>.
 */
package ru.bocharova.tm.api.endpoint.jaxws;

import javax.xml.namespace.QName;

/**
 * Response bean for the findAllTaskByProjectIdResponse operation.
 */
@javax.xml.bind.annotation.XmlRootElement (
  name = "findAllTaskByProjectIdResponse",
  namespace = "http://endpoint.api.tm.bocharova.ru/"
)
@javax.xml.bind.annotation.XmlType (
  name = "findAllTaskByProjectIdResponse",
  namespace = "http://endpoint.api.tm.bocharova.ru/",
  propOrder = { "_retval" }
)
 @javax.xml.bind.annotation.XmlAccessorType ( javax.xml.bind.annotation.XmlAccessType.FIELD )
public class FindAllTaskByProjectIdResponse {

  @javax.xml.bind.annotation.XmlElement (
    name = "return"
  )
  protected java.util.Collection<ru.bocharova.tm.entity.Task> _retval;

  /**
   * 
   */
  public java.util.Collection<ru.bocharova.tm.entity.Task> getReturn() {
    return this._retval;
  }

  /**
   * 
   */
  public void setReturn(java.util.Collection<ru.bocharova.tm.entity.Task> value) {
    this._retval = value;
  }

}