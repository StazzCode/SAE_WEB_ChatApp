package controleur;
import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import model.dao.UsersDAO;
import jakarta.servlet.annotation.WebServlet;
import java.sql.*;
@WebServlet("/Authent")
public class Authent extends HttpServlet{
    public void doPost( HttpServletRequest req, HttpServletResponse res )
    throws ServletException, IOException{
        try {
            HttpSession session = req.getSession(true);
            session.setAttribute("auth",true);
            String userName = req.getParameter("username");
            String password = req.getParameter("password");
            UsersDAO usersDAO = new UsersDAO();
            String action = req.getParameter("action");
            String vue = "";
            User utilisateur;
            switch(action) {
                case "login":
                    utilisateur = usersDAO.findByUsername(userName);
                    if(utilisateur != null && utilisateur.getUsername().equals(userName)) {
                        session.setAttribute("user", utilisateur);
                        vue = "WEB-INF/vue/home.jsp";
                    } else {
                        vue = "WEB-INF/vue/login.jsp";
                    }
                    break;
                case "register":
                    if(usersDAO.save(userName, password)) {
                        session.setAttribute("user", userName);
                        vue = "WEB-INF/vue/home.jsp";
                    } else {
                        vue = "WEB-INF/vue/login.jsp";
                    }
                    break;
            }
            req.getRequestDispatcher(vue).forward(req, res);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}