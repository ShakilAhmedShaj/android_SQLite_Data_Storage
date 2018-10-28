package com.t3ch.shaj.android_sqlite_data_storage;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    databaseHandler databaseHandler;

    private EditText nameEditText, ageEditText, genderEditText, idEditText;
    private Button addDataBTN, showBTN, updateBTN;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHandler = new databaseHandler(this);
        SQLiteDatabase sqLiteDatabase = databaseHandler.getWritableDatabase();

        nameEditText = findViewById(R.id.editTextName);
        ageEditText = findViewById(R.id.editTextAge);
        genderEditText = findViewById(R.id.editTextGender);
        idEditText = findViewById(R.id.editTextID);

        addDataBTN = findViewById(R.id.addDataBTN);
        showBTN = findViewById(R.id.showDataBTN);
        updateBTN = findViewById(R.id.updateDataBTN);

        addDataBTN.setOnClickListener(this);
        showBTN.setOnClickListener(this);
        updateBTN.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        String id = idEditText.getText().toString();
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

        if (v.getId() == R.id.showDataBTN) {

            Cursor cursor = databaseHandler.displayData();

            if (cursor.getCount() == 0) {

                showData("Error", "No Data Found");


            } else {
                StringBuffer stringBuffer = new StringBuffer();

                while (cursor.moveToNext()) {
                    stringBuffer.append("ID :" + cursor.getString(0) + "\n");
                    stringBuffer.append("Name :" + cursor.getString(1) + "\n");
                    stringBuffer.append("Age :" + cursor.getString(2) + "\n");
                    stringBuffer.append("Gender :" + cursor.getString(3) + "\n\n");
                }

                showData("ResultSet", stringBuffer.toString());

            }


        } else if (v.getId() == R.id.updateDataBTN)

        {

            Boolean isUpdated = databaseHandler.updateData(id, name, age, gender);

            if (isUpdated == true) {
                Toast.makeText(getApplicationContext(), "Data Updated", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "Data Update Failed", Toast.LENGTH_SHORT).show();
            }


        }


    }

    private void showData(String title, String data) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle(title);
        builder.setMessage(data);
        builder.setCancelable(true);
        builder.show();

    }
}
