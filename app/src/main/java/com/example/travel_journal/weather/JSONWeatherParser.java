package com.example.travel_journal.weather;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONWeatherParser {

    public static Weather fromJson(String json) {
        try {
            JSONObject weatherJSON = new JSONObject(json);
            JSONObject weather = weatherJSON.getJSONArray("weather").getJSONObject(0);

            String main = weather.getString("main");
            String description = weather.getString("description");
            String icon = weather.getString("icon");

            JSONObject mainObject = weatherJSON.getJSONObject("main");

            double temp = mainObject.getDouble("temp");
            double temp_feels_like = mainObject.getDouble("feels_like");
            double humidity = mainObject.getDouble("humidity");


            return new Weather(icon, main, description, temp, temp_feels_like, humidity);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
