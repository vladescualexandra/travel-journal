package com.example.travel_journal.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travel_journal.R;
import com.example.travel_journal.model.Trip;

import java.util.List;

public class TripAdapter extends RecyclerView.Adapter<TripAdapter.ViewHolder> {

    private List<Trip> trips;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView iv_image;
        private final TextView tv_name;
        private final TextView tv_destination;
        private final TextView tv_price;
        private final RatingBar rb_rating;
        private final ImageButton ib_fav;

        public ViewHolder(View view) {
            super(view);
            iv_image = view.findViewById(R.id.home_row_image);
            tv_name = view.findViewById(R.id.home_row_trip_name);
            tv_destination = view.findViewById(R.id.home_row_trip_destination);
            tv_price = view.findViewById(R.id.home_row_trip_price);
            rb_rating = view.findViewById(R.id.home_row_trip_rating);
            ib_fav = view.findViewById(R.id.home_row_button_fav);
        }
    }

    public TripAdapter(List<Trip> trips) {
        this.trips = trips;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.home_row_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        holder.iv_image.setImageResource();
        holder.tv_name.setText(trips.get(position).getName());
        holder.tv_destination.setText(trips.get(position).getDestination());
        holder.tv_price.setText(String.valueOf(trips.get(position).getPrice()));
        holder.rb_rating.setRating((float) trips.get(position).getRating());
        holder.ib_fav.setOnClickListener(addToFavorites(trips.get(position).getId()));
    }

    private View.OnClickListener addToFavorites(int id) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // delete trip with id
            }
        };
    }

    @Override
    public int getItemCount() {
        return trips.size();
    }


}
