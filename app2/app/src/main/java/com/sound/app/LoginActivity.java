package com.sound.app;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.cocosw.bottomsheet.BottomSheet;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.sound.app.util.BackPressCloseHandler;
import com.sound.app.weather.GpsLocationInfo;
import com.sound.app.weather.Weather;
import com.sound.app.weather.WeatherAsyncTask;
import com.sound.app.weather.WeatherHttpClient;
import com.sound.app.youtube.VideoListDemoActivity;

import java.util.ArrayList;

public class LoginActivity extends Activity {

    private static final String TAG = "LoginActivity";

    private BottomSheet bottomSheet;
    private Button youtubeBtn;
    private GpsLocationInfo gpsLocationInfo;
    private BackPressCloseHandler backPressCloseHandler;
    private ImageView imgview;
    private TextView weatherinfo;

    ArrayList<Content> manylist;
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

        this.youtubeBtn=(Button)findViewById(R.id.youtubeBtn);
        this.youtubeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, VideoListDemoActivity.class);
                startActivity(intent);
                finish();
            }
        });

        this.gpsLocationInfo = new GpsLocationInfo(LoginActivity.this);
        if(!this.gpsLocationInfo.isGetLocation()){
            this.gpsLocationInfo.showSettingsAlert();
        }
        imgview=(ImageView)findViewById(R.id.weatherimg);
        weatherinfo=(TextView)findViewById(R.id.weatherinfo);
        new WeatherAsyncTask(getApplicationContext(), this.gpsLocationInfo, this.imgview, this.weatherinfo).execute();

        manylist=new ArrayList<Content>();
        Content con;
        con=new Content(R.drawable.ic_launcher, "김연상"); manylist.add(con);
        con=new Content(R.drawable.ic_launcher, "롤로"); manylist.add(con);
        con=new Content(R.drawable.ic_launcher, "루리"); manylist.add(con);

        ConlistAdapter ConAdapter=new ConlistAdapter(this,R.layout.context,manylist);

        ListView conlist;
        conlist=(ListView)findViewById(R.id.list);
        conlist.setAdapter(ConAdapter);
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
class Content {
    Content(int aicon, String atext) {
        icon=aicon;
        text=atext;
    }
    int icon;
    String text;
}

class ConlistAdapter extends BaseAdapter {
    Context lefticon;
    LayoutInflater Inflater;
    ArrayList<Content> asrc;
    int layout;

    public ConlistAdapter(Context context, int alayout, ArrayList<Content> bsrc) {
        lefticon=context;
        Inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        asrc=bsrc;
        layout=alayout;
    }

    public int getCount() {
        return asrc.size();
    }

    public String getItem(int position) {
        return asrc.get(position).text;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos=position;
        if(convertView==null) {
            convertView=Inflater.inflate(layout, parent, false);
        }
        ImageView img=(ImageView)convertView.findViewById(R.id.img);
        img.setImageResource(asrc.get(position).icon);

        TextView txt=(TextView)convertView.findViewById(R.id.text);
        txt.setText(asrc.get(position).text);

        return convertView;
    }
}