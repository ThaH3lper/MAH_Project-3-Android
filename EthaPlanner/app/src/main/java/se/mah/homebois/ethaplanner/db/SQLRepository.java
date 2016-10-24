package se.mah.homebois.ethaplanner.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Simon on 9/8/2016.
 */
public abstract class SQLRepository<T> extends SQLiteOpenHelper implements IRepository<T> {
    private static final int DATABASE_VERSION = 4;

    protected SQLiteDatabase db;

    public SQLRepository(Context context, String dbName) {
        super(context, dbName, null, DATABASE_VERSION);
        db = getWritableDatabase();
    }

    @Override
    public void put(T model) {
        ContentValues values = new ContentValues();
        for (Field f : model.getClass().getFields()) {
            String name = f.getName();

            // Don't allow setting id
            if (name.equals("id"))
                continue;

            try {
                if (f.get(model) == null)
                    continue;

                if (int.class.isAssignableFrom(f.getType())) {
                    values.put(name, f.getInt(model));
                } else if (float.class.isAssignableFrom(f.getType())) {
                    values.put(name, f.getFloat(model));
                } else if (String.class.isAssignableFrom(f.getType())) {
                    values.put(name, (String) f.get(model));
                } else if (Date.class.isAssignableFrom(f.getType())) {
                    values.put(name, ((Date) f.get(model)).getTime());
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        db.insert(model.getClass().getSimpleName(), null, values);
    }

    @Override
    public void remove(T model) {
        // db.delete(model.getClass().getSimpleName(), "id=" + model.id, null);
    }

    protected void createTable(Class<? extends T> model, SQLiteDatabase createDb) {
        String query = "CREATE TABLE " + model.getSimpleName() + " (\n";
        for (Field f : model.getFields()) {
            if (f.getName().equals("id")) {
                query += f.getName() + " integer primary key autoincrement,\n";
            } else if (int.class.isAssignableFrom(f.getType())) {
                query += f.getName() + " integer,\n";
            } else if (float.class.isAssignableFrom(f.getType())) {
                query += f.getName() + " decimal,\n";
            } else if (String.class.isAssignableFrom(f.getType())) {
                query += f.getName() + " varchar(255),\n";
            } else if (Date.class.isAssignableFrom(f.getType())) {
                query += f.getName() + " integer,\n";
            }
        }
        query = query.substring(0, query.length() - 2) + "\n);";

        createDb.execSQL(query);
    }

    @Override
    public <A extends T> A get(Class<A> model, int id) {
        Cursor cursor = db.query(model.getSimpleName(), null, "id=" + id, null, null, null, null);
        try {
            A a = model.newInstance();

            if (cursor.moveToFirst()) {
                for (Field f : model.getFields()) {
                    int index = cursor.getColumnIndex(f.getName());

                    Object o = null;
                    if (int.class.isAssignableFrom(f.getType())) {
                        o = cursor.getInt(index);
                    } else if (float.class.isAssignableFrom(f.getType())) {
                        o = cursor.getFloat(index);
                    } else if (String.class.isAssignableFrom(f.getType())) {
                        o = cursor.getString(index);
                    } else if (Date.class.isAssignableFrom(f.getType())) {
                        o = new Date(cursor.getLong(index));
                    }
                    if (o != null)
                        f.set(a, o);
                }

                cursor.close();
                return a;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        cursor.close();
        return null;
    }

    public <A extends T> List<A> get(Class<A> model, Cursor cursor) {
        List<A> list = new ArrayList<>();

        while (cursor.moveToNext()) {
            try {
                A a = model.newInstance();
                for (Field f : model.getFields()) {
                    int index = cursor.getColumnIndex(f.getName());

                    if (index == -1)
                        continue;

                    Object o = null;
                    if (int.class.isAssignableFrom(f.getType())) {
                        o = cursor.getInt(index);
                    } else if (float.class.isAssignableFrom(f.getType())) {
                        o = cursor.getFloat(index);
                    } else if (String.class.isAssignableFrom(f.getType())) {
                        o = cursor.getString(index);
                    } else if (Date.class.isAssignableFrom(f.getType())) {
                        o = new Date(cursor.getLong(index));
                    }
                    f.set(a, o);
                }
                list.add(a);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        cursor.close();
        return list;
    }

    @Override
    public <A extends T> List<A> get(Class<A> model, String where) {
        Cursor cursor = db.query(model.getSimpleName(), null, where, null, null, null, null);
        return get(model, cursor);
    }
}
