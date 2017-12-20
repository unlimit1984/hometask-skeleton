<html>

<head>
    <title>Login page</title>
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
        <h3>Authorization</h3>

    <#if error??>
        <p style="color: red">Your login attempt was not successful, try again.</p>
    </#if>

    <#if logout??>
        <p style="color: green">You have been logged out</p>
    </#if>


        <form action="perform_login" method="post">
            <div class="input-group">
                <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                <input id="username" type="text" class="form-control" name="username">
            </div>
            <div class="input-group">
                <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                <input id="password" type="password" class="form-control" name="password">
            </div>
            <br>
        <#--<input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}"/>-->
            <div class="input-group">
                <button type="submit" class="btn btn-primary">Login</button>
            </div>
        </form>
        <br>
    </div>
</div>

</body>

</html>