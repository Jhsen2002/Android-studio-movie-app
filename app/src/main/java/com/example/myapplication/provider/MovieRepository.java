package com.example.myapplication.provider;

import android.app.Application;
import androidx.lifecycle.LiveData;

import java.util.List;

public class MovieRepository  {

    private MovieDao mMovieDao;
    private LiveData<List<Movie>> mAllMovie;

    MovieRepository(Application application) {
        MovieDatabase db = MovieDatabase.getDatabase(application);
        mMovieDao = db.movieDao();
        mAllMovie = mMovieDao.getAllMovies();
    }
    LiveData<List<Movie>> getAllMovie() {
        return mAllMovie;
    }
    void insert(Movie movie) {
        MovieDatabase.databaseWriteExecutor.execute(() -> mMovieDao.addMovie(movie));
    }

    void deleteAll(){
        MovieDatabase.databaseWriteExecutor.execute(()->{
            mMovieDao.deleteAllMovies();
        });
    }
    void deleteByYear(int year){
        MovieDatabase.databaseWriteExecutor.execute(()->{
            mMovieDao.deleteByYear(year);
        });
    }
}
