package com.sound.server.request;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Francis on 2015-06-04.
 */
public class RequestController {
    
    private static Logger logger = LoggerFactory.getLogger(RequestController.class);

    private static final String USER_AGENT = "";
    private static final String QUERY_URL = "";

    public void sendPost() throws Exception {
        
        URL obj = new URL(QUERY_URL);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        con.setRequestMethod("GET");
        con.setRequestProperty("Accept-Charset", "UTF-8");

        int responseCode = con.getResponseCode();
        logger.debug("Sending 'GET' request to URL : " + QUERY_URL);
        logger.debug("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
    }
}
