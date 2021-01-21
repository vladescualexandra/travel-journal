package com.example.travel_journal.model;

import java.io.Serializable;
import java.util.Date;

public class Trip implements Serializable {

    private int id;
    private String name;
    private String destination;
    private Type type;
    private int price;
    private int rating;
    private Date start_date;
    private Date end_date;

    public Trip() {
    }

    public Trip(String name, String destination, Type type, int price, int rating) {
        this.name = name;
        this.destination = destination;
        this.type = type;
        this.price = price;
        this.rating = rating;
    }

    public Trip(String name, String destination, Type type,
                int price, int rating, Date start_date, Date end_date) {
        this.name = name;
        this.destination = destination;
        this.type = type;
        this.price = price;
        this.rating = rating;
        this.start_date = start_date;
        this.end_date = end_date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
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
