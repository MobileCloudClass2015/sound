package com.sound.server.sound;

import java.util.List;

/**
 * Created by Francis on 2015-06-04.
 */
public interface SoundService {
    
    void insert(Sound sound);
    
    List<Sound> selectMaxCountSound(String id);
}
