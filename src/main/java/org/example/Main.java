package org.example;

import images.ImageProcessor;

public class Main {
    public static void main(String[] args) {
        ImageProcessor ip = new ImageProcessor();
        ip.loadImage("cat.jpg");
        ip.increaseBrightnessMultiThread(-50);
        ip.saveImage("brightened_Cat_multithreead_threadpull.jpg");
    }
}