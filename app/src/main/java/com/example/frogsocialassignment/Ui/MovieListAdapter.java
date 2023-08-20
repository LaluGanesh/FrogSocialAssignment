package com.example.frogsocialassignment.Ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.frogsocialassignment.R;

import java.util.List;


public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.NewHolder> {

    Context mContext;
    private List<Search> movieListResponseModels;
    private MovieListAdapter movieListAdapter;


    public MovieListAdapter(Context mContext, List<Search> movieListResponseModels) {
        this.mContext = mContext;
        this.movieListResponseModels = movieListResponseModels;
    }

    @NonNull
    @Override
    public NewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView;

            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.movie_row_item, parent, false);

        return new NewHolder(itemView, viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull NewHolder holder, int position) {

        Search course_listItem = movieListResponseModels.get(position);
            holder.movieName.setText(course_listItem.getTitle());
            holder.tvYear.setText(course_listItem.getYear());

            Glide.with(mContext)
                    .load(course_listItem.getPoster())
                    .fitCenter()
                    .centerCrop()
                    .into(holder.ivBanner);



    }

    @Override
    public int getItemViewType(int position) {

            return position;

    }

    @Override
    public int getItemCount() {

            return movieListResponseModels.size();

    }



    public static class NewHolder extends RecyclerView.ViewHolder {

        TextView movieName, tvYear;
        ImageView ivBanner;


        public NewHolder(@NonNull View itemView, int viewType) {
            super(itemView);
            movieName = itemView.findViewById(R.id.tv_title);
            tvYear = itemView.findViewById(R.id.tv_year);
            ivBanner = itemView.findViewById(R.id.iv_poster);
        }
    }



}
