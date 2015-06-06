package com.sound.server.request;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * Created by Francis on 2015-06-04.
 */
@Service
public class RequestService {
    
    private static Logger logger = LoggerFactory.getLogger(RequestService.class);

    public String sendPost(String queryUrl, JSONObject parameter) throws Exception {
        
        URL url = new URL(queryUrl);
        URLConnection conn = url.openConnection();
        HttpURLConnection http = (HttpURLConnection) conn;
        http.setRequestMethod("POST");
        http.setRequestProperty("Accept-Charset", "UTF-8");
        http.setDoOutput(true);
        http.setDoInput(true);
        http.setUseCaches(false);
        http.setDefaultUseCaches(false);

        String query = "data="+parameter.toString();
        logger.debug(query.toString());
        logger.debug("URLEncode utf-8 " + query);
        
        PrintWriter out = new PrintWriter(http.getOutputStream());
        out.println(query);
        out.close();

        int responseCode = http.getResponseCode();
        logger.debug("Response Code : " + responseCode +" "+http.getResponseMessage());

        BufferedReader in = new BufferedReader(new InputStreamReader(http.getInputStream(), "UTF-8"));
        String text="";
        String inputLine;
        while((inputLine = in.readLine()) != null){
            text+=inputLine;
        }
        in.close();
        
        logger.debug(text);
        
        return text;
    }
    
    public SearchReturn makeSearchTextResultToObject(String text){
        
        JsonParser parser = new JsonParser();
        JsonObject object = (JsonObject) parser.parse(text);
        JsonArray tracks = (JsonArray) object.getAsJsonArray("tracks");
        
        SearchReturn searchReturn;
        if(tracks.size() > 0){
            JsonObject track = (JsonObject) tracks.get(0);
            logger.debug(track.toString());
            JsonPrimitive trackId = (JsonPrimitive) track.get("track_id");
            JsonPrimitive artist = (JsonPrimitive) track.get("artist");
            JsonPrimitive title = (JsonPrimitive) track.get("title");
            JsonPrimitive url = (JsonPrimitive) track.get("url");
        
            searchReturn = new SearchReturn(trackId.toString(), artist.toString(), title.toString(), url.toString());
        }else{
            searchReturn = new SearchReturn();
        }

        return searchReturn;
    }
    
    
}
