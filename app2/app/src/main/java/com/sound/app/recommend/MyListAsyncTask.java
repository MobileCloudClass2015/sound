package com.sound.app.recommend;

import android.content.Context;
import android.content.Intent;
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
import com.sound.app.youtube.ReommendSeachActivity;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Francis on 2015-06-06.
 */
public class MyListAsyncTask extends AsyncTask<Void, Void, MyPlayMap> {

    private static final String TAG = "MyListAsyncTask";

    private Context context;

    private ListView listView;

    public MyListAsyncTask(Context context, ListView listView) {
        this.context = context;
        this.listView = listView;
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

    private List<String> artists;

    private List<String> titles;

    @Override
    protected void onPostExecute(MyPlayMap myPlayMap) {
        if(myPlayMap == null || !myPlayMap.getResult()){
            return;
        }
        Log.d(TAG, myPlayMap.toString());
        artists = new ArrayList<>();
        titles = new ArrayList<>();

        List<Sound> sounds = myPlayMap.getSounds();
        List<String> list = new ArrayList<String>();
        if(sounds != null && sounds.size() > 0){
            for(Sound sound : sounds) {
                list.add(sound.getArtist() + " " + sound.getTitle());
                artists.add(sound.getArtist());
                titles.add(sound.getTitle());
            }
        }else{
            list.add("Data doesn't exist");
        }
        final StableArrayAdapter adapter = new StableArrayAdapter(context, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);
    }

    private class StableArrayAdapter extends ArrayAdapter<String> {

        HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

        public StableArrayAdapter(Context context, int textViewResourceId, List<String> objects) {
            super(context, textViewResourceId, objects);
            for (int i = 0; i < objects.size(); ++i) {
                mIdMap.put(objects.get(i), i);
            }
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View view =super.getView(position, convertView, parent);

            TextView textView=(TextView) view.findViewById(android.R.id.text1);
            /*YOUR CHOICE OF COLOR*/
            textView.setTextColor(Color.WHITE);
            textView.setTextSize(12);

            textView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ReommendSeachActivity.class);
                    intent.putExtra("title", titles.get(position));
                    intent.putExtra("artist", artists.get(position));
                    context.startActivity(intent);
                }
            });

            return view;
        }

        @Override
        public long getItemId(int position) {
            String item = getItem(position);
            return mIdMap.get(item);
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

    }
}

