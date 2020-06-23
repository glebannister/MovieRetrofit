package android.glebannister.movieretrofit.viewmodel;

import android.app.Application;
import android.glebannister.movieretrofit.model.MovieRepository;
import android.glebannister.movieretrofit.model.Result;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {

    private MovieRepository movieRepository;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        movieRepository = new MovieRepository(application);
    }

    public LiveData<List<Result>> getAllMoviesData() {
        return movieRepository.getMutableLiveData();
    }
}
