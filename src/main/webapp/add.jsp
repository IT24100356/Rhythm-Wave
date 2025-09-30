<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add New Track</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h1>Add New Track</h1>
    <% if (request.getAttribute("error") != null) { %>
    <div class="alert alert-danger"><%= request.getAttribute("error") %></div>
    <% } %>
    <form action="add" method="post" enctype="multipart/form-data">
        <div class="mb-3">
            <label for="title" class="form-label">Title</label>
            <input type="text" class="form-control" id="title" name="title" required>
        </div>
        <div class="mb-3">
            <label for="album" class="form-label">Album</label>
            <input type="text" class="form-control" id="album" name="album" required>
        </div>
        <div class="mb-3">
            <label for="genre" class="form-label">Genre</label>
            <input type="text" class="form-control" id="genre" name="genre" required>
        </div>
        <div class="mb-3">
            <label for="release_date" class="form-label">Release Date (YYYY-MM-DD)</label>
            <input type="date" class="form-control" id="release_date" name="release_date" required>
        </div>
        <div class="mb-3">
            <label for="price" class="form-label">Price</label>
            <input type="number" step="0.01" min="0.01" class="form-control" id="price" name="price" required>
        </div>
        <div class="mb-3">
            <label for="track_file" class="form-label">Track File</label>
            <input type="file" class="form-control" id="track_file" name="track_file" required>
        </div>
        <div class="mb-3">
            <label for="thumbnail_file" class="form-label">Thumbnail File</label>
            <input type="file" class="form-control" id="thumbnail_file" name="thumbnail_file">
        </div>
        <div class="mb-3">
            <label for="lyrics" class="form-label">Lyrics</label>
            <textarea class="form-control" id="lyrics" name="lyrics"></textarea>
        </div>
        <button type="submit" class="btn btn-primary">Add Track</button>
    </form>
    <a href="index.jsp" class="btn btn-secondary mt-3">Back to Dashboard</a>
</div>
</body>
</html>