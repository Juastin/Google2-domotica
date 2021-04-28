package src.core;
import src.Main;

import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

public class Audio {

    public static void play(String file) {
        if (Main.enableAudio) {
            try {
                Audio.playSound(file);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    public static Clip playSound(String soundFile) throws Exception {
        AudioInputStream audioIn;
        Clip clip = AudioSystem.getClip();
        audioIn = AudioSystem.getAudioInputStream(Audio.class.getResource(soundFile));
        clip.open(audioIn);
        clip.start();
        return clip;
    }

}
