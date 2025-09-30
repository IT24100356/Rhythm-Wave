package servlets;

import dao.TrackDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Track;

import java.io.IOException;

public class DetailServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        TrackDAO dao = new TrackDAO();
        Track track = dao.getById(id);
        req.setAttribute("track", track);
        req.getRequestDispatcher("/detail.jsp").forward(req, resp);
    }
}