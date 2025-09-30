<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <title>Pending Tracks</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
  <link href="css/style.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
  <h1>Pending Tracks</h1>
  <table class="table table-striped">
    <thead>
    <tr>
      <th>ID</th>
      <th>Title</th>
      <th>Album</th>
      <th>Genre</th>
      <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="track" items="${tracks}">
      <tr>
        <td>${track.id}</td>
        <td>${track.title}</td>
        <td>${track.album}</td>
        <td>${track.genre}</td>
        <td>
          <a href="detail?id=${track.id}" class="btn btn-primary">Review</a>
        </td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
  <a href="index.jsp" class="btn btn-secondary">Back to Dashboard</a>
</div>
</body>
</html>