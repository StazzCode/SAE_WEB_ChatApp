package model.dao;

import java.sql.SQLException;
import java.util.List;

public interface DAO<E> {
    public E findById(int id) throws SQLException;
    public List<E> findAll() throws SQLException;
    public void delete(int id) throws SQLException;
    public void save(E e) throws SQLException;

}