package com.sound.app.weather;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;

/**
 * Created by Francis on 2015-06-07.
 */
public class WeatherAsyncTask extends AsyncTask<Void, Void, Weather> {

    private GpsLocationInfo gpsLocationInfo;
    private Context context;
    private TextView textView;

    public WeatherAsyncTask(Context context, GpsLocationInfo gpsLocationInfo, TextView textView) {
        this.gpsLocationInfo = gpsLocationInfo;
        this.context = context;
        this.textView = textView;
    }

    @Override
    protected Weather doInBackground(Void... params) {
        WeatherHttpClient weatherHttpClient = new WeatherHttpClient();

        String text = weatherHttpClient.getWeatherData(this.gpsLocationInfo.getLatitude(), this.gpsLocationInfo.getLongitude());
        JsonParser parser = new JsonParser();
        JsonObject object = (JsonObject) parser.parse(text);
        JsonArray weathers = object.getAsJsonArray("weather");

        if(weathers.size() > 0){
            JsonObject weather = (JsonObject) weathers.get(0);
            JsonPrimitive main = weather.getAsJsonPrimitive("main");
            JsonPrimitive description = weather.getAsJsonPrimitive("description");
            JsonPrimitive icon = weather.getAsJsonPrimitive("icon");
            byte[] image = weatherHttpClient.getImage(icon.toString());
            return new Weather(main.toString(), description.toString(), icon.toString());
        }

        return null;
    }

    @Override
    protected void onPostExecute(Weather getWeather) {
        if(getWeather == null){
            return;
        }

        if(textView != null) {
            this.textView.setText(getWeather.toString());
        }
    }
}
