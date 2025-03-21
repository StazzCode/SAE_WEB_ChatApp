package controleur;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.dao.UsersDAO;
import model.dto.User;
import org.postgresql.shaded.com.ongres.scram.common.bouncycastle.pbkdf2.Digest;

@WebServlet("/Authent")
public class Authent extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String action = req.getParameter("action");
        String vue = "";

        switch (action) {
            case "register":
                vue = "WEB-INF/src/vue/register.jsp";
                break;
            case "login":
                vue = "WEB-INF/src/vue/login.jsp";
                break;
            default:
                vue = "WEB-INF/src/vue/index.jsp";
                break;
        }

        req.getRequestDispatcher(vue).forward(req, res);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        Boolean isLogged = false;
        try {
            HttpSession session = req.getSession(true);
            String userName = req.getParameter("username");
            String password = req.getParameter("password");
            UsersDAO usersDAO = new UsersDAO();
            String action = req.getParameter("action");
            String vue = "";

            switch (action) {
                case "login":
                    User utilisateur = usersDAO.findByUsername(userName);

                    if (utilisateur == null || utilisateur.getPassword() == null || !utilisateur.getPassword().equals(encryptPassword(password))) {
                        vue = "WEB-INF/src/vue/login.jsp";
                    } else {
                        session.setAttribute("user", utilisateur);
                        isLogged = true;
                    }
                    break;
                case "register":
                    if (usersDAO.save(userName, password)) {
                        User newUser = usersDAO.findByUsername(userName);
                        session.setAttribute("user", newUser);
                        isLogged = true;
                    } else {
                        vue = "WEB-INF/src/vue/register.jsp";
                    }
                    break;
            }
            if (isLogged) {
                res.sendRedirect(req.getContextPath() + "/homepage");
            } else {
                req.getRequestDispatcher(vue).forward(req, res);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private String encryptPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        StringBuilder sb = new StringBuilder();
        for (byte b : digest) {
            sb.append(String.format("%02x", b & 0xff));
        }
        return sb.toString();
    }
}