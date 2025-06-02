package images;

import org.example.Main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageProcessor {

    private BufferedImage image;


    public void loadImage(String path){
        try {
            image = ImageIO.read(new File(path));
        } catch (IOException e) {
            System.out.println("Error loading image");
        }


    }
    public void saveImage(String path){
        try {
            ImageIO.write(image, "jpg",new File(path));
        } catch (IOException e) {
            System.out.println("Saving loading image");
        }
    }
    public void changeBrightness(int brightness){
        int width = image.getWidth();
        int height = image.getHeight();
        for(int i=0; i< width; i++){
            for(int j=0; j<height; j++){
                Color c = new Color(image.getRGB(i,j));
                int r = Math.clamp(c.getRed()+ brightness,0,255);
                int g = Math.clamp(c.getGreen()+ brightness,0,255);
                int b = Math.clamp(c.getBlue()+ brightness,0,255);
                image.setRGB(i,j,new Color(r,g,b).getRGB());

            }
        }
    }
    public void increaseBrightnessMultiThread(int brightness){
        int cores= Runtime.getRuntime().availableProcessors();
        Thread[] threads = new Thread[cores];
        int width = image.getWidth();
        int height = image.getHeight();
        int part = height/cores;
        for(int n=0; n<cores; n++){
            int start = n+part;
            int end;
            if(n==cores-1){
                end=height;
            }else{
                end= start+part-1;
            }
            threads[n]= new Thread(()->{
                for(int i=0; i< width; i++) {
                    for (int j = 0; j < height; j++) {
                        Color c = new Color(image.getRGB(i, j));
                        int r = Math.clamp(c.getRed() + brightness, 0, 255);
                        int g = Math.clamp(c.getGreen() + brightness, 0, 255);
                        int b = Math.clamp(c.getBlue() + brightness, 0, 255);
                        image.setRGB(i, j, new Color(r, g, b).getRGB());
                    }
                }
            });


        }

    }
}
