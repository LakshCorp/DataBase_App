package com.example.lakshya.mydbapp;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Items myDB;

    EditText edName, edCourse, edBranch,edRoll_NO;

    Button add, View,Update,Delete;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDB = new Items(this);

        edName = (EditText) findViewById(R.id.editText);
        edCourse = (EditText) findViewById(R.id.editText2);
        edBranch = (EditText) findViewById(R.id.editText4);
        add = (Button) findViewById(R.id.button);
        View =(Button)findViewById(R.id.button4);
        Update = (Button)findViewById(R.id.button3);
        edRoll_NO =(EditText)findViewById(R.id.editText3);
        Delete=(Button)findViewById(R.id.button2);
    }

    public void addData(View v) {
        boolean isInserted = myDB.insertData(edName.getText().toString(), edCourse.getText().toString(), edBranch.getText().toString(),edRoll_NO.getText().toString());
        if (isInserted)
            Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();
        else Toast.makeText(MainActivity.this, "Data Not Inserted", Toast.LENGTH_LONG).show();


    }

    public void viewAll(View v) {
        Cursor res = myDB.getAllData();

        if (res.getCount() == 0) {
            //show message
            showMessage("ERROR"," NO DATA FOUND");
            return;
        }
        StringBuilder buffer = new StringBuilder();
        while (res.moveToNext()) {
            buffer.append("Roll No: ").append(res.getString(0)).append("\n");
            buffer.append("Name: ").append(res.getString(1)).append("\n");

            buffer.append("Course: ").append(res.getString(2)).append("\n");

            buffer.append("Branch: ").append(res.getString(3)).append("\n\n");

        }
        showMessage("DataBase",buffer.toString());
    }


    public void showMessage(String title, String message) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
                    builder.show();
    }


            public void Update(View v){
                boolean isUpdate = myDB.updateData(edName.getText().toString(),edCourse.getText().toString(), edBranch.getText().toString(),edRoll_NO.getText().toString());
                        if(isUpdate){
                            Toast.makeText(MainActivity.this, "Data Updated", Toast.LENGTH_LONG).show();
                        }
                        else Toast.makeText(MainActivity.this, "Data Not Updated", Toast.LENGTH_LONG).show();

            }



                public void Delete(View v){
                    Integer deleteRows = myDB.deleteData(edRoll_NO.getText().toString());
                    if(deleteRows>0) {
                        Toast.makeText(MainActivity.this, "Data Deleted", Toast.LENGTH_LONG).show();
                    }
                        else Toast.makeText(MainActivity.this, "Data Not Deleted", Toast.LENGTH_LONG).show();

                    }
                }
