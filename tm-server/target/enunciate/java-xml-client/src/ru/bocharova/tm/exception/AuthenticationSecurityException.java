/**
 * 
 *
 * Generated by <a href="http://enunciate.webcohesion.com">Enunciate</a>.
 */
package ru.bocharova.tm.exception;

/**
 * (no documentation provided)
 */
@javax.xml.ws.WebFault (
  faultBean = "ru.bocharova.tm.exception.jaxws.AuthenticationSecurityExceptionBean"
)
public class AuthenticationSecurityException extends Exception {
  private ru.bocharova.tm.exception.jaxws.AuthenticationSecurityExceptionBean faultInfo;

  public AuthenticationSecurityException(String message, ru.bocharova.tm.exception.jaxws.AuthenticationSecurityExceptionBean faultInfo) {
    super(message);
    init(faultInfo);
    if (message != null) {
      this.message = message;
    }
  }

  public AuthenticationSecurityException(String message, ru.bocharova.tm.exception.jaxws.AuthenticationSecurityExceptionBean faultInfo, Throwable cause) {
    super(message, cause);
    init(faultInfo);
    if (message != null) {
      this.message = message;
    }
  }

  protected void init(ru.bocharova.tm.exception.jaxws.AuthenticationSecurityExceptionBean faultInfo) {
    setMessage(faultInfo.getMessage());
  }

  /**
   * The fault info for this exception.
   */
  public ru.bocharova.tm.exception.jaxws.AuthenticationSecurityExceptionBean getFaultInfo() {
    return this.faultInfo;
  }

  private java.lang.String message;

  /**
   * (no documentation provided)
   */
  public java.lang.String getMessage() {
    return this.message;
  }

  /**
   * (no documentation provided)
   */
  public void setMessage(java.lang.String message) {
    this.message = message;
  }

}
