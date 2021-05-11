package src.components;
import java.lang.*;
import com.fazecast.jSerialComm.SerialPort;
import java.io.IOException;


public class PlayMusic {


    public PlayMusic(){}

    public PlayMusic(Integer[] melody) throws InterruptedException, IOException,NullPointerException {

    }

    public void Speelaf(Integer[] melody) throws InterruptedException, IOException {
        Integer[] welknummer = melody;
        int tempo=40;
        int notes = melody.length;
        int wholenote = (6000*4)/tempo;
        int divider =0, noteDuration =0;

        SerialPort sp = SerialPort.getCommPort("COM3");

        sp.setComPortTimeouts(SerialPort.TIMEOUT_WRITE_BLOCKING, 0, 0);

        if (sp.openPort()) {
            System.out.println("Port is open :)");
        } else {
            System.out.println("Failed to open port :(");
            return;
        }

        for (int thisNote = 0; thisNote < notes * 2; thisNote = thisNote + 2) {
            // calculates the duration of each note
            divider = melody[thisNote + 1];
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
            Integer noot= melody[thisNote]/10;
            System.out.println(noot);


            sp.getOutputStream().write(noot);
//            sp.getOutputStream().write(notenduratie);

//            System.out.println("Melodie is:"+ noot);

            // Wait for the specief duration before playing the next note.
            Thread.sleep(noteDuration);
            Integer zero = 0;
            // stop the waveform generation before the next note.
            sp.getOutputStream().write(zero.byteValue());
        }

        if (sp.closePort()) {
            System.out.println("Port is closed :)");
        } else {
            System.out.println("Failed to close port :(");
            return;
        }
    }
}
