package com.example.videosearch;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieHolder> {

    List<Movie> movies;

    public MovieAdapter(List<Movie> movies) {
        this.movies = movies;
    }

    @NonNull
    @Override
    public MovieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rv_item, parent, false);

        MovieHolder movieHolder = new MovieHolder(view);
        return movieHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MovieHolder holder, int position) {

        String name = movies.get(position).getName();
        if (name != null && name != "") {
            holder.tv_name.setText(name);
        } else holder.tv_name.setText("null");

        String image_circle = movies.get(position).getimage().getMedium();
        if (image_circle != null && image_circle != "") {
            Picasso.with(holder.circleImageView.getContext()).load(image_circle);
        } else
            Picasso.with(holder.circleImageView.getContext()).load(R.drawable.ic_launcher_foreground);

        holder.tv_raiting.setText("Rating +");

        holder.image_favorite.setImageResource(R.drawable.ic_favorite_border);


    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public static class MovieHolder extends RecyclerView.ViewHolder {

        CircleImageView circleImageView;
        TextView tv_name;
        ImageView image_favorite;
        TextView tv_raiting;


        public MovieHolder(@NonNull final View itemView) {
            super(itemView);
            circleImageView = itemView.findViewById(R.id.item__image_photo);
            tv_name = itemView.findViewById(R.id.item__tv_name);
            image_favorite = itemView.findViewById(R.id.item__image_favorite);
            tv_raiting = itemView.findViewById(R.id.item__tv_rating);

            image_favorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    boolean isFavorite = false;
                    if (isFavorite) {
                        image_favorite.setImageResource(R.drawable.ic_favorite);
                        // getID and send to array in Favorites
                    } else {
                        image_favorite.setImageResource(R.drawable.ic_favorite_border);
                        // getID and remove from array in Favorites
                    }
                }
            });

        }
    }
}
