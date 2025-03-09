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
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/homepage")
public class HomePageController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");

        ThreadsDAO threadsDAO = new ThreadsDAO();
        User user;

        int userId = 1;
        user = UserUtils.getUpdatedUser(userId);
        req.getSession().setAttribute("userId", userId);
        req.getSession().setAttribute("user", user);

        String selectedThreadParam = req.getParameter("selectedThread");

        if (selectedThreadParam == null){
            req.getSession().setAttribute("selectedThread", null);
            req.getRequestDispatcher("/WEB-INF/src/vue/homePage.jsp").forward(req, resp);
        }

        if (selectedThreadParam != null && selectedThreadParam.isEmpty()){
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Thread ID cannot be empty");
        }

        Thread selectedThread;
        int selectedThreadId = Integer.parseInt(req.getParameter("selectedThread"));
        selectedThread = threadsDAO.findById(selectedThreadId);
        req.getSession().setAttribute("selectedThread", selectedThread);
        req.getRequestDispatcher("/WEB-INF/src/vue/homePage.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String message = req.getParameter("message");
        String escapedMessage = message
                .replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&#x27;")
                .replace("/", "&#x2F;");

        User user = (User) req.getSession().getAttribute("user");

        Thread selectedThread = (Thread) req.getSession().getAttribute("selectedThread");

        Post post = new Post(user, selectedThread.getId(), escapedMessage);
        PostDAO postDAO = new PostDAO();
        postDAO.create(post);

        resp.sendRedirect(req.getContextPath() + "/homepage?selectedThread=" + selectedThread.getId());
    }
}
