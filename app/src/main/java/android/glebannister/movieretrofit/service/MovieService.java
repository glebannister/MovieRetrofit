package android.glebannister.movieretrofit.service;

import android.glebannister.movieretrofit.model.MovieApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieService {
    @GET("movie/popular")
    Call<MovieApiResponse> getResults(@Query("api_key") String apiKey);
}
