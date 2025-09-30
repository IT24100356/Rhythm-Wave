package models;

public class Track {
    private int id;
    private String title;
    private String album;
    private String genre;
    private String releaseDate;
    private double price;
    private String trackPath;
    private String thumbnailPath;
    private String status;
    private Integer artistId;  // Changed to Integer to allow null
    private String lyrics;

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getAlbum() { return album; }
    public void setAlbum(String album) { this.album = album; }
    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }
    public String getReleaseDate() { return releaseDate; }
    public void setReleaseDate(String releaseDate) { this.releaseDate = releaseDate; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public String getTrackPath() { return trackPath; }
    public void setTrackPath(String trackPath) { this.trackPath = trackPath; }
    public String getThumbnailPath() { return thumbnailPath; }
    public void setThumbnailPath(String thumbnailPath) { this.thumbnailPath = thumbnailPath; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public Integer getArtistId() { return artistId; }
    public void setArtistId(Integer artistId) { this.artistId = artistId; }
    public String getLyrics() { return lyrics; }
    public void setLyrics(String lyrics) { this.lyrics = lyrics; }
}