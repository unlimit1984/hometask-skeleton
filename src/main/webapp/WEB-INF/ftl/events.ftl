<html>

<head><title>User list</title></head>

<body>
<h1><a href="./">Main page</a></h1>
<h3>Event list</h3>

<table style="border: solid 1px">
    <tr>
        <th>Name</th>
        <th>Base price</th>
        <th>Rating</th>
    </tr>
<#list events as event>
    <tr>
        <td>${event.name}</td>
        <td>${event.basePrice}</td>
        <td>${event.rating}</td>
    </tr>
</#list>

</table>

</body>

</html>