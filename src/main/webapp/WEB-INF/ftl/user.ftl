<html>

<head>
    <title>User</title>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <!-- Latest compiled JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>

<body>
<div class="container">
    <h1><a href="../">Main page</a></h1>
    <h3><a href="javascript: window.history.go(-1)">Back</a></h3>
    <h3>User</h3>

    <form action="/addUser" method="post">
        <div class="form-group">
            <label for="id">Id:</label>
            <input type="text" class="form-control" id="id" disabled
                   value="">
            <div class="form-group">
                <label for="firstName">First Name:</label>
                <input type="text" class="form-control" id="firstName" placeholder="Enter First Name"
                       value="">
            </div>
            <div class="form-group">
                <label for="lastName">Last Name:</label>
                <input type="text" class="form-control" id="lastName" placeholder="Enter Last Name"
                       value="">
            </div>
            <div class="form-group">
                <label for="email">Email:</label>
                <input type="text" class="form-control" id="email" placeholder="Enter Email"
                       value="">
            </div>
    </form>

</div>
</body>

</html>