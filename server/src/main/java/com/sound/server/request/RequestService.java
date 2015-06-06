package com.sound.server.request;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

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
        PrintWriter out = new PrintWriter(http.getOutputStream());
        logger.debug(out.toString());
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
        
        return text;
    }
}
