package servlets;

import dao.EmailDAO;
import dao.TrackDAO;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Track;
import utils.EmailSender;

import java.io.IOException;

public class RejectServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String reason = req.getParameter("reason");
        TrackDAO dao = new TrackDAO();
        Track track = dao.getById(id);
        Integer artistId = track.getArtistId();
        String email = (artistId != null) ? dao.getArtistEmail(artistId) : null;
        if (email != null) {
            String subject = "Song Rejected";
            String body = "Your song '" + track.getTitle() + "' has been rejected. Reason: " + (reason != null ? reason : "Not specified.");
            EmailSender.sendEmail(email, subject, body);
            EmailDAO emailDAO = new EmailDAO();
            emailDAO.save(email, subject, body);
        }
        dao.updateStatus(id, "REJECTED");
        String contextPath = req.getContextPath();
        resp.sendRedirect(contextPath + "/pending");
    }
}