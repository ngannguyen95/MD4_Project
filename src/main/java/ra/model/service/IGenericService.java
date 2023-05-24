package ra.model.service;

import java.util.List;

public interface IGenericService <T,E>{
    List<T> findAll();
    boolean save(T t);
    boolean update(T t);
    T findById(E e);
    T findByName(T t);
    boolean delete(E e);
}
