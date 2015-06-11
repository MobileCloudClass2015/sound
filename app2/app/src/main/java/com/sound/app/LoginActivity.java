package com.sound.app;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.cocosw.bottomsheet.BottomSheet;
import com.kure.musicplayer.activities.ActivityMenuMain;
import com.sound.app.recommend.MyListAsyncTask;
import com.sound.app.util.BackPressCloseHandler;
import com.sound.app.weather.GpsLocationInfo;
import com.sound.app.weather.Weather;
import com.sound.app.weather.WeatherAsyncTask;
import com.sound.app.youtube.VideoListDemoActivity;

public class LoginActivity extends Activity {

    private static final String TAG = "LoginActivity";

    private BottomSheet bottomSheet;
    private Button youtubeBtn;
    private GpsLocationInfo gpsLocationInfo;
    private BackPressCloseHandler backPressCloseHandler;

    private ImageView imgview;
    private TextView weatherinfo;
    private TextView weatherDescription;
    private ListView listView;
    private Weather weather = null;

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

                        Intent logoutIntent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(logoutIntent);
                        break;

                    case R.id.playlist:
                        Intent playLstIntent = new Intent(LoginActivity.this, ActivityMenuMain.class);
                        startActivity(playLstIntent);
                        break;
                }
            }
        }).build();
        this.backPressCloseHandler = new BackPressCloseHandler(this);

        this.youtubeBtn=(Button)findViewById(R.id.youtubeBtn);
        this.youtubeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(weather == null){
                    Toast.makeText(LoginActivity.this, "Weather information has been received. Please wait.", Toast.LENGTH_SHORT);
                    return;
                }
                Intent intent = new Intent(LoginActivity.this, VideoListDemoActivity.class);
                intent.putExtra("weatherMain", weather.getMain());
                startActivity(intent);
            }
        });

        this.gpsLocationInfo = new GpsLocationInfo(LoginActivity.this);
        if(!this.gpsLocationInfo.isGetLocation()){
            this.gpsLocationInfo.showSettingsAlert();
        }
        imgview=(ImageView)findViewById(R.id.weatherimg);
        weatherinfo=(TextView)findViewById(R.id.weatherinfo);
        weatherDescription = (TextView) findViewById(R.id.weatherDescription);
        new WeatherAsyncTask(getApplicationContext(), this.gpsLocationInfo, this.imgview, this.weatherinfo, this.weatherDescription, this.weather).execute();



        listView = (ListView) findViewById(R.id.list);

        new MyListAsyncTask(LoginActivity.this, listView).execute();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.gpsLocationInfo.stopUsingGPS();
    }

    @Override
    public void onBackPressed() {
        this.backPressCloseHandler.onBackPressed(this.bottomSheet);
    }
}