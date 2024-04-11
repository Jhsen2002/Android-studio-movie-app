package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.provider.Movie;
import com.example.myapplication.provider.MovieViewModel;

import java.util.ArrayList;
import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {
    List<Movie> movieList  = new ArrayList<>();
    ArrayAdapter myAdapter;

//    public MyRecyclerViewAdapter(ArrayList<Movie> movieList){
//        this.movieList = movieList;
//    }
    public MyRecyclerViewAdapter(){ ;
    }


    public void setMovieList(List<Movie> movieList) {
        this.movieList = movieList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText("Title: " + movieList.get(position).getTitle());
        int year = movieList.get(position).getYear();
        holder.year.setText("Year: " + movieList.get(position).getYear());
        holder.country.setText("Country: " + movieList.get(position).getCountry());
        holder.genre.setText("Genre: " + movieList.get(position).getGenre());
        holder.cost.setText("Cost: " + movieList.get(position).getCost());
        holder.keywords.setText("Keywords: " + movieList.get(position).getKeywords());

        int newPosition = position;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Toast msg = Toast.makeText(v.getContext(), "Movie No." + (newPosition + 1) + " with title: " + movieList.get(newPosition).getTitle() + " is selected", Toast.LENGTH_SHORT);
                msg.show();
                //TODO
                MainActivity.mMovieViewModel.deleteByYear(year);
//
//                myAdapter.notifyDataSetChanged();
            }
        });
    }


    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView title;
        public TextView year;
        public TextView country;
        public TextView genre;
        public TextView cost;
        public TextView keywords;

        public ViewHolder(View itemView){
            super(itemView);
            title = itemView.findViewById(R.id.cardTitle);
            year = itemView.findViewById(R.id.cardYear);
            country = itemView.findViewById(R.id.cardCountry);
            genre = itemView.findViewById(R.id.cardGenre);
            cost = itemView.findViewById(R.id.cardCost);
            keywords = itemView.findViewById(R.id.cardKeyword);
        }
    }
}

