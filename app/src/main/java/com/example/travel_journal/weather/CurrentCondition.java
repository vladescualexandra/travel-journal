package com.example.travel_journal.weather;

public class CurrentCondition {

    private int weatherId;
    private String descr;
    private String condition;
    private String icon;
    private int humidity;
    private int pressure;

    public void setWeatherId(int weatherId) {
        this.weatherId = weatherId;
    }

    public int getWeatherId() {
        return weatherId;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public String getDescr() {
        return descr;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getCondition() {
        return condition;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getIcon() {
        return icon;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public int getPressure() {
        return pressure;
    }
}
