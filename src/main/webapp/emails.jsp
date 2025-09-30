<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
  <title>Sent Emails</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
  <link href="css/style.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
  <h1>Sent Emails</h1>
  <table class="table table-striped">
    <thead>
    <tr>
      <th>To</th>
      <th>Subject</th>
      <th>Body</th>
      <th>Sent At</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="email" items="${emails}">
      <tr>
        <td>${email.toEmail}</td>
        <td>${email.subject}</td>
        <td>${email.body}</td>
        <td><fmt:formatDate value="${email.sentAt}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
  <a href="index.jsp" class="btn btn-secondary">Back to Dashboard</a>
</div>
</body>
</html>