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
import java.io.Writer;
import java.sql.SQLException;

@WebServlet("/homepage/*")
public class HomePageController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");

        UsersDAO usersDAO = new UsersDAO();
        ThreadsDAO threadsDAO = new ThreadsDAO();
        User user;
        try {
            user = usersDAO.findById(1);
            req.getSession().setAttribute("user", user);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        Thread selectedThread;

        String info = req.getPathInfo();

        if (info == null || info.equals("/")) {
            req.getRequestDispatcher("/WEB-INF/src/vue/homePage.jsp").forward(req, resp);
            return;
        }

        String[] splits = info.split("/");
        if (splits.length > 2) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        if (splits.length == 2) {
            try {
                int id = Integer.parseInt(splits[1]);
                selectedThread = threadsDAO.findById(id);
                req.setAttribute("selectedThread", selectedThread);
                req.getRequestDispatcher("/WEB-INF/src/vue/homePage.jsp").forward(req, resp);
            } catch (NumberFormatException | SQLException e) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            }
        }


    }
}
