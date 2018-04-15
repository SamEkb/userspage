<%--
  Created by IntelliJ IDEA.
  User: Semen
  Date: 14.04.2018
  Time: 14:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <jsp:include page="../view/tags/tagPage.jsp"/>
</head>
<body>
<h1>Hello USER!</h1>
<div class="container">
    <h1>Save User</h1>
    <form method="post" action="/register">
        <div class="form-group">
            <label>Name</label>
            <input class="form-control" name="name" placeholder="Name">
        </div>
        <div class="form-group">
            <label>Login</label>
            <input class="form-control" name="login" placeholder="Login">
        </div>
        <div class="form-group">
            <label>Password</label>
            <input class="form-control" name="password" placeholder="Password">
        </div>
        <div class="form-group">
            <label>Email</label>
            <input class="form-control" name="email" placeholder="Email">
        </div>
        <input class="btn btn-default btn-xs" type="submit" value="Save">
        <a class="btn btn-default btn-xs" href="/" role="button">cancel</a>
    </form>
</div>
</body>
</html>
