package model.dao;

import java.util.List;

public interface DAO<E> {
    public List<E> findAll();
    public E findById(int id);
    public E save(E e);
    public boolean delete(int id);

}