package com.example.myapplication.provider;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MovieDao {

    @Query("select * from Movies")
    LiveData<List<Movie>> getAllMovies();

    @Query("select * from Movies where Title=:name")
    List<Movie> getMovies(String name);

    @Insert
    void addMovie(Movie movie);

    @Query("delete from Movies where Title= :name")
    void deleteMovie(String name);

    @Query("delete FROM Movies")
    void deleteAllMovies();

//TODO
    @Query("delete from Movies where Year= :year")
    void deleteByYear(int year);
}
