<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Admin Dashboard</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h1>Admin Dashboard</h1>
    <a href="pending" class="btn btn-primary m-2">Pending Requests</a>
    <a href="approved" class="btn btn-success m-2">Approved Songs</a>
    <a href="rejected" class="btn btn-danger m-2">Rejected Songs</a>
    <a href="add" class="btn btn-info m-2">Add New Song</a>
    <a href="emails" class="btn btn-secondary m-2">View Sent Emails</a>
</div>
</body>
</html>