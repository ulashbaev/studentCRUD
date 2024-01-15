package db;

import java.util.List;

public interface Repository <T>{
    void create(T t);
    void update(T t);
    void delete(T t);
    List<T> read();
}
