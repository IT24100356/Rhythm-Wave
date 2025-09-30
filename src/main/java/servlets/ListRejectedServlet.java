package servlets;

import dao.TrackDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Track;

import java.io.IOException;
import java.util.List;

public class ListRejectedServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TrackDAO dao = new TrackDAO();
        List<Track> tracks = dao.getTracksByStatus("REJECTED");
        req.setAttribute("tracks", tracks);
        req.getRequestDispatcher("/rejected.jsp").forward(req, resp);
    }
}