package com.valentine.adapterDesignPAttern.adapter;

import com.valentine.adapterDesignPAttern.api.AdvancedMediaPlayer;
import com.valentine.adapterDesignPAttern.api.MediaPlayer;
import com.valentine.adapterDesignPAttern.impl.Mp4Player;
import com.valentine.adapterDesignPAttern.impl.VlcPlayer;

public class MediaAdapter implements MediaPlayer {

     AdvancedMediaPlayer advancedMediaPlayer;

    public MediaAdapter(String audioType) {

        if(audioType.equalsIgnoreCase("vlc")){
            advancedMediaPlayer = new VlcPlayer();
        } else

        if(audioType.equalsIgnoreCase("mp4")){
            advancedMediaPlayer = new Mp4Player();
        }

    }

    @Override
    public void play(String audioType, String fileName) {

            if(audioType.equalsIgnoreCase("vlc")){
                advancedMediaPlayer.playVlc(fileName);
            }
            else if(audioType.equalsIgnoreCase("mp4")){
                advancedMediaPlayer.playMp4(fileName);
            }
        }



  }

