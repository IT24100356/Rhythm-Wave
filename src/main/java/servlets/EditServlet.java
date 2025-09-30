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
public class EditServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        TrackDAO dao = new TrackDAO();
        Track track = dao.getById(id);
        req.setAttribute("track", track);
        req.getRequestDispatcher("/edit.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        TrackDAO dao = new TrackDAO();
        Track track = dao.getById(id);
        track.setTitle(req.getParameter("title"));
        track.setAlbum(req.getParameter("album"));
        track.setGenre(req.getParameter("genre"));
        track.setReleaseDate(req.getParameter("release_date"));
        track.setPrice(Double.parseDouble(req.getParameter("price")));
        track.setStatus(req.getParameter("status"));
        track.setArtistId(Integer.parseInt(req.getParameter("artist_id")));
        track.setLyrics(req.getParameter("lyrics"));

        Part trackPart = req.getPart("track_file");
        if (trackPart.getSize() > 0) {
            String trackFileName = getFileName(trackPart);
            track.setTrackPath(uploadFile(trackPart, trackFileName, req));
        }

        Part thumbPart = req.getPart("thumbnail_file");
        if (thumbPart.getSize() > 0) {
            String thumbFileName = getFileName(thumbPart);
            track.setThumbnailPath(uploadFile(thumbPart, thumbFileName, req));
        }

        dao.update(track);
        resp.sendRedirect("/approved");
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