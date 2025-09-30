<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Edit Track</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h1>Edit Track: ${track.title}</h1>
    <form action="edit" method="post" enctype="multipart/form-data">
        <input type="hidden" name="id" value="${track.id}">
        <div class="mb-3">
            <label for="title" class="form-label">Title</label>
            <input type="text" class="form-control" id="title" name="title" value="${track.title}" required>
        </div>
        <div class="mb-3">
            <label for="album" class="form-label">Album</label>
            <input type="text" class="form-control" id="album" name="album" value="${track.album}">
        </div>
        <div class="mb-3">
            <label for="genre" class="form-label">Genre</label>
            <input type="text" class="form-control" id="genre" name="genre" value="${track.genre}">
        </div>
        <div class="mb-3">
            <label for="release_date" class="form-label">Release Date</label>
            <input type="date" class="form-control" id="release_date" name="release_date" value="${track.releaseDate}">
        </div>
        <div class="mb-3">
            <label for="price" class="form-label">Price</label>
            <input type="number" step="0.01" class="form-control" id="price" name="price" value="${track.price}">
        </div>
        <div class="mb-3">
            <label for="track_file" class="form-label">Track File (leave blank to keep current)</label>
            <input type="file" class="form-control" id="track_file" name="track_file">
            <small>Current: ${track.trackPath}</small>
        </div>
        <div class="mb-3">
            <label for="thumbnail_file" class="form-label">Thumbnail File (leave blank to keep current)</label>
            <input type="file" class="form-control" id="thumbnail_file" name="thumbnail_file">
            <small>Current: ${track.thumbnailPath}</small>
        </div>
        <div class="mb-3">
            <label for="artist_id" class="form-label">Artist ID</label>
            <input type="number" class="form-control" id="artist_id" name="artist_id" value="${track.artistId}" required>
        </div>
        <div class="mb-3">
            <label for="lyrics" class="form-label">Lyrics</label>
            <textarea class="form-control" id="lyrics" name="lyrics">${track.lyrics}</textarea>
        </div>
        <div class="mb-3">
            <label for="status" class="form-label">Status</label>
            <select class="form-select" id="status" name="status">
                <option value="APPROVED" ${track.status == 'APPROVED' ? 'selected' : ''}>Approved</option>
                <option value="INACTIVE" ${track.status == 'INACTIVE' ? 'selected' : ''}>Inactive</option>
            </select>
        </div>
        <button type="submit" class="btn btn-primary">Update Track</button>
    </form>
    <a href="index.jsp" class="btn btn-secondary mt-3">Back to Dashboard</a>
</div>
</body>
</html>