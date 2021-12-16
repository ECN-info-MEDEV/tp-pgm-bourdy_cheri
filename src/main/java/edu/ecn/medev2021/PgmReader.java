package edu.ecn.medev2021;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PgmReader
{
    /**
     * Reads the given pgm file <br>
     * It is assumed that 'file' is a valid pgm file (respecting this format)
     * @param file The pgm file to read
     * @return The pixels of the pgm file
     * @throws FileNotFoundException If the file is not found
     */
    public int[][] readPgmFile(File file) throws FileNotFoundException {
        Scanner sc = new Scanner(new FileInputStream(file));
        if(!sc.nextLine().equals("P2")) { //Type ?
            throw new IllegalArgumentException(file+" is not a valid pgm file !");
        }
        sc.nextLine(); //comment
        String line = sc.nextLine(); //Size
        String[] tokens = line.replace("  ", " ").split(" ");
        int width = Integer.parseInt(tokens[0]);
        int height = Integer.parseInt(tokens[1]);
        sc.nextLine(); //Max color (255)

        int[][] img = new int[height][width];
        int counter = 0;
        while(sc.hasNextLine()) {
            line = sc.nextLine();
            tokens = line.split(" ");
            for(String token : tokens) {
                if(!token.isEmpty()) {
                    img[counter / width][counter % width] = Integer.parseInt(token.trim());
                    counter++;
                }
            }
        }
        sc.close();

        return img;
    }

    /**
     * Computes the gray level histogram of img
     * @param img The image
     * @return The resulting histogram
     */
    public int[] getHistogram(int[][] img) {
        int[] histo = new int[256];
        for(int[] line : img) {
            for(int pixel : line) {
                histo[pixel]++;
            }
        }
        return histo;
    }

    /**
     * Computes the difference between the two images (img1 - img2) <br>
     * Invalid pixels are equals to 0
     *
     * @param img1 Image 1
     * @param img2 Image 2
     * @return The result of img1 - img2, with a size equals to the bigger of the two images
     */
    public int[][] difference(int[][] img1, int[][] img2) {
        int[][] newImg = new int[Math.max(img1.length, img2.length)][Math.max(img1[0].length, img2[0].length)];

        for (int i = 0; i < newImg.length; i++) {
            for (int j = 0; j < newImg[0].length; j++) {
                if(i < img1.length && j < img1[0].length) {
                    newImg[i][j] = img1[i][j];
                }
                if(i < img2.length && j < img2[0].length) {
                    newImg[i][j] = newImg[i][j] - img2[i][j];
                }
            }
        }
        return newImg;
    }

    public int[][] resizeImage(int[][] loadedImg, int newWith, int newHeight) {
        int[][] newImage = new int[newHeight][newWith];
        //todo resize
        //loadedImg = newImage;
        int height = loadedImg.length;
        int width = loadedImg[0].length;
        for (int i=0; i<newHeight; i++) {
            for (int j=0; j<newWith; j++) {
                newImage[i][j] = loadedImg[i*height/newHeight][j*width/newWith];
            }
        }
        loadedImg = newImage;
        //state.setText("Image redimensionnée !");
        return loadedImg ;
    }
}
