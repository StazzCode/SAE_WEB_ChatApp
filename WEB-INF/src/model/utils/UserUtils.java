package model.utils;

import model.dao.UsersDAO;
import model.dto.User;

import java.sql.SQLException;

public class UserUtils {
    public static User getUpdatedUser(int userId) {
        UsersDAO usersDAO = new UsersDAO();
        return usersDAO.findById(userId);
    }
}