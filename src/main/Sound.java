/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 *
 * @author kring
 */
public class Sound {
    Clip clip;
    URL soundURL[] = new URL[30];
    boolean isPaused = false; // New variable to track if the music is paused

    public Sound() {
        soundURL[0] = getClass().getResource("/sound/Blocks - Minecraft Music Disc - C418.wav");
        soundURL[1] = getClass().getResource("/sound/coin.wav");
        soundURL[2] = getClass().getResource("/sound/powerup.wav");
        soundURL[3] = getClass().getResource("/sound/fanfare.wav");
        soundURL[4] = getClass().getResource("/sound/banana_effect.wav");
        soundURL[5] = getClass().getResource("/sound/GameOver.wav");
        soundURL[6] = getClass().getResource("/sound/shadow.wav");
        soundURL[7] = getClass().getResource("/sound/background_music.wav");
    }

    public void setFile(int i) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        } catch (Exception e) {
        }
    }

    public void play() {
        clip.start();
    }

    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }


    public void stop() {
            clip.stop();
        
    }
}
