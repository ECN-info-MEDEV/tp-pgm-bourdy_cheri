package edu.ecn.medev2021;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

public class TP3Medev
{
    public static void main(String[] args) {
        PgmReader reader = new PgmReader();
        try {
            File file = new File("baboon.pgm");
            System.out.println("File "+file.getAbsolutePath());
            int[][] img = reader.readPgmFile(file);
            int[] histogram = reader.getHistogram(img);
            System.out.println(Arrays.toString(histogram));

            PgmGenerator generator = new PgmGenerator();
            img = generator.generatePgmImage(256, 256);
            System.out.println(Arrays.toString(reader.getHistogram(img)));
            generator.writePgmImage(new File("test1.pgm"), img);

            file = new File("test1.pgm");
            System.out.println("File "+file.getAbsolutePath());
            img = reader.readPgmFile(file);
            histogram = reader.getHistogram(img);
            System.out.println(Arrays.toString(histogram));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

            /*for(int[] line : img) {
                for(int pixel : line) {
                    System.out.print(pixel+" ");
                }
                System.out.println();
            }*/
}
