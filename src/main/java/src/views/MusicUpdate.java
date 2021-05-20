package src.views;

import src.system.Queries;

import java.util.ArrayList;

public class MusicUpdate {
    private String currentSongName;
    private int currentSongID;
    private int currentSongDuration;
    private boolean isPlaying = false;
    private int currentSongTime = 0;

    private ArrayList<ArrayList<String>> songsList;
    private int currentIndex = 0;

    public MusicUpdate() {
        songsList = Queries.getAllSongs();
        ArrayList<String> firstSong = songsList.get(currentIndex);
        currentSongID = Integer.parseInt(firstSong.get(0));
        currentSongName = firstSong.get(1);
        currentSongDuration = Integer.parseInt(firstSong.get(2));
    }

    public void nextSong() {
        if (currentIndex+1 == songsList.size()) {
            currentIndex = 0;
        } else {
            currentIndex++;
        }
        setSong(currentIndex);
    }

    public void previousSong() {
        if (currentIndex == 0) {
            currentIndex = songsList.size()-1;
        } else {
            currentIndex--;
        }
        setSong(currentIndex);
    }

    public void setSong(int currentIndex) {
        ArrayList<String> setSong = songsList.get(currentIndex);
        currentSongID = Integer.parseInt(setSong.get(0));
        currentSongName = setSong.get(1);
        currentSongDuration = Integer.parseInt(setSong.get(2));
        currentSongTime = 0;
        System.out.printf("\n===Song playing===\n" +
                "Id: %s\n" +
                "Name: %s\n" +
                "Duration: %ss\n" +
                "==================\n", currentSongID, currentSongName, currentSongDuration);
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public void setPlaying(boolean playing) {
        isPlaying = playing;
    }

    public int getCurrentSongTime() {
        return currentSongTime;
    }

    public int getCurrentSongDuration() {
        return currentSongDuration;
    }

    public String getCurrentSongName() {
        return currentSongName;
    }

    public ArrayList<ArrayList<String>> getSongsList() {
        return songsList;
    }
}
