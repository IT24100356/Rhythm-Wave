package servlets;

import dao.TrackDAO;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class SetInactiveServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        TrackDAO dao = new TrackDAO();
        dao.updateStatus(id, "INACTIVE");
        String contextPath = req.getContextPath();
        resp.sendRedirect(contextPath + "/approved");
    }
}