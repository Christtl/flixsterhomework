package com.example.flixsterhomework1.adapters;

import static com.example.flixsterhomework1.R.layout.item_movie;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Parcel;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.flixsterhomework1.DetailActivity;
import com.example.flixsterhomework1.R;
import com.example.flixsterhomework1.models.Movie;

import org.parceler.Parcels;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder>{

    Context context;
    List<Movie>movies;

    public MovieAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }
    // usually involves inflating a layout from XML and returning the holding

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("MovieAdapter","onCreateViewHolder");
        View movieView = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false);
        return new ViewHolder(movieView);
    }
    // involves populating data into the item through holder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d("MovieAdapter","onBindViewHolder" + position);
    // get the movie at the passed in position
        Movie movie = movies.get(position);
        holder.bind(movie);
        // Bind the movie into the VH

    }
    // return the total count of items in the list
    @Override
    public int getItemCount() {
        return movies.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        RelativeLayout Container;
        TextView tvTitle;
        TextView tvOverview;
        ImageView ivImage;


        public ViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvOverview =itemView.findViewById(R.id.tvOverview);
            ivImage = itemView.findViewById(R.id.ivPoster);
            Container = itemView.findViewById(R.id.container);
        }

        public void bind(@NonNull Movie movie) {
            tvTitle.setText(movie.getTitle());
            tvOverview.setText(movie.getOverview());
            String imageUrl;
            //if phone is in landscape
            if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                // then imageUrl= back dro image
               imageUrl = movie.getBackdropPath();
            } else {
                //else imageUrl= poster image
                imageUrl = movie.getPosterPath();
            }
            Glide.with(context).load(imageUrl).into(ivImage);

             // 1. register click listener on the whole row

            Container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // 2.navigate to a new activity on top
                    Intent i = new Intent(context, DetailActivity.class);
                    i.putExtra("title", movie.getTitle());
                    i.putExtra("movie", Parcels.wrap(movie));
                    context.startActivity(i);


                }
            });
        }
    }
}