package controleur;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.ThreadsDAO;
import model.dao.UsersDAO;
import model.dto.Thread;
import model.dto.User;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/addThread")
public class AddThreadController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/src/vue/addThread.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ThreadsDAO threadsDAO = new ThreadsDAO();
        User user = (User) req.getSession().getAttribute("user");
        threadsDAO.create(new Thread(req.getParameter("title"), user.getId(), user.getUsername()));
        Thread thread = threadsDAO.findByTitle(req.getParameter("title"));

        /*
        PrintWriter out = resp.getWriter();


        out.println(user.getThreads().get(user.getThreads().size() - 1).getTitle());


         */
        user.setThreads(new UsersDAO().getThreads(user.getId()));
        req.getSession().setAttribute("selectedThread", thread);
        resp.sendRedirect(req.getContextPath() + "/homepage?selectedThread=" + thread.getId());
    }
}
