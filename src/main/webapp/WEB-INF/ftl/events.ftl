<html>

<head>
    <title>Event list</title>
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
        <h3>Event list</h3>
        <a href="./event/add">Add new</a>

        <table class="table">
            <thead>
            <tr>
                <td>Id</td>
                <th>Name</th>
                <th>Base price</th>
                <th>Rating</th>
                <th>Action</th>
                <th>Purchase</th>
            </tr>
            </thead>
            <tbody>
            <#list events as event>
            <tr>
                <td>${event.id}</td>
                <td>${event.name}</td>
                <td>${event.basePrice}</td>
                <td>${event.rating}</td>
                <td>
                    <div class="btn-group">
                        <input type="button"
                               class="btn btn-primary"
                               onclick="location.href='./event/id?id=${event.id}'"
                               value="Edit">
                        <input type="button"
                               class="btn btn-danger"
                               onclick="location.href='./event/removeEvent?id=${event.id}'"
                               value="Delete">
                    </div>
                </td>
                <td>
                    <#list event.airDates as airDate>
                        <div class="form-group">
                            <label for="${airDate}">${airDate.format("dd.MM.yyyy HH:mm")}</label>
                            <input type="button"
                                   id="${airDate}"
                                   class="btn btn-warning"
                                   onclick="location.href='tickets?eventId=${event.id}&dateTime=${airDate}'"
                                   value="Buy a ticket">
                        </div>
                    </#list>
                </td>
            </tr>
            </#list>
            </tbody>
        </table>
    </div>
</div>
</body>

</html>