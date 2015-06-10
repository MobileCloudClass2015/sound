package com.sound.app.recommend;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;

import com.sound.app.R;
import com.sound.app.dto.MyPlayMap;
import com.sound.app.dto.Sound;
import com.sound.app.dto.Track;
import com.sound.app.util.Content;
import com.sound.app.util.ContentListAdapter;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Francis on 2015-06-06.
 */
public class MyListAsyncTask extends AsyncTask<Void, Void, MyPlayMap> {

    private static final String TAG = "MyListAsyncTask";

    private Context context;

    private ListView listView;

    private Track track;

    public MyListAsyncTask(Context context, ListView listView, Track track) {
        this.context = context;
        this.listView = listView;
        this.track = track;
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
        Log.d(TAG, myPlayMap.toString());

        List<Sound> sounds = myPlayMap.getSounds();
        ArrayList<Content> list = new ArrayList<Content>();
        if(sounds != null && sounds.size() > 0){
            for(Sound sound : sounds) {
                list.add(new Content(R.drawable.ic_launcher, sound.getArtist() + " " + sound.getTitle()));
            }
        }
        ContentListAdapter contentListAdapter = new ContentListAdapter(context, R.layout.context, list);
        listView.setAdapter(contentListAdapter);

        this.track = myPlayMap.getTrack();
        Log.d(TAG, ""+this.track);
    }
}
