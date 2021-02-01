package com.example.travel_journal.weather;

import java.text.DecimalFormat;

public class Weather {

    private String main;
    private String description;
    private String icon;
    private double temp;
    private double temp_feels_like;
    private double humidity;

    public Weather() {

    }

    public Weather(String icon, String main, String description,
                   double temp, double temp_feels_like, double humidity) {
        this.icon = icon;
        this.main = main;
        this.description = description;
        this.temp = fromKelvinToCelsius(temp);
        this.temp_feels_like = fromKelvinToCelsius(temp_feels_like);
        this.humidity = humidity;
    }

    private double fromKelvinToCelsius(double kelvin) {
        // 0 K = -273.15
        double d = kelvin - 273.15;
        DecimalFormat df = new DecimalFormat("##.#");
        double p = Double.parseDouble(df.format(d));
        return p;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = Math.round(fromKelvinToCelsius(temp));
    }

    public double getTemp_feels_like() {
        return temp_feels_like;
    }

    public void setTemp_feels_like(double temp_feels_like) {
        this.temp_feels_like = Math.round(fromKelvinToCelsius(temp_feels_like));
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }
}
