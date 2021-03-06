package edu.ecn.medev2021;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class PgmGuiController {
    /**
     * The pgm gui
     */
    private final PgmGui gui;
    /**
     * The text field for the name of the image to load
     */
    private final JTextField field;
    /**
     * The label for the status of the image
     */
    private final JLabel state;
    /**
     * A PgmReader to load and edit the image
     */
    private final PgmReader reader;
    /**
     * A PgmGenerator to generate and save the image
     */
    private final PgmGenerator generator;

    private int[][] loadedImg;
    private int[] histogram;

    /**
     * Creates a new controller on the specified pgm gui
     * @param gui The pgm gui
     * @param field The text field for the name of the image to load
     * @param state The label for the status of the image
     */
    public PgmGuiController(PgmGui gui, JTextField field, JLabel state) {
        this.gui = gui;
        this.field = field;
        this.state = state;
        reader = new PgmReader();
        generator = new PgmGenerator();
    }

    /**
     * Loads the image specified in the gui text field
     */
    public void clickLoad() {
        String name = field.getText();
        if (!name.isEmpty()) {
            try {
                histogram = null;
                File file = new File(name);
                TP3Medev.log.info("File " + file.getAbsolutePath());
                loadedImg = reader.readPgmFile(file);
                state.setText("Image chargée !");
            } catch (IOException e) {
                state.setText("Une erreur est survenue !");
                e.printStackTrace();
            }
        } else {
            state.setText("Entrez un nom de fichier !");
        }
    }

    /**
     * Generates a histogram of the loaded image
     */
    public void generateHistogram() {
        if (loadedImg != null) {
            histogram = reader.getHistogram(loadedImg);
            TP3Medev.log.info(() -> Arrays.toString(histogram));
            state.setText("Histogramme généré");
        } else {
            state.setText("Chargez une image avant cela !");
        }
    }

    /**
     * Generates a random image with 512x512px
     */
    public void generateRandom() {
        histogram = null;
        loadedImg = generator.generatePgmImage(512, 512);
        state.setText("Image générée !");
    }

    /**
     * Computes the difference between the loaded image of the controller and loadedImg
     * @param loadedImg The image to compare
     */
    public void generateDiffWith(int[][] loadedImg) {
        if (loadedImg != null) {
            if (this.loadedImg != null) {
                this.loadedImg = reader.difference(loadedImg, this.loadedImg);
                for (int i = 0; i < this.loadedImg.length; i++) {
                    int[] line = this.loadedImg[i];
                    for (int j = 0; j < line.length; j++) {
                        int pixel = line[j];
                        this.loadedImg[i][j] = Math.min(Math.abs(pixel), 255);
                    }
                }
                state.setText("Différence générée");
            } else {
                state.setText("Chargez l'image 2");
            }
        } else {
            state.setText("Chargez l'image 1");
        }
    }

    /**
     * Thresholds the loaded image
     */
    public void thresholdImage() {
        if (loadedImg != null) {
            for (int i = 0; i < this.loadedImg.length; i++) {
                int[] line = this.loadedImg[i];
                for (int j = 0; j < line.length; j++) {
                    int pixel = line[j];
                    this.loadedImg[i][j] = pixel > 125 ? 255 : 0;
                }
            }
            state.setText("Seuillage effectué");
        } else {
            state.setText("Chargez une image avant cela");
        }
    }

    /**
     * Resizes the loaded image
     * @param newWith The new with
     * @param newHeight The new height
     */
    public void resizeImage(int newWith, int newHeight) {
        if(loadedImg != null) {
            loadedImg = reader.resizeImage(loadedImg, newHeight, newWith);
            state.setText("Image redimensionnée !");
        }
    }

    /**
     * Saves the loaded image if the specified file
     * @param name The name of the file
     */
    public void saveImage(String name) {
        try {
            generator.writePgmImage(new File(name), loadedImg);
            state.setText("Image sauvegardée !");
        } catch (IOException e) {
            state.setText("Erreur : " + e);
            e.printStackTrace();
        }
    }

    /**
     * @return The pgm gui
     */
    public PgmGui getGui() {
        return gui;
    }

    /**
     * @return The last image histogram generated by generateHistogram()
     */
    public int[] getHistogram() {
        return histogram;
    }

    /**
     * @return The currently loaded image
     */
    public int[][] getLoadedImg() {
        return loadedImg;
    }
}
