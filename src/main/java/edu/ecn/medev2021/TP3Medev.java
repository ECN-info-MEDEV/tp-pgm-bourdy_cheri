package edu.ecn.medev2021;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Logger;

/**
 * A simple test for the pgm code
 */
public class TP3Medev {
    public static final Logger log = Logger.getLogger("PgmGenerator");
    
    public static void main(String[] args) {
        PgmReader reader = new PgmReader();
        try {
            File file = new File("baboon.pgm");
            TP3Medev.log.info("File " + file.getAbsolutePath());
            int[][] img = reader.readPgmFile(file);
            int[] histogram = reader.getHistogram(img);
            TP3Medev.log.info(() -> Arrays.toString(histogram));

            PgmGenerator generator = new PgmGenerator();
            int[][] img2 = generator.generatePgmImage(256, 256);
            TP3Medev.log.info(() -> Arrays.toString(reader.getHistogram(img2)));
            generator.writePgmImage(new File("test1.pgm"), img2);

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
