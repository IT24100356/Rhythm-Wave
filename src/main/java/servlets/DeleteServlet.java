package servlets;

import dao.TrackDAO;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Track;

import java.io.File;
import java.io.IOException;

public class DeleteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            TrackDAO dao = new TrackDAO();
            Track track = dao.getById(id);

            if (track != null) {
                // Delete track file
                if (track.getTrackPath() != null && !track.getTrackPath().isEmpty()) {
                    String trackFilePath = req.getServletContext().getRealPath(track.getTrackPath());
                    File trackFile = new File(trackFilePath);
                    if (trackFile.exists()) {
                        trackFile.delete();
                    }
                }

                // Delete thumbnail file
                if (track.getThumbnailPath() != null && !track.getThumbnailPath().isEmpty()) {
                    String thumbnailFilePath = req.getServletContext().getRealPath(track.getThumbnailPath());
                    File thumbnailFile = new File(thumbnailFilePath);
                    if (thumbnailFile.exists()) {
                        thumbnailFile.delete();
                    }
                }

                // Delete track from database
                dao.delete(id);
            }

            String contextPath = req.getContextPath();
            resp.sendRedirect(contextPath + "/index.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "Failed to delete track: " + e.getMessage());
            try {
                req.getRequestDispatcher("/error.jsp").forward(req, resp);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}