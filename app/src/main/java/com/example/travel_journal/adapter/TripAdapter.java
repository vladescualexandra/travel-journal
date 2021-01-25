package com.example.travel_journal.adapter;

import android.content.Context;
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
import com.example.travel_journal.database.model.Trip;

import java.util.List;

public class TripAdapter extends RecyclerView.Adapter<TripAdapter.ViewHolder> {

    private List<Trip> trips;
    private Context context;

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

    public TripAdapter(List<Trip> trips, Context context) {
        this.trips = trips;
        this.context = context;
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
        switch (trips.get(position).getType()) {
            case Trip.SEA_SIDE:
                holder.iv_image.setImageResource(R.drawable.beach);
                break;
            case Trip.MOUNTAINS:
                holder.iv_image.setImageResource(R.drawable.mountains);
                break;
            default:
                holder.iv_image.setImageResource(R.drawable.city);
                break;
        }

        holder.tv_name.setText(trips.get(position).getName());
        holder.tv_destination.setText(trips.get(position).getDestination());
        holder.tv_price.setText(String.valueOf(trips.get(position).getPrice()));
        holder.rb_rating.setRating((float) trips.get(position).getRating());

        if (trips.get(position).isFav()) {
            holder.ib_fav.setImageResource(R.drawable.ic_fav);
        } else {
            holder.ib_fav.setImageResource(R.drawable.ic_not_fav);
        }
        holder.ib_fav.setOnClickListener(addToFavorites(trips.get(position).getId(), position));

    }

    private View.OnClickListener addToFavorites(long id, int position) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                trips.get(position).setFav(!trips.get(position).isFav());
            }
        };
    }

    @Override
    public int getItemCount() {
        return trips.size();
    }


}
