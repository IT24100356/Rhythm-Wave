package servlets;

import dao.EmailDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.SentEmail;

import java.io.IOException;
import java.util.List;

public class ListEmailsServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EmailDAO dao = new EmailDAO();
        List<SentEmail> emails = dao.getAll();
        req.setAttribute("emails", emails);
        req.getRequestDispatcher("/emails.jsp").forward(req, resp);
    }
}