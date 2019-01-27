<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Car-Share</title>
    <link href="/bootstrap.min.css" rel="stylesheet">
    <script src="/jquery-2.2.1.min.js"></script>
    <script src="/bootstrap.min.js"></script>
</head>
<body>
<div class="container" style="width: 400px; vertical-align: middle; margin-top: 50px;">
    <h3>Car-Share</h3>
    <c:if test="${param.error ne null}">
        <div style="color: red">Invalid credentials.</div>
    </c:if>
    <form action="/register" method="post">
        <div class="form-group">
            <label for="username">UserName:</label>
            <input type="text" class="form-control" id="username" name="username" required>
        </div>
        <div class="form-group">
            <label for="pwd">Password:</label>
            <input type="password" class="form-control" id="pwd" name="password" required>
        </div>
        <div class="form-group">
            <label for="rpwd">Repeated password:</label>
            <input type="password" class="form-control" id="rpwd" name="rpassword" required>
        </div>
        <button type="submit" class="btn btn-success">Register</button>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form>
</div>
</body>
</html>