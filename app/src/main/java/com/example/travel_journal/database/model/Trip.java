package com.example.travel_journal.database.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Date;

@Entity(tableName = "trips")
public class Trip implements Serializable {

    public static final String CITY_BREAK = "City Break";
    public static final String SEA_SIDE = "Sea Side";
    public static final String MOUNTAINS = "Mountains";

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private long id;

    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "destination")
    private String destination;
    @ColumnInfo(name = "type")
    private String type;
    @ColumnInfo(name = "price")
    private int price;
    @ColumnInfo(name = "rating")
    private int rating;
    @ColumnInfo(name = "start_date")
    private String start_date;
    @ColumnInfo(name = "end_date")
    private String end_date;
    @ColumnInfo(name = "fav")
    private boolean fav;

    @Ignore
    public Trip() {
    }


    public Trip(long id, String name, String destination,
                String type, int price, int rating,
                String start_date, String end_date, boolean fav) {
        this.id = id;
        this.name = name;
        this.destination = destination;
        this.type = type;
        this.price = price;
        this.rating = rating;
        this.start_date = start_date;
        this.end_date = end_date;
        this.fav = fav;
    }

    public boolean isFav() {
        return fav;
    }

    public void setFav(boolean fav) {
        this.fav = fav;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getType() {
        return this.type;
    }


    public void setType(String type) {
        this.type = type;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    @Override
    public String toString() {
        return "Trip{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", destination='" + destination + '\'' +
                ", type=" + type +
                ", price=" + price +
                ", rating=" + rating +
                ", start_date=" + start_date +
                ", end_date=" + end_date +
                '}';
    }
}
