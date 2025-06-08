package org.example;

import images.ImageProcessor;

public class Main {
    public static void main(String[] args) {
        ImageProcessor ip = new ImageProcessor();
        ip.loadImage("Patates.jpg");
        ip.increaseBrightnessThreadPool(-50);
        ip.saveImage("multithread2Patates.jpg");
    }
}