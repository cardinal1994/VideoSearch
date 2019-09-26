package com.example.videosearch.fragments;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.videosearch.ApiService;
import com.example.videosearch.Movie;
import com.example.videosearch.MovieAdapter;
import com.example.videosearch.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainFragment extends Fragment {

    private RecyclerView recyclerView;
    private List<Movie> movies;
    private MovieAdapter movieAdapter;
    View view;
    EditText editSearch;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main, container, false);

        editSearch = view.findViewById(R.id.main__search);

        getMovies();

        editSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                //Array with IDs from MovieHolder (MovieAdapter.class)

            }
        });

        return view;


    }

    public List<Movie> getMovies() {
        final ApiService apiService = RetrofitInstance.getService();
        Call<List<Movie>> call = apiService.getMovies();

        call.enqueue(new Callback<List<Movie>>() {
            @Override
            public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
                movies = response.body();

                movies = (ArrayList<Movie>) apiService.getMovies();

                viewData();
            }

            @Override
            public void onFailure(Call<List<Movie>> call, Throwable throwable) {
                Toast.makeText(getContext(), "Failure", Toast.LENGTH_SHORT).show();
            }
        });


        return movies;
    }

    private void viewData() {

        recyclerView = (RecyclerView) view.findViewById(R.id.main__recycler);
        movieAdapter = new MovieAdapter(movies);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(movieAdapter);
    }

}