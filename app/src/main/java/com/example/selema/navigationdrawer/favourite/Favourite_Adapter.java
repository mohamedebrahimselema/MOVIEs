package com.example.selema.navigationdrawer.favourite;

/**
 * Created by selema on 2/8/18.
 */

import com.example.selema.navigationdrawer.DetailsActivity;
import com.example.selema.navigationdrawer.R;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.selema.navigationdrawer.db.DatabaseHelper;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by selema on 1/30/18.
 */

public class Favourite_Adapter extends RecyclerView.Adapter<Favourite_Adapter.modelViewHolder>{

    public static final String TAG = Favourite_Adapter.class.getSimpleName();


    private Context context;
    private  List<Fav_movie> fav_movie;



    DatabaseHelper myDb;
     public Favourite_Adapter(Context context, List<Fav_movie> fav_movie) {
         this.context = context;
         this.fav_movie = fav_movie;
     }

    @Override
    public Favourite_Adapter.modelViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.list_item, null);
        myDb = new DatabaseHelper(this.context);

        final Favourite_Adapter.modelViewHolder holder = new Favourite_Adapter.modelViewHolder(view,context,fav_movie);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                Fav_movie movie = holder.fav_movie.get(position);
                Intent i = new Intent(context, DetailsActivity.class);


  //              Toast.makeText(view.getContext(), "position = " + movie.getId() , Toast.LENGTH_SHORT).show();

                i.putExtra("title",movie.getMovie_name());
                i.putExtra("poster", movie.getMovie_image());
                i.putExtra("overview", movie.getMovie_overview());
                //i.putExtra("video", movie.getVideo());
                i.putExtra("votecount", movie.getMovie_votecounte());
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);

                //                listner.onFav_movieClick(fav_movie.get(holder.getAdapterPosition()));
            }
        });



        return holder;
    }

    @Override
    public void onBindViewHolder(Favourite_Adapter.modelViewHolder holder, int position) {

        YoYo.with(Techniques.BounceInRight).playOn(holder.cardView);
        final Fav_movie movie= fav_movie.get(position);
        holder.textViewTitle.setText(movie.getMovie_name());
        holder.textViewRating.setText(String.valueOf(movie.getMovie_votecounte()));
        holder.textViewPrice.setText(String.valueOf(movie.getMovie_overview()));
        Picasso.with(context).load("http://image.tmdb.org/t/p/w185/" + movie.getMovie_image()).into(holder.imageView);

    }


    public interface Fav_movieCallbacks{
        void onFav_movieClick(Fav_movie movie);
    }

    @Override
    public int getItemCount() {
        return fav_movie.size();
    }

    class modelViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener{
        CardView cardView ;
        TextView textViewTitle, textViewShortDesc, textViewRating, textViewPrice;
        ImageView imageView;
        Context context;
        List<Fav_movie> fav_movie ;
        Button favoriteButton ;

        public modelViewHolder(View itemView,Context context,List<Fav_movie> fav_movie ) {

            super(itemView);

            itemView.setOnClickListener(this);
            this.fav_movie = fav_movie;
            this.context = context;

            cardView =itemView.findViewById(R.id.cardview);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewRating = itemView.findViewById(R.id.textViewRating);
            textViewPrice = itemView.findViewById(R.id.textViewPrice);
            imageView = itemView.findViewById(R.id.imageView);
        }

        @Override
        public void onClick(View view) {
            int position = this.getAdapterPosition();
            Fav_movie movie = this.fav_movie.get(position);
            Toast.makeText(view.getContext(), "position = " + position, Toast.LENGTH_SHORT).show();
            Intent i = new Intent(context, DetailsActivity.class);
            i.putExtra("Value1", "Android By Javatpoint");

        }

    }
}

