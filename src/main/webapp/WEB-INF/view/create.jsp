<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add user</title>
    <jsp:include page="../view/tags/tagPage.jsp"/>
</head>
<body>
<%--<form method="post" action="/create">--%>
    <%--<label><input type="text" name="name"></label>Name<br/>--%>
    <%--<label><input type="text" name="login"></label>Login<br/>--%>
    <%--<label><input type="text" name="password"></label>Password<br/>--%>
    <%--<label><input type="text" name="role"></label>Role<br/>--%>
    <%--<label><input type="text" name="email"></label>Email<br/>--%>
    <%--<input type="submit" name="ok" value="ok">--%>
<%--</form>--%>
<%--<p align="left"><a class="btn btn-info btn-xs" href="list" role="button">To users list</a></p>--%>

<div class="container">
    <h1>Save User</h1>
    <form method="post" action="/create">
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
            <label>Role</label>
            <input class="form-control" name="role" placeholder="Role">
        </div>
        <div class="form-group">
            <label>Email</label>
            <input class="form-control" name="email" placeholder="Email">
        </div>
        <input class="btn btn-default btn-xs" type="submit" value="Save">
        <a class="btn btn-default btn-xs" href="list" role="button">cancel</a>
    </form>
</div>
</body>
</html>
