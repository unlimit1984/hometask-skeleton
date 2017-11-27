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
    <h3>User list</h3>
    <a href="./user/add">Add new</a>

    <table class="table">
        <thead>
        <tr>
            <th>Id</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Email</th>
            <th>Birthday</th>
        </tr>
        </thead>
    <tbody>
    <#list users as user>
        <tr>
            <td><a href="./user/id?id=${user.id}">${user.id}</a></td>
            <td>${user.firstName}</td>
            <td>${user.lastName}</td>
            <td><a href="./user/email?email=${user.email}">${user.email}</a></td>
            <td>${user.birthday}</td>
        </tr>

    </#list>
    </tbody>
    </table>
</div>
</body>

</html>