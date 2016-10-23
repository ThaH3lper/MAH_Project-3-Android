package se.mah.homebois.ethaplanner.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.List;

import se.mah.homebois.ethaplanner.models.BolagetArticle;

/**
 * Created by Simon on 10/23/2016.
 */

public class BolagetDB extends SQLRepository<BolagetArticle> {
    public static final String DB_NAME = "etha.db";

    public BolagetDB(Context context) {
        super(context, DB_NAME);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createTable(BolagetArticle.class, db);
    }

    public List<BolagetArticle> findByType(String type) {
        return get(BolagetArticle.class, String.format("Varugrupp LIKE '%%%s%%'", type));
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(BolagetDB.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");

        // Drop all tables
        db.execSQL("DROP TABLE IF EXISTS " + BolagetArticle.class.getSimpleName());

        onCreate(db);
    }

    public void clearAndUpdate(List<BolagetArticle> articles) {
        db.execSQL("DELETE FROM " + BolagetArticle.class.getSimpleName());
        db.beginTransaction();
        for (BolagetArticle article : articles) {
            put(article);
        }
        db.setTransactionSuccessful();
        db.endTransaction();
    }
}
