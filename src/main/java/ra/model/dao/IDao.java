package ra.model.dao;

import java.util.List;

public interface IDao<T,E> {
    List<T> findAll();
    boolean save(T t);
    boolean update(T t);
    T findById(E e);
    T findByName(T t);
    boolean delete(E e);
}
