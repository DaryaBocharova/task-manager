/**
 * 
 *
 * Generated by <a href="http://enunciate.webcohesion.com">Enunciate</a>.
 */
package ru.bocharova.tm.api.endpoint.jaxws;

import javax.xml.namespace.QName;

/**
 * Request bean for the sortAllTaskByUserId operation.
 */
@javax.xml.bind.annotation.XmlRootElement (
  name = "sortAllTaskByUserId",
  namespace = "http://endpoint.api.tm.bocharova.ru/"
)
@javax.xml.bind.annotation.XmlType (
  name = "sortAllTaskByUserId",
  namespace = "http://endpoint.api.tm.bocharova.ru/",
  propOrder = { "session", "comparator" }
)
@javax.xml.bind.annotation.XmlAccessorType ( javax.xml.bind.annotation.XmlAccessType.FIELD )
public class SortAllTaskByUserId {

  @javax.xml.bind.annotation.XmlElement (
    name = "session"
  )
  protected ru.bocharova.tm.entity.Session session;
  @javax.xml.bind.annotation.XmlElement (
    name = "comparator"
  )
  protected java.lang.String comparator;

  /**
   * 
   */
  public ru.bocharova.tm.entity.Session getSession() {
    return this.session;
  }

  /**
   * 
   */
  public void setSession(ru.bocharova.tm.entity.Session session) {
    this.session = session;
  }

  /**
   * 
   */
  public java.lang.String getComparator() {
    return this.comparator;
  }

  /**
   * 
   */
  public void setComparator(java.lang.String comparator) {
    this.comparator = comparator;
  }
}
