package edu.ecn.medev2021;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class PgmGenerator {
    private final Random random = new Random();
    /**
     * Generates a random pgm image (a matrice with values between 0 and 255)
     *
     * @param width  The width of the image
     * @param height The height of the image
     * @return The generated image
     */
    public int[][] generatePgmImage(int width, int height) {
        int[][] img = new int[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                img[i][j] = random.nextInt(256);
            }
        }
        return img;
    }

    /**
     * Saves the given image
     *
     * @param writeTo The location of the new image. Overwritten if it already exists
     * @param image The image to save
     * @throws IOException If an error occurs writing the file
     */
    public void writePgmImage(File writeTo, int[][] image) throws IOException {
        writeTo.createNewFile();
        try (FileWriter writer = new FileWriter(writeTo)) {
            writer.write("P2\n");
            writer.write("# \n");
            writer.write(image[0].length + "  " + image.length + "\n");
            writer.write("255\n");
            int lineSize = 0;
            for (int[] line : image) {
                for (int pixel : line) {
                    if (lineSize > 0) {
                        writer.write("  " + pixel);
                    } else {
                        writer.write("" + pixel);
                    }
                    lineSize++;
                    if (lineSize == 10) {
                        writer.write(" \n");
                        lineSize = 0;
                    }
                }
            }
        }
    }
}
