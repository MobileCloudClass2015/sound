package com.sound.app;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.cocosw.bottomsheet.BottomSheet;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.sound.app.weather.GpsLocationInfo;
import com.sound.app.weather.Weather;
import com.sound.app.weather.WeatherHttpClient;
import com.sound.app.youtube.VideoListDemoActivity;

public class LoginActivity extends Activity {

    private static final String TAG = "LoginActivity";

    private BottomSheet bottomSheet;
    private Button youtubeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.bottomSheet = new BottomSheet.Builder(this, R.style.BottomSheet_StyleDialog).title("Option").sheet(R.menu.list).listener(new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {

                    case R.id.logout:
                        SharedPreferences pref = LoginActivity.this.getSharedPreferences("userInfo", 0);
                        SharedPreferences.Editor prefEdit = pref.edit();
                        prefEdit.putString("id","");
                        prefEdit.commit();

                        Intent idCheck = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(idCheck);
                        break;

                }
            }
        }).build();

        this.youtubeBtn=(Button)findViewById(R.id.youtubeBtn);
        this.youtubeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, VideoListDemoActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

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
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
