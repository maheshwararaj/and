package com.example.practice;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import androidx.appcompat.app.AppCompatActivity;

public class UserListActivity extends AppCompatActivity {

    ListView listView;
    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    SimpleCursorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        listView = findViewById(R.id.listView);
        openHelper = new DatabaseHelper(this);
        db = openHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT ID as _id, email, password FROM users", null);

        String[] from = {"email", "password"};
        int[] to = {R.id.tvEmail, R.id.tvPassword};

        adapter = new SimpleCursorAdapter(this, R.layout.user_list_item, cursor, from, to, 0);
        listView.setAdapter(adapter);
    }
}
