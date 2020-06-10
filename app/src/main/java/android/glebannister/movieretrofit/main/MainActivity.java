package android.glebannister.movieretrofit.main;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.glebannister.movieretrofit.R;
import android.glebannister.movieretrofit.model.MovieApiResponse;
import android.glebannister.movieretrofit.model.Result;
import android.glebannister.movieretrofit.service.MovieService;
import android.glebannister.movieretrofit.service.RetrofitInstance;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Result> movieArray = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getMovies();
    }
    private Object getMovies(){
        MovieService movieService = RetrofitInstance.getMovieService();
        Call<MovieApiResponse> call = movieService.getResults(getString(R.string.api));
        call.enqueue(new Callback<MovieApiResponse>() {
            @Override
            public void onResponse(Call<MovieApiResponse> call, Response<MovieApiResponse> response) {
                MovieApiResponse movieApiResponse = response.body();
                if (movieApiResponse != null){
                    movieArray = (ArrayList<Result>) movieApiResponse.getResults();
                    for (Result result : movieArray){
                        Log.d("Movies", result.getTitle());
                    }
                }
            }

            @Override
            public void onFailure(Call<MovieApiResponse> call, Throwable t) {

            }
        });
        return movieArray;
    }
}
