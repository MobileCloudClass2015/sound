package com.sound.app.recommend;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.sound.app.R;
import com.sound.app.dto.MyPlayMap;
import com.sound.app.dto.Sound;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Francis on 2015-06-06.
 */
public class YoutubeTaskAsyncTask extends AsyncTask<Void, Void, MyPlayMap> {

    private static final String TAG = "YoutubeTaskAsyncTask";

    private Context context;

    public YoutubeTaskAsyncTask(Context context) {
        this.context = context;
    }

    @Override
    protected MyPlayMap doInBackground(Void... params) {

        SharedPreferences pref = context.getSharedPreferences("userInfo", 0);
        String id = pref.getString("id", "");
        String url = context.getString(R.string.contextPath) + "/recommend/myList/"+id;

        Boolean isSuccess = false;
        MyPlayMap myPlayMap =  null;
        try {
            while (!isSuccess) {
                try {
                    myPlayMap = postTemplate(url);
                    if (myPlayMap.getResult()) {
                        isSuccess = true;
                    }
                } catch (ResourceAccessException e) {
                    Log.e("Error", e.getMessage(), e);
                    isSuccess = false;
                }
            }
        }catch (Exception e) {
            Log.e("Error", e.getMessage(), e);
            myPlayMap.setResult(false);
        }
        return myPlayMap;
    }

    private MyPlayMap postTemplate(String url){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<MyPlayMap> responseEntity = restTemplate.postForEntity(url, null, MyPlayMap.class);
        return responseEntity.getBody();
    }

    @Override
    protected void onPostExecute(MyPlayMap myPlayMap) {
        if(myPlayMap == null || !myPlayMap.getResult()){
            return;
        }

    }

}

