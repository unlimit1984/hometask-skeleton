<html>

<head>
    <title>Event</title>
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
    <h3>Event</h3>

    <form action="addEvent" method="post">
        <div class="form-group">
            <label for="id">Id:</label>
            <input type="text" class="form-control" id="id" name="id" readonly
                   value="${(event.id)!""}">
        </div>
        <div class="form-group">
            <label for="name">Name:</label>
            <input type="text" class="form-control" id="name" name="name" placeholder="Enter name"
                   required="required"
                   value="${(event.name)!""}">
        </div>
        <div class="form-group">
            <label for="basePrice">Base price:</label>
            <input type="text" class="form-control" id="basePrice" name="basePrice" placeholder="Enter Base Price"
                   required="required"
                   value="${(event.basePrice)!""}">
        </div>

        <div class="form-group">
            <label for="rating">Rating:</label>
            <input type="text" class="form-control" id="rating" name="rating" placeholder="Enter rating"
                   required="required"
                   value="${(event.rating)!""}">
        </div>

        <#--<div class="radio">-->
            <#--<label><input type="radio" name="rating">LOW</label>-->
        <#--</div>-->
        <#--<div class="radio">-->
            <#--<label><input type="radio" name="rating">MID</label>-->
        <#--</div>-->
        <#--<div class="radio disabled">-->
            <#--<label><input type="radio" name="rating">HIGH</label>-->
        <#--</div>-->

        <button type="submit" class="btn btn-primary">Submit</button>
    </form>

</div>
</body>

</html>