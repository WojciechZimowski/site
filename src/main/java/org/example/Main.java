package org.example;

import images.ImageProcessor;

public class Main {
    public static void main(String[] args) {
        ImageProcessor ip = new ImageProcessor();
        ip.loadImage("cat.jpg");
        ip.changeBrightness(70);
        ip.saveImage("brightened_Cat.jpg");
    }
}