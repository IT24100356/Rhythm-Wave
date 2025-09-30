package dao;

import models.SentEmail;
import utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmailDAO {

    public List<SentEmail> getAll() {
        List<SentEmail> emails = new ArrayList<>();
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement("SELECT * FROM sent_emails ORDER BY sent_at DESC")) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SentEmail e = new SentEmail();
                e.setId(rs.getInt("id"));
                e.setToEmail(rs.getString("to_email"));
                e.setSubject(rs.getString("subject"));
                e.setBody(rs.getString("body"));
                e.setSentAt(rs.getTimestamp("sent_at"));
                emails.add(e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return emails;
    }

    public void save(String to, String subject, String body) {
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement("INSERT INTO sent_emails (to_email, subject, body) VALUES (?, ?, ?)")) {
            ps.setString(1, to);
            ps.setString(2, subject);
            ps.setString(3, body);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}