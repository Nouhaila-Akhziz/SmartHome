package MDP;

import java.util.List;

public interface IDAO<T> {
    T find(int id);
    List<T> findAll();
    void save(T obj);
    void update(T obj);
    void delete(T obj);
}
