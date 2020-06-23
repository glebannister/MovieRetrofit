package android.glebannister.movieretrofit.model;

import android.app.Application;
import android.glebannister.movieretrofit.R;
import android.glebannister.movieretrofit.service.MovieService;
import android.glebannister.movieretrofit.service.RetrofitInstance;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepository {

    private ArrayList<Result> movieArray = new ArrayList<>();
    private MutableLiveData<List<Result>> mutableLiveData = new MutableLiveData<>();
    private Application application;

    public MovieRepository(Application application){
        this.application = application;
    }

    public MutableLiveData<List<Result>> getMutableLiveData() {

        MovieService movieService = RetrofitInstance.getMovieService();
        Call<MovieApiResponse> call = movieService.getResults(application.getApplicationContext()
                .getString(R.string.api));
        call.enqueue(new Callback<MovieApiResponse>() {
            @Override
            public void onResponse(Call<MovieApiResponse> call, Response<MovieApiResponse> response) {
                MovieApiResponse movieApiResponse = response.body();
                if (movieApiResponse != null){
                    movieArray = (ArrayList<Result>) movieApiResponse.getResults();
                    mutableLiveData.setValue(movieArray);
                }
            }

            @Override
            public void onFailure(Call<MovieApiResponse> call, Throwable t) {

            }
        });

        return mutableLiveData;
    }
}
