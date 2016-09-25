package br.com.erico.efemerides;

import java.util.List;

/**
 * Created by Mobile on 22/09/2016.
 */
public interface DAO<T> {

    public static final String DATABASE = "efemerides.db";
    public static final int VERSAO = 1;

    public void insert(T t);

    public void update(T t);

    public void delete(int id);

    public T get(int id);

    public List<T> all();

}
