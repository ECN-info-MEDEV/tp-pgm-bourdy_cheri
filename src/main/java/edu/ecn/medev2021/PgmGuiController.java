package edu.ecn.medev2021;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class PgmGuiController {
    private final PgmGui gui;
    private final JTextField field;
    private final JLabel state;
    private final PgmReader reader;
    private final PgmGenerator generator;

    private int[][] loadedImg;
    private int[] histogram;

    public int[] getHistogram() {
        return histogram;
    }

    public int[][] getLoadedImg() {
        return loadedImg;
    }

    public PgmGuiController(PgmGui gui, JTextField field, JLabel state) {
        this.gui = gui;
        this.field = field;
        this.state = state;
        reader = new PgmReader();
        generator = new PgmGenerator();
    }

    public void clickLoad() {
        String name = field.getText();
        if (!name.isEmpty()) {
            try {
                histogram = null;
                File file = new File(name);
                System.out.println("File " + file.getAbsolutePath());
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

    public void generateHistogram() {
        if (loadedImg != null) {
            int[] histogram = reader.getHistogram(loadedImg);
            System.out.println(Arrays.toString(histogram));
            this.histogram = histogram;
            state.setText("Histogramme généré");
        } else {
            state.setText("Chargez une image avant cela !");
        }
    }

    public void generateRandom() {
        histogram = null;
        loadedImg = generator.generatePgmImage(512, 512);
        state.setText("Image générée !");
    }

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

    //TODO DOC
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

    public void resizeImage(int newWith, int newHeight) {
        int[][] newImage = new int[newHeight][newWith];
        //todo resize
        //loadedImg = newImage;
        int height = loadedImg.length;
        int width = loadedImg[0].length;
        for (int i = 0; i < newHeight; i++) {
            for (int j = 0; j < newWith; j++) {
                newImage[i][j] = loadedImg[i * height / newHeight][j * width / newWith];
            }
        }
        loadedImg = newImage;
        state.setText("Image redimensionnée !");
    }

    public void saveImage(String name) {
        try {
            generator.writePgmImage(new File(name), loadedImg);
            state.setText("Image sauvegardée !");
        } catch (IOException e) {
            state.setText("Erreur : " + e);
            e.printStackTrace();
        }
    }
}
