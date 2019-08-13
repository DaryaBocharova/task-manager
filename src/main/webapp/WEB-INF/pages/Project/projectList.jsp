<%--
  Created by IntelliJ IDEA.
  User: DaryBocharova
  Date: 12.08.2019
  Time: 21:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="ru.bocharova.tm.util.FieldConst" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<jsp:include page="/WEB-INF/pages/part/header.jsp"/>
<body>
<jsp:include page="/WEB-INF/pages/part/navigable.jsp"/>
<div class="container theme-showcase" role="main">
    <div class="header">
        <h3 class="text-muted"><br/></h3>
        <h4 class="text-muted">PROJECT MANAGEMENT:</h4>
    </div>
    <div class="jumbotron">
        <div class="row">
            <div class="col-md-12">

                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>#</th>
                        <th>Name</th>
                        <th>Description</th>
                        <th>Data begin</th>
                        <th>Data End</th>
                        <th>Status</th>
                        <th></th>
                        <th></th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:set var="i" value="0"/>
                    <c:forEach var="task" items="${projects}">
                        <tr>
                            <td>
                                    ${i=i+1}
                            </td>
                            <td>${task.getName()}
                            </td>
                            <td>${task.getDescription()}
                            </td>
                            <td><fmt:formatDate pattern="yyyy-MM-dd HH:mm"
                                                value="${task.getDateBegin()}"/>
                            </td>
                            <td><fmt:formatDate pattern="yyyy-MM-dd HH:mm"
                                                value="${task.getDateEnd()}"/>
                            </td>
                            <td>${task.getStatus()}
                            </td>
                            <td>
                                <button class="btn btn-primary btn-xs"
                                        onclick="postToUrl(
                                                '${pageContext.request.contextPath}/task/list',
                                                {'<%=FieldConst.PROJECT_ID%>':'${task.getId( )}'},
                                                'GET');">
                                    TASKS
                                </button>
                            </td>
                            <td>
                                <button class="btn btn-primary btn-xs"
                                        onclick="postToUrl(
                                                '${pageContext.request.contextPath}/project/edit',
                                                {'<%=FieldConst.PROJECT_ID%>':'${task.getId( )}'},
                                                'GET');">
                                    EDIT
                                </button>
                            </td>
                            <td>
                                <button class="btn btn-danger btn-xs"
                                        onclick="postToUrl(
                                                '${pageContext.request.contextPath}/project/delete',
                                                {'<%=FieldConst.PROJECT_ID%>':'${task.getId( )}'},
                                                'POST');">
                                    DELETE
                                </button>
                            </td>

                        </tr>
                    </c:forEach>
                    </tbody>
                </table>

                <button class="btn btn-success"
                        onclick="postToUrl(
                                '${pageContext.request.contextPath}/project/create',
                                '',
                                'POST');">
                    CREATE
                </button>
            </div>
        </div>
    </div>
    <!-- Footer -->
    <jsp:include page="/WEB-INF/pages/part/footer.jsp"/>
</div>
</body>
</html>