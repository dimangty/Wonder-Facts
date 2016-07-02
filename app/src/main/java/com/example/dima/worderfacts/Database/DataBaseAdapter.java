package com.example.dima.worderfacts.Database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;


/**
 * Created by dima on 04.06.16.
 */
public class DataBaseAdapter extends SQLiteOpenHelper {
    private static DataBaseAdapter instance;


    //   private static String DB_PATH = "/data/data/com.example.dima.worderfacts/databases/";


    private static String DB_PATH = "/databases/";
    private static String DB_NAME = "data.sqlite";
    private static final int SCHEMA = 1; // версия базы данных
    static final String TABLE = "users";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_YEAR = "year";
    public SQLiteDatabase database;
    private Context myContext;

    public static DataBaseAdapter getAdapter(Context context) {
        DataBaseAdapter localInstance = instance;
        if (localInstance == null) {
            synchronized (DataBaseAdapter.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new DataBaseAdapter(context);

                    localInstance.create_db();
                    // localInstance.open();
                }
            }
        }
        return localInstance;

    }

    public DataBaseAdapter(Context context) {
        super(context, DB_NAME, null, SCHEMA);
        DB_PATH = context.getFilesDir().getPath().replace("/files", "") + DB_PATH;
        this.myContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void create_db() {
        InputStream myInput = null;
        OutputStream myOutput = null;
        try {
            File file = new File(DB_PATH + DB_NAME);
            if (!file.exists()) {
                this.getReadableDatabase();
                //получаем локальную бд как поток
                myInput = myContext.getAssets().open(DB_NAME);
                // Путь к новой бд
                String outFileName = DB_PATH + DB_NAME;

                // Открываем пустую бд
                myOutput = new FileOutputStream(outFileName);

                // побайтово копируем данные
                byte[] buffer = new byte[1024];
                int length;
                while ((length = myInput.read(buffer)) > 0) {
                    myOutput.write(buffer, 0, length);
                }

                myOutput.flush();
                myOutput.close();
                myInput.close();
            }
        } catch (IOException ex) {

        }
    }

    public void open() throws SQLException {
        String path = DB_PATH + DB_NAME;
        database = SQLiteDatabase.openDatabase(path, null,
                SQLiteDatabase.OPEN_READWRITE);

    }

    @Override
    public synchronized void close() {
        if (database != null) {
            database.close();
        }
        super.close();
    }


    public ArrayList<FactItem> getAllFacts() {
        try {
            this.open();
        } catch (SQLException ex) {
            return null;
        }

        ArrayList<FactItem> result = new ArrayList<>();
        String selectQuery = "SELECT  * FROM Facts";
        Cursor cursor = database.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                FactItem factItem = new FactItem();
                factItem.setFactId(cursor.getInt(cursor.getColumnIndex("FactId")));
                factItem.setFactDateStr(cursor.getString(cursor.getColumnIndex("FactDate")));
                factItem.setFactRating(cursor.getInt(cursor.getColumnIndex("Rating")));
                factItem.setFactBody(cursor.getString(cursor.getColumnIndex("FactBody")));
                factItem.setUserId(cursor.getInt(cursor.getColumnIndex("UserId")));
                factItem.setFirstName(cursor.getString(cursor.getColumnIndex("FName")));
                factItem.setLastName(cursor.getString(cursor.getColumnIndex("LName")));
                factItem.setFactCount(cursor.getInt(cursor.getColumnIndex("FCount")));
                factItem.setCurrentTotalRating(cursor.getInt(cursor.getColumnIndex("TotalRaiting")));
                factItem.setCurrentTitul(cursor.getString(cursor.getColumnIndex("Titul")));
                factItem.setFactStar(cursor.getInt(cursor.getColumnIndex("Star")));
                factItem.setCurrentAvatar(cursor.getString(cursor.getColumnIndex("AvatarUrl")));
                result.add(factItem);

            } while (cursor.moveToNext());
        }

        this.close();
        return result;
    }

    public ArrayList<FactItem> getCategoryFacts(int categoryId) {
        try {
            this.open();
        } catch (SQLException ex) {
            return null;
        }


        String selectQuery = "SELECT  * FROM FACTS_BY_CATEGORY where Categoryid=" + categoryId;
        Cursor cursor = database.rawQuery(selectQuery, null);

        ArrayList<Integer> facts = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                int factId = cursor.getInt(cursor.getColumnIndex("Factid"));
                facts.add(factId);
            } while (cursor.moveToNext());
        }
        this.close();

        ArrayList<FactItem> result = new ArrayList<>();

        for (Integer factId : facts) {
            FactItem item = getFact(factId);
            if (item != null)
                result.add(item);

        }

        return result;
    }

    public FactItem getFact(int factId) {
        try {
            this.open();
        } catch (SQLException ex) {
            return null;
        }

        ArrayList<FactItem> result = new ArrayList<>();
        String selectQuery = "SELECT  * FROM Facts where Factid=" + factId;
        Cursor cursor = database.rawQuery(selectQuery, null);

        FactItem factItem = new FactItem();

        if (cursor.moveToFirst()) {

            factItem.setFactId(cursor.getInt(cursor.getColumnIndex("FactId")));
            factItem.setFactDateStr(cursor.getString(cursor.getColumnIndex("FactDate")));
            factItem.setFactRating(cursor.getInt(cursor.getColumnIndex("Rating")));
            factItem.setFactBody(cursor.getString(cursor.getColumnIndex("FactBody")));
            factItem.setUserId(cursor.getInt(cursor.getColumnIndex("UserId")));
            factItem.setFirstName(cursor.getString(cursor.getColumnIndex("FName")));
            factItem.setLastName(cursor.getString(cursor.getColumnIndex("LName")));
            factItem.setFactCount(cursor.getInt(cursor.getColumnIndex("FCount")));
            factItem.setCurrentTotalRating(cursor.getInt(cursor.getColumnIndex("TotalRaiting")));
            factItem.setCurrentTitul(cursor.getString(cursor.getColumnIndex("Titul")));
            factItem.setFactStar(cursor.getInt(cursor.getColumnIndex("Star")));
            factItem.setCurrentAvatar(cursor.getString(cursor.getColumnIndex("AvatarUrl")));
        }
        this.close();
        return factItem;
    }

    public ArrayList<CategoryItem> getAllCategories() {
        try {
            this.open();
        } catch (SQLException ex) {
            return null;
        }

        ArrayList<CategoryItem> result = new ArrayList<CategoryItem>();

        String selectQuery = "SELECT  * FROM Categorys";
        Cursor cursor = database.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                CategoryItem categoryItem = new CategoryItem();
                categoryItem.setCategoryId(cursor.getInt(cursor.getColumnIndex("Categoryid")));
                categoryItem.setCategoryName(cursor.getString(cursor.getColumnIndex("Category")));
                categoryItem.setFactsCount(cursor.getInt(cursor.getColumnIndex("FactsCount")));
                result.add(categoryItem);
            } while (cursor.moveToNext());
        }

        this.close();
        return result;
    }


    public String getCategoryForFact(int factId) {
        String result = "";

        try {
            this.open();
        } catch (SQLException ex) {
            return null;
        }


        String selectQuery = "SELECT  * FROM FACTS_BY_CATEGORY where Factid=" + factId;
        Cursor cursor = database.rawQuery(selectQuery, null);


        if (cursor.moveToFirst()) {
            do {
                String category = cursor.getString(cursor.getColumnIndex("Category"));
                if (result.length() == 0)
                    result = "" + category;
                else
                    result = result + ", " + category;

            } while (cursor.moveToNext());
        }
        this.close();
        return result;
    }


    public ArrayList<FactItem> getTopFacts() {
        try {
            this.open();
        } catch (SQLException ex) {
            return null;
        }

        ArrayList<FactItem> result = new ArrayList<FactItem>();
        String selectQuery = "SELECT * FROM Facts order by Rating desc limit 50";
        Cursor cursor = database.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                String factBody = cursor.getString(cursor.getColumnIndex("FactBody"));
                FactItem factItem = new FactItem();
                factItem.setFactId(cursor.getInt(cursor.getColumnIndex("FactId")));
                factItem.setFactDateStr(cursor.getString(cursor.getColumnIndex("FactDate")));
                factItem.setFactRating(cursor.getInt(cursor.getColumnIndex("Rating")));
                factItem.setFactBody(cursor.getString(cursor.getColumnIndex("FactBody")));
                factItem.setUserId(cursor.getInt(cursor.getColumnIndex("UserId")));
                factItem.setFirstName(cursor.getString(cursor.getColumnIndex("FName")));
                factItem.setLastName(cursor.getString(cursor.getColumnIndex("LName")));
                factItem.setFactCount(cursor.getInt(cursor.getColumnIndex("FCount")));
                factItem.setCurrentTotalRating(cursor.getInt(cursor.getColumnIndex("TotalRaiting")));
                factItem.setCurrentTitul(cursor.getString(cursor.getColumnIndex("Titul")));
                factItem.setFactStar(cursor.getInt(cursor.getColumnIndex("Star")));
                factItem.setCurrentAvatar(cursor.getString(cursor.getColumnIndex("AvatarUrl")));
                result.add(factItem);
                factBody = "";
            } while (cursor.moveToNext());
        }

        this.close();
        return result;
    }


    public ArrayList<FactItem> getFavoritesFacts() {
        try {
            this.open();
        } catch (SQLException ex) {
            return null;
        }


        String selectQuery = "SELECT  * FROM Favorites";
        Cursor cursor = database.rawQuery(selectQuery, null);

        ArrayList<Integer> facts = new ArrayList<Integer>();

        if (cursor.moveToFirst()) {
            do {
                int factId = cursor.getInt(cursor.getColumnIndex("Factid"));
                facts.add(factId);
            } while (cursor.moveToNext());
        }
        this.close();

        ArrayList<FactItem> result = new ArrayList<FactItem>();

        for (Integer factId : facts) {
            FactItem item = getFact(factId);
            if (item != null)
                result.add(item);

        }

        return result;
    }


    public ArrayList<UserItem> getDesk() {
        try {
            this.open();
        } catch (SQLException ex) {
            return null;
        }

        ArrayList<UserItem> result = new ArrayList<>();
        String selectQuery = "Select * from HALLO_FRAME  order by TotalRaiting Desc";
        Cursor cursor = database.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {


                UserItem userItem = new UserItem();

                userItem.setUserId(cursor.getInt(cursor.getColumnIndex("UserId")));
                userItem.setFirstName(cursor.getString(cursor.getColumnIndex("FName")));
                userItem.setLastName(cursor.getString(cursor.getColumnIndex("LName")));
                userItem.setFactCount(cursor.getInt(cursor.getColumnIndex("FCount")));
                userItem.setTotalRating(cursor.getInt(cursor.getColumnIndex("TotalRaiting")));
                userItem.setTitul(cursor.getString(cursor.getColumnIndex("Titul")));
                userItem.setStarCount(cursor.getInt(cursor.getColumnIndex("Star")));
                userItem.setAvatarUrl(cursor.getString(cursor.getColumnIndex("AvatarUrl")));
                userItem.setSocialType(cursor.getInt(cursor.getColumnIndex("SocialType")));
                result.add(userItem);

            } while (cursor.moveToNext());
        }

        this.close();
        return result;
    }


    public Boolean addToFavorite(int factId) {

        try {
            this.open();
        } catch (SQLException ex) {
            return false;
        }

        ContentValues values = new ContentValues();
        values.put("Factid", factId);

        long result = database.insert("Favorites", null, values);
        this.close();

        if (result >= 0)
            return true;
        else
            return false;
    }

    public Boolean testFavorite(int factId) {
        try {
            this.open();
        } catch (SQLException ex) {
            return false;
        }


        String selectQuery = "SELECT  * FROM Favorites where Factid=" + factId;
        Cursor cursor = database.rawQuery(selectQuery, null);


        if (cursor.moveToFirst()) {
            return true;
        }
        return false;
    }

    public Boolean removeFromFavorite(int factId) {

        try {
            this.open();
        } catch (SQLException ex) {
            return false;
        }

        long result = database.delete("Favorites", "Factid=" + factId, null);
        this.close();

        if (result >= 0)
            return true;
        else
            return false;
    }

}





