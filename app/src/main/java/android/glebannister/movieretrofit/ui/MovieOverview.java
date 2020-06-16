package android.glebannister.movieretrofit.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.glebannister.movieretrofit.R;
import android.glebannister.movieretrofit.model.Result;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class MovieOverview extends AppCompatActivity {

    private Result result;
    private ImageView poster;
    private TextView overview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_overview);
        setTitle(R.string.overview);
        poster = findViewById(R.id.movieImage);
        overview = findViewById(R.id.overview);

        Intent intent = getIntent();
        if (intent!=null && intent.hasExtra("Overview")){
            result = intent.getParcelableExtra("Overview");
            assert result != null;
            String posterPath = "https://image.tmdb.org/t/p/w500/" + result.getPosterPath();
            Glide.with(this).load(posterPath).placeholder(R.drawable.original).into(poster);
            overview.setText(result.getOverview());
        }
    }
}
