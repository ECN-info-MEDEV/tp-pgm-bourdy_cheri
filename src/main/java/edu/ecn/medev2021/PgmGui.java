package edu.ecn.medev2021;

import javax.swing.*;
import java.awt.*;

public class PgmGui {
    private JTextField textField1;
    private JTextField textField2;
    private JButton loadButton;
    private JButton loadButton1;
    private JButton generateRandomSaveButton;
    private JButton image1Image2Button;
    private JButton seuillageButton;
    private JPanel rootPane;
    private JLabel state1;
    private JLabel state2;
    private JButton histogramButton;
    private JPanel imgDisplay1;
    private JPanel imgDisplay2;
    private JButton histogramButton2;
    private JButton resizeButton;
    private JButton saveButton;
    private JButton saveButton1;

    private final PgmGuiController controller1;
    private final PgmGuiController controller2;

    public PgmGui() {
        $$$setupUI$$$();

        //Left part
        controller1 = new PgmGuiController(this, textField2, state1);
        loadButton.addActionListener(e -> {
            controller1.clickLoad();
            imgDisplay1.repaint();
        });
        generateRandomSaveButton.addActionListener(e -> {
            controller1.generateRandom();
            imgDisplay1.repaint();
        });
        histogramButton.addActionListener(e -> {
            controller1.generateHistogram();
            imgDisplay1.repaint();
        });
        seuillageButton.addActionListener(e -> {
            controller1.thresholdImage();
            imgDisplay1.repaint();
        });
        saveButton1.addActionListener(e -> {
            if (controller1.getLoadedImg() != null) {
                String name = JOptionPane.showInputDialog(rootPane, "Enter the new file name", "File name ?", JOptionPane.QUESTION_MESSAGE);
                controller1.saveImage(name);
            }
        });

        //Right part
        controller2 = new PgmGuiController(this, textField1, state2);
        loadButton1.addActionListener(e -> {
            controller2.clickLoad();
            imgDisplay2.repaint();
        });
        histogramButton2.addActionListener(e -> {
            controller2.generateHistogram();
            imgDisplay2.repaint();
        });
        image1Image2Button.addActionListener(e -> {
            controller2.generateDiffWith(controller1.getLoadedImg());
            imgDisplay2.repaint();
        });
        resizeButton.addActionListener(e -> {
            if (controller2.getLoadedImg() != null) {
                String width = JOptionPane.showInputDialog(rootPane, "Enter the new image with", "" + controller2.getLoadedImg()[0].length, JOptionPane.QUESTION_MESSAGE);
                String height = JOptionPane.showInputDialog(rootPane, "Enter the new image height", "" + controller2.getLoadedImg().length, JOptionPane.QUESTION_MESSAGE);
                controller2.resizeImage(Integer.parseInt(width), Integer.parseInt(height));
                imgDisplay2.repaint();
            }
        });
        saveButton.addActionListener(e -> {
            if (controller2.getLoadedImg() != null) {
                String name = JOptionPane.showInputDialog(rootPane, "Enter the new file name", "File name ?", JOptionPane.QUESTION_MESSAGE);
                controller2.saveImage(name);
            }
        });
    }

    public JPanel getRootPane() {
        return rootPane;
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        createUIComponents();
        rootPane.setLayout(new java.awt.GridBagLayout());
        final javax.swing.JLabel label1 = new javax.swing.JLabel();
        java.awt.GridBagConstraints gbc;
        gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.anchor = java.awt.GridBagConstraints.WEST;
        rootPane.add(label1, gbc);
        final javax.swing.JLabel label2 = new javax.swing.JLabel();
        gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.anchor = java.awt.GridBagConstraints.WEST;
        rootPane.add(label2, gbc);
        loadButton = new javax.swing.JButton();
        gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.weightx = 1.0;
        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
        rootPane.add(loadButton, gbc);
        generateRandomSaveButton = new javax.swing.JButton();
        gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.weightx = 1.0;
        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
        rootPane.add(generateRandomSaveButton, gbc);
        seuillageButton = new javax.swing.JButton();
        gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.weightx = 1.0;
        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
        rootPane.add(seuillageButton, gbc);
        histogramButton = new javax.swing.JButton();
        gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
        rootPane.add(histogramButton, gbc);
        loadButton1 = new javax.swing.JButton();
        gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.weightx = 1.0;
        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
        rootPane.add(loadButton1, gbc);
        histogramButton2 = new javax.swing.JButton();
        gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
        rootPane.add(histogramButton2, gbc);
        image1Image2Button = new javax.swing.JButton();
        gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.weightx = 1.0;
        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
        rootPane.add(image1Image2Button, gbc);
        state1 = new javax.swing.JLabel();
        gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = java.awt.GridBagConstraints.WEST;
        rootPane.add(state1, gbc);
        textField2 = new javax.swing.JTextField();
        gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.anchor = java.awt.GridBagConstraints.WEST;
        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
        rootPane.add(textField2, gbc);
        state2 = new javax.swing.JLabel();
        gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = java.awt.GridBagConstraints.WEST;
        rootPane.add(state2, gbc);
        gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = java.awt.GridBagConstraints.BOTH;
        gbc.ipady = 500;
        rootPane.add(imgDisplay1, gbc);
        gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = java.awt.GridBagConstraints.BOTH;
        gbc.ipady = 500;
        rootPane.add(imgDisplay2, gbc);
        textField1 = new javax.swing.JTextField();
        gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.anchor = java.awt.GridBagConstraints.WEST;
        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
        rootPane.add(textField1, gbc);
        resizeButton = new javax.swing.JButton();
        gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 7;
        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
        rootPane.add(resizeButton, gbc);
        saveButton = new javax.swing.JButton();
        gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 8;
        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
        rootPane.add(saveButton, gbc);
        saveButton1 = new javax.swing.JButton();
        gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
        rootPane.add(saveButton1, gbc);
    }

    /**
     * @noinspection ALL
     */
    public javax.swing.JComponent $$$getRootComponent$$$() {
        return rootPane;
    }

    public JTextField getTextField1() {
        return textField1;
    }

    public JTextField getTextField2() {
        return textField2;
    }

    public JLabel getState1() {
        return state1;
    }

    public JLabel getState2() {
        return state2;
    }

    private void drawImage(Graphics g, int[][] img) {
        for (int i = 0; i < img.length; i++) {
            for (int j = 0; j < img[0].length; j++) {
                g.setColor(new Color(img[i][j], img[i][j], img[i][j]));

                int xPos = 5 + j;
                int yPos = 5 + i;

                g.fillRect(xPos, yPos, 1, 1);
            }
        }
    }

    private void drawHistogram(Graphics g, int[] histogram) {
        // Determine the maximum vertical value...
        int maxValue = 0;

        for (int value : histogram) {
            maxValue = Math.max(maxValue, value);
        }

        int xPos = 5;

        for (int value : histogram) {
            // Calculate the percentage that the given value uses compared to that of the
            // maximum value
            float percentage = maxValue == 0 ? 0.01f : (float) value / (float) maxValue;
            // Calculate the line height based on the available vertical space...
            int barHeight = Math.round(percentage * 200);
            g.setColor(new Color(255, 0, 0));

            int yPos = 200 + 10 - barHeight;

            g.fillRect(xPos, yPos, 3, barHeight);
            g.setColor(Color.DARK_GRAY);
            g.drawRect(xPos, yPos, 3, barHeight);

            xPos += 3;
        }
    }

    private void createUIComponents() {
        rootPane = new JPanel();
        rootPane.setLayout(new GridBagLayout());

        imgDisplay1 = new JPanel() {
            @Override
            public void paint(Graphics g) {
                super.paint(g);

                if (controller1.getHistogram() != null) {
                    drawHistogram(g, controller1.getHistogram());
                } else if (controller1.getLoadedImg() != null) {
                    drawImage(g, controller1.getLoadedImg());
                }
            }
        };
        imgDisplay2 = new JPanel() {
            @Override
            public void paint(Graphics g) {
                super.paint(g);

                if (controller2.getHistogram() != null) {
                    drawHistogram(g, controller2.getHistogram());
                } else if (controller2.getLoadedImg() != null) {
                    drawImage(g, controller2.getLoadedImg());
                }
            }
        };
    }
}
