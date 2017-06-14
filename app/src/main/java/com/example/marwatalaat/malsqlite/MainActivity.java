package com.example.marwatalaat.malsqlite;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.marwatalaat.malsqlite.database.Data;
import com.example.marwatalaat.malsqlite.database.SQliteUtil;

public class MainActivity extends AppCompatActivity {
    EditText nameField,phoneField,emailField;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SQliteUtil util = new SQliteUtil(this);
         db = util.getWritableDatabase();

        nameField = (EditText) findViewById(R.id.editText);
        phoneField = (EditText) findViewById(R.id.editText2);
        emailField = (EditText) findViewById(R.id.editText3);
    }

    public void saveInSqlite(View v){
        String name = nameField.getText().toString();
        String phone = phoneField.getText().toString();
        String email = emailField.getText().toString();

        ContentValues values = new ContentValues();
        values.put(Data.KEY_NAME,name);
        values.put(Data.KEY_PHONE,phone);
        values.put(Data.KEY_EMAIL,email);
        //insert into emp(col1,col3,col4) values('','','');

        long row = db.insert(Data.TABLE_NAME, null, values);
        if(row >0){
            Toast.makeText(MainActivity.this,"Done..",Toast.LENGTH_SHORT).show();
        }
    }
}
