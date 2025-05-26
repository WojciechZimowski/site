package music;

import java.util.ArrayList;

public class Playlist extends ArrayList<Song> {

 public Song atSecond(int seconds){

     if(seconds < 0) return null;
     for(Song music : this){
         seconds-=music.duration();
         if(seconds<=0){
             return music;
         }
     }
     return null;


 }
}
