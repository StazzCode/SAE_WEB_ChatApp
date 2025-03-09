package controleur;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
}
