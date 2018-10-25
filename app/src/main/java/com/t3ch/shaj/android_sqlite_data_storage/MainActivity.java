package com.t3ch.shaj.android_sqlite_data_storage;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    databaseHandler databaseHandler;

    private EditText nameEditText, ageEditText, genderEditText;
    private Button addDataBTN;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHandler = new databaseHandler(this);
        SQLiteDatabase sqLiteDatabase = databaseHandler.getWritableDatabase();

        nameEditText = findViewById(R.id.editTextName);
        ageEditText = findViewById(R.id.editTextAge);
        genderEditText = findViewById(R.id.editTextGender);
        addDataBTN = findViewById(R.id.addDataBTN);

        addDataBTN.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        String name = nameEditText.getText().toString();
        String age = ageEditText.getText().toString();
        String gender = genderEditText.getText().toString();


        if (v.getId() == R.id.addDataBTN) {

            long rowID = databaseHandler.insertData(name, age, gender);

            if (rowID > 0) {
                Toast.makeText(getApplicationContext(), "Data Inserted in " + rowID + " row  ", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "Data Insert Failed ", Toast.LENGTH_SHORT).show();
            }
        }


    }
}
