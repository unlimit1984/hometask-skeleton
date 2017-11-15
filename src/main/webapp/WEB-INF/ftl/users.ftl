<html>

<head><title>User list</title></head>

<body>
<h1><a href="./">Main page</a></h1>
<h3>User list</h3>

<table style="border: solid 1px">
    <tr>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Email</th>
        <th>Birthday</th>
    </tr>
<#list users as user>
    <tr>
        <td>${user.firstName}</td>
        <td>${user.lastName}</td>
        <td>${user.email}</td>
        <td>${user.birthday}</td>
    </tr>

</#list>

</table>

</body>

</html>