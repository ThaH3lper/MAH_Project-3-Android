package se.mah.homebois.ethaplanner.db;

import java.util.List;

/**
 * Created by Simon on 9/8/2016.
 */
public interface IRepository<T> {

    void put(T model);

    void remove(T model);

    <A extends T> A get(Class<A> model, int id);

    <A extends T> List<A> get(Class<A> model, String where);

}
