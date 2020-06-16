package android.glebannister.movieretrofit.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.glebannister.movieretrofit.R;
import android.glebannister.movieretrofit.model.Result;
import android.glebannister.movieretrofit.ui.MovieOverview;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieAdapterViewHolder> {
    private ArrayList<Result> movieArray = new ArrayList<>();
    private Context context;

    public MovieAdapter(ArrayList<Result> movieArray, Context context){
        this.movieArray = movieArray;
        this.context = context;
    }

    @NonNull
    @Override
    public MovieAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent,false);
        return new MovieAdapterViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MovieAdapterViewHolder holder, int position) {
        holder.title.setText(movieArray.get(position).getOriginalTitle());
        holder.vote.setText(Double.toString(movieArray.get(position).getVoteAverage()));
        holder.year.setText(movieArray.get(position).getReleaseDate());
        String posterPath = "https://image.tmdb.org/t/p/w500/" + movieArray.get(position).getPosterPath();
        Glide.with(context).load(posterPath).placeholder(R.drawable.original).into(holder.poster);
    }


    @Override
    public int getItemCount() {
        return movieArray.size();
    }

      class MovieAdapterViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView vote;
        TextView year;
        ImageView poster;

         public MovieAdapterViewHolder(@NonNull View itemView) {
             super(itemView);
             title = itemView.findViewById(R.id.title);
             vote = itemView.findViewById(R.id.vote);
             year = itemView.findViewById(R.id.year);
             poster = itemView.findViewById(R.id.movieImage);

             itemView.setOnClickListener(v -> {
                 int position = getAdapterPosition();
                 if (position != RecyclerView.NO_POSITION){
                     Result result = movieArray.get(position);
                     Intent intent = new Intent(context, MovieOverview.class);
                     intent.putExtra("Overview", result);
                     context.startActivity(intent);
                 }
             });
         }
     }
}
