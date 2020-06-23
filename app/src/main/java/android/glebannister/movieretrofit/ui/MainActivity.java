package android.glebannister.movieretrofit.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
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
import android.glebannister.movieretrofit.viewmodel.MainActivityViewModel;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Result> movieArray = new ArrayList<>();
    private SwipeRefreshLayout swipeRefreshLayout;
    private MainActivityViewModel mainActivityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.mainTitle);
        setContentView(R.layout.activity_main);
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        mainActivityViewModel = new ViewModelProvider
                .AndroidViewModelFactory(getApplication()).create(MainActivityViewModel.class);

        getMovies();
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimaryDark);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getMovies();
            }
        });

    }
    private void getMovies(){
        mainActivityViewModel.getAllMoviesData().observe(this, results -> {
            movieArray = (ArrayList<Result>) results;
            buildRecyclerView();
        });
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
        swipeRefreshLayout.setRefreshing(false);
    }
}
