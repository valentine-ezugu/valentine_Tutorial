package com.valentine.adapterDesignPAttern.api;

/**
 * Classes that implement this interface wont be compatible with
 * the classes that will implement AdvancedMediaPlayer.
 *
 * This is a regular media player that cant play mp4
 */
public interface MediaPlayer {

    public void play(String audioType, String fileName);
}
