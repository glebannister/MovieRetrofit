package android.glebannister.movieretrofit.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.glebannister.movieretrofit.R;
import android.glebannister.movieretrofit.model.Result;
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
        holder.popularity.setText(Double.toString(movieArray.get(position).getPopularity()));
        String posterPath = "https://image.tmdb.org/t/p/w500/" + movieArray.get(position).getPosterPath();
        Glide.with(context).load(posterPath).placeholder(R.drawable.original).into(holder.poster);
    }


    @Override
    public int getItemCount() {
        return movieArray.size();
    }

     class MovieAdapterViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView popularity;
        ImageView poster;

         public MovieAdapterViewHolder(@NonNull View itemView) {
             super(itemView);
             title = itemView.findViewById(R.id.title);
             popularity = itemView.findViewById(R.id.popularity);
             poster = itemView.findViewById(R.id.movieImage);
         }
     }
}
