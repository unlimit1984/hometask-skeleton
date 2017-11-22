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

    <form action="addUser" method="post">
        <div class="form-group">
            <label for="id">Id:</label>
            <input type="text" class="form-control" id="id" name="id" disabled
                   value="${(user.id)!""}">
        </div>
        <div class="form-group">
            <label for="firstName">First Name:</label>
            <input type="text" class="form-control" id="firstName" name="firstName" placeholder="Enter first name"
                   required="required"
                   value="${(user.firstName)!""}">
        </div>
        <div class="form-group">
            <label for="lastName">Last Name:</label>
            <input type="text" class="form-control" id="lastName" name="lastName" placeholder="Enter last name"
                   required="required"
                   value="${(user.lastName)!""}">
        </div>
        <div class="form-group">
            <label for="email">Email:</label>
            <input type="text" class="form-control" id="email" name="email" placeholder="Enter email"
                   required="required"
                   value="${(user.email)!""}">
        </div>
        <div class="form-group">
            <label for="birthday">Email:</label>
            <input type="date" class="form-control" id="birthday" name="birthday" placeholder="Enter birthday"
                   required="required"
                   value="${(user.birthday)!""}">
        </div>
        <button type="submit" class="btn btn-primary">Submit</button>
    </form>

</div>
</body>

</html>