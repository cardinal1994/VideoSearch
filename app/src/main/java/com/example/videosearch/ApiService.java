package com.example.videosearch;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("schedule/full")
    Call<List<Movie>> getMovies();
}
