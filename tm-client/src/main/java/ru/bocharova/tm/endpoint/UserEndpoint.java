
package ru.bocharova.tm.endpoint;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.FaultAction;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 3.2.7
 * 2019-07-24T15:29:41.849+03:00
 * Generated source version: 3.2.7
 *
 */
@WebService(targetNamespace = "http://endpoint.api.tm.bocharova.ru/", name = "UserEndpoint")
@XmlSeeAlso({ObjectFactory.class})
public interface UserEndpoint {

    @WebMethod
    @Action(input = "http://endpoint.api.tm.bocharova.ru/UserEndpoint/changeUserPasswordRequest", output = "http://endpoint.api.tm.bocharova.ru/UserEndpoint/changeUserPasswordResponse", fault = {@FaultAction(className = AuthenticationSecurityException_Exception.class, value = "http://endpoint.api.tm.bocharova.ru/UserEndpoint/changeUserPassword/Fault/AuthenticationSecurityException"), @FaultAction(className = DataValidateException_Exception.class, value = "http://endpoint.api.tm.bocharova.ru/UserEndpoint/changeUserPassword/Fault/DataValidateException")})
    @RequestWrapper(localName = "changeUserPassword", targetNamespace = "http://endpoint.api.tm.bocharova.ru/", className = "ru.bocharova.tm.api.endpoint.ChangeUserPassword")
    @ResponseWrapper(localName = "changeUserPasswordResponse", targetNamespace = "http://endpoint.api.tm.bocharova.ru/", className = "ru.bocharova.tm.api.endpoint.ChangeUserPasswordResponse")
    public void changeUserPassword(
            @WebParam(name = "session", targetNamespace = "")
                    SessionDTO session,
            @WebParam(name = "user", targetNamespace = "")
                    UserDTO user
    ) throws AuthenticationSecurityException_Exception, DataValidateException_Exception;

    @WebMethod
    @Action(input = "http://endpoint.api.tm.bocharova.ru/UserEndpoint/getUserBySessionRequest", output = "http://endpoint.api.tm.bocharova.ru/UserEndpoint/getUserBySessionResponse", fault = {@FaultAction(className = AuthenticationSecurityException_Exception.class, value = "http://endpoint.api.tm.bocharova.ru/UserEndpoint/getUserBySession/Fault/AuthenticationSecurityException"), @FaultAction(className = DataValidateException_Exception.class, value = "http://endpoint.api.tm.bocharova.ru/UserEndpoint/getUserBySession/Fault/DataValidateException")})
    @RequestWrapper(localName = "getUserBySession", targetNamespace = "http://endpoint.api.tm.bocharova.ru/", className = "ru.bocharova.tm.api.endpoint.GetUserBySession")
    @ResponseWrapper(localName = "getUserBySessionResponse", targetNamespace = "http://endpoint.api.tm.bocharova.ru/", className = "ru.bocharova.tm.api.endpoint.GetUserBySessionResponse")
    @WebResult(name = "return", targetNamespace = "")
    public UserDTO getUserBySession(
            @WebParam(name = "session", targetNamespace = "")
                    SessionDTO session
    ) throws AuthenticationSecurityException_Exception, DataValidateException_Exception;

    @WebMethod
    @Action(input = "http://endpoint.api.tm.bocharova.ru/UserEndpoint/findAllUserRequest", output = "http://endpoint.api.tm.bocharova.ru/UserEndpoint/findAllUserResponse", fault = {@FaultAction(className = AuthenticationSecurityException_Exception.class, value = "http://endpoint.api.tm.bocharova.ru/UserEndpoint/findAllUser/Fault/AuthenticationSecurityException"), @FaultAction(className = DataValidateException_Exception.class, value = "http://endpoint.api.tm.bocharova.ru/UserEndpoint/findAllUser/Fault/DataValidateException")})
    @RequestWrapper(localName = "findAllUser", targetNamespace = "http://endpoint.api.tm.bocharova.ru/", className = "ru.bocharova.tm.api.endpoint.FindAllUser")
    @ResponseWrapper(localName = "findAllUserResponse", targetNamespace = "http://endpoint.api.tm.bocharova.ru/", className = "ru.bocharova.tm.api.endpoint.FindAllUserResponse")
    @WebResult(name = "return", targetNamespace = "")
    public java.util.List<UserDTO> findAllUser(
            @WebParam(name = "session", targetNamespace = "")
                   SessionDTO session
    ) throws AuthenticationSecurityException_Exception, DataValidateException_Exception;

    @WebMethod
    @Action(input = "http://endpoint.api.tm.bocharova.ru/UserEndpoint/createUserRequest", output = "http://endpoint.api.tm.bocharova.ru/UserEndpoint/createUserResponse", fault = {@FaultAction(className = AuthenticationSecurityException_Exception.class, value = "http://endpoint.api.tm.bocharova.ru/UserEndpoint/createUser/Fault/AuthenticationSecurityException"), @FaultAction(className = DataValidateException_Exception.class, value = "http://endpoint.api.tm.bocharova.ru/UserEndpoint/createUser/Fault/DataValidateException")})
    @RequestWrapper(localName = "createUser", targetNamespace = "http://endpoint.api.tm.bocharova.ru/", className = "ru.bocharova.tm.api.endpoint.CreateUser")
    @ResponseWrapper(localName = "createUserResponse", targetNamespace = "http://endpoint.api.tm.bocharova.ru/", className = "ru.bocharova.tm.api.endpoint.CreateUserResponse")
    public void createUser(
            @WebParam(name = "session", targetNamespace = "")
                SessionDTO session,
            @WebParam(name = "user", targetNamespace = "")
                    UserDTO user
    ) throws AuthenticationSecurityException_Exception, DataValidateException_Exception;

    @WebMethod
    @Action(input = "http://endpoint.api.tm.bocharova.ru/UserEndpoint/editUserProfileRequest", output = "http://endpoint.api.tm.bocharova.ru/UserEndpoint/editUserProfileResponse", fault = {@FaultAction(className = AuthenticationSecurityException_Exception.class, value = "http://endpoint.api.tm.bocharova.ru/UserEndpoint/editUserProfile/Fault/AuthenticationSecurityException"), @FaultAction(className = DataValidateException_Exception.class, value = "http://endpoint.api.tm.bocharova.ru/UserEndpoint/editUserProfile/Fault/DataValidateException")})
    @RequestWrapper(localName = "editUserProfile", targetNamespace = "http://endpoint.api.tm.bocharova.ru/", className = "ru.bocharova.tm.api.endpoint.EditUserProfile")
    @ResponseWrapper(localName = "editUserProfileResponse", targetNamespace = "http://endpoint.api.tm.bocharova.ru/", className = "ru.bocharova.tm.api.endpoint.EditUserProfileResponse")
    public void editUserProfile(
            @WebParam(name = "session", targetNamespace = "")
                  SessionDTO session,
            @WebParam(name = "user", targetNamespace = "")
                    UserDTO user
    ) throws AuthenticationSecurityException_Exception, DataValidateException_Exception;

    @WebMethod
    @Action(input = "http://endpoint.api.tm.bocharova.ru/UserEndpoint/findUserByLoginRequest", output = "http://endpoint.api.tm.bocharova.ru/UserEndpoint/findUserByLoginResponse", fault = {@FaultAction(className = AuthenticationSecurityException_Exception.class, value = "http://endpoint.api.tm.bocharova.ru/UserEndpoint/findUserByLogin/Fault/AuthenticationSecurityException"), @FaultAction(className = DataValidateException_Exception.class, value = "http://endpoint.api.tm.bocharova.ru/UserEndpoint/findUserByLogin/Fault/DataValidateException")})
    @RequestWrapper(localName = "findUserByLogin", targetNamespace = "http://endpoint.api.tm.bocharova.ru/", className = "ru.bocharova.tm.api.endpoint.FindUserByLogin")
    @ResponseWrapper(localName = "findUserByLoginResponse", targetNamespace = "http://endpoint.api.tm.bocharova.ru/", className = "ru.bocharova.tm.api.endpoint.FindUserByLoginResponse")
    @WebResult(name = "return", targetNamespace = "")
    public UserDTO findUserByLogin(
            @WebParam(name = "session", targetNamespace = "")
                SessionDTO session,
            @WebParam(name = "login", targetNamespace = "")
                    String login
    ) throws AuthenticationSecurityException_Exception, DataValidateException_Exception;
}
