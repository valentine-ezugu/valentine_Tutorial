package com.valentine.adapterDesignPAttern.impl;

import com.valentine.adapterDesignPAttern.api.AdvancedMediaPlayer;

public class Mp4Player implements AdvancedMediaPlayer {

    public void playVlc(String fileName) {
        //do nothing
    }

    @Override
    public void playMp4(String fileName) {
        System.out.println("Playing mp4 file. Name: "+ fileName);
    }
}
