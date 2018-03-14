<html>

<head>
    <title>Something is going wrong</title>
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
        <h3>Error page</h3>
        <h3><a href="javascript: window.history.go(-1)">Back</a></h3>
        <p>Wrong request</p>
        <button type="button" class="btn btn-info" data-toggle="collapse" data-target="#error">Show details</button>
        <div id="error" class="collapse">
        ${message!""}
        </div>
    </div>
</div>
</body>

</html>