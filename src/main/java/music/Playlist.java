package music;

import java.util.ArrayList;

public class Playlist extends ArrayList<Song> {

 public Song atSecond(int seconds) throws IndexOutOfBoundsException{

     if(seconds < 0) return null;
     //dobre rozwiązanie bez throw
//     for(Song music : this){
//         seconds-=music.duration();
//         if(seconds<=0){
//             return music;
//         }
//     }
     //seconds = 1000000
     int counter =0;
     Song songToReturn = null;
     for(int i=0;; i++){
         counter +=this.get(i).duration();
         if(counter > seconds)
             songToReturn = this.get(i);
         break;

     }
     return null;


 }
}
