package com.example.selema.navigationdrawer.favourite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.selema.navigationdrawer.MainActivity;
import com.example.selema.navigationdrawer.MovieAdapter;
import com.example.selema.navigationdrawer.R;
import com.example.selema.navigationdrawer.db.DatabaseHelper;
import com.example.selema.navigationdrawer.network.Movie;

import java.util.ArrayList;
import java.util.List;

public class Favourite extends AppCompatActivity {
    DatabaseHelper myDb;


    DatabaseHelper database;
    RecyclerView recyclerView;
    Favourite_Adapter adapter;
    List<Fav_movie> movies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite);

        movies = new ArrayList<Fav_movie>();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewFav);


        database = new DatabaseHelper(Favourite.this);
        movies = database.getdata();
        Favourite_Adapter adapter = new Favourite_Adapter(getApplicationContext(), movies);
        recyclerView.setAdapter(adapter);


        Log.i("HIteshdata", "" + movies);
        RecyclerView.LayoutManager reLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));


    }
    /* @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite);
        myDb = new DatabaseHelper(this);
        viewAll();


    }

    public void viewAll() {
        Cursor res = myDb.getAllData();
        if (res.getCount() == 0) {
            // show message
            showMessage("Error", "Nothing found");
            return;
        }

        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()) {
            buffer.append("Id :" + res.getString(0) + "\n");
            buffer.append("Name :" + res.getString(1) + "\n");
        }

        // Show all data
        showMessage("Data", buffer.toString());
    }
*/

    public void showMessage(String title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }


}





