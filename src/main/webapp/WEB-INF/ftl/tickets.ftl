<html>

<head>
    <title>Ticket list</title>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <!-- Latest compiled JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>

<body>
<div class="container">
    <h1><a href="./">Main page</a></h1>
    <h3>Ticket list</h3>
    <#--<a href="#">Add new</a>-->

    <form action="./tickets/book" method="post">
        <table class="table">
            <thead>
            <tr>
                <th>Ticket Id</th>
                <th>User Id</th>
                <th>Event Id</th>
                <th>Date Time</th>
                <th>Seat</th>
            </tr>
            </thead>

            <tbody>
                <tr>
                    <td>
                        <input type="text" class="form-control" name="tickets[0].id" placeholder="Enter ticket Id"
                               disabled>
                    </td>
                    <td>
                        <input type="text" class="form-control" name="tickets[0].userId" placeholder="Enter user Id"
                               >
                    </td>
                    <td>
                        <input type="text" class="form-control" name="tickets[0].eventId" placeholder="Enter event Id"
                               >
                    </td>
                    <td>
                        <input type="datetime-local" class="form-control" name="tickets[0].dateTime" placeholder="Enter date time"
                               required="required">
                    </td>
                    <td>
                        <input type="text" class="form-control" name="tickets[0].seat" placeholder="Enter seat"
                               >
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="text" class="form-control" name="tickets[1].id" placeholder="Enter ticket Id"
                               disabled>
                    </td>
                    <td>
                        <input type="text" class="form-control" name="tickets[1].userId" placeholder="Enter user Id"
                               >
                    </td>
                    <td>
                        <input type="text" class="form-control" name="tickets[1].eventId" placeholder="Enter event Id"
                               >
                    </td>
                    <td>
                        <input type="datetime-local" class="form-control" name="tickets[1].dateTime" placeholder="Enter date time"
                               required="required">
                    </td>
                    <td>
                        <input type="text" class="form-control" name="tickets[1].seat" placeholder="Enter seat"
                               >
                    </td>
                </tr>
            </tbody>
        </table>
        <button type="submit" class="btn btn-primary">Save</button>
    </form>

    <table class="table">
        <thead>
        <tr>
            <th>Id</th>
            <th>User Id</th>
            <th>Event Id</th>
            <th>Date Time</th>
            <th>Seat</th>
            <#--<th>Action</th>-->
        </tr>
        </thead>
        <tbody>
        <#list ticketsToShow as ticket>
        <tr>
            <td>${ticket.id}</td>
            <td>${ticket.userId}</td>
            <td>${ticket.eventId}</td>
            <#--<td>${(ticket.dateTime)}</td>-->
            <#--<td>${(ticket.dateTime)?string["yyyy-MM-dd HH:mm"]}</td>-->
            <td>${ticket.dateTime.format("dd.MM.yyyy HH:mm")}</td>
            <td>${ticket.seat}</td>
        </tr>

        </#list>
        </tbody>
    </table>
</div>
</body>

</html>