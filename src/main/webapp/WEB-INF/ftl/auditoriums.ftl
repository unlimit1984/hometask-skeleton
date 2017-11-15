<html>

<head><title>User list</title></head>

<body>
<h1><a href="./">Main page</a></h1>
<h3>Auditorium list</h3>

<table style="border: solid 1px">
    <tr>
        <th>Name</th>
        <th>Number of seats</th>
    </tr>
<#list auditoriums as auditorium>
    <tr>
        <td>${auditorium.name}</td>
        <td>${auditorium.numberOfSeats}</td>
    </tr>

</#list>

</table>

</body>

</html>