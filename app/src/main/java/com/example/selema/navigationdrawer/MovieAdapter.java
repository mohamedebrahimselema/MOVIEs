package com.example.selema.navigationdrawer;

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

import com.example.selema.navigationdrawer.favourite.Fav_movie;
import com.example.selema.navigationdrawer.network.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by selema on 1/30/18.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.modelViewHolder> {

    public static final String TAG = MovieAdapter.class.getSimpleName();


    private Context context;
    private List<Movie> movies;
    private List<Fav_movie> fav_movies;
    private MovieCallbacks listner;


    DatabaseHelper myDb;

    public MovieAdapter(Context context, List<Movie> movies, MovieCallbacks listner) {
        this.context = context;
        this.movies = movies;
        this.listner = listner;
    }

    @Override
    public modelViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.list_item, null);
        myDb = new DatabaseHelper(this.context);

        final modelViewHolder holder = new modelViewHolder(view, context, movies);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                Movie movie = holder.movies.get(position);
                Intent i = new Intent(context, DetailsActivity.class);


                Toast.makeText(view.getContext(), "position = " + movie.getId(), Toast.LENGTH_SHORT).show();

                i.putExtra("title", movie.getTitle());
                i.putExtra("poster", movie.getPosterPath());
                i.putExtra("id", movie.getId().toString());
                i.putExtra("overview", movie.getOverview());
                i.putExtra("video", movie.getVideo());
                i.putExtra("votecount", movie.getVoteCount());
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);

                //                listner.onMovieClick(movies.get(holder.getAdapterPosition()));
            }
        });


        return holder;
    }

    @Override
    public void onBindViewHolder(modelViewHolder holder, int position) {

        YoYo.with(Techniques.BounceInRight).playOn(holder.cardView);
        final Movie movie = movies.get(position);
        holder.textViewTitle.setText(movie.getOriginalTitle());
        holder.textViewRating.setText(String.valueOf(movie.getVoteAverage()));
        holder.textViewPrice.setText(String.valueOf(movie.getVoteCount()));
        Picasso.with(context).load("http://image.tmdb.org/t/p/w185/" + movie.getPosterPath()).into(holder.imageView);

    }


    public interface MovieCallbacks {
        void onMovieClick(Movie movie);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    class modelViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        CardView cardView;
        TextView textViewTitle, textViewShortDesc, textViewRating, textViewPrice;
        ImageView imageView;
        Context context;
        List<Movie> movies;
        Button favoriteButton;

        public modelViewHolder(View itemView, Context context, List<Movie> movies) {

            super(itemView);

            itemView.setOnClickListener(this);
            this.movies = movies;
            this.context = context;

            cardView = itemView.findViewById(R.id.cardview);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewRating = itemView.findViewById(R.id.textViewRating);
            textViewPrice = itemView.findViewById(R.id.textViewPrice);
            imageView = itemView.findViewById(R.id.imageView);
        }

        @Override
        public void onClick(View view) {
            int position = this.getAdapterPosition();
            Movie movie = this.movies.get(position);
            Toast.makeText(view.getContext(), "position = " + position, Toast.LENGTH_SHORT).show();
            Intent i = new Intent(context, DetailsActivity.class);
            i.putExtra("Value1", "Android By Javatpoint");
            i.putExtra("Value2", movie.getPosterPath());

        }

    }
}
