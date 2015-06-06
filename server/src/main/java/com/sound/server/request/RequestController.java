package com.sound.server.request;

import com.sound.server.sound.Sound;
import com.sound.server.sound.SoundService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by Francis on 2015-06-06.
 */
@Controller
public class RequestController {
    
    private static Logger logger = LoggerFactory.getLogger(RequestController.class);

    private static final String CONTEXT_PATH = "http://52.68.180.80/soundnerd";
    
    @Autowired
    private RequestService requestService;
    
    @Autowired
    private SoundService soundService;

    @ResponseBody
    @RequestMapping(value = "/request/search", method = RequestMethod.POST)
    public Map<String, Object> requestSearch(@RequestBody Search search) throws Exception {
        Map<String, Object> response = new HashMap<String, Object>();
        String line = requestService.sendPost(CONTEXT_PATH+"/music/search", search.getJSONObject());
        response.put("line", line);
        response.put("track", requestService.makeSearchTextResultToObject(line));
        return response;
    }

    @ResponseBody
    @RequestMapping(value = "/request/recommend", method = RequestMethod.POST)
    public Map<String, Object> requestRecommend(@RequestBody Recommend recommend) throws Exception {
        Map<String, Object> response = new HashMap<String, Object>();
        String line = requestService.sendPost(CONTEXT_PATH+"/music/recommend", recommend.getJSONObject());
        response.put("line", line);
        response.put("tracks", requestService.makeRecommendTextResultToObject(line));
        return response;
    }

    @ResponseBody
    @RequestMapping(value = "/recommendList", method = RequestMethod.POST)
    public RecommendMap recommendList(@RequestBody Search search) throws Exception {
        return selectRecommendMap(search);
    }
    
    private RecommendMap selectRecommendMap(Search search) throws Exception {
        String text = requestService.sendPost(CONTEXT_PATH+"/music/search", search.getJSONObject());
        Track  track = requestService.makeSearchTextResultToObject(text);

        List<Track> tracks;
        if(track.getTrackId() != null) {
            Recommend recommend = new Recommend(10, track.getTrackId());
            text = requestService.sendPost(CONTEXT_PATH + "/music/recommend", recommend.getJSONObject());
            tracks = requestService.makeRecommendTextResultToObject(text);
        }else{
            tracks = new ArrayList<Track>();
        }

        RecommendMap recommendMap = new RecommendMap(tracks, track);
        logger.debug(recommendMap.toString());
        return recommendMap;
    }
    
    
    @ResponseBody
    @RequestMapping(value = "/recommend/myList/{id}", method = RequestMethod.POST)
    public MyPlayMap recommendMyList(@PathVariable("id") String id) throws Exception {
        List<Sound> sounds = soundService.selectMaxCountSound(id);
        MyPlayMap myPlayMap = new MyPlayMap(sounds);
        if(sounds.size() > 0){
            Sound sound = sounds.get(0);
            RecommendMap recommendMap = selectRecommendMap(new Search(sound.getArtist(), sound.getTitle()));    
            myPlayMap.setTrack(recommendMap.getTrack());
            myPlayMap.setTracks(recommendMap.getTracks());
        }
        myPlayMap.setResult(true);
        return myPlayMap;
    }


}
