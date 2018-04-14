<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Main page</title>
    <jsp:include page="../view/tags/tagPage.jsp"/>
</head>
<body>
<div class="container">
    <h2>Welcome to our page</h2>
    <p>Please input login and password:</p>

    <form method="post" action="">
        <div class="form-group">
            <label for="login">Login:</label>
            <input type="text" class="form-control" name="login" id="login">
        </div>
        <div class="form-group">
            <label for="password">Password:</label>
            <input type="password" class="form-control" name="password" id="password">
        </div>

        <div class="container">
            <input type="submit" class="btn btn-info" value="Sign in">
            <a class="btn btn-info" href="register" role="button">Sign up</a>
        </div>

    </form>
</div>
</body>
</html>
