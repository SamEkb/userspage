<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <jsp:include page="../view/tags/tagPage.jsp"/>
</head>
<body>
<div class="container">
    <h1>Save User</h1>
    <form method="post" action="edit">
        <input class="form-control" name="id" value="${user.id}" type="hidden">

        <div class="form-group">
            <label>Name</label>
            <input class="form-control" name="name" placeholder="Name" value="${user.name}">
        </div>
        <div class="form-group">
            <label>Login</label>
            <input class="form-control" name="login" placeholder="Login" value="${user.login}">
        </div>
        <div class="form-group">
            <label>Password</label>
            <input class="form-control" name="password" placeholder="Password" value="${user.password}">
        </div>
        <div class="form-group">
            <label>Role</label>
            <input class="form-control" name="role" placeholder="Role" value="${user.role}">
        </div>
        <div class="form-group">
            <label>Email</label>
            <input class="form-control" name="email" placeholder="Email" value="${user.email}">
        </div>
        <input class="btn btn-default btn-xs" type="submit" value="save">
        <a class="btn btn-default btn-xs" href="list" role="button">cancel</a>
    </form>
</div>
</body>
</html>