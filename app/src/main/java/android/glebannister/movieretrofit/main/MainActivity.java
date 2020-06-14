package android.glebannister.movieretrofit.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.res.Configuration;
import android.glebannister.movieretrofit.R;
import android.glebannister.movieretrofit.adapters.MovieAdapter;
import android.glebannister.movieretrofit.model.MovieApiResponse;
import android.glebannister.movieretrofit.model.Result;
import android.glebannister.movieretrofit.service.MovieService;
import android.glebannister.movieretrofit.service.RetrofitInstance;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Result> movieArray = new ArrayList<>();
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.mainTitle);
        setContentView(R.layout.activity_main);
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        getMovies();
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimaryDark);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getMovies();
            }
        });

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
                    buildRecyclerView();
                    swipeRefreshLayout.setRefreshing(false);
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

    private void buildRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        MovieAdapter adapter = new MovieAdapter(movieArray, this);
        int columnCount;
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            columnCount = 2;
        } else {
            columnCount = 4;
        }
        recyclerView.setLayoutManager(new GridLayoutManager(this, columnCount));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
