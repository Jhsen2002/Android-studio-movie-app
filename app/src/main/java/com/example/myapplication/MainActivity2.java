package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.example.myapplication.provider.Movie;
import com.example.myapplication.provider.MovieViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
//    RecyclerView.Adapter adapter;
    MyRecyclerViewAdapter adapter;
    static MovieViewModel mMovieViewModel;

//    ArrayList<Movie> movieListRecycle = new ArrayList<Movie>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        recyclerView = findViewById(R.id.rv);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

//        SharedPreferences sp = getSharedPreferences("db1", 0);
//        String dbStr = sp.getString("MOV_LIST", "");
//        Type type = new TypeToken<ArrayList<Movie>>(){}.getType();
//        Gson gson = new Gson();
//        movieListRecycle = gson.fromJson(dbStr, type);

        adapter = new MyRecyclerViewAdapter();
        recyclerView.setAdapter(adapter);

        FloatingActionButton fab = findViewById(R.id.floatingActionButton2);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        mMovieViewModel = new ViewModelProvider(this).get(MovieViewModel.class);
        mMovieViewModel.getAllMovies().observe(this,newData -> {
            adapter.setMovieList(newData);
            adapter.notifyDataSetChanged();
//            tv.setText(newData.size() + "");
        });
    }
}