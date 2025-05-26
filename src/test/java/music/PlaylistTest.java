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
        Song secondSongToAdd =new Song("Zenek","Oczy zielone",265);
        playList.add(songToAdd);
        playList.add(secondSongToAdd);
        assertTrue(playList.get(1)==playList.get(0));
    }
    //spoko sprawa == nie równa się equals
    @Test
    public void testIfAddedSongHasTheSameFields(){
        Playlist playList = new Playlist();
        Song songToAdd =new Song("Zenek","Oczy zielone",265);
        Song secondSongToAdd =new Song("Zenek","Oczy zielone",265);
        playList.add(songToAdd);
        playList.add(secondSongToAdd);
        assertEquals(playList.get(1),playList.get(0));

    }


    @Test
    void atSecond() {
        Playlist playList = new Playlist();

        assertNull(playList.atSecond(45));
        Song songToAdd =new Song("Zenek","Oczy zielone",250);
        playList.add(songToAdd);
        assertNull(playList.atSecond(-6));
        playList.add(songToAdd);
        playList.add(songToAdd);
        playList.add(songToAdd);
        assertNotNull(playList.atSecond(727));
        assertThrowsExactly(IndexOutOfBoundsException.class,() -> {
            playList.atSecond(1024);
        });


    }
    @Test
    void atSecondOutOfBounds() {
        Playlist playList = new Playlist();

        assertNull(playList.atSecond(45));
        Song songToAdd =new Song("Zenek","Oczy zielone",250);
        playList.add(songToAdd);
        playList.add(songToAdd);
        playList.add(songToAdd);
        playList.add(songToAdd);
        assertThrowsExactly(IndexOutOfBoundsException.class,() -> {
            playList.atSecond(1024);
        });


    }
}