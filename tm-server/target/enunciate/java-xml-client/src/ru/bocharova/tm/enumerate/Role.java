/**
 * 
 *
 * Generated by <a href="http://enunciate.webcohesion.com">Enunciate</a>.
 */
package ru.bocharova.tm.enumerate;

/**
 * (no documentation provided)
 */
@javax.xml.bind.annotation.XmlType (
  name = "role",
  namespace = ""
)
@javax.xml.bind.annotation.XmlEnum (
  java.lang.String.class
)
public enum Role {

  /**
   * (no documentation provided)
   */
  @javax.xml.bind.annotation.XmlEnumValue ( "USER" )
  USER("USER"),

  /**
   * (no documentation provided)
   */
  @javax.xml.bind.annotation.XmlEnumValue ( "ADMINISTRATOR" )
  ADMINISTRATOR("ADMINISTRATOR");

  private final String value;

  private Role(String value) {
    this.value = value;
  }

  public String toString() {
    return value;
  }

}