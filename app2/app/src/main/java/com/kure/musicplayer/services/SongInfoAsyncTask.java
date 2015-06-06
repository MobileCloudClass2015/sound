package com.kure.musicplayer.services;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;

import com.kure.musicplayer.model.Song;
import com.sound.app.R;
import com.sound.app.dto.Sound;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Francis on 2015-06-06.
 */
public class SongInfoAsyncTask extends AsyncTask<Song, Void, Map<String, Object>> {

    private Context context;

    public SongInfoAsyncTask(Context context) {
        this.context = context;
    }

    @Override
    protected Map<String, Object> doInBackground(Song... params) {
        if(params[0] == null){
            return new HashMap<>();
        }
        SharedPreferences pref = context.getSharedPreferences("userInfo", 0);
        String id = pref.getString("id", "");

        Song song = params[0];
        Sound sound = new Sound(id, song.getTitle(), song.getArtist());
        String url = context.getString(R.string.contextPath) + "/sound/timestamp";

        Boolean isSuccess = false;
        Map<String, Object> response =  null;
        try {
            while (!isSuccess) {
                try {
                    response = postTemplate(url, sound);
                    if ((Boolean) response.get("result")) {
                        isSuccess = true;
                    }
                } catch (ResourceAccessException e) {
                    Log.e("Error", e.getMessage(), e);
                    isSuccess = false;
                }
            }
        }catch (Exception e) {
            Log.e("Error", e.getMessage(), e);
            response.put("result", false);
        }
        return response;
    }

    private Map<String, Object> postTemplate(String url, Sound sound){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Map> responseEntity = restTemplate.postForEntity(url, sound, Map.class);
        Map<String, Object> messages = responseEntity.getBody();
        return messages;
    }

    @Override
    protected void onPostExecute(Map<String, Object> stringObjectMap) {
        super.onPostExecute(stringObjectMap);

    }
}
