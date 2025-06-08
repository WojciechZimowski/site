package images;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ImageProcessor {

    private BufferedImage image;

    //Zad 1.
    public void loadImage(String path){
        try {
            image = ImageIO.read(new File(path));
        } catch (IOException e) {
            System.out.println("Error loading image");
        }
    }

    public void saveImage(String path){
        try {
            ImageIO.write(image, "jpg", new File(path));
        } catch (IOException e) {
            System.out.println("Error saving image");
        }
    }

    public void changeBrightness(int brightness){
        int width = image.getWidth();
        int height = image.getHeight();
        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){
                Color c = new Color(image.getRGB(i, j));
                int r = Math.clamp(c.getRed() + brightness,0, 255);
                int g = Math.clamp(c.getGreen() + brightness, 0, 255);
                int b = Math.clamp(c.getBlue() + brightness, 0, 255);
                image.setRGB(i, j, new Color(r,g,b).getRGB());
            }
        }
    }

    public void increaseBrightnessMultiThread(int brightness){
        int cores = Runtime.getRuntime().availableProcessors();
        Thread[] threads = new Thread[cores];
        int width = image.getWidth();
        int height = image.getHeight();
        int part = height / cores;

        for(int n = 0; n < cores; n++){
            int start = n * part;
            int end;
            if (n == cores - 1){
                end = height - 1;
            }else{
                end = start + part;
            }
            threads[n] = new Thread(() -> {
                for(int i = 0; i < width; i++){
                    for(int j = start; j < end; j++){
                        Color c = new Color(image.getRGB(i, j));
                        int r = Math.clamp(c.getRed() + brightness,0, 255);
                        int g = Math.clamp(c.getGreen() + brightness, 0, 255);
                        int b = Math.clamp(c.getBlue() + brightness, 0, 255);
                        image.setRGB(i, j, new Color(r,g,b).getRGB());
                    }
                }
            }
            );
            threads[n].start();
        }
        for(Thread thread : threads){
            try {
                thread.join();
            } catch (InterruptedException e) {
                System.out.println("Error joining thread");
            }
        }
    }

//PD poprawić metode
public void increaseBrightnessThreadPool(int brightness) {
    long startTime = System.currentTimeMillis(); // ⏱️ Start

    ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    int height = image.getHeight();
    int width = image.getWidth();

    for (int i = 0; i < height; i++) {
        final int row = i;
        executorService.submit(() -> {
            for (int j = 0; j < width; j++) {
                Color c = new Color(image.getRGB(j, row));
                int r = Math.min(255, Math.max(0, c.getRed() + brightness));
                int g = Math.min(255, Math.max(0, c.getGreen() + brightness));
                int b = Math.min(255, Math.max(0, c.getBlue() + brightness));
                image.setRGB(j, row, new Color(r, g, b).getRGB());
            }
        });
    }

    executorService.shutdown();
    try {
        executorService.awaitTermination(Long.MAX_VALUE, java.util.concurrent.TimeUnit.NANOSECONDS);
    } catch (InterruptedException e) {
        System.out.println("Executor interrupted");
    }

    long endTime = System.currentTimeMillis(); // ⏱️ Stop
    System.out.println("Czas wykonania procesu wielkowątkowego: " + (endTime - startTime) + " ms");
}
}
