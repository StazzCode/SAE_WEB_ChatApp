package controleur;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import model.dao.*;
import model.dto.*;
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
                case "creerCompte":
                    vue = "WEB-INF/vue/creerCompte.jsp";
                    break;
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
                    vue = "WEB-INF/vue/lireMessages.jsp";
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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String action = request.getParameter("action");

            switch(action) {
                case "creerCompte":
                    creerCompte(request, response);
                    break;
                case "authentifier":
                    //authentifier(request, response);
                    break;
                case "creerFil":
                    //creerFil(request, response);
                    break;
                case "posterMessage":
                    //posterMessage(request, response);
                    break;
                default:
                    response.sendRedirect("index.jsp");
                    break;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void creerCompte(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        response.sendRedirect("index.jsp");
    }
}