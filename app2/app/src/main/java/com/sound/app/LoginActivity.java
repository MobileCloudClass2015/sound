package com.sound.app;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.cocosw.bottomsheet.BottomSheet;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.kure.musicplayer.activities.ActivityMenuMain;
import com.sound.app.auth.Auth;
import com.sound.app.recommend.MyListAsyncTask;
import com.sound.app.util.BackPressCloseHandler;
import com.sound.app.weather.GpsLocationInfo;
import com.sound.app.weather.Weather;
import com.sound.app.weather.WeatherAsyncTask;
import com.sound.app.weather.WeatherHttpClient;
import com.sound.app.youtube.VideoListDemoActivity;

public class LoginActivity extends Activity {

    private static final String TAG = "LoginActivity";

    private TextView oauth;
    private TextView weather;
    private String id;

    private BottomSheet bottomSheet;
    private BackPressCloseHandler backPressCloseHandler;
    private Button button;
    private Button myListBtn;
    private Button youtubeBtn;
    private GpsLocationInfo gpsLocationInfo;

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

        this.backPressCloseHandler = new BackPressCloseHandler(this);

        Intent intent = getIntent();
        Auth auth = intent.getParcelableExtra("auth");

        SharedPreferences pref = getSharedPreferences("userInfo", 0);
        this.id = pref.getString("id", "");

        oauth = (TextView) findViewById(R.id.oauth);
        oauth.setText(auth.toString());
        Log.d(TAG, auth.toString());
        weather = (TextView) findViewById(R.id.weather);

        this.button = (Button) findViewById(R.id.button);

        this.button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, ActivityMenuMain.class);
                startActivity(intent);
                finish();
            }
        });

        this.myListBtn = (Button) findViewById(R.id.myListBtn);
        this.myListBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                new MyListAsyncTask(getApplicationContext()).execute();
            }
        });

        this.youtubeBtn=(Button)findViewById(R.id.youtubeBtn);
        this.youtubeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, VideoListDemoActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // Check if GPS enabled
        this.gpsLocationInfo = new GpsLocationInfo(LoginActivity.this);
        if(!this.gpsLocationInfo.isGetLocation()) {
            this.gpsLocationInfo.showSettingsAlert();
        }else{
            new WeatherAsyncTask(getApplicationContext(), this.gpsLocationInfo, this.weather).execute();
        }
    }

    @Override
    public void onBackPressed() {
        this.backPressCloseHandler.onBackPressed(this.bottomSheet);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.gpsLocationInfo.stopUsingGPS();
    }

}
