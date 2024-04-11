package com.example.myapplication.provider;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.security.Key;

@Entity(tableName = "Movies")
public class Movie {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "MovieID")
    private int MovieID;

    @ColumnInfo(name = "Title")
    private String title;

    @ColumnInfo(name = "Year")
    private int year;

    @ColumnInfo(name = "Country")
    private String Country;

    @ColumnInfo(name = "Cost")
    private int Cost;

    @ColumnInfo(name = "Genre")
    private String Genre;

    @ColumnInfo(name = "Keywords")
    private String Keywords;

    public Movie(String title, int year, String Country, int Cost, String Genre, String Keywords) {
        this.title = title;
        this.year = year;
        this.Country = Country;
        this.Cost = Cost;
        this.Genre = Genre;
        this.Keywords = Keywords;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public int getCost() {
        return Cost;
    }

    public void setCost(int cost) {
        Cost = cost;
    }

    public String getGenre() {
        return Genre;
    }

    public void setGenre(String genre) {
        Genre = genre;
    }

    public String getKeywords() {
        return Keywords;
    }

    public void setKeywords(String keywords) {
        Keywords = keywords;
    }

    public void setId(@NonNull int id) {
        this.MovieID = id;
    }

    public void setMovieID(int movieID) {
        MovieID = movieID;
    }

    public int getMovieID() {
        return MovieID;
    }
}

//public class Movie {
//    private String title;
//    private int year;
//    private String country;
//    private String genre;
//    private int cost;
//    private String keyword;
//
//    public Movie(String title, int year, String country, String genre, int cost, String keyword) {
//        this.title = title;
//        this.year = year;
//        this.country = country;
//        this.genre = genre;
//        this.cost = cost;
//        this.keyword = keyword;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public int getYear() {
//        return year;
//    }
//
//    public void setYear(int year) {
//        this.year = year;
//    }
//
//    public String getCountry() {
//        return country;
//    }
//
//    public void setCountry(String country) {
//        this.country = country;
//    }
//
//    public String getGenre() {
//        return genre;
//    }
//
//    public void setGenre(String genre) {
//        this.genre = genre;
//    }
//
//    public int getCost() {
//        return cost;
//    }
//
//    public void setCost(int cost) {
//        this.cost = cost;
//    }
//
//    public String getKeyword() {
//        return keyword;
//    }
//
//    public void setKeyword(String keyword) {
//        this.keyword = keyword;
//    }
//}
