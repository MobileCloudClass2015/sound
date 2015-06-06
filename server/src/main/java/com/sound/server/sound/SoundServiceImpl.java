package com.sound.server.sound;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

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
        getSqlSession().insert("sound.insertTimeStamp", sound);
    }
    
    private Sound insertSound(Sound sound){
        List<Sound> sounds = getSqlSession().selectList("sound.isExist", sound);
        if(sounds.size() > 0){
            return sounds.get(0);
        }
        
        getSqlSession().insert("sound.insert", sound);
        return sound;
    }
}
