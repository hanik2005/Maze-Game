/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

/**
 *
 * @author kring
 */
public class Sound {
    Clip clip;
    URL soundURL[] = new URL[30];
    boolean isPaused = false; // New variable to track if the music is paused
    FloatControl fc;
    int volumeScale = 3;
    Float volume;

    public Sound() {
        soundURL[0] = getClass().getResource("/Assets/sound/Blocks - Minecraft Music Disc - C418.wav");
        soundURL[1] = getClass().getResource("/Assets/sound/coin.wav");
        soundURL[2] = getClass().getResource("/Assets/sound/powerup.wav");
        soundURL[3] = getClass().getResource("/Assets/sound/fanfare.wav");
        soundURL[4] = getClass().getResource("/Assets/sound/banana_effect.wav");
        soundURL[5] = getClass().getResource("/Assets/sound/GameOver.wav");
        soundURL[6] = getClass().getResource("/Assets/sound/shadow.wav");
        soundURL[7] = getClass().getResource("/Assets/sound/background_music.wav");
        soundURL[8] = getClass().getResource("/Assets/sound/spike_trap.wav");
        soundURL[9] = getClass().getResource("/Assets/sound/hitmonster.wav");
        soundURL[10] = getClass().getResource("/Assets/sound/receivedamage.wav");
        soundURL[11] = getClass().getResource("/Assets/sound/swing.wav");
        soundURL[12] = getClass().getResource("/Assets/sound/cursor.wav");
        soundURL[13] = getClass().getResource("/Assets/sound/burning.wav");
        soundURL[14] = getClass().getResource("/Assets/sound/destroyed_brick_sound.wav");

    }

    public void setFile(int i) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
            fc = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN); //change volume
            checkVolume();
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
    public void checkVolume(){
    
        switch(volumeScale){
            case 0: volume = -80f; break;
            case 1: volume = -20f; break;
            case 2: volume = -12f; break;
            case 3: volume = -5f;break;
            case 4: volume = 1f; break;
            case 5: volume = 6f; break;
        
        }
        fc.setValue(volume);
    }
}
