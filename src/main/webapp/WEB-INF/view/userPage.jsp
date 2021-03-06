<%--
  Created by IntelliJ IDEA.
  User: Semen
  Date: 10.04.2018
  Time: 16:56
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>USER</title>
    <jsp:include page="../view/tags/tagPage.jsp"/>
</head>
<body>

<h1>Hello USER!</h1>
<div class="container">
    <h1><p class="text-center">User</p></h1>
    <table class="table">
        <thead>
        <tr>
            <td>Id</td>
            <td>Name</td>
            <td>Login</td>
            <td>Password</td>
            <td>Role</td>
            <td>Email</td>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td><c:out value="${user.id}"/></td>
            <td><c:out value="${user.name}"/></td>
            <td><c:out value="${user.login}"/></td>
            <td><c:out value="${user.password}"/></td>
            <td><c:out value="${user.role}"/></td>
            <td><c:out value="${user.email}"/></td>
            <td>
                <form method="post" action="/delete">
                    <input type="number" hidden name="id" value="${user.id}"/>
                    <input type="submit" class="btn btn-danger btn-xs" name="delete" value="Delete"/>
                </form>
            </td>
        </tr>
        </tbody>
    </table>
    <p align="left"><a class="btn btn-info btn-xs" href="logout" role="button">Logout</a></p>
</div>
</body>
</html>
