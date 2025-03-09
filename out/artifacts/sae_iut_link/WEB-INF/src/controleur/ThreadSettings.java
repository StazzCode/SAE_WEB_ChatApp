package controleur;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.SubscriptionsDAO;
import model.dto.Thread;
import model.dto.User;
import model.utils.UserUtils;

import java.io.IOException;

@WebServlet("/threadSettings")
public class ThreadSettings extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user;
        int userId = 1;
        user = UserUtils.getUpdatedUser(userId);
        req.getSession().setAttribute("userId", userId);
        req.getSession().setAttribute("user", user);
        req.getRequestDispatcher("/WEB-INF/src/vue/threadSettings.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        String action = req.getParameter("action");

        SubscriptionsDAO subscriptionsDAO = new SubscriptionsDAO();

        if (action.equals("removeSubscriber")) {
            // Remove the subscriber
            int subscriberId = Integer.parseInt(req.getParameter("subscriberId"));
            Thread selectedThread = (Thread) req.getSession().getAttribute("selectedThread");
            subscriptionsDAO.delete(subscriptionsDAO.findByUserIdAndThreadId(subscriberId, selectedThread.getId()).getId());
            resp.sendRedirect(req.getContextPath() + "/threadSettings");
        }

        if (action.equals("deleteThread")) {
            // Delete the thread
            Thread selectedThread = (Thread) req.getSession().getAttribute("selectedThread");
            subscriptionsDAO.delete(selectedThread.getId());
            req.getSession().setAttribute("selectedThread", null);
            resp.sendRedirect(req.getContextPath() + "/homepage");
        }
    }
}
