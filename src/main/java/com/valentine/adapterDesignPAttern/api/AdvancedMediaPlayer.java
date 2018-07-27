package com.valentine.adapterDesignPAttern.api;

/**
 * This is an interface for the second class that generally wont be compatible
 * with the class that implements MediaPlayer interface.
 *
 *
 */
public interface AdvancedMediaPlayer {

    public void playVlc(String fileName);
    public void playMp4(String fileName);

}
