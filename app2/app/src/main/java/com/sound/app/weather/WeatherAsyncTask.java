package com.sound.app.weather;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Message;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;

import java.io.InputStream;
import java.net.URL;
import android.os.Handler;
import android.widget.Toast;


/**
 * Created by Francis on 2015-06-07.
 */
public class WeatherAsyncTask extends AsyncTask<Void, Void, Weather> {

    private static final String TAG = "WeatherAsyncTask";
    byte[] image;
    private GpsLocationInfo gpsLocationInfo;
    private Context context;
    ImageView weatherimg;
    String imgUrl;
    TextView weatherinfo;
    TextView weatherDescription;
    String weatherMain;

    public WeatherAsyncTask(Context context, GpsLocationInfo gpsLocationInfo, ImageView weatherimg, TextView weatherinfo, TextView weatherDescription, String weatherMain) {
        this.gpsLocationInfo = gpsLocationInfo;
        this.context = context;
        this.weatherimg=weatherimg;
        this.weatherinfo=weatherinfo;
        this.weatherDescription=weatherDescription;
        this.weatherMain = weatherMain;
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
            image = weatherHttpClient.getImage(icon.toString());
            return new Weather(main.getAsString(), description.getAsString(), icon.getAsString());
        }

        return null;
    }

    @Override
    protected void onPostExecute(Weather getWeather) {
        if(getWeather == null){
            return;
        }

        Log.d(TAG, getWeather.toString());
        if (image.length > 0) {
                // ~ TODO
            imgUrl="http://openweathermap.org/img/w/"+getWeather.getIcon()+".png".replace("\"","");
            (new DownThread(imgUrl)).start();
        }
        if(getWeather != null) {
            this.weatherMain += getWeather.getMain();
            this.weatherinfo.setText(getWeather.getMain()+getWeather.getIcon());
            this.weatherDescription.setText(getWeather.getDescription());
        }
    }
    class DownThread extends Thread {
        String mAddr;

        DownThread(String addr) {
            mAddr=addr;
        }

        public void run() {
            try {
                InputStream is = new URL(mAddr).openStream();
                Bitmap bit = BitmapFactory.decodeStream(is);
                is.close();
                Message message = mAfterDown.obtainMessage();
                message.obj = bit;
                mAfterDown.sendMessage(message);
            } catch (Exception e) {;}
            }
        }
    Handler mAfterDown = new Handler() {
        public void handleMessage(Message msg) {
            Bitmap bit=(Bitmap)msg.obj;
            if(bit==null) {
            } else {
                weatherimg.setImageBitmap(bit);
            }
        }
    };
}
