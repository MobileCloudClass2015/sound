package com.sound.server.request;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by Francis on 2015-06-06.
 */
@Controller
@ResponseBody
@RequestMapping("/request")
public class RequestController {

    private static final String CONTEXT_PATH = "http://52.68.180.80/soundnerd";
    
    @Autowired
    private RequestService requestService;

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public Map<String, Object> requestSearch(@RequestBody Request request) throws Exception {
        Map<String, Object> response = new HashMap<String, Object>();
        String line = requestService.sendPost(CONTEXT_PATH+"/music/search", request.getJSONObject());
        response.put("line", line);
        return response;
    }
    
}
