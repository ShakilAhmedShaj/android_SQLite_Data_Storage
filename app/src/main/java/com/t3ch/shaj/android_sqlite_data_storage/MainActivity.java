package com.t3ch.shaj.android_sqlite_data_storage;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    databaseHandler  databaseHandler;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHandler = new databaseHandler(this);

        SQLiteDatabase sqLiteDatabase = databaseHandler.getWritableDatabase();


    }
}
