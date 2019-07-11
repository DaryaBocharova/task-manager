package ru.bocharova.tm.api.endpoint;

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
 * 2019-07-11T16:26:48.463+03:00
 * Generated source version: 3.2.7
 *
 */
@WebService(targetNamespace = "http://endpoint.api.tm.bocharova.ru/", name = "IProjectEndpoint")
@XmlSeeAlso({ObjectFactory.class})
public interface IProjectEndpoint {

    @WebMethod
    @Action(input = "http://endpoint.api.tm.bocharova.ru/IProjectEndpoint/editProjectRequest", output = "http://endpoint.api.tm.bocharova.ru/IProjectEndpoint/editProjectResponse", fault = {@FaultAction(className = AuthenticationSecurityException_Exception.class, value = "http://endpoint.api.tm.bocharova.ru/IProjectEndpoint/editProject/Fault/AuthenticationSecurityException")})
    @RequestWrapper(localName = "editProject", targetNamespace = "http://endpoint.api.tm.bocharova.ru/", className = "ru.bocharova.tm.api.endpoint.EditProject")
    @ResponseWrapper(localName = "editProjectResponse", targetNamespace = "http://endpoint.api.tm.bocharova.ru/", className = "ru.bocharova.tm.api.endpoint.EditProjectResponse")
    @WebResult(name = "return", targetNamespace = "")
    public ru.bocharova.tm.api.endpoint.Project editProject(
        @WebParam(name = "session", targetNamespace = "")
        ru.bocharova.tm.api.endpoint.Session session,
        @WebParam(name = "id", targetNamespace = "")
        java.lang.String id,
        @WebParam(name = "name", targetNamespace = "")
        java.lang.String name,
        @WebParam(name = "description", targetNamespace = "")
        java.lang.String description,
        @WebParam(name = "status", targetNamespace = "")
        java.lang.String status
    ) throws AuthenticationSecurityException_Exception;

    @WebMethod
    @Action(input = "http://endpoint.api.tm.bocharova.ru/IProjectEndpoint/removeAllProjectByUserIdRequest", output = "http://endpoint.api.tm.bocharova.ru/IProjectEndpoint/removeAllProjectByUserIdResponse", fault = {@FaultAction(className = AuthenticationSecurityException_Exception.class, value = "http://endpoint.api.tm.bocharova.ru/IProjectEndpoint/removeAllProjectByUserId/Fault/AuthenticationSecurityException")})
    @RequestWrapper(localName = "removeAllProjectByUserId", targetNamespace = "http://endpoint.api.tm.bocharova.ru/", className = "ru.bocharova.tm.api.endpoint.RemoveAllProjectByUserId")
    @ResponseWrapper(localName = "removeAllProjectByUserIdResponse", targetNamespace = "http://endpoint.api.tm.bocharova.ru/", className = "ru.bocharova.tm.api.endpoint.RemoveAllProjectByUserIdResponse")
    public void removeAllProjectByUserId(
        @WebParam(name = "session", targetNamespace = "")
        ru.bocharova.tm.api.endpoint.Session session
    ) throws AuthenticationSecurityException_Exception;

    @WebMethod
    @Action(input = "http://endpoint.api.tm.bocharova.ru/IProjectEndpoint/removeProjectRequest", output = "http://endpoint.api.tm.bocharova.ru/IProjectEndpoint/removeProjectResponse", fault = {@FaultAction(className = AuthenticationSecurityException_Exception.class, value = "http://endpoint.api.tm.bocharova.ru/IProjectEndpoint/removeProject/Fault/AuthenticationSecurityException")})
    @RequestWrapper(localName = "removeProject", targetNamespace = "http://endpoint.api.tm.bocharova.ru/", className = "ru.bocharova.tm.api.endpoint.RemoveProject")
    @ResponseWrapper(localName = "removeProjectResponse", targetNamespace = "http://endpoint.api.tm.bocharova.ru/", className = "ru.bocharova.tm.api.endpoint.RemoveProjectResponse")
    @WebResult(name = "return", targetNamespace = "")
    public ru.bocharova.tm.api.endpoint.Project removeProject(
        @WebParam(name = "session", targetNamespace = "")
        ru.bocharova.tm.api.endpoint.Session session,
        @WebParam(name = "id", targetNamespace = "")
        java.lang.String id
    ) throws AuthenticationSecurityException_Exception;

    @WebMethod
    @Action(input = "http://endpoint.api.tm.bocharova.ru/IProjectEndpoint/findOneProjectRequest", output = "http://endpoint.api.tm.bocharova.ru/IProjectEndpoint/findOneProjectResponse", fault = {@FaultAction(className = AuthenticationSecurityException_Exception.class, value = "http://endpoint.api.tm.bocharova.ru/IProjectEndpoint/findOneProject/Fault/AuthenticationSecurityException")})
    @RequestWrapper(localName = "findOneProject", targetNamespace = "http://endpoint.api.tm.bocharova.ru/", className = "ru.bocharova.tm.api.endpoint.FindOneProject")
    @ResponseWrapper(localName = "findOneProjectResponse", targetNamespace = "http://endpoint.api.tm.bocharova.ru/", className = "ru.bocharova.tm.api.endpoint.FindOneProjectResponse")
    @WebResult(name = "return", targetNamespace = "")
    public ru.bocharova.tm.api.endpoint.Project findOneProject(
        @WebParam(name = "session", targetNamespace = "")
        ru.bocharova.tm.api.endpoint.Session session,
        @WebParam(name = "id", targetNamespace = "")
        java.lang.String id
    ) throws AuthenticationSecurityException_Exception;

    @WebMethod
    @Action(input = "http://endpoint.api.tm.bocharova.ru/IProjectEndpoint/createProjectRequest", output = "http://endpoint.api.tm.bocharova.ru/IProjectEndpoint/createProjectResponse", fault = {@FaultAction(className = AuthenticationSecurityException_Exception.class, value = "http://endpoint.api.tm.bocharova.ru/IProjectEndpoint/createProject/Fault/AuthenticationSecurityException")})
    @RequestWrapper(localName = "createProject", targetNamespace = "http://endpoint.api.tm.bocharova.ru/", className = "ru.bocharova.tm.api.endpoint.CreateProject")
    @ResponseWrapper(localName = "createProjectResponse", targetNamespace = "http://endpoint.api.tm.bocharova.ru/", className = "ru.bocharova.tm.api.endpoint.CreateProjectResponse")
    @WebResult(name = "return", targetNamespace = "")
    public ru.bocharova.tm.api.endpoint.Project createProject(
        @WebParam(name = "session", targetNamespace = "")
        ru.bocharova.tm.api.endpoint.Session session,
        @WebParam(name = "name", targetNamespace = "")
        java.lang.String name,
        @WebParam(name = "description", targetNamespace = "")
        java.lang.String description
    ) throws AuthenticationSecurityException_Exception;

    @WebMethod
    @Action(input = "http://endpoint.api.tm.bocharova.ru/IProjectEndpoint/findAllProjectByUserIdRequest", output = "http://endpoint.api.tm.bocharova.ru/IProjectEndpoint/findAllProjectByUserIdResponse", fault = {@FaultAction(className = AuthenticationSecurityException_Exception.class, value = "http://endpoint.api.tm.bocharova.ru/IProjectEndpoint/findAllProjectByUserId/Fault/AuthenticationSecurityException")})
    @RequestWrapper(localName = "findAllProjectByUserId", targetNamespace = "http://endpoint.api.tm.bocharova.ru/", className = "ru.bocharova.tm.api.endpoint.FindAllProjectByUserId")
    @ResponseWrapper(localName = "findAllProjectByUserIdResponse", targetNamespace = "http://endpoint.api.tm.bocharova.ru/", className = "ru.bocharova.tm.api.endpoint.FindAllProjectByUserIdResponse")
    @WebResult(name = "return", targetNamespace = "")
    public java.util.List<ru.bocharova.tm.api.endpoint.Project> findAllProjectByUserId(
        @WebParam(name = "session", targetNamespace = "")
        ru.bocharova.tm.api.endpoint.Session session
    ) throws AuthenticationSecurityException_Exception;

    @WebMethod
    @Action(input = "http://endpoint.api.tm.bocharova.ru/IProjectEndpoint/sortAllProjectByUserIdRequest", output = "http://endpoint.api.tm.bocharova.ru/IProjectEndpoint/sortAllProjectByUserIdResponse", fault = {@FaultAction(className = AuthenticationSecurityException_Exception.class, value = "http://endpoint.api.tm.bocharova.ru/IProjectEndpoint/sortAllProjectByUserId/Fault/AuthenticationSecurityException")})
    @RequestWrapper(localName = "sortAllProjectByUserId", targetNamespace = "http://endpoint.api.tm.bocharova.ru/", className = "ru.bocharova.tm.api.endpoint.SortAllProjectByUserId")
    @ResponseWrapper(localName = "sortAllProjectByUserIdResponse", targetNamespace = "http://endpoint.api.tm.bocharova.ru/", className = "ru.bocharova.tm.api.endpoint.SortAllProjectByUserIdResponse")
    @WebResult(name = "return", targetNamespace = "")
    public java.util.List<ru.bocharova.tm.api.endpoint.Project> sortAllProjectByUserId(
        @WebParam(name = "session", targetNamespace = "")
        ru.bocharova.tm.api.endpoint.Session session,
        @WebParam(name = "comparator", targetNamespace = "")
        java.lang.String comparator
    ) throws AuthenticationSecurityException_Exception;

    @WebMethod
    @Action(input = "http://endpoint.api.tm.bocharova.ru/IProjectEndpoint/findAllProjectByPartOfNameOrDescriptionRequest", output = "http://endpoint.api.tm.bocharova.ru/IProjectEndpoint/findAllProjectByPartOfNameOrDescriptionResponse", fault = {@FaultAction(className = AuthenticationSecurityException_Exception.class, value = "http://endpoint.api.tm.bocharova.ru/IProjectEndpoint/findAllProjectByPartOfNameOrDescription/Fault/AuthenticationSecurityException")})
    @RequestWrapper(localName = "findAllProjectByPartOfNameOrDescription", targetNamespace = "http://endpoint.api.tm.bocharova.ru/", className = "ru.bocharova.tm.api.endpoint.FindAllProjectByPartOfNameOrDescription")
    @ResponseWrapper(localName = "findAllProjectByPartOfNameOrDescriptionResponse", targetNamespace = "http://endpoint.api.tm.bocharova.ru/", className = "ru.bocharova.tm.api.endpoint.FindAllProjectByPartOfNameOrDescriptionResponse")
    @WebResult(name = "return", targetNamespace = "")
    public java.util.List<ru.bocharova.tm.api.endpoint.Project> findAllProjectByPartOfNameOrDescription(
        @WebParam(name = "session", targetNamespace = "")
        ru.bocharova.tm.api.endpoint.Session session,
        @WebParam(name = "name", targetNamespace = "")
        java.lang.String name,
        @WebParam(name = "description", targetNamespace = "")
        java.lang.String description
    ) throws AuthenticationSecurityException_Exception;
}