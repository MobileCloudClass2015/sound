package com.sound.server.sound;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Francis on 2015-06-04.
 */
@Controller
@ResponseBody
@RequestMapping("/sound")
public class SoundController {

    private static Logger logger = LoggerFactory.getLogger(SoundController.class);

    @Autowired
    private SoundService soundService;
    
    @RequestMapping(value = "/timestamp", method = RequestMethod.POST)
    public Map<String, Object> timestamp(@RequestBody Sound sound){
        Map<String, Object> response = new HashMap<String, Object>();
        soundService.insert(sound);
        response.put("result", true);
        return response;
    }
    
}
