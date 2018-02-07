package com.example.selema.navigationdrawer.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.selema.navigationdrawer.favourite.Fav_movie;
import com.example.selema.navigationdrawer.network.Movie;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by selema on 2/4/18.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Movies.db";
    private static final int DATABASE_VERSION = 1 ;
    public static final String TABLE_NAME = "movie";
    public static final String COLUMN_ID = "ids";
    public static final String COLUMN_MOVIE_ID = "iding";
    public static final String COLUMN_Movie_NAME = "name";
    public static final String COLUMN_Movie_OVERVIEW = "age";
    public static final String COLUMN_Movie_VoteCounte = "occupation";
    public static final String COLUMN_Movie_IMAGE = "image";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_Movie_NAME + " TEXT NOT NULL, " +
                COLUMN_Movie_OVERVIEW + " NUMBER NOT NULL, " +
                COLUMN_Movie_VoteCounte + " TEXT NOT NULL, " +
                COLUMN_Movie_IMAGE + " BLOB NOT NULL);"
        );    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String prodId,String movie_name,String movie_overview,String movierate,String movieImage ) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_MOVIE_ID,prodId);
        contentValues.put(COLUMN_Movie_NAME,movie_name);
        contentValues.put(COLUMN_Movie_OVERVIEW,movie_overview);
        contentValues.put(COLUMN_Movie_VoteCounte,movierate);
        contentValues.put(COLUMN_Movie_IMAGE,movieImage);
        long result = db.insert(TABLE_NAME,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public boolean CheckIsDataAlreadyInDBorNot(  String fieldValue) {
        String Query = "Select * from " + TABLE_NAME + " where " + COLUMN_MOVIE_ID+ " = " + fieldValue;
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery(Query, null);
        if(cursor.getCount() <= 0){
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }
    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }

/*
    public boolean updateData(String id,String name,String surname,String marks) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,surname);
        contentValues.put(COL_4,marks);
        db.update(TABLE_NAME, contentValues, "ID = ?",new String[] { id });
        return true;
    }
*/

    public Integer deleteData (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, COLUMN_MOVIE_ID+ " = ?",new String[] {id});
    }
    public List<Fav_movie> getdata(){
    //    Fav_movie dataModel = new Fav_movie();
        List<Fav_movie> data=new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from "+TABLE_NAME+" ;",null);
        StringBuffer stringBuffer = new StringBuffer();
        Fav_movie dataModel = null;
        while (cursor.moveToNext()) {
            dataModel= new Fav_movie ();
            String name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_Movie_NAME));
            String overview = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_Movie_OVERVIEW));
            String votecount = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_Movie_VoteCounte));
            String id = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_MOVIE_ID));
            String image = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_Movie_IMAGE));
            dataModel.setMovie_name(name);
            dataModel.setMovie_overview(overview);
            dataModel.setMovie_image(image);
            dataModel.setMovie_votecounte(votecount);
            stringBuffer.append(dataModel);
            // stringBuffer.append(dataModel);
            data.add(dataModel);
        }

        for (Fav_movie mo:data ) {

            Log.i("Hellomo",""+mo.getMovie_name());
        }

        //

        return data;
    }
}
