<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Result</title>
    <jsp:include page="../view/tags/tagPage.jsp"/>
</head>
<body>
<div class="container">
    <h1><p class="text-center">All User</p></h1>
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
        <c:forEach var="user" items="${users}">
            <tr>
                <td>${user.id}</td>
                <td>${user.name}</td>
                <td>${user.login}</td>
                <td>${user.password}</td>
                <td>${user.role}</td>
                <td>${user.email}</td>
                <td>
                    <form method="get" action="/edit">
                        <input type="number" hidden name="id" value="${user.id}"/>
                        <input type="submit" class="btn btn-default btn-xs" name="edit" value="Edit"/>
                    </form>
                    <form method="post" action="/delete">
                        <input type="number" hidden name="id" value="${user.id}"/>
                        <input type="submit" class="btn btn-danger btn-xs" name="delete" value="Delete"/>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <p align="left"><a class="btn btn-info btn-xs" href="create" role="button">Add User</a>
    <p align="left"><a class="btn btn-info btn-xs" href="/" role="button">Back</a></p>
</div>
</body>
</html>