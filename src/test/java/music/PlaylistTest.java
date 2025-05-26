package music;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlaylistTest {


    @Test
    public void testIfNewListIsEmpty(){
        Playlist playList = new Playlist();
        assertTrue(playList.isEmpty());
    }
    @Test
    public void testIfAfterAddingFirstOneSizeIsOne(){
        Playlist playList = new Playlist();
        playList.add(new Song("Zenek","Oczy zielone",265));
        assertEquals(1,playList.size());
    }
    @Test
    public void testIfAddedSongIsTheSameObjectInList(){
        Playlist playList = new Playlist();
        Song songToAdd =new Song("Zenek","Oczy zielone",265);
        playList.add(songToAdd);
        assertTrue(songToAdd==playList.get(0));
    }
    //bardzo ważne zapamiętać
    @Test
    public void testIfAddedSongHasTheSameFields(){
        Playlist playList = new Playlist();
        Song songToAdd =new Song("Zenek","Oczy zielone",265);
        playList.add(songToAdd);
        assertEquals(songToAdd,playList.get(0));
    }



}