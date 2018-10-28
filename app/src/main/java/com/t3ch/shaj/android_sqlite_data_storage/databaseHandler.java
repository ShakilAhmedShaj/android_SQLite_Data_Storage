package com.t3ch.shaj.android_sqlite_data_storage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;
import android.widget.Toast;


/**
 * Created by Shakil Ahmed Shaj on 25-Oct-18.
 */
public class databaseHandler extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "student";
    private static final String TABLE_NAME = "details";
    private static final String ID = "_id";
    private static final String NAME = "name";
    private static final String AGE = "age";
    private static final String GENDER = "gender";
    private static final int VERSION_NUMBER = 9;
    //private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME + " VARCHAR(255)," + AGE + " INTEGER    ); ";
    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME + " VARCHAR(255)," + AGE + " INTEGER," + GENDER + " VARCHAR(15)    ); ";

    private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    private static final String SELECT_ALL = "SELECT * FROM " + TABLE_NAME;


    private Context context;

    public databaseHandler(Context context) {
        super(context, DATABASE_NAME, null, VERSION_NUMBER);
        this.context = context;


    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        try {

            Toast.makeText(context, "Table Created", Toast.LENGTH_SHORT).show();

            db.execSQL(CREATE_TABLE);

        } catch (Exception e) {

            Toast.makeText(context, "Exception : " + e, Toast.LENGTH_SHORT).show();

        }


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        try {

            Toast.makeText(context, "Upgraded", Toast.LENGTH_SHORT).show();

            db.execSQL(DROP_TABLE);

            onCreate(db);


        } catch (Exception e) {

            Toast.makeText(context, "Exception : " + e, Toast.LENGTH_SHORT).show();

        }


    }

    public long insertData(String name, String age, String gender)

    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(NAME, name);
        contentValues.put(AGE, age);
        contentValues.put(GENDER, gender);

        long rowID = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);

        return rowID;


    }

    public Cursor displayData() {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery(SELECT_ALL, null);


        return cursor;
    }

    public boolean updateData(String id, String name, String age, String gender) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();


        contentValues.put(ID, id);
        contentValues.put(NAME, name);
        contentValues.put(AGE, age);
        contentValues.put(GENDER, gender);

        sqLiteDatabase.update(TABLE_NAME, contentValues, ID + "= ?", new String[]{id});
        return true;

    }


    public int deleteData(String id) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        return sqLiteDatabase.delete(TABLE_NAME, ID + " = ?", new String[]{id});

    }


}
