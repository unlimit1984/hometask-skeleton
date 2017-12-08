<html>

<head>
    <title>Ticket list</title>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <!-- Latest compiled JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <script>

        $(document).ready(function(){

            $('#addOneRow').on('click', function () {
                var id = $(".ticket_input_row:last").attr('id');
                var newId = parseInt(id)+1;
                var html = $(".ticket_input_row:last")[0].outerHTML;

                var regex1=/\[/;
                var regex2=/\]/;
                var regex = new RegExp(regex1.source+id+regex2.source, "g");
                html = html.replace(regex, "["+newId+"]");
                var appendTr = jQuery(html);
                appendTr.attr('id', ++id).insertAfter(".ticket_input_row:last");
            });

        });
    </script>
</head>

<body>
<div class="container">
    <div class="jumbotron" style="padding: 1px 20px 20px 20px; margin: 0;">
        <h2><a href="./">Main page</a></h2>
        <hr>
        <h3><a href="./events">Back</a></h3>
        <h3>Ticket list</h3>

        <form action="./tickets/book" method="post">
            <input type="hidden" name="airDate" value="${airDate}">
            <table class="table">
                <thead>
                <tr>
                    <th>User Id</th>
                    <th>Date Time</th>
                    <th>Seat</th>
                </tr>
                </thead>

                <tbody>
                <tr class="ticket_input_row" id="0">
                    <td>
                        <input type="text" class="form-control" name="tickets[0].userId" placeholder="Enter user Id"
                               required>
                    </td>
                    <td>
                        <input type="datetime-local" class="form-control" name="tickets[0].dateTime"
                               placeholder="Enter date time"
                               value="${airDate}"
                               readonly>
                    </td>
                    <td>
                        <input type="text" class="form-control" name="tickets[0].seat" placeholder="Enter seat"
                               required>
                    </td>
                </tr>
                <#--<tr>-->
                    <#--<td>-->
                        <#--<input type="text" class="form-control" name="tickets[1].userId" placeholder="Enter user Id"-->
                               <#--required>-->
                    <#--</td>-->
                    <#--<td>-->
                        <#--<input type="datetime-local" class="form-control" name="tickets[1].dateTime"-->
                               <#--placeholder="Enter date time"-->
                               <#--value="${airDate}"-->
                               <#--readonly>-->
                    <#--</td>-->
                    <#--<td>-->
                        <#--<input type="text" class="form-control" name="tickets[1].seat" placeholder="Enter seat"-->
                               <#--required>-->
                    <#--</td>-->
                <#--</tr>-->
                </tbody>
            </table>
            <input type="hidden" name="eventId" value="${eventId}">
            <div class="btn-group-vertical">
                <button type="button" class="btn btn-success" id="addOneRow">+</button>
                <button type="submit" class="btn btn-primary">Save</button>
            </div>
        </form>

        <table class="table">
            <thead>
            <tr>
                <th>Id</th>
                <th>User Id</th>
                <th>Event Id</th>
                <th>Date Time</th>
                <th>Seat</th>
            </tr>
            </thead>
            <tbody>
            <#list ticketsToShow as ticket>
            <tr>
                <td>${ticket.id}</td>
                <td>${ticket.userId}</td>
                <td>${ticket.eventId}</td>
                <td>${ticket.dateTime.format("dd.MM.yyyy HH:mm")}</td>
                <td>${ticket.seat}</td>
            </tr>

            </#list>
            </tbody>
        </table>
    </div>
</div>
</body>

</html>