<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Track Details</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h1>${track.title}</h1>
    <p><strong>Album:</strong> ${track.album}</p>
    <p><strong>Genre:</strong> ${track.genre}</p>
    <p><strong>Release Date:</strong> ${track.releaseDate}</p>
    <p><strong>Price:</strong> $${track.price}</p>
    <p><strong>Status:</strong> ${track.status}</p>
    <p><strong>Artist ID:</strong> ${track.artistId}</p>
    <c:if test="${not empty track.thumbnailPath}">
        <img src="${track.thumbnailPath}" alt="Thumbnail" class="img-fluid" width="200">
    </c:if>
    <audio controls>
        <source src="${track.trackPath}" type="audio/mpeg">
        Your browser does not support the audio element.
    </audio>
    <p><strong>Lyrics:</strong></p>
    <pre>${track.lyrics}</pre>

    <c:choose>
        <c:when test="${track.status == 'PENDING'}">
            <form action="approve" method="post" class="d-inline">
                <input type="hidden" name="id" value="${track.id}">
                <button type="submit" class="btn btn-success">Approve</button>
            </form>
            <form action="reject" method="post" class="d-inline">
                <input type="hidden" name="id" value="${track.id}">
                <textarea name="reason" class="form-control mt-2" placeholder="Reason for rejection"></textarea>
                <button type="submit" class="btn btn-danger mt-2">Reject</button>
            </form>
        </c:when>
        <c:when test="${track.status == 'APPROVED' || track.status == 'INACTIVE'}">
            <a href="edit?id=${track.id}" class="btn btn-info">Edit</a>
            <c:if test="${track.status == 'APPROVED'}">
                <form action="setInactive" method="post" class="d-inline">
                    <input type="hidden" name="id" value="${track.id}">
                    <button type="submit" class="btn btn-warning">Set Inactive</button>
                </form>
            </c:if>
            <c:if test="${track.status == 'INACTIVE'}">
                <form action="setActive" method="post" class="d-inline">
                    <input type="hidden" name="id" value="${track.id}">
                    <button type="submit" class="btn btn-success">Set Active</button>
                </form>
            </c:if>
            <form action="delete" method="post" class="d-inline">
                <input type="hidden" name="id" value="${track.id}">
                <button type="submit" class="btn btn-danger">Delete</button>
            </form>
        </c:when>
        <c:when test="${track.status == 'REJECTED'}">
            <form action="delete" method="post" class="d-inline">
                <input type="hidden" name="id" value="${track.id}">
                <button type="submit" class="btn btn-danger">Delete</button>
            </form>
        </c:when>
    </c:choose>
    <a href="index.jsp" class="btn btn-secondary mt-3">Back to Dashboard</a>
</div>
</body>
</html>