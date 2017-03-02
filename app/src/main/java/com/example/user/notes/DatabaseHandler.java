package com.example.user.notes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by User on 3/2/2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "dailyNotes";
    public static final String TABLE_NAME= "notes";
    public static final String KEY_ID = "id";
    public static final String KEY_TITLE = "title";
    public static final String KEY_DATE= "date";
    public static final String KEY_SUBJECT = "subject";
    public static final String KEY_DETAILS = "details";
    Context context;
    String sq = "CREATE TABLE " + TABLE_NAME + " (" + KEY_ID + " INTEGER PRIMARY KEY , " + KEY_TITLE + " TEXT, " + KEY_DATE + " TEXT, " + KEY_SUBJECT + " TEXT, " + KEY_DETAILS + " TEXT)";


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(sq);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


    public void insertInformation(String title, Date date, String subject, String details) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("title", title);
        values.put("date", String.valueOf(date));
        values.put("subject", subject);
        values.put("details", details);
        db.insert(TABLE_NAME, null, values);
        db.close();
    }


    public List<DatabaseModel> getInformations() {
        List<DatabaseModel> modelList = new ArrayList<DatabaseModel>();

        String query = "select * from " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {

            do {
                DatabaseModel model = new DatabaseModel();

                model.setTitle(cursor.getString(1));
                model.setDate(cursor.getString(2));
                model.setSubject(cursor.getString(3));
                model.setDetails(cursor.getString(4));
                modelList.add(model);
            } while (cursor.moveToNext());
        }
        return modelList;
    }

    public int updateInformation(long id, String upTitle, String upDate, String upSubject, String upDescription) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, upTitle);
        values.put(KEY_DATE, upDate);
        values.put(KEY_SUBJECT, upSubject);
        values.put(KEY_DETAILS, upDescription);
        int i = database.update(TABLE_NAME, values, KEY_ID + " =? ", new String[]{id + ""});


        return i;
    }


    public void deleteInfo(int id)
    {
        SQLiteDatabase database = this.getWritableDatabase();
        database.delete(TABLE_NAME,KEY_ID+"="+id,null);


    }


}
