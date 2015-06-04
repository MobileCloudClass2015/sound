package com.sound.server.sound;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

/**
 * Created by Francis on 2015-06-04.
 */
@Repository
public class SoundServiceImpl extends SqlSessionDaoSupport implements SoundService{
    
    private static Logger logger = LoggerFactory.getLogger(SoundServiceImpl.class);
    
    
}
