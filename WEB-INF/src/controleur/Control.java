package controleur;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import model.dao.UsersDAO;

@WebServlet("/Control")
public class Control extends HttpServlet {

    private UsersDAO daoUtilisateur;

    public Control() {
        try {
            daoUtilisateur = new UsersDAO();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override   
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String action = request.getParameter("action");
            String vue = "";

            switch(action) {
                case "authentifier":
                    vue = "WEB-INF/vue/authentifier.jsp";
                    break;
                case "creerFil":
                    vue = "WEB-INF/vue/creerFil.jsp";
                    break;
                case "posterMessage":
                    vue = "WEB-INF/vue/posterMessage.jsp";
                    break;
                case "likeMessage":
                    likeMessage(request, response);
                    break;
                case "Acceuil":
                    vue = "WEB-INF/vue/index.jsp";
                    break;
                case "profil":
                vue = "WEB-INF/vue/profil.jsp";
                    break;                
                default:
                    vue = "WEB-INF/vue/index.jsp";
                    break;
            }

            request.getRequestDispatcher(vue).forward(request, response);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void likeMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/vue/index.jsp").forward(request, response);
    }
}