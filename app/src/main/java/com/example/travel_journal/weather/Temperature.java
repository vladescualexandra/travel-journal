package com.example.travel_journal.weather;

public class Temperature {

    private float maxTemp;
    private float minTemp;
    private float temp;

    public Temperature() {
    }

    public Temperature(float maxTemp, float minTemp, float temp) {
        this.maxTemp = maxTemp;
        this.minTemp = minTemp;
        this.temp = temp;
    }

    public void setMaxTemp(float maxTemp) {
        this.maxTemp = maxTemp;
    }

    public float getMaxTemp() {
        return maxTemp;
    }

    public void setMinTemp(float minTemp) {
        this.minTemp = minTemp;
    }

    public float getMinTemp() {
        return minTemp;
    }

    public void setTemp(float temp) {
        this.temp = temp;
    }

    public float getTemp() {
        return temp;
    }
}
