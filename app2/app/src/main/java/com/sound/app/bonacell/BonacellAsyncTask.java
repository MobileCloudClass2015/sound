package com.sound.app.bonacell;

import android.os.AsyncTask;
import android.util.Log;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Francis on 2015-05-02.
 */
public class BonacellAsyncTask extends AsyncTask<Void, Void, String>{

    private static final String TAG = "BonacellAsyncTask";

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(Void... params) {

        try {
            URL url = new URL("http://52.68.180.80/soundnerd/music/search");
            URLConnection conn = url.openConnection();
            HttpURLConnection http = (HttpURLConnection) conn;
            http.setRequestMethod("POST");
            http.setDoOutput(true);
            http.setDoInput(true);
            http.setUseCaches(false);
            http.setDefaultUseCaches(false);

            JSONObject parameter = new JSONObject();
            parameter.put("artist", "아이유");
            parameter.put("start", "10");
            parameter.put("count", "10");

            String query = "data="+parameter.toString();
            Log.d(TAG, query);
            PrintWriter out = new PrintWriter(http.getOutputStream());
            out.println(query);
            out.close();

            BufferedReader in = new BufferedReader(new InputStreamReader(http.getInputStream()));
            String inputLine = null;
            while((inputLine = in.readLine()) != null){
                Log.d(TAG,inputLine);
            }
            in.close();
        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e);
        }

        return "finish";
    }

    @Override
    protected void onPostExecute(String str) {
        Log.d(TAG, str);
    }

}
