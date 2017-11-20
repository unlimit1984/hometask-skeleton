<html>

<head>
    <title>Auditorium</title>
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
    <h3><a href="javascript: window.history.go(-1)">Back</a></h3>
    <h3>Auditorium</h3>

    <form>
        <div class="form-group">
            <label for="name">Name:</label>
            <input type="text" class="form-control" id="name" placeholder="Enter name" disabled
                   value="${auditorium.name}">
        </div>
        <div class="form-group">
            <label for="name">Name:</label>
            <input type="text" class="form-control" id="numberOfSeats" placeholder="Enter number of seats" disabled
                   value="${auditorium.numberOfSeats}">
        </div>
    </form>

</div>
</body>

</html>