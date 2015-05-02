package com.sound.app;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.sound.app.auth.Auth;
import com.sound.app.bonacell.BonacellAsyncTask;
import com.sound.app.weather.WeatherHttpClient;


public class LoginActivity extends ActionBarActivity {

    private static final String TAG = "LoginActivity";

    TextView oauth;
    TextView weather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Intent intent = getIntent();
        Auth auth = intent.getParcelableExtra("auth");

        oauth = (TextView) findViewById(R.id.oauth);
        oauth.setText(auth.toString());
        Log.d(TAG, auth.toString());
        weather = (TextView) findViewById(R.id.weather);

        new WeatherAsyncTask().execute();
    }

    private class WeatherAsyncTask extends AsyncTask<Void, Void, String>{
        @Override
        protected String doInBackground(Void... params) {
            WeatherHttpClient weatherHttpClient = new WeatherHttpClient();
            String info = weatherHttpClient.getWeatherData("London,uk");
            return info;
        }

        @Override
        protected void onPostExecute(String info) {
            Log.d(TAG, info);
            weather.setText(info);
        }
    }

}
