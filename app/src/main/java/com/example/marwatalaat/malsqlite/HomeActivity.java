package com.example.marwatalaat.malsqlite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.marwatalaat.malsqlite.database.Data;
import com.example.marwatalaat.malsqlite.database.SQliteUtil;
import com.example.marwatalaat.malsqlite.model.Contact;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        SQliteUtil util = new SQliteUtil(this);
        db = util.getWritableDatabase();

        viewAllContacts();
    }

    public List<Contact> viewAllContacts() {

        List<Contact> contactList = new ArrayList<>();
        String columns[] = {Data.KEY_ID, Data.KEY_NAME, Data.KEY_PHONE, Data.KEY_EMAIL};
//            String args  []={String.valueOf(1)};
//            db.query(Data.TABLE_NAME,columns,Data.KEY_ID+"=?",args,null,null,Data.KEY_NAME);
//        db.query(Data.TABLE_NAME,columns,Data.KEY_ID+"=1 and "+Data.KEY_NAME+"=mohamed",null,null,null,Data.KEY_NAME);


        Cursor c = db.query(Data.TABLE_NAME, columns, null, null, null, null, Data.KEY_NAME);
        if (c.moveToFirst()) {
            do {
                int id = c.getInt(c.getColumnIndex(Data.KEY_ID));
                String name = c.getString(c.getColumnIndex(Data.KEY_NAME));
                String phone = c.getString(c.getColumnIndex(Data.KEY_PHONE));
                String email = c.getString(c.getColumnIndex(Data.KEY_EMAIL));

                contactList.add(new Contact(id,name,phone,email));

            } while (c.moveToNext());
            Toast.makeText(HomeActivity.this,contactList.get(0).getName(),Toast.LENGTH_SHORT).show();
        }
        return contactList;
    }
}
