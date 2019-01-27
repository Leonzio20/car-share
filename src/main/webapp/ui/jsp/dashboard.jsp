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
            <a href="/passage" about="">New passage</a>
        </div>
        <div class="row text-center"><strong>Available passages</strong></div>
        <div class="row" style="border:1px solid green;padding:10px">
            <div class="col-md-2 text-center"><strong>Origin</strong></div>
            <div class="col-md-2 text-center"><strong>Destination</strong></div>
            <div class="col-md-2 text-center"><strong>Available seats</strong></div>
            <div class="col-md-2 text-center"><strong>Time of departure</strong></div>
            <div class="col-md-2 text-center"><strong>Driver</strong></div>
            <div class="col-md-2 text-center"></div>
        </div>
        <c:forEach var="passage" items="${passages}">
            <div class="row" style="border:1px solid green;padding:10px">
                <div class="col-md-2 text-center">${passage.origin}</div>
                <div class="col-md-2 text-center">${passage.destination}</div>
                <div class="col-md-2 text-center">${passage.availableSeatsCount}</div>
                <div class="col-md-2 text-center">${passage.timeOfDeparture}</div>
                <div class="col-md-2 text-center">${passage.author.userName}</div>
                <div class="col-md-2 text-center">
                    <form action="/dashboard" method="post">
                        <button type="submit" class="btn btn-success">Book</button>
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    </form>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
</body>
</html>