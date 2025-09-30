package servlets;

import dao.TrackDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import models.Track;

import java.io.File;
import java.io.IOException;

@MultipartConfig
public class AddServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/add.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Track track = new Track();
            track.setTitle(req.getParameter("title"));
            track.setAlbum(req.getParameter("album"));
            track.setGenre(req.getParameter("genre"));
            track.setReleaseDate(req.getParameter("release_date"));
            track.setPrice(Double.parseDouble(req.getParameter("price")));
            track.setStatus("APPROVED");  // Hardcoded to APPROVED for admin
            track.setArtistId(null);  // No artist ID for admin-added songs
            track.setLyrics(req.getParameter("lyrics"));

            // Server-side validation (basic, since no JS)
            if (track.getTitle() == null || track.getTitle().isEmpty() ||
                    track.getAlbum() == null || track.getAlbum().isEmpty() ||
                    track.getGenre() == null || track.getGenre().isEmpty() ||
                    track.getReleaseDate() == null || track.getReleaseDate().isEmpty() ||
                    track.getPrice() <= 0) {
                throw new ServletException("Required fields are missing or invalid.");
            }

            Part trackPart = req.getPart("track_file");
            if (trackPart.getSize() > 0) {
                String trackFileName = getFileName(trackPart);
                track.setTrackPath(uploadFile(trackPart, trackFileName, req));
            } else {
                throw new ServletException("Track file is required.");
            }

            Part thumbPart = req.getPart("thumbnail_file");
            if (thumbPart.getSize() > 0) {
                String thumbFileName = getFileName(thumbPart);
                track.setThumbnailPath(uploadFile(thumbPart, thumbFileName, req));
            }

            TrackDAO dao = new TrackDAO();
            dao.insert(track);
            String contextPath = req.getContextPath();
            resp.sendRedirect(contextPath + "/approved");
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "Failed to add track: " + e.getMessage());
            req.getRequestDispatcher("/add.jsp").forward(req, resp);
        }
    }

    private String getFileName(Part part) {
        String header = part.getHeader("content-disposition");
        for (String cd : header.split(";")) {
            if (cd.trim().startsWith("filename")) {
                return cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }

    private String uploadFile(Part part, String fileName, HttpServletRequest req) throws IOException {
        String uploadPath = req.getServletContext().getRealPath("/uploads");
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) uploadDir.mkdir();
        String fullPath = uploadPath + File.separator + fileName;
        part.write(fullPath);
        return "/uploads/" + fileName;
    }
}