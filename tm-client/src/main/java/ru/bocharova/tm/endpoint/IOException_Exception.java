
package ru.bocharova.tm.endpoint;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 3.2.7
 * 2019-07-24T15:29:41.442+03:00
 * Generated source version: 3.2.7
 */

@WebFault(name = "IOException", targetNamespace = "http://endpoint.api.tm.bocharova.ru/")
public class IOException_Exception extends Exception {

    private IOException ioException;

    public IOException_Exception() {
        super();
    }

    public IOException_Exception(String message) {
        super(message);
    }

    public IOException_Exception(String message, Throwable cause) {
        super(message, cause);
    }

    public IOException_Exception(String message, IOException ioException) {
        super(message);
        this.ioException = ioException;
    }

    public IOException_Exception(String message, IOException ioException, Throwable cause) {
        super(message, cause);
        this.ioException = ioException;
    }

    public IOException getFaultInfo() {
        return this.ioException;
    }
}
