package controleur;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.PostDAO;
import model.dao.ThreadsDAO;
import model.dao.UsersDAO;
import model.dto.Post;
import model.dto.Thread;
import model.dto.User;
import model.utils.UserUtils;

import java.io.IOException;

@WebServlet("/homepage")
public class HomePageController extends HttpServlet {
    private ThreadsDAO threadsDAO;
    private UsersDAO usersDAO;
    private PostDAO postDAO;

    @Override
    public void init() throws ServletException {
        threadsDAO = new ThreadsDAO();
        usersDAO = new UsersDAO();
        postDAO = new PostDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");

        ThreadsDAO threadsDAO = new ThreadsDAO();
        User user;

        int userId =  ((User) req.getSession().getAttribute("user")).getId();
        user = UserUtils.getUpdatedUser(userId);
        req.getSession().setAttribute("userId", userId);
        req.getSession().setAttribute("user", user);

        String selectedThreadParam = req.getParameter("selectedThread");

        if (selectedThreadParam == null) {
            req.getSession().setAttribute("selectedThread", null);
            req.getRequestDispatcher("/WEB-INF/src/vue/homePage.jsp").forward(req, resp);
            return;
        }

        if (selectedThreadParam.isEmpty()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Thread ID cannot be empty");
            return;
        }

        try {
            int selectedThreadId = Integer.parseInt(selectedThreadParam);
            Thread selectedThread = threadsDAO.findById(selectedThreadId);
            req.getSession().setAttribute("selectedThread", selectedThread);
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid Thread ID format");
            return;
        }

        req.getRequestDispatcher("/WEB-INF/src/vue/homePage.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String message = req.getParameter("message");
        String escapedMessage = escapeHtml(message);

        User user = (User) req.getSession().getAttribute("user");
        Thread selectedThread = (Thread) req.getSession().getAttribute("selectedThread");

        if (user == null || selectedThread == null) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "User or Thread not found in session");
            return;
        }

        Post post = new Post(user, selectedThread.getId(), escapedMessage);
        postDAO.create(post);

        resp.sendRedirect(req.getContextPath() + "/homepage?selectedThread=" + selectedThread.getId());
    }

    private String escapeHtml(String message) {
        if (message == null) {
            return null;
        }
        return message
                .replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&#x27;")
                .replace("/", "&#x2F;");
    }
}