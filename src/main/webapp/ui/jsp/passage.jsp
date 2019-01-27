<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Car-Share</title>
    <link href="/bootstrap.min.css" rel="stylesheet">
    <script src="/jquery-2.2.1.min.js"></script>
    <script src="/bootstrap.min.js"></script>
</head>
<body>
<div>
    <div class="container-fluid" style="margin:50px;border: 1px solid green;">
        <div>
            <form action="/logout" method="post">
                <button type="submit" class="btn btn-danger">Log Out</button>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </form>
            <a href="/dashboard" about="">Available passages</a>
        </div>
        <div class="row text-center"><strong>New passage</strong></div>
        <div class="row text-center" style="border:1px solid green;padding:10px; width: 500px;">
            <form action="/passage" method="post" >
                <div class="form-group">
                    <label for="origin">Origin:</label>
                    <input type="text" class="form-control" id="origin" name="origin" required>
                </div>
                <div class="form-group">
                    <label for="destination">Destination:</label>
                    <input type="text" class="form-control" id="destination" name="destination" required>
                </div>
                <div class="form-group">
                    <label for="seatsCount">Seats count:</label>
                    <input type="number" class="form-control" id="seatsCount" name="seatsCount" required>
                </div>
                <div class="form-group">
                    <label for="time">Time:</label>
                    <input type="datetime-local" class="form-control" id="time" name="time" required>
                </div>
                <button type="submit" class="btn btn-success">Create</button>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </form>
        </div>
    </div>
</div>
</body>
</html>