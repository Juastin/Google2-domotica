package src.views;

import src.components.PlayMusic;
import src.components.Songs;
import src.system.Queries;

import java.util.ArrayList;

public class MusicUpdate {
    private static ArrayList<ArrayList<String>> songsList = Queries.getAllSongs();
    private static int currentIndex = 0;

    private static ArrayList<String> firstSong = songsList.get(currentIndex);
    private static String currentSongName = firstSong.get(1);
    private static int currentSongID = Integer.parseInt(firstSong.get(0));
    private static int currentSongDuration = Integer.parseInt(firstSong.get(2));
    private static boolean isPlaying = false;
    private static int currentSongTime = 0;
    private static PlayMusic music = new PlayMusic();
    private static Songs song = new Songs();

    private MusicUpdate() {}

    public static void nextSong() {
        if (currentIndex+1 == songsList.size()) {
            currentIndex = 0;
        } else {
            currentIndex++;
        }
        setSong(currentIndex);
        isPlaying = true;
    }

    public static void previousSong() {
        if (currentIndex == 0) {
            currentIndex = songsList.size()-1;
        } else {
            currentIndex--;
        }
        setSong(currentIndex);
        isPlaying = true;
    }

    public static void setSong(int currentIndex) {
        ArrayList<String> setSong = songsList.get(currentIndex);
        currentSongID = Integer.parseInt(setSong.get(0));
        currentSongName = setSong.get(1);
        currentSongDuration = Integer.parseInt(setSong.get(2));
        currentSongTime = 0;
        music = new PlayMusic();
        System.out.printf("\n===Song playing===\n" +
                "Id: %s\n" +
                "Name: %s\n" +
                "Duration: %ss\n" +
                "==================\n", currentSongID, currentSongName, currentSongDuration);
    }

    public static boolean isPlaying() {
        return isPlaying;
    }

    public static void setPlaying(boolean playing) {
        isPlaying = playing;
    }

    public static int getCurrentSongTime() {
        return currentSongTime;
    }

    public static int getCurrentSongDuration() {
        return currentSongDuration;
    }

    public static String getCurrentSongName() {
        return currentSongName;
    }

    public static ArrayList<ArrayList<String>> getSongsList() {
        return songsList;
    }

    public static void setSongsList(ArrayList<ArrayList<String>> new_list) {
        songsList = new_list;
    }

    public static PlayMusic getMusic() {
        return music;
    }

    public static int getCurrentSongID() {
        return currentSongID-1;
    }

    public static Songs getSong() {
        return song;
    }

    public static void setIndex(int new_index) {
        currentIndex = new_index;
    }

    public static int getIndex() {
        return currentIndex;
    }
}
