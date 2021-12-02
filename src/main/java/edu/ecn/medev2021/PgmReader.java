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
        String[] tokens = line.split("  ");
        int width = Integer.parseInt(tokens[0]);
        int height = Integer.parseInt(tokens[1]);
        sc.nextLine(); //Max color (255)

        int[][] img = new int[height][width];
        int counter = 0;
        while(sc.hasNextLine()) {
            line = sc.nextLine();
            tokens = line.split("  ");
            for(String token : tokens) {
                img[counter / width][counter % width] = Integer.parseInt(token.trim());
                counter++;
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
}
