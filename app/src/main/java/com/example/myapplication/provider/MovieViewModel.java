package com.example.myapplication.provider;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class MovieViewModel extends AndroidViewModel {
    private MovieRepository mRepository;
    private LiveData<List<Movie>> mAllMovie;

    public MovieViewModel(@NonNull Application application) {
        super(application);
        mRepository = new MovieRepository(application);
        mAllMovie = mRepository.getAllMovie();
    }

    public LiveData<List<Movie>> getAllMovies() {
        return mAllMovie;
    }

    public void insert(Movie movie) {
        mRepository.insert(movie);
    }
    public void deleteAll(){
        mRepository.deleteAll();
    }
    public void deleteByYear(int year){
        mRepository.deleteByYear(year);
    }

}
