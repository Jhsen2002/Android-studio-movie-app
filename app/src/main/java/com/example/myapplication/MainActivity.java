package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.myapplication.provider.Movie;
import com.example.myapplication.provider.MovieViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> myList = new ArrayList<String>();
//    ArrayList<Movie> movieList = new ArrayList<Movie>();
    ArrayAdapter myAdapter;
    DrawerLayout drawerlayout;

    static MovieViewModel mMovieViewModel;
    MyRecyclerViewAdapter adapter;

    int count;

    String title;
    int year;
    String country;
    String keywords;
    String genre;
    int cost;

    EditText titleInput;
    EditText yearInput;
    EditText countryInput;
    EditText keywordsInput;
    EditText genreInput;
    EditText costInput;

    Button button;
    Button button4;
    Button button5;

    View myFrame;
    int x_down;
    int y_down;

    GestureDetector gestureDetector;
    ScaleGestureDetector scaleGestureDetector;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        adapter = new MyRecyclerViewAdapter();


        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_layout);

        gestureDetector = new GestureDetector(this, new MyGestureDetector());
        scaleGestureDetector = new ScaleGestureDetector(this, new MyScaleGestureDetector());

        mMovieViewModel = new ViewModelProvider(this).get(MovieViewModel.class);
        mMovieViewModel.getAllMovies().observe(this,newData -> {
            adapter.setMovieList(newData);
            adapter.notifyDataSetChanged();
//            tv.setText(newData.size() + "");
        });


        titleInput = (EditText) findViewById(R.id.movie_title);
        yearInput = (EditText) findViewById(R.id.movie_year);
        countryInput = (EditText) findViewById(R.id.movie_country);
        keywordsInput = (EditText) findViewById(R.id.movie_keywords);
        genreInput = (EditText) findViewById(R.id.movie_genre);
        costInput = (EditText) findViewById(R.id.movie_cost);

        myFrame = findViewById(R.id.myFrame);
        myFrame.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                gestureDetector.onTouchEvent(motionEvent);
                scaleGestureDetector.onTouchEvent(motionEvent);
                return true;
//                int action = motionEvent.getActionMasked();
//                switch(action) {
//                    case MotionEvent.ACTION_DOWN:
//                        x_down = (int) motionEvent.getX();
//                        y_down = (int) motionEvent.getY();
//                        if (x_down > 1000 && y_down < 400) {
//                            cost = Integer.parseInt(costInput.getText().toString());
//                            cost += 50;
//                            costInput.setText("" + cost);
//                        } else if (x_down < 400 && y_down < 400) {
//                            cost = Integer.parseInt(costInput.getText().toString());
//                            cost -= 50;
//                            if (cost < 0) {
//                                cost = 0;
//                            }
//                            costInput.setText("" + cost);
//                        }
//                        return true;
//                    case MotionEvent.ACTION_MOVE:
//                        return true;
//                    case MotionEvent.ACTION_UP:
//                        if (Math.abs(y_down - motionEvent.getY()) < 40)
//                        {
//                            if ((motionEvent.getX()) > x_down+50)
//                            {
////                                Toast.makeText(getApplicationContext(),"xd: "+x_down+"yd: "+y_down+"x: "+ (int)motionEvent.getX()+ "y: "+ (int)motionEvent.getY(),Toast.LENGTH_SHORT).show();
//                                title = titleInput.getText().toString();
//                                year =Integer. parseInt(yearInput.getText().toString());
//                                country = countryInput.getText().toString();
//                                keywords = keywordsInput.getText().toString();
//                                genre = genreInput.getText().toString();
//                                cost = Integer. parseInt(costInput.getText().toString());
//                                showToast(title+" has been added");
//
//                                myList.add(title+"|"+year);
//                                count++;
//                                myAdapter.notifyDataSetChanged();
//                                Movie movie = new Movie(title,year,country,cost,genre,keywords);
//                                mMovieViewModel.insert(movie);
//
//                            }
//                        }
//                        else if(Math.abs(x_down - motionEvent.getX()) < 40){
//                            if(motionEvent.getY()> y_down+50){
//                                titleInput.setText(" ");
//                                yearInput.setText(" ");
//                                countryInput.setText(" ");
//                                keywordsInput.setText(" ");
//                                genreInput.setText(" ");
//                                costInput.setText(" ");
//                            }
//                        }
//                        return true;
//                }
//                return false;
            }
        });






        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton floatingActionButton = findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title = titleInput.getText().toString();
                year =Integer. parseInt(yearInput.getText().toString());
                country = countryInput.getText().toString();
                keywords = keywordsInput.getText().toString();
                genre = genreInput.getText().toString();
                cost = Integer. parseInt(costInput.getText().toString());
                showToast(title+" has been added");

                myList.add(title+"|"+year);
                count++;
//                myAdapter.notifyDataSetChanged();
                Movie movie = new Movie(title,year,country,cost,genre,keywords);
//                movieList.add(movie);
                mMovieViewModel.insert(movie);
            }
        });

        ListView listView = findViewById(R.id.listview);
        myAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, myList);
        listView.setAdapter(myAdapter);

        drawerlayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerlayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerlayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.navigationLayout);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if(id == R.id.drawerAdd){
                    title = titleInput.getText().toString();
                    year =Integer. parseInt(yearInput.getText().toString());
                    country = countryInput.getText().toString();
                    keywords = keywordsInput.getText().toString();
                    genre = genreInput.getText().toString();
                    cost = Integer. parseInt(costInput.getText().toString());
                    showToast(title+" has been added");

                    myList.add(title+"|"+year);
                    count++;
                    myAdapter.notifyDataSetChanged();
                    Movie movie = new Movie(title,year,country,cost,genre,keywords);
//                    movieList.add(movie);
                    mMovieViewModel.insert(movie);

                }else if( id == R.id.drawer_remove){
                    myList.remove(myList.size()-1);
                    count--;
                    myAdapter.notifyDataSetChanged();

                }else if( id == R.id.drawer_clear){
                    mMovieViewModel.deleteAll();

                }else if( id == R.id.drawer_list){
//                    Gson gson = new Gson();
////                    String dbStr = gson.toJson(movieList);
//                    SharedPreferences sp = getSharedPreferences("db1", 0);
//                    SharedPreferences.Editor edit = sp.edit();
//                    edit.putString("MOV_LIST", dbStr);
//                    edit.apply();

                    Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
                    startActivity(intent);


                }else if( id == R.id.close){
                    finish();
                }

                drawerlayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });

        button5 = (Button) findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                titleInput.setText(" ");
                yearInput.setText(" ");
                countryInput.setText(" ");
                keywordsInput.setText(" ");
                genreInput.setText(" ");
                costInput.setText(" ");


            }
        });

        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title = titleInput.getText().toString();
                year =Integer. parseInt(yearInput.getText().toString());
                country = countryInput.getText().toString();
                keywords = keywordsInput.getText().toString();
                genre = genreInput.getText().toString();
                cost = Integer. parseInt(costInput.getText().toString());
                showToast(title+" has been added");

                myList.add(title+"|"+year);
                count++;
                myAdapter.notifyDataSetChanged();
                Movie movie = new Movie(title,year,country,cost,genre,keywords);
                mMovieViewModel.insert(movie);
            }
        });


    }

    public void showToast(String text){
        Toast.makeText(MainActivity.this,text, Toast.LENGTH_LONG).show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        if (id == R.id.menuClear) {
            titleInput.setText(" ");
            yearInput.setText(" ");
            countryInput.setText(" ");
            keywordsInput.setText(" ");
            genreInput.setText(" ");
            costInput.setText(" ");
        } else if (id == R.id.menuTotal) {
            showToast("Total number of movies :" + count);
        }
        return true;
    }

    class MyGestureDetector extends GestureDetector.SimpleOnGestureListener{
        @Override
        public void onLongPress(MotionEvent e) {
            titleInput.setText(" ");
            yearInput.setText(" ");
            countryInput.setText(" ");
            keywordsInput.setText(" ");
            genreInput.setText(" ");
            costInput.setText(" ");
            super.onLongPress(e);
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            if(Math.abs(distanceY)<20) {
                year = Integer.parseInt(yearInput.getText().toString());
                year += distanceX;
                yearInput.setText("" + year);
            }
            if(distanceY>20 && Math.abs(distanceX)>=0 || Math.abs(distanceX)<5){
                keywordsInput.setText("" +keywordsInput.getText().toString().toUpperCase());
            }
            return super.onScroll(e1, e2, distanceX, distanceY);
        }


        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            if(Math.abs(velocityX)>1000 && Math.abs(velocityY)>1000){
                moveTaskToBack(true);
            }
            return super.onFling(e1, e2, velocityX, velocityY);
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            titleInput.setText("Batman");
            yearInput.setText("2022");
            countryInput.setText("United States");
            keywordsInput.setText("Superhero");
            genreInput.setText("Action");
            costInput.setText("10000000");
            return super.onDoubleTap(e);
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            cost = Integer.parseInt(costInput.getText().toString());
            cost += 150;
            costInput.setText("" + cost);
            return super.onSingleTapConfirmed(e);
        }
    }

    class MyScaleGestureDetector extends ScaleGestureDetector.SimpleOnScaleGestureListener{
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            keywordsInput.setText("" +keywordsInput.getText().toString().toLowerCase());
            return super.onScale(detector);
        }
    }

}