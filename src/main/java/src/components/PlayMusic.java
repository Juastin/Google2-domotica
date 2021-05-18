package src.components;
import java.lang.*;
import com.fazecast.jSerialComm.SerialPort;
import src.core.Arduino;
import src.views.MusicPlayerView;

import java.io.IOException;
import java.lang.reflect.Array;


public class PlayMusic {
    private Arduino ar = new Arduino();
    private Integer note;
    private int divider =0, noteDuration =0;
    private int tempo=40;
    private int wholenote = (6000*4)/tempo;
    private int thisNote = 0;
    private long vorigetijd =0;

    public PlayMusic(){}



    public void sendnotes(Integer[] melody) throws InterruptedException, IOException {
        int notes = melody.length;

        if(thisNote < notes * 2&&System.currentTimeMillis()-vorigetijd>noteDuration) {
            thisNote = thisNote + 2;
            // calculates the duration of each note
            try {
                divider = melody[thisNote + 1];
            } catch (ArrayIndexOutOfBoundsException e) {
                divider = 0;
            }
            if (divider > 0) {
                // regular note, just proceed
                noteDuration = (wholenote) / divider;
            } else if (divider < 0) {
                // dotted notes are represented with negative durations!!
                noteDuration = (wholenote) / Math.abs(divider);
                noteDuration *= 1.5; // increases the duration in half for dotted notes
            }
            // we only play the note for 90% of the duration, leaving 10% as a pause
            //========tone(buzzer, melody[thisNote], noteDuration*0.9);==========
            Integer notenduratie = noteDuration;
            try {
                note = melody[thisNote];
            } catch (ArrayIndexOutOfBoundsException e) {
                note = 0;
                thisNote = 0;
            }
            ar.getoutputstream(note);
            // Wait for the specief duration before playing the next note.
            // stop the waveform generation before the next note.
            vorigetijd = System.currentTimeMillis();
        }
    }
    public void pause() throws IOException {
        ar.getoutputstream(0);
    }
}
