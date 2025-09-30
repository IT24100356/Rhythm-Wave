package dao;

import models.Track;
import utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TrackDAO {

    public List<Track> getTracksByStatus(String status) {
        List<Track> tracks = new ArrayList<>();
        String sql = status.equals("APPROVED") ? "SELECT * FROM tracks WHERE status IN ('APPROVED', 'INACTIVE')" : "SELECT * FROM tracks WHERE status = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            if (!status.equals("APPROVED")) {
                ps.setString(1, status);
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Track t = extractTrack(rs);
                tracks.add(t);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tracks;
    }

    public Track getById(int id) {
        Track t = null;
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement("SELECT * FROM tracks WHERE id = ?")) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                t = extractTrack(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return t;
    }

    public void updateStatus(int id, String status) {
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement("UPDATE tracks SET status = ?, updated_at = CURRENT_TIMESTAMP WHERE id = ?")) {
            ps.setString(1, status);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insert(Track t) {
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement("INSERT INTO tracks (title, album, genre, release_date, price, track_path, thumbnail_path, status, artist_id, lyrics) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
            setTrackParams(ps, t, false);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Track t) {
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement("UPDATE tracks SET title = ?, album = ?, genre = ?, release_date = ?, price = ?, track_path = ?, thumbnail_path = ?, status = ?, artist_id = ?, lyrics = ?, updated_at = CURRENT_TIMESTAMP WHERE id = ?")) {
            setTrackParams(ps, t, true);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement("DELETE FROM tracks WHERE id = ?")) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getArtistEmail(int artistId) {
        String email = null;
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement("SELECT email FROM users WHERE id = ?")) {
            ps.setInt(1, artistId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                email = rs.getString("email");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return email;
    }

    private Track extractTrack(ResultSet rs) throws SQLException {
        Track t = new Track();
        t.setId(rs.getInt("id"));
        t.setTitle(rs.getString("title"));
        t.setAlbum(rs.getString("album"));
        t.setGenre(rs.getString("genre"));
        t.setReleaseDate(rs.getString("release_date"));
        t.setPrice(rs.getDouble("price"));
        t.setTrackPath(rs.getString("track_path"));
        t.setThumbnailPath(rs.getString("thumbnail_path"));
        t.setStatus(rs.getString("status"));
        t.setArtistId(rs.getObject("artist_id") == null ? null : rs.getInt("artist_id"));  // Handle null
        t.setLyrics(rs.getString("lyrics"));
        return t;
    }

    private void setTrackParams(PreparedStatement ps, Track t, boolean isUpdate) throws SQLException {
        ps.setString(1, t.getTitle());
        ps.setString(2, t.getAlbum());
        ps.setString(3, t.getGenre());
        ps.setString(4, t.getReleaseDate());
        ps.setDouble(5, t.getPrice());
        ps.setString(6, t.getTrackPath());
        ps.setString(7, t.getThumbnailPath());
        ps.setString(8, t.getStatus());
        if (t.getArtistId() != null) {
            ps.setInt(9, t.getArtistId());
        } else {
            ps.setNull(9, Types.INTEGER);
        }
        ps.setString(10, t.getLyrics());
        if (isUpdate) {
            ps.setInt(11, t.getId());
        }
    }
}