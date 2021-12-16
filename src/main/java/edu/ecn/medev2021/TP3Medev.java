package edu.ecn.medev2021;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/**
 * A simple test for the pgm code
 */
public class TP3Medev {
    public static void main(String[] args) {
        PgmReader reader = new PgmReader();
        try {
            File file = new File("baboon.pgm");
            System.out.println("File " + file.getAbsolutePath());
            int[][] img = reader.readPgmFile(file);
            int[] histogram = reader.getHistogram(img);
            System.out.println(Arrays.toString(histogram));

            PgmGenerator generator = new PgmGenerator();
            int[][] img2 = generator.generatePgmImage(256, 256);
            System.out.println(Arrays.toString(reader.getHistogram(img2)));
            generator.writePgmImage(new File("test1.pgm"), img2);

            /*file = new File("test1.pgm");
            System.out.println("File "+file.getAbsolutePath());
            img = reader.readPgmFile(file);
            histogram = reader.getHistogram(img);
            System.out.println(Arrays.toString(histogram));*/

            img = reader.difference(img2, img);
            displayImage(img);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void displayImage(int[][] img) {
        for (int[] line : img) {
            for (int pixel : line) {
                System.out.print(pixel + " ");
            }
            System.out.println();
        }
    }
}
