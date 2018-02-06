<html>

<head>
    <title>Booking tickets</title>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <!-- Latest compiled JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>

<body>
<div class="container">
    <div class="jumbotron" style="padding: 1px 20px 20px 20px; margin: 0;">
        <h2><a href="./">Main page</a></h2>
        <hr>
        <h3><a href="javascript: window.history.go(-1)">Back</a></h3>
        <h3>Check price and buy</h3>

        <form action="./tickets/book" method="post">
            <input type="hidden" name="eventId" value="${eventId}">
            <input type="hidden" name="airDate" value="${airDate}">

            <table class="table">
                <thead>
                <tr>
                    <th>Event</th>
                    <th>Date Time</th>
                    <th>Seat</th>
                </tr>
                </thead>

                <tbody>
                <#list purchasingTickets as ticket>
                <tr>
                    <td>${ticket.eventName}</td>
                    <td>${ticket.dateTime.format("dd.MM.yyyy HH:mm")}</td>
                    <td>${ticket.seat}</td>
                </tr>

                </#list>

                </tbody>
            </table>

            <div class="btn-group-vertical">
                <button type="button" class="btn btn-success" id="addOneRow">+</button>
                <button type="submit" class="btn btn-primary">Check price</button>
            </div>

            <!--with CSRF-->
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

        </form>

    </div>
</div>
</body>

</html>