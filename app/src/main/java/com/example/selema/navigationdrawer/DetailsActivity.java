package com.example.selema.navigationdrawer;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ToggleButton;
import com.example.selema.navigationdrawer.db.DatabaseHelper;
import com.squareup.picasso.Picasso;


public class DetailsActivity extends AppCompatActivity {

    DatabaseHelper myDb;
    ToggleButton toggleButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_layout);

        myDb = new DatabaseHelper(this.getBaseContext());


        final Bundle extras = getIntent().getExtras();
        final String ids = extras.getString("id");
        //final int id = Integer.parseInt(ids);
        final String overview = extras.getString("overview");
        // String video= extras.getString("video");
        final String votecount = extras.getString("votecount");
        final String title = extras.getString("title");
        final String poster = extras.getString("poster");

      /*  TextView overviewTXT = (TextView) findViewById(R.id.overviewTXT);
        TextView titleTXT = (TextView) findViewById(R.id.overview);
*//*
        overviewTXT.setText(overview);
        titleTXT.setText(title);*/
        Toast.makeText(getBaseContext(), "position  " + myDb.CheckIsDataAlreadyInDBorNot(ids) + " = " + ids, Toast.LENGTH_SHORT).show();

        final ImageView image = (ImageView) findViewById(R.id.productImage);
        Picasso.with(getBaseContext()).load("http://image.tmdb.org/t/p/w185/" + poster).into(image);

        boolean isCheckeded = myDb.CheckIsDataAlreadyInDBorNot(ids);
        toggleButton = (ToggleButton) findViewById(R.id.button_favorite);
        toggleButton.setChecked(false);

        toggleButton.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_border));


        if (isCheckeded) {
            toggleButton.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_favourite));
        } else {
            toggleButton.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_border));
        }

        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    toggleButton.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_favourite));
                    AddData(ids,title,overview,votecount,poster);
                } else {
                    toggleButton.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_border));
                    DeleteData(ids);
                }
            }
        });
    }



    public void AddData(String id,String movie_name,String movie_overview,String movierate,String movieImage ) {
        boolean isInserted = myDb.insertData(id,movie_name,movie_overview,movierate,movieImage);
        if (isInserted == true)
            Toast.makeText(this, "Data Inserted", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(this, "Data not Inserted", Toast.LENGTH_LONG).show();
    }

    public void DeleteData(String id) {
        Integer deletedRows = myDb.deleteData(id.toString());
        if (deletedRows > 0)
            Toast.makeText(this, "Data Deleted", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(this, "Data not Deleted", Toast.LENGTH_LONG).show();
    }

}
