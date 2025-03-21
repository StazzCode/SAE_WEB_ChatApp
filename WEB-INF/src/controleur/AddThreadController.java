package controleur;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.SubscriptionsDAO;
import model.dao.ThreadsDAO;
import model.dto.Subscription;
import model.dto.Thread;
import model.dto.User;
import model.utils.UserUtils;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/addThread")
public class AddThreadController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user;
        int userId = 0;

        if (req.getSession().getAttribute("user") == null) {
            req.getRequestDispatcher("/WEB-INF/src/vue/login.jsp").forward(req, resp);
        } else {
            userId = ((User) req.getSession().getAttribute("user")).getId();
            user = UserUtils.getUpdatedUser(userId);
            req.getSession().setAttribute("userId", userId);
            req.getSession().setAttribute("user", user);
        }
        req.getRequestDispatcher("/WEB-INF/src/vue/addThread.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ThreadsDAO threadsDAO = new ThreadsDAO();
        SubscriptionsDAO subscriptionsDAO = new SubscriptionsDAO();

        User user = (User) req.getSession().getAttribute("user");
        String action = req.getParameter("action");


        if (action.equals("add")) {
            threadsDAO.create(new Thread(req.getParameter("title"), user.getId(), user.getUsername()));
            Thread thread = threadsDAO.findByTitle(req.getParameter("title"));
            Subscription subscription = new Subscription(user.getId(), thread.getId());
            subscriptionsDAO.create(subscription);


            req.getSession().setAttribute("selectedThread", thread);
            resp.sendRedirect(req.getContextPath() + "/homepage?selectedThread=" + thread.getId());
        }

        if (action.equals("subscribe")){
            Subscription subscription = new Subscription(user.getId(), Integer.parseInt(req.getParameter("threadId")));
            subscriptionsDAO.create(subscription);

            PrintWriter out = resp.getWriter();
            out.println(subscription.getId() + subscription.getUserId() + subscription.getThreadId());
            resp.sendRedirect(req.getContextPath() + "/addThread");
        }

        if (action.equals("unsubscribe")){
            subscriptionsDAO.delete(subscriptionsDAO.findByUserIdAndThreadId(user.getId(), Integer.parseInt(req.getParameter("threadId"))).getId());
            resp.sendRedirect(req.getContextPath() + "/addThread");
        }
    }
}
