package com.sound.server.sound;

import com.sound.server.util.Pagination;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Francis on 2015-06-04.
 */
@Repository
public class SoundServiceImpl extends SqlSessionDaoSupport implements SoundService{
    
    private static Logger logger = LoggerFactory.getLogger(SoundServiceImpl.class);
    
    @Override
    public void insert(Sound sound) {
        insertSound(sound);
        logger.debug(sound.toString());
        getSqlSession().insert("sound.insertTimeStamp", sound);
    }

    @Override
    public List<Sound> selectMaxCountSound(String id) {
        return getSqlSession().selectList("sound.selectMaxCountSound", id);
    }

    @Override
    public List<Sound> selectSounds(SoundFilter soundFilter) {
        Pagination pagination = soundFilter.getPagination();
        int count = selectCountSounds(soundFilter);
        pagination.setNumItems(count);
        if(count == 0){
            return new ArrayList<Sound>();
        }
        return getSqlSession().selectList("sound.selectSounds", soundFilter);
    }

    @Override
    public Sound selectRecommendTimeWeather(Sound sound) {
        // ~ 해당 시간, 날씨에 많은 노래를 추천해준다.
        Sound getSound = getSqlSession().selectOne("sound.selectMaxCountWeatherTime", sound);
        // ~ 정보가 없을 경우 시간에 대한 노래를 추천한다.
        if (getSound == null || getSound.getPn() == null || getSound.getPn().equals(0)){
            getSound = getSqlSession().selectOne("sound.selectMaxCountTime", sound);
        }
        // ~ 다시 정보가 없을 경우 해당 노래가 없을 경우 전체 노래에 대한 노래를 추천한다.
        if (getSound == null || getSound.getPn() == null || getSound.getPn().equals(0)){
            getSound = getSqlSession().selectOne("sound.selectMaxCountAll.", sound);
        }

        if (getSound == null || getSound.getPn() == null || getSound.getPn().equals(0)){
            Sound returnSound = new Sound();
            returnSound.setArtist("GOD");
            returnSound.setTitle("거짓말");
            return returnSound;
        }
        
        return getSqlSession().selectOne("sound.selectOne", getSound.getPn());
    }

    private int selectCountSounds(SoundFilter soundFilter) {
        return getSqlSession().selectOne("sound.selectCountSounds",soundFilter);
    }

    private void insertSound(Sound sound){
        List<Sound> sounds = getSqlSession().selectList("sound.isExist", sound);
        if(sounds.size() > 0){
            Sound getSound = sounds.get(0);
            sound.setPn(getSound.getPn());
            return;
        }
        
        getSqlSession().insert("sound.insert", sound);
    }
}
