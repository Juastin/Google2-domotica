package src.components;

import src.core.Audio;
import src.core.Container;
import src.core.View;
import src.views.MusicPlayerView;
import src.views.MusicUpdate;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public abstract class MusicPlayerController extends View {
    protected JButton jbList, jbPrevious, jbPlay, jbNext;
    protected JLabel jlTitle, jlCurrentPlayTime, jlMelodyLength;
    protected String currentPlayTime, melodyLength;
    protected JSlider jsPlayTime;

    public MusicPlayerController(Container parent, String name) {
        super(parent, name);
    }

    public void onTick(long now) {
        if(MusicUpdate.isPlaying()){
            try {
                Integer[] song = MusicUpdate.getSong().getMelody(MusicUpdate.getCurrentSongID());
                MusicUpdate.getMusic().sendnotes(song);
            } catch (InterruptedException | IOException interruptedException) {
                interruptedException.printStackTrace();
            }
        } if(!MusicUpdate.isPlaying()) {
            try {
                MusicUpdate.getMusic().pause();
            } catch (IOException interruptedException) {
                interruptedException.printStackTrace();
            }
        }
    }

    public abstract void updateSongInfoView();
}