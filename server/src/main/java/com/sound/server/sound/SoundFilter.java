package com.sound.server.sound;

import com.sound.server.util.AbstractListFilter;

/**
 * Created by Francis on 2015-06-04.
 */
public class SoundFilter extends AbstractListFilter {
    
    private String id;

    public SoundFilter() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "SoundFilter{" +
                "id='" + id + '\'' +
                '}';
    }
}
