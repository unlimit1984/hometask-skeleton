<html>

<head>
    <title>User list</title>
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
    <h3>Event list</h3>

    <table class="table">
        <thead>
        <tr>
            <th>Name</th>
            <th>Base price</th>
            <th>Rating</th>
        </tr>
        </thead>
        <tbody>
        <#list events as event>
        <tr>
            <td>${event.name}</td>
            <td>${event.basePrice}</td>
            <td>${event.rating}</td>
        </tr>
        </#list>
        </tbody>
    </table>
</div>
</body>

</html>